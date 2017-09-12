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

	public Image decorateImage(Image image) {
		// create the base image
		ImageDescriptor baseImage = createBaseImageDescriptor(image);
		int type = 0;
		switch (getElementKind()) {
			case IDslElement.MODULE:
				baseImage = getImageProvider().DESC_OBJ_MODULE;
				type = 1;
				break;
			case IDslElement.STATEMENT:
				baseImage = getImageProvider().DESC_OBJ_STATEMENT;
				type = 2;
				if (hasData()) {
					ModelData data = (ModelData) getData();
					switch (data.type) {
						case Options:
							baseImage = getImageProvider().DESC_OBJ_OPTION;
							type = 3;
							break;
						case Option:
							baseImage = getImageProvider().DESC_OBJ_ENUM;
							type = 4;
							break;
						case PathRule:
							baseImage = getImageProvider().DESC_OBJ_LEXER;
							type = 8;
							break;
						case GroupRule:
							baseImage = getImageProvider().DESC_OBJ_PARSER;
							type = 10;
							break;
						case AtAction:
							baseImage = getImageProvider().DESC_OBJ_ACTION;
							type = 13;
							break;
						default:
							baseImage = getImageProvider().DESC_OBJ_REQUIRED;
							type = 7;
							break;
					}
				}
				break;
			case IDslElement.BEG_BLOCK:
			case IDslElement.END_BLOCK:
				baseImage = getImageProvider().DESC_OBJ_BLOCK;
				type = 14;
				break;
		}
		Image img = fetchImage(type);
		if (img == null) {
			img = baseImage.createImage();
			storeImage(type, img);
		}
		return img;
	}
}
