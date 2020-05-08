/*******************************************************************************
 * Copyright (c) 2010-2015 Gerald Rosenberg & others. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the standard 3-clause BSD License.  A copy of the License
 * is provided with this distribution in the License.txt file.
 *******************************************************************************/
package net.certiv.xvisitor.dt.ui.wizards;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class ContentGenerator {

	public static final String TEMPLATE_ROOT = "/templates";
	public static final String TEMPLATE_NAME = "XVisitor.stg";

	public static String newVisitor(String filename, String packageName, String parserName, String superclass,
			String importTxt) {

		STGroup group = new STGroupFile(TEMPLATE_ROOT + "/" + TEMPLATE_NAME);
		ST st = group.getInstanceOf("NewVisitor");

		st.add("filename", filename);
		st.add("packageName", packageName);
		st.add("parserName", parserName);
		st.add("superclass", superclass);
		st.add("importTxt", importTxt);

		return st.render(72);
	}
}
