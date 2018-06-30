package net.certiv.xvisitordt.ui.preferences.page;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.texteditor.ITextEditor;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.IColorManager;
import net.certiv.dsl.core.preferences.IDslPrefsManager;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.text.DslSourceViewerConfiguration;
import net.certiv.dsl.ui.formatter.DslFormatterPreferencePage;
import net.certiv.xvisitordt.core.XVisitorCore;
import net.certiv.xvisitordt.ui.XVisitorUI;
import net.certiv.xvisitordt.ui.editor.Partitions;
import net.certiv.xvisitordt.ui.editor.XVisitorSimpleSourceViewerConfiguration;

/** Preference page for formatting */
public class PrefPageFormatter extends DslFormatterPreferencePage {

	@Override
	protected DslSourceViewerConfiguration createSimpleSourceViewerConfiguration(IColorManager colorManager,
			IPreferenceStore preferenceStore, ITextEditor editor, boolean configureFormatter) {

		return new XVisitorSimpleSourceViewerConfiguration(colorManager, (IDslPrefsManager) preferenceStore, editor,
				Partitions.XVISITOR_PARTITIONING, configureFormatter);
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
