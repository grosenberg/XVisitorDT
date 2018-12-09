package net.certiv.xvisitordt.ui.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import net.certiv.dsl.core.model.IDslElement;
import net.certiv.dsl.ui.editor.OutlineLabelDecorator;
import net.certiv.xvisitordt.core.model.ModelData;
import net.certiv.xvisitordt.ui.XVImageManager;
import net.certiv.xvisitordt.ui.XVisitorUI;

public class XVisitorOutlineLabelDecorator extends OutlineLabelDecorator {

	public XVisitorOutlineLabelDecorator() {
		super(XVisitorUI.getDefault().getImageManager());
	}

	@Override
	public String decorateText(String text) {
		switch (getElementKind()) {
			case IDslElement.MODULE:
				if (hasData()) {
					ModelData data = (ModelData) getData();
					return data.key;
				}
				return text;

			case IDslElement.DECLARATION:
				return text;

			case IDslElement.STATEMENT:
			case IDslElement.FIELD:
				if (hasData()) {
					ModelData data = (ModelData) getData();
					switch (data.mType) {
						case Option:
							return data.key + " = " + data.value.getText();

						case AtAction:
							if (!data.key.isEmpty()) {
								return data.key + "::" + data.value.getText();
							}
							return data.value.getText();
						default:
							return data.key;
					}
				}
				return text;
			case IDslElement.BEG_BLOCK:
			case IDslElement.END_BLOCK:
				return text;
		}
		return text;
	}

	@Override
	public Image decorateImage(Image image) {
		XVImageManager mgr = (XVImageManager) imgMgr;
		ImageDescriptor desc = null;

		switch (getElementKind()) {
			case IDslElement.MODULE:
				desc = mgr.getDescriptor(mgr.IMG_OBJ_MODULE);
				break;
			case IDslElement.STATEMENT:
			case IDslElement.FIELD:
				desc = mgr.getDescriptor(mgr.IMG_OBJ_STATEMENT);
				if (hasData()) {
					ModelData data = (ModelData) getData();
					switch (data.mType) {
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
			case IDslElement.BEG_BLOCK:
			case IDslElement.END_BLOCK:
				desc = mgr.getDescriptor(mgr.IMG_OBJ_BLOCK);
				break;

			default:
				desc = ImageDescriptor.getMissingImageDescriptor();
		}
		return mgr.get(desc);
	}
}
