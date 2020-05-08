package net.certiv.xvisitor.dt.ui.editor;

import net.certiv.dsl.ui.editor.DslDocumentSetupParticipant;
import net.certiv.xvisitor.dt.ui.XVisitorUI;

/**
 * Reference in the extension point is used to associate the contextTypeId with a file extension
 */
public class DocumentSetupParticipant extends DslDocumentSetupParticipant {

	public DocumentSetupParticipant() {
		super(XVisitorUI.getDefault().getTextTools());
	}
}
