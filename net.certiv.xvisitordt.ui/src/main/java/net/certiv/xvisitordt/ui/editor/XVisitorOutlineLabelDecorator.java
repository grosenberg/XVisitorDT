package net.certiv.xvisitordt.ui.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import net.certiv.dsl.core.model.IDslElement;
import net.certiv.dsl.ui.editor.OutlineLabelDecorator;
import net.certiv.xvisitordt.core.parser.ModelData;
import net.certiv.xvisitordt.ui.XVisitorImages;
import net.certiv.xvisitordt.ui.XVisitorUI;

public class XVisitorOutlineLabelDecorator extends OutlineLabelDecorator {

	public XVisitorOutlineLabelDecorator() {
		super();
	}

	private XVisitorImages getImageProvider() {
		return (XVisitorImages) XVisitorUI.getDefault().getImageProvider();
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
			case IDslElement.STATEMENT:
				if (hasData()) {
					ModelData data = (ModelData) getData();
					switch (data.type) {
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
		// create the base image
		ImageDescriptor desc = createMissingImageDescriptor(image);
		switch (getElementKind()) {
			case IDslElement.MODULE:
				desc = getImageProvider().DESC_OBJ_MODULE;
				break;
			case IDslElement.STATEMENT:
				desc = getImageProvider().DESC_OBJ_STATEMENT;
				if (hasData()) {
					ModelData data = (ModelData) getData();
					switch (data.type) {
						case Options:
							desc = getImageProvider().DESC_OBJ_OPTION;
							break;
						case Option:
							desc = getImageProvider().DESC_OBJ_ENUM;
							break;
						case PathRule:
							desc = getImageProvider().DESC_OBJ_LEXER;
							break;
						case GroupRule:
							desc = getImageProvider().DESC_OBJ_PARSER;
							break;
						case AtAction:
							desc = getImageProvider().DESC_OBJ_ACTION;
							break;
						default:
							desc = getImageProvider().DESC_OBJ_REQUIRED;
							break;
					}
				}
				break;
			case IDslElement.BEG_BLOCK:
			case IDslElement.END_BLOCK:
				desc = getImageProvider().DESC_OBJ_BLOCK;
				break;
		}
		return findImage(desc);
	}
}
