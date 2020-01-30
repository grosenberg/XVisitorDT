package net.certiv.xvisitordt.ui;

import org.osgi.framework.Bundle;

import org.eclipse.jface.resource.ImageRegistry;

import net.certiv.dsl.ui.DslImageManager;

public class XVImageManager extends DslImageManager {

	private static final Bundle locBundle = XVisitorUI.getDefault().getBundle();
	private static final String locPrefix = locBundle.getSymbolicName() + '.';

	public final String IMG_OBJ_MODULE = create(locBundle, OBJ, locPrefix + "module_blue.png");
	public final String IMG_OBJ_STATEMENT = create(locBundle, OBJ, locPrefix + "statement.png");
	public final String IMG_OBJ_BLOCK = create(locBundle, OBJ, locPrefix + "block.png");

	public final String IMG_OBJ_BUFFER = create(locBundle, OBJ, locPrefix + "buffer.png");
	public final String IMG_OBJ_MESSAGE = create(locBundle, OBJ, locPrefix + "message_obj.png");
	public final String IMG_OBJ_REQUIRED = create(locBundle, OBJ, locPrefix + "required_obj.png");
	public final String IMG_OBJ_OPTION = create(locBundle, OBJ, locPrefix + "option_obj.png");

	public final String IMG_OBJ_IMPORT = create(locBundle, OBJ, locPrefix + "import_obj.png");
	public final String IMG_OBJ_PACKAGE = create(locBundle, OBJ, locPrefix + "packageBlue_obj.png");
	public final String IMG_OBJ_EXTEND = create(locBundle, OBJ, locPrefix + "extend_obj.png");
	public final String IMG_OBJ_SERVICE = create(locBundle, OBJ, locPrefix + "service_obj.png");
	public final String IMG_OBJ_OPTIONAL = create(locBundle, OBJ, locPrefix + "optional_obj.png");
	public final String IMG_OBJ_OPTION_ELEMENT = create(locBundle, OBJ, locPrefix + "optional_obj.png");
	public final String IMG_OBJ_REPEATED = create(locBundle, OBJ, locPrefix + "repeat_obj.png");
	public final String IMG_OBJ_EXTENSIONS = create(locBundle, OBJ, locPrefix + "extensions_obj.png");
	public final String IMG_OBJ_RPC = create(locBundle, OBJ, locPrefix + "rpc_obj.png");
	public final String IMG_OBJ_ENUM = create(locBundle, OBJ, locPrefix + "enum_obj.png");
	public final String IMG_OBJ_ENUM_ELEMENT = create(locBundle, OBJ, locPrefix + "enum_element_obj.png");

	public final String IMG_OVR_CUSTOM = create(locBundle, OVR, locPrefix + "custom_ovr.png");

	public final String IMG_OBJ_GRAMMAR = create(locBundle, OBJ, locPrefix + "grammar_obj.png");
	public final String IMG_OBJ_PARSER = create(locBundle, OBJ, locPrefix + "parser_obj.png");
	public final String IMG_OBJ_LEXER = create(locBundle, OBJ, locPrefix + "lexer_obj.png");
	public final String IMG_OBJ_ACTION = create(locBundle, OBJ, locPrefix + "action_obj.png");
	public final String IMG_OBJ_OPTIONS = create(locBundle, OBJ, locPrefix + "options_obj.png");
	public final String IMG_OVR_COMBINED = create(locBundle, OVR, locPrefix + "combined_ovr.png");
	public final String IMG_OVR_PARSER = create(locBundle, OVR, locPrefix + "parser_ovr.png");
	public final String IMG_OVR_LEXER = create(locBundle, OVR, locPrefix + "lexer_ovr.png");
	public final String IMG_OVR_TREE = create(locBundle, OVR, locPrefix + "tree_ovr.png");
	public final String IMG_OVR_FRAGMENT = create(locBundle, OVR, locPrefix + "fragment_ovr.png");
	public final String IMG_OVR_PROTECTED = create(locBundle, OVR, locPrefix + "protected_ovr.png");
	public final String IMG_OVR_PRIVATE = create(locBundle, OVR, locPrefix + "private_ovr.png");

	public final String IMG_OBJ_PARSER_FILTER = create(locBundle, OBJ, locPrefix + "filter_parser_obj.png");
	public final String IMG_OBJ_LEXER_FILTER = create(locBundle, OBJ, locPrefix + "filter_lexer_obj.png");
	public final String IMG_OBJ_OPTIONS_FILTER = create(locBundle, OBJ, locPrefix + "filter_options_obj.png");
	public final String IMG_OBJ_ACTIONS_FILTER = create(locBundle, OBJ, locPrefix + "filter_action_obj.png");

	public final String COLLAPSE = create(locBundle, OBJ, locPrefix + "collapseall.png");
	public final String EXPAND = create(locBundle, OBJ, locPrefix + "expandall.png");
	public final String LINK = create(locBundle, OBJ, locPrefix + "link_editor.png");
	public final String BUILD = create(locBundle, OBJ, locPrefix + "invocation.png");
	public final String LOAD = create(locBundle, OBJ, locPrefix + "loadGrammar.png");
	public final String CLEAR = create(locBundle, OBJ, locPrefix + "clearHistory.png");
	public final String NODES = create(locBundle, OBJ, locPrefix + "annotationOutlined.png");

	public XVImageManager() {
		super();
		IMG_OBJS_UNIT = create(locBundle, OBJ, locPrefix + "xvisitorFile.png");  //$NON-NLS-1$
	}

	@Override
	public ImageRegistry getRegistry() {
		return XVisitorUI.getDefault().getImageRegistry();
	}
}
