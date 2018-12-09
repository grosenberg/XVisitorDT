package net.certiv.xvisitordt.ui.templates;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPartitioningException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateContextType;

import net.certiv.dsl.ui.editor.text.completion.DslContentAssistInvocationContext;
import net.certiv.dsl.ui.editor.text.completion.tmpl.DslTemplateCompletionProcessor;
import net.certiv.dsl.ui.templates.CompletionManager;
import net.certiv.xvisitordt.ui.XVisitorUI;
import net.certiv.xvisitordt.ui.editor.Partitions;

public class XVisitorTemplateCompletionProcessor extends DslTemplateCompletionProcessor {

	private static char[] IGNORE = new char[] { '.' };

	public XVisitorTemplateCompletionProcessor(DslContentAssistInvocationContext context) {
		super(XVisitorUI.getDefault(), context);
	}

	@Override
	protected String getContextTypeId() {
		return XVisitorTemplateContextType.CONTEXT_TYPE_ID;
	}

	@Override
	protected char[] getIgnore() {
		return IGNORE;
	}

	private CompletionManager getCompletionMgr() {
		return XVisitorUI.getDefault().getCompletionMgr();
	}

	// NOTE: this handles empty prefixes
	// TODO: implement for globally enabling blank line template completions
	protected boolean isValid(ITextViewer viewer, Region region, String prefix) {
		if (prefix == null) return false;
		if (prefix.length() == 0) {
			TemplateContextType contextType = getContextType(viewer, region);
			String cId = contextType.getId();
			if (XVisitorTemplateContextType.CONTEXT_TYPE_ID.equals(cId)) {
				return true;
			}
			return false;
		}
		return true;
	}

	@Override
	protected TemplateContextType getContextType(ITextViewer viewer, IRegion region) {
		IDocumentExtension3 doc = (IDocumentExtension3) viewer.getDocument();
		ITypedRegion typedRegion = null;
		try {
			typedRegion = doc.getPartition(Partitions.PARTITIONING, region.getOffset(), true);
		} catch (BadLocationException | BadPartitioningException e) {
			e.printStackTrace();
		}
		if (typedRegion == null) return null;
		String type = typedRegion.getType();

		if (type.equals(IDocument.DEFAULT_CONTENT_TYPE)) {
			return getCompletionMgr().getTemplateContextTypeRegistry()
					.getContextType(XVisitorTemplateContextType.GRAMMAR_CONTEXT_TYPE_ID);
			// } else if (mType.equals(Partitions.OPTIONS)) {
			// return getTemplateAccess().getContextTypeRegistry().getContextType(
			// XVisitorTemplateContextType.OPTIONS_CONTEXT_TYPE_ID);
		} else if (type.equals(Partitions.COMMENT_JD)) {
			return getCompletionMgr().getTemplateContextTypeRegistry()
					.getContextType(XVisitorTemplateContextType.JAVADOC_CONTEXT_TYPE_ID);
		} else if (isValidPartition(type)) {
			return getCompletionMgr().getTemplateContextTypeRegistry()
					.getContextType(XVisitorTemplateContextType.ACTIONS_CONTEXT_TYPE_ID);
		}

		return super.getContextType(viewer, region);
	}

	private boolean isValidPartition(String type) {
		for (String partition : Partitions.getContentTypes()) {
			if (type.equals(partition)) return true;
		}
		return false;
	}

	@Override
	protected Template[] getTemplates(String contextTypeId) {
		if (contextTypeId.equals(XVisitorTemplateContextType.GRAMMAR_CONTEXT_TYPE_ID)
				|| contextTypeId.equals(XVisitorTemplateContextType.OPTIONS_CONTEXT_TYPE_ID)
				|| contextTypeId.equals(XVisitorTemplateContextType.ACTIONS_CONTEXT_TYPE_ID)
				|| contextTypeId.equals(XVisitorTemplateContextType.JAVADOC_CONTEXT_TYPE_ID)) {
			return getCompletionMgr().getTemplates();
		}
		return new Template[0];
	}
}
