package net.certiv.xvisitordt.core.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;

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
		try {
			stmt.decend(new IStatementVisitor() {

				@Override
				public boolean onEntry(IStatement child) throws CoreException {
					if (getSpecializedType(child) == type) children.add(child);
					return child.hasChildren();
				}
			});
		} catch (CoreException e) {}

		return children;
	}
}
