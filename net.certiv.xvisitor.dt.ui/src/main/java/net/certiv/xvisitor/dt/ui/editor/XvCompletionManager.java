/*******************************************************************************
 * Copyright (c) 2012, 2020 Certiv Analytics.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package net.certiv.xvisitor.dt.ui.editor;

import org.eclipse.jface.text.IAutoEditStrategy;

import net.certiv.dsl.core.model.IStatement;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.text.DefaultAutoEditSemicolonStrategy;
import net.certiv.dsl.ui.templates.CompletionManager;

public class XvCompletionManager extends CompletionManager {

	public static final String GRAMMAR = "grammar";
	public static final String OPTIONS = "options";
	public static final String RULE = "rule";

	public XvCompletionManager(DslUI ui, String editorId) {
		super(ui, editorId, GRAMMAR, OPTIONS, RULE);
	}

	@Override
	public String getContentAssistScope(IStatement stmt) {
		switch (stmt.getModelType()) {
			case STATEMENT:
				switch (stmt.getElementName()) {
					case OPTIONS:
						return OPTIONS;
					case RULE:
						return RULE;
					default:
						return GRAMMAR;
				}

			default:
				return null;
		}
	}

	@Override
	public IAutoEditStrategy getSmartTriggerStrategy(String contentType) {
		String partitioning = ui.getTextTools().getDocumentPartitioning();
		// TODO: customize the strategy
		return new DefaultAutoEditSemicolonStrategy(partitioning, mgr);
	}
}
