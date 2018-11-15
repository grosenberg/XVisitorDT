package net.certiv.xvisitordt.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.osgi.framework.Bundle;

import net.certiv.dsl.ui.DslImages;

public class XVisitorImages extends DslImages {

	private static final Bundle locBundle = XVisitorUI.getDefault().getBundle();
	private static final String locPrefix = locBundle.getSymbolicName() + '.';

	public final ImageDescriptor DESC_OBJ_MODULE = create(locBundle, OBJ, locPrefix + "module_blue.png");
	public final ImageDescriptor DESC_OBJ_STATEMENT = create(locBundle, OBJ, locPrefix + "statement.png");
	public final ImageDescriptor DESC_OBJ_BLOCK = create(locBundle, OBJ, locPrefix + "block.png");

	public final ImageDescriptor DESC_OBJ_BUFFER = create(locBundle, OBJ, locPrefix + "buffer.png");
	public final ImageDescriptor DESC_OBJ_MESSAGE = create(locBundle, OBJ, locPrefix + "message_obj.png");
	public final ImageDescriptor DESC_OBJ_REQUIRED = create(locBundle, OBJ, locPrefix + "required_obj.png");
	public final ImageDescriptor DESC_OBJ_OPTION = create(locBundle, OBJ, locPrefix + "option_obj.png");

	public final ImageDescriptor DESC_OBJ_IMPORT = create(locBundle, OBJ, locPrefix + "import_obj.png");
	public final ImageDescriptor DESC_OBJ_PACKAGE = create(locBundle, OBJ, locPrefix + "packageBlue_obj.png");
	public final ImageDescriptor DESC_OBJ_EXTEND = create(locBundle, OBJ, locPrefix + "extend_obj.png");
	public final ImageDescriptor DESC_OBJ_SERVICE = create(locBundle, OBJ, locPrefix + "service_obj.png");
	public final ImageDescriptor DESC_OBJ_OPTIONAL = create(locBundle, OBJ, locPrefix + "optional_obj.png");
	public final ImageDescriptor DESC_OBJ_OPTION_ELEMENT = create(locBundle, OBJ, locPrefix + "optional_obj.png");
	public final ImageDescriptor DESC_OBJ_REPEATED = create(locBundle, OBJ, locPrefix + "repeat_obj.png");
	public final ImageDescriptor DESC_OBJ_EXTENSIONS = create(locBundle, OBJ, locPrefix + "extensions_obj.png");
	public final ImageDescriptor DESC_OBJ_RPC = create(locBundle, OBJ, locPrefix + "rpc_obj.png");
	public final ImageDescriptor DESC_OBJ_ENUM = create(locBundle, OBJ, locPrefix + "enum_obj.png");
	public final ImageDescriptor DESC_OBJ_ENUM_ELEMENT = create(locBundle, OBJ, locPrefix + "enum_element_obj.png");

	public final ImageDescriptor DESC_OVR_CUSTOM = create(locBundle, OVR, locPrefix + "custom_ovr.png");

	public final ImageDescriptor DESC_OBJ_GRAMMAR = create(locBundle, OBJ, locPrefix + "grammar_obj.png");
	public final ImageDescriptor DESC_OBJ_PARSER = create(locBundle, OBJ, locPrefix + "parser_obj.png");
	public final ImageDescriptor DESC_OBJ_LEXER = create(locBundle, OBJ, locPrefix + "lexer_obj.png");
	public final ImageDescriptor DESC_OBJ_ACTION = create(locBundle, OBJ, locPrefix + "action_obj.png");
	public final ImageDescriptor DESC_OBJ_OPTIONS = create(locBundle, OBJ, locPrefix + "options_obj.png");
	public final ImageDescriptor DESC_OVR_COMBINED = create(locBundle, OVR, locPrefix + "combined_ovr.png");
	public final ImageDescriptor DESC_OVR_PARSER = create(locBundle, OVR, locPrefix + "parser_ovr.png");
	public final ImageDescriptor DESC_OVR_LEXER = create(locBundle, OVR, locPrefix + "lexer_ovr.png");
	public final ImageDescriptor DESC_OVR_TREE = create(locBundle, OVR, locPrefix + "tree_ovr.png");
	public final ImageDescriptor DESC_OVR_FRAGMENT = create(locBundle, OVR, locPrefix + "fragment_ovr.png");
	public final ImageDescriptor DESC_OVR_PROTECTED = create(locBundle, OVR, locPrefix + "protected_ovr.png");
	public final ImageDescriptor DESC_OVR_PRIVATE = create(locBundle, OVR, locPrefix + "private_ovr.png");

	public final ImageDescriptor DESC_OBJ_PARSER_FILTER = create(locBundle, OBJ, locPrefix + "filter_parser_obj.png");
	public final ImageDescriptor DESC_OBJ_LEXER_FILTER = create(locBundle, OBJ, locPrefix + "filter_lexer_obj.png");
	public final ImageDescriptor DESC_OBJ_OPTIONS_FILTER = create(locBundle, OBJ, locPrefix + "filter_options_obj.png");
	public final ImageDescriptor DESC_OBJ_ACTIONS_FILTER = create(locBundle, OBJ, locPrefix + "filter_action_obj.png");

	public final ImageDescriptor COLLAPSE = create(locBundle, OBJ, locPrefix + "collapseall.png");
	public final ImageDescriptor EXPAND = create(locBundle, OBJ, locPrefix + "expandall.png");
	public final ImageDescriptor LINK = create(locBundle, OBJ, locPrefix + "link_editor.png");
	public final ImageDescriptor BUILD = create(locBundle, OBJ, locPrefix + "invocation.png");
	public final ImageDescriptor LOAD = create(locBundle, OBJ, locPrefix + "loadGrammar.png");
	public final ImageDescriptor CLEAR = create(locBundle, OBJ, locPrefix + "clearHistory.png");
	public final ImageDescriptor NODES = create(locBundle, OBJ, locPrefix + "annotationOutlined.png");

	public XVisitorImages() {
		super();
		IMG_OBJS_TUNIT = locPrefix + "xvisitorFile.png"; //$NON-NLS-1$
		DESC_OBJS_TUNIT = create(locBundle, OBJ, IMG_OBJS_TUNIT);
	}

	@Override
	public ImageRegistry getImageRegistry() {
		return XVisitorUI.getDefault().getImageRegistry();
	}
}
