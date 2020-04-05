package net.certiv.xvisitordt.ui.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import net.certiv.dsl.ui.editor.outline.OutlineLabelProvider;
import net.certiv.xvisitordt.core.model.Specialization;
import net.certiv.xvisitordt.ui.XVImageManager;
import net.certiv.xvisitordt.ui.XVisitorUI;

public class XVisitorStatementLabelProvider extends OutlineLabelProvider {

	public XVisitorStatementLabelProvider() {
		super(XVisitorUI.getDefault().getImageManager());
	}

	@Override
	public String decorateText(String text) {
		switch (getStatementType()) {
			case MODULE:
				if (hasData()) {
					Specialization data = (Specialization) getData();
					return data.name;
				}
				return text;

			case DECLARATION:
				return text;

			case STATEMENT:
			case FIELD:
				if (hasData()) {
					Specialization data = (Specialization) getData();
					switch (data.specializedType) {
						case Option:
							return data.name + " = " + data.value.getText();

						case AtAction:
							if (!data.name.isEmpty()) {
								return data.name + "::" + data.value.getText();
							}
							return data.value.getText();
						default:
							return data.name;
					}
				}
				return text;

			case BEG_BLOCK:
				return text;

			case END_BLOCK:
			default:
				break;
		}
		return text;
	}

	@Override
	public Image decorateImage(Image image) {
		XVImageManager mgr = (XVImageManager) imgMgr;
		ImageDescriptor desc = null;

		switch (getStatementType()) {
			case MODULE:
				desc = mgr.getDescriptor(mgr.IMG_OBJ_MODULE);
				break;
			case STATEMENT:
			case FIELD:
				desc = mgr.getDescriptor(mgr.IMG_OBJ_STATEMENT);
				if (hasData()) {
					Specialization data = (Specialization) getData();
					switch (data.specializedType) {
						case Options:
							desc = mgr.getDescriptor(mgr.IMG_OBJ_OPTION);
							break;
						case Option:
							desc = mgr.getDescriptor(mgr.IMG_OBJ_ENUM);
							break;
						case PathRule:
							desc = mgr.getDescriptor(mgr.IMG_OBJ_LEXER);
							break;
						case GroupRule:
							desc = mgr.getDescriptor(mgr.IMG_OBJ_PARSER);
							break;
						case AtAction:
							desc = mgr.getDescriptor(mgr.IMG_OBJ_ACTION);
							break;
						default:
							desc = mgr.getDescriptor(mgr.IMG_OBJ_REQUIRED);
							break;
					}
				}
				break;
			case BEG_BLOCK:
				desc = mgr.getDescriptor(mgr.IMG_OBJ_BLOCK);
				break;

			case END_BLOCK:
				break;

			default:
				desc = ImageDescriptor.getMissingImageDescriptor();
		}
		return mgr.get(desc);
	}
}
