package net.certiv.xvisitordt.ui.preferences.formatter;

import java.net.URL;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.formatter.IDslCodeFormatter;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.formatter.DslFormatterFactory;
import net.certiv.dsl.ui.formatter.IFormatterModifyDialog;
import net.certiv.dsl.ui.formatter.IFormatterModifyDialogOwner;
import net.certiv.xvisitordt.core.XVisitorCore;
import net.certiv.xvisitordt.core.formatter.XVisitorSourceFormatter;
import net.certiv.xvisitordt.ui.XVisitorUI;

public class FormatterFactory extends DslFormatterFactory {

	private IDslCodeFormatter formatter;

	public FormatterFactory() {
		super();
	}

	@Override
	public URL getPreviewContent() {
		return getClass().getResource("Preview.xv");
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

	@Override
	public IDslCodeFormatter createFormatter() {
		if (formatter == null) {
			formatter = new XVisitorSourceFormatter();
		}
		return formatter;
	}
}
