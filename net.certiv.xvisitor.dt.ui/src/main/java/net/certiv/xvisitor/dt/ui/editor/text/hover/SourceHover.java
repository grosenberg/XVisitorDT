package net.certiv.xvisitor.dt.ui.editor.text.hover;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.ui.IEditorPart;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.preferences.IPrefsManager;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.text.DslWordFinder;
import net.certiv.dsl.ui.editor.text.hover.DslSourceHover;
import net.certiv.xvisitor.dt.core.XVisitorCore;
import net.certiv.xvisitor.dt.ui.XVisitorUI;

public class SourceHover extends DslSourceHover {

	private DslWordFinder finder;

	public SourceHover(IEditorPart editor, IPrefsManager store) {
		super(editor, store);
		finder = new DslWordFinder();
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
	public IRegion findWord(IDocument doc, int offset) {
		return finder.findWord(doc, offset);
	}
}