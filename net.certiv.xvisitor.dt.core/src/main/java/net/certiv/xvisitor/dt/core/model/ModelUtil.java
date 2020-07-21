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
package net.certiv.xvisitor.dt.core.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;

import net.certiv.dsl.core.model.ICodeUnit;
import net.certiv.dsl.core.model.IStatement;
import net.certiv.dsl.core.model.IStatementVisitor;

public class ModelUtil {

	public static SpecializedType getSpecializedType(IStatement stmt) {
		if (stmt.hasData()) {
			Specialization data = (Specialization) stmt.getData();
			return data.specializedType;
		}
		return SpecializedType.Unknown;
	}

	/** Returns all children of the given statement of the given model type. */
	public static List<IStatement> getChildren(IStatement stmt, SpecializedType type) {
		List<IStatement> children = new ArrayList<>();
		ICodeUnit unit = stmt.getCodeUnit();
		unit.lock();
		try {
			stmt.decend(new IStatementVisitor() {

				@Override
				public boolean onEntry(IStatement child) throws CoreException {
					if (getSpecializedType(child) == type) children.add(child);
					return child.hasStatements();
				}
			});
		} catch (CoreException e) {} finally {
			unit.unlock();
		}

		return children;
	}
}
