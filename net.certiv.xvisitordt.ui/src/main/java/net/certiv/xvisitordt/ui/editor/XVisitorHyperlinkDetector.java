package net.certiv.xvisitordt.ui.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;

import net.certiv.dsl.core.model.DslModel;
import net.certiv.dsl.core.model.IDslElement;
import net.certiv.dsl.core.model.IStatement;
import net.certiv.dsl.ui.editor.hyperlink.DslHyperlinkDetector;
import net.certiv.dsl.ui.editor.text.DslWordFinder;
import net.certiv.xvisitordt.core.XVisitorCore;

public class XVisitorHyperlinkDetector extends DslHyperlinkDetector {

	private DslWordFinder finder;

	public XVisitorHyperlinkDetector() {
		super();
		finder = new DslWordFinder();
	}

	@Override
	public IRegion findWord(IDocument doc, int offset) {
		return finder.findWord(doc, offset);
	}

	@Override
	public String getWord(IDocument doc, IRegion word) {
		return finder.getWord(doc, word);
	}

	/** Filter/modify the elements for display on the popup */
	@Override
	public IStatement[] selectOpenableElements(IStatement[] elements) {
		List<IStatement> stmts = new ArrayList<>();
		for (IStatement elem : elements) {
			if (elem.getKind() != IDslElement.MODULE) {
				stmts.add(elem);
			}
		}
		return stmts.toArray(new IStatement[stmts.size()]);
	}

	@Override
	public DslModel getDslModel() {
		return XVisitorCore.getDefault().getDslModel();
	}
}
