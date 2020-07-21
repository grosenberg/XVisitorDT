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
package net.certiv.xvisitor.dt.ui.editor.text;

import org.eclipse.jface.text.rules.IWordDetector;

public class RefWordDetector implements IWordDetector {

	@Override
	public boolean isWordStart(char c) {
		return c == '$';
	}

	@Override
	public boolean isWordPart(char c) {
		return Character.isLetterOrDigit((int) c) || c == '_' || c == '.';
	}
}
