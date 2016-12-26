package net.certiv.xvisitordt.ui.editor.completion;

import org.eclipse.jface.resource.ImageDescriptor;

import net.certiv.dsl.core.completion.CompletionProposal;
import net.certiv.dsl.ui.text.completion.CompletionLabelProvider;
import net.certiv.xvisitordt.ui.XVisitorUI;

public class XVisitorCompletionLabelProvider extends CompletionLabelProvider {

	public XVisitorCompletionLabelProvider() {
		super(XVisitorUI.getDefault());
	}

	@Override
	public ImageDescriptor createImageDescriptor(CompletionProposal proposal) {
		return null;
	}

	@Override
	public String createLabel(CompletionProposal proposal) {
		return null;
	}
}
