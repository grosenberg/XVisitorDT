package net.certiv.xvisitordt.ui.editor.completion;

import org.eclipse.jface.resource.ImageDescriptor;

import net.certiv.dsl.core.completion.CompletionProposal;
import net.certiv.dsl.ui.editor.text.completion.CompletionLabelProvider;
import net.certiv.xvisitordt.ui.XVisitorUI;

public class XVisitorCompletionLabelProvider extends CompletionLabelProvider {

	public XVisitorCompletionLabelProvider() {
		super(XVisitorUI.getDefault());
	}

	@Override
	public ImageDescriptor createImageDescriptor(CompletionProposal proposal) {
		return imgMgr.getDescriptor(imgMgr.IMG_OBJS_KEYWORD);

	}

	@Override
	public String createLabel(CompletionProposal proposal) {
		return createSimpleLabel(proposal);
	}
}
