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
package net.certiv.xvisitor.dt.ui;

import org.apache.logging.log4j.Level;

import org.osgi.framework.BundleContext;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.log.Log;
import net.certiv.dsl.ui.DslImageManager;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.console.StyledConsole;
import net.certiv.dsl.ui.editor.text.DslTextTools;
import net.certiv.dsl.ui.templates.CompletionManager;
import net.certiv.xvisitor.dt.core.XVisitorCore;
import net.certiv.xvisitor.dt.ui.console.XvConsoleFactory;
import net.certiv.xvisitor.dt.ui.editor.XVisitorEditor;
import net.certiv.xvisitor.dt.ui.editor.XVisitorTextTools;
import net.certiv.xvisitor.dt.ui.editor.XvCompletionManager;

public class XVisitorUI extends DslUI {

	private static XVisitorUI plugin;

	private XVImageManager imgMgr;
	private DslTextTools textTools;
	private XvCompletionManager compMgr;

	public XVisitorUI() {
		super();
		Log.defLevel(Level.DEBUG);
	}

	public static XVisitorUI getDefault() {
		return plugin;
	}

	@Override
	public DslUI getDslUI() {
		return getDefault();
	}

	@Override
	public DslCore getDslCore() {
		return XVisitorCore.getDefault();
	}

	@Override
	public void start(BundleContext context) throws Exception {
		plugin = this;
		super.start(context);
		assert (XVisitorCore.getDefault().getPluginId() != null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	@Override
	public String getPluginId() {
		return getDefault().getBundle().getSymbolicName();
	}

	@Override
	public String getEditorId() {
		return XVisitorEditor.EDITOR_ID;
	}

	@Override
	protected StyledConsole getConsole() {
		return XvConsoleFactory.getFactory().getConsole();
	}

	@Override
	public DslImageManager getImageManager() {
		if (imgMgr == null) {
			imgMgr = new XVImageManager();
		}
		return imgMgr;
	}

	@Override
	public DslTextTools getTextTools() {
		if (textTools == null) {
			textTools = new XVisitorTextTools(true);
		}
		return textTools;
	}

	@Override
	public CompletionManager getCompletionMgr() {
		if (compMgr == null) {
			compMgr = new XvCompletionManager(this, getEditorId());
		}
		return compMgr;
	}
}
