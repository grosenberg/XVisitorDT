package net.certiv.xvisitordt.ui.preferences.formatter;

import java.net.URL;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.formatter.DslFormatterFactory;
import net.certiv.dsl.ui.formatter.IFormatterModifyDialog;
import net.certiv.dsl.ui.formatter.IFormatterModifyDialogOwner;
import net.certiv.xvisitordt.core.XVisitorCore;
import net.certiv.xvisitordt.ui.XVisitorUI;

public class FormatterFactory extends DslFormatterFactory {

	public FormatterFactory() {
		super();
	}

	/**
	 * List of keys that will be buffered while editing Preferences - allows undo
	 */
	@Override
	public String[] getFormatterKeys() {
		String[] keys = new String[XVisitorCore.getDefault().getPrefsManager().getFormatterKeys().size()];
		XVisitorCore.getDefault().getPrefsManager().getFormatterKeys().toArray(keys);
		return keys;
	}

	@Override
	public URL getPreviewContent() {
		return getClass().getResource("FormatPreview.g4");
	}

	@Override
	public IFormatterModifyDialog createDialog(IFormatterModifyDialogOwner dialogOwner) {
		return new ModifyDialog(dialogOwner, this);
	}

	@Override
	public DslUI getDslUI() {
		return XVisitorUI.getDefault();
	}

	@Override
	public DslCore getDslCore() {
		return XVisitorCore.getDefault();
	}
}
