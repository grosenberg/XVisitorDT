package net.certiv.xvisitor.dt.ui.editor.strategies;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DefaultIndentLineAutoEditStrategy;
import org.eclipse.jface.text.DocumentCommand;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITypedRegion;

import net.certiv.dsl.core.preferences.PrefsManager;
import net.certiv.dsl.ui.editor.text.DslTextTools;
import net.certiv.dsl.ui.editor.text.LineRegion;
import net.certiv.dsl.ui.editor.text.SmartEdit;
import net.certiv.xvisitor.dt.core.XVisitorCore;
import net.certiv.xvisitor.dt.ui.XVisitorUI;
import net.certiv.xvisitor.dt.ui.editor.Partitions;

public class XVisitorAutoEditActionStrategy extends DefaultIndentLineAutoEditStrategy {

	private static final String[] ALIGNTERMS = new String[] { "{" };
	private static final String[] CLOSETERMS = new String[] { ")", "}" };
	private String partitioning;

	public XVisitorAutoEditActionStrategy(String partition) {
		super();
		this.partitioning = partition;
	}

	public PrefsManager getPrefsMgr() {
		return XVisitorCore.getDefault().getPrefsManager();
	}

	public DslTextTools getTools() {
		return XVisitorUI.getDefault().getTextTools();
	}

	@Override
	public void customizeDocumentCommand(IDocument doc, DocumentCommand cmd) {

		if (getPrefsMgr().isSmartMode()) {
			if (SmartEdit.isNewLineCommand(doc, cmd)) {
				insertAfterNewLine(doc, cmd);

			} else if (SmartEdit.isSingleCharactedCommand(cmd)) {
				if (cmd.offset != -1) smartInsertCharacter(doc, cmd);
			}
		} else {
			super.customizeDocumentCommand(doc, cmd);
		}
	}

	private void insertAfterNewLine(IDocument doc, DocumentCommand cmd) {
		try {
			StringBuilder buf = new StringBuilder(cmd.text);
			LineRegion lr = new LineRegion(doc, cmd);
			String adjIndent = adjustIndent(doc, cmd, lr, getPrefsMgr().getTabWidth());
			buf.append(adjIndent);

			IRegion rightWord = SmartEdit.locateTextRight(doc, lr);
			if (rightWord != null) { // trim to word
				cmd.length = rightWord.getOffset() - lr.pos;
			} else if (lr.lineEnd > lr.pos) { // trim trailing WS
				cmd.length = lr.lineEnd - lr.pos;
			}
			cmd.text = buf.toString();
		} catch (BadLocationException e) {
			e.printStackTrace();
			super.customizeDocumentCommand(doc, cmd);
		}
	}

	/**
	 * Evaluate current line to determine an alignment column
	 */
	private String adjustIndent(IDocument doc, DocumentCommand cmd, LineRegion lr, int tabSize)
			throws BadLocationException {

		int alignCol = findAlignmentColumn(doc, lr, tabSize, 100, partitioning, Partitions.PARTITIONING);

		// if at line beginning, no appended indent
		if (alignCol <= 0) return "";

		// IRegion leftWord = SmartEdit.locateTextLeft(doc, lr);
		IRegion rightWord = SmartEdit.locateTextRight(doc, lr);
		ITypedRegion part = SmartEdit.getPartitionAtOffset(doc, lr.pos, Partitions.PARTITIONING);
		int oBrace = SmartEdit.findMatchingCharacterInPartition(doc, part.getOffset(), '{', part.getType(), 40);
		int cBrace = part.getOffset() + part.getLength() - 1;

		if (rightWord != null) {
			// if before first open brace, prefix indent one from alignment col
			if (rightWord.getOffset() == oBrace) {
				return getPrefsMgr().getIndentByVirtualSize(alignCol + getPrefsMgr().getTabWidth());
			}

			// if before last close brace, align with open brace or reduce indent
			if (rightWord.getOffset() == cBrace) {
				int lnStart = SmartEdit.getLineOffset(doc, oBrace);
				int oBraceCol = SmartEdit.calculateVisualLength(doc, tabSize, lnStart, oBrace);
				alignCol = oBraceCol <= alignCol ? oBraceCol : alignCol - getPrefsMgr().getTabWidth();
				alignCol = alignCol > 0 ? alignCol : 0;
				return getPrefsMgr().getIndentByVirtualSize(alignCol);
			}
		}

		// if on open brace line, align with first word, else indent from open brace
		if (SmartEdit.getLineOffset(doc, lr.pos) == SmartEdit.getLineOffset(doc, oBrace)) {
			int lnStart = SmartEdit.getLineOffset(doc, oBrace);
			int wordStart = SmartEdit.findEndOfWhiteSpace(doc, oBrace + 1, lr.pos);
			if (wordStart < lr.pos) {
				alignCol = SmartEdit.calculateVisualLength(doc, tabSize, lnStart, wordStart);
			} else {
				int oBraceCol = SmartEdit.calculateVisualLength(doc, tabSize, lnStart, oBrace);
				alignCol = oBraceCol + getPrefsMgr().getTabWidth();
			}
			return getPrefsMgr().getIndentByVirtualSize(alignCol);
		}

		// otherwise, indent to alignment col
		return getPrefsMgr().getIndentByVirtualSize(alignCol);
	}

	private int findAlignmentColumn(IDocument doc, LineRegion lr, int tabSize, int limit, String contentType,
			String partitioning) throws BadLocationException {

		int partOffset = SmartEdit.getPartitionStartOffset(doc, lr.pos, partitioning);
		int pos = lr.pos < lr.indentCol ? lr.indentCol : lr.pos;

		if (pos == lr.lineBeg || pos == lr.indentCol || pos == partOffset) {
			return SmartEdit.calculateVisualLength(doc, tabSize, lr.lineBeg, pos);
		}

		int offset = pos - 1;
		while (offset > 0 && offset > lr.indentCol && offset > partOffset && offset > pos - limit) {
			char c = doc.getChar(offset);
			if (SmartEdit.matchTerms(ALIGNTERMS, doc, offset)) {
				break;
			} else if (SmartEdit.matchTerms(CLOSETERMS, doc, offset)) {
				offset = SmartEdit.findMatchingCharacter(doc, offset - 1, c, partitioning,
						Partitions.STRING_AND_COMMENT_TYPES, limit);
			}
			offset--;
		}
		return SmartEdit.calculateVisualLength(doc, tabSize, lr.lineBeg, offset);
	}

	private void smartInsertCharacter(IDocument doc, DocumentCommand cmd) {
		char newChar = cmd.text.charAt(0);
		try {
			switch (newChar) {
				case '(':
				case '{':
				case '[':
					cmd = SmartEdit.autoClose(getTools(), doc, cmd, getPrefsMgr().closeBrackets(),
							getPrefsMgr().closeStrings());
					break;
				case '}':
				case ')':
				case ']':
					// TODO: this looks wrong
					cmd = SmartEdit.autoClose(getTools(), doc, cmd, getPrefsMgr().closeBrackets(),
							getPrefsMgr().closeStrings());
					break;
				case '\t':
					cmd = smartInsertTab(doc, cmd, getPrefsMgr().getTabWidth());
					break;
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	private DocumentCommand smartInsertTab(IDocument doc, DocumentCommand cmd, int tabSize)
			throws BadLocationException {

		LineRegion lr = new LineRegion(doc, cmd);
		IRegion rightText = SmartEdit.locateTextRight(doc, lr);

		if (lr.pos <= lr.indentCol) {
			if (rightText == null) {
				int prevLine = SmartEdit.getLastNonEmptyLine(doc, lr.lineNum, null);
				LineRegion lrp = new LineRegion(doc, prevLine);
				int alignCol = findAlignmentColumn(doc, lrp, tabSize, 100, partitioning, Partitions.PARTITIONING);
				cmd.offset = lr.lineBeg;
				cmd.length = lr.lineEnd - lr.lineBeg;
				cmd.text = getPrefsMgr().getIndentByVirtualSize(alignCol);
			} else if (rightText.getOffset() > lr.pos) {
				cmd.length = 0;
				cmd.text = "";
				cmd.caretOffset = rightText.getOffset();
				cmd.shiftsCaret = false;
			}
		}
		return cmd;
	}
}
