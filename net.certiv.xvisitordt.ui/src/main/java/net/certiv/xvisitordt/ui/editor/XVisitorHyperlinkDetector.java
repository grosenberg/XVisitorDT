package net.certiv.xvisitordt.ui.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.texteditor.ITextEditor;

import net.certiv.dsl.core.model.IDslElement;
import net.certiv.dsl.ui.editor.hyperlink.DslElementHyperlinkDetector;

public class XVisitorHyperlinkDetector extends DslElementHyperlinkDetector {

	public XVisitorHyperlinkDetector(ITextEditor editor) {
		super(editor);
	}

	/** Filter/modify the elements for display on the popup */
	@Override
	public IDslElement[] selectOpenableElements(IDslElement[] elements) {
		List<IDslElement> s = new ArrayList<>();
		for (IDslElement e : elements) {
			if (e.getKind() != IDslElement.MODULE) {
				s.add(e);
			}
		}
		return s.toArray(new IDslElement[0]);
	}
}
