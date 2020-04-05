package net.certiv.xvisitordt.ui.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.ui.IActionBars;

import net.certiv.dsl.core.model.ISourceUnit;
import net.certiv.dsl.core.model.IStatement;
import net.certiv.dsl.core.model.ModelType;
import net.certiv.dsl.ui.editor.outline.OutlinePage;
import net.certiv.dsl.ui.viewsupport.DslFilterAction;
import net.certiv.dsl.ui.viewsupport.DslFilterActionGroup;
import net.certiv.xvisitordt.ui.XVImageManager;
import net.certiv.xvisitordt.ui.XVisitorUI;
import net.certiv.xvisitordt.ui.editor.filter.AtFilter;
import net.certiv.xvisitordt.ui.editor.filter.OptionsFilter;
import net.certiv.xvisitordt.ui.editor.filter.RuleFilter;
import net.certiv.xvisitordt.ui.editor.filter.TokenFilter;

public class XVisitorOutlinePage extends OutlinePage {

	protected class GrammarDataProvider extends OutlineDataProvider {

		@Override
		protected IStatement[] filterChildren(ISourceUnit node) {
			List<IStatement> filtered = new ArrayList<>();
			IStatement[] children = super.filterChildren(node);
			for (int idx = 0; idx < children.length; idx++) {
				IStatement child = children[idx];
				if (endBlock(child)) continue;
				if (firstFieldOfStatement(idx, child)) continue;

				filtered.add(child);
			}
			return filtered.toArray(new IStatement[filtered.size()]);
		}

		private boolean endBlock(IStatement child) {
			return child.getModelType() == ModelType.END_BLOCK;
		}

		private boolean firstFieldOfStatement(int idx, IStatement child) {
			if (idx == 0 && child.getModelType() == ModelType.FIELD) {
				if (child.getParent() != null && (child.getParent().getModelType() == ModelType.STATEMENT
						|| child.getParent().getModelType() == ModelType.DECLARATION)) {
					return true;
				}
			}
			return false;
		}
	}

	public XVisitorOutlinePage(IPreferenceStore store) {
		super(XVisitorUI.getDefault(), store);
	}

	@Override
	protected ILabelDecorator getLabelDecorator() {
		return new XVisitorStatementLabelProvider();
	}

	@Override
	protected IContentProvider getOutlineDataProvider() {
		return new GrammarDataProvider();
	}

	@Override
	public void registerSpecialToolbarActions(IActionBars actionBars, IToolBarManager barMgr, IMenuManager menuMgr) {
		DslFilterActionGroup group = new DslFilterActionGroup(viewer, store);
		List<DslFilterAction> actions = buildActions(group);
		group.setActions(actions.toArray(new DslFilterAction[actions.size()]));
		group.contributeToToolBar(barMgr);
	}

	// order defines the order in toolbar
	private List<DslFilterAction> buildActions(DslFilterActionGroup group) {
		XVImageManager imgMgr = (XVImageManager) XVisitorUI.getDefault().getImageManager();
		List<DslFilterAction> actions = new ArrayList<>();
		String title, helpContext;

		// fill-in actions rule
		title = ActionMessages.MemberFilterActionGroup_hide_rules_label;
		helpContext = "";
		DslFilterAction hideRules = new DslFilterAction(group, title, new RuleFilter(), helpContext, true);
		hideRules.setDescription(ActionMessages.MemberFilterActionGroup_hide_rules_description);
		hideRules.setToolTipText(ActionMessages.MemberFilterActionGroup_hide_rules_tooltip);
		ImageDescriptor ruleDesc = imgMgr.getDescriptor(imgMgr.IMG_OBJ_PARSER_FILTER);
		hideRules.setHoverImageDescriptor(ruleDesc);
		hideRules.setImageDescriptor(ruleDesc);
		actions.add(hideRules);

		// fill-in actions lexer rules
		title = ActionMessages.MemberFilterActionGroup_hide_tokens_label;
		helpContext = "";
		DslFilterAction hideTokens = new DslFilterAction(group, title, new TokenFilter(), helpContext, true);
		hideTokens.setDescription(ActionMessages.MemberFilterActionGroup_hide_tokens_description);
		hideTokens.setToolTipText(ActionMessages.MemberFilterActionGroup_hide_tokens_tooltip);
		ImageDescriptor tokenDesc = imgMgr.getDescriptor(imgMgr.IMG_OBJ_LEXER_FILTER);
		hideTokens.setHoverImageDescriptor(tokenDesc);
		hideTokens.setImageDescriptor(tokenDesc);
		actions.add(hideTokens);

		// fill-in actions options
		title = ActionMessages.MemberFilterActionGroup_hide_options_label;
		helpContext = "";
		DslFilterAction hideOptions = new DslFilterAction(group, title, new OptionsFilter(), helpContext, true);
		hideOptions.setDescription(ActionMessages.MemberFilterActionGroup_hide_options_description);
		hideOptions.setToolTipText(ActionMessages.MemberFilterActionGroup_hide_options_tooltip);
		ImageDescriptor optDesc = imgMgr.getDescriptor(imgMgr.IMG_OBJ_OPTIONS_FILTER);
		hideOptions.setHoverImageDescriptor(optDesc);
		hideOptions.setImageDescriptor(optDesc);
		actions.add(hideOptions);

		// fill-in actions at
		title = ActionMessages.MemberFilterActionGroup_hide_at_label;
		helpContext = "";
		DslFilterAction atElements = new DslFilterAction(group, title, new AtFilter(), helpContext, true);
		atElements.setDescription(ActionMessages.MemberFilterActionGroup_hide_at_description);
		atElements.setToolTipText(ActionMessages.MemberFilterActionGroup_hide_at_tooltip);
		ImageDescriptor atDesc = imgMgr.getDescriptor(imgMgr.IMG_OBJ_ACTIONS_FILTER);
		atElements.setHoverImageDescriptor(atDesc);
		atElements.setImageDescriptor(atDesc);
		actions.add(atElements);

		return actions;
	}
}
