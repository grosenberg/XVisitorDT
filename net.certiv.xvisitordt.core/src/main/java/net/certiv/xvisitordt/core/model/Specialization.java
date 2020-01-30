package net.certiv.xvisitordt.core.model;

import org.antlr.v4.runtime.tree.ParseTree;

import net.certiv.dsl.core.model.builder.ISpecializedType;
import net.certiv.dsl.core.model.builder.ISpecialization;

public class Specialization implements ISpecialization, Cloneable {

	// overlay modelType
	public static final int COMBINED = 1 << 0;
	public static final int LEXER = 1 << 1;
	public static final int PARSER = 1 << 2;
	public static final int TREE = 1 << 3;
	public static final int FRAGMENT = 1 << 4;
	public static final int PROTECTED = 1 << 5;
	public static final int PUBLIC = 1 << 6;
	public static final int PRIVATE = 1 << 7;

	public int decoration = 0;
	public SpecializedType specializedType;

	public ParseTree stmtNode;
	public String name;
	public ParseTree value;

	public Specialization(SpecializedType type, ParseTree stmtNode, String name) {
		this.specializedType = type;
		this.stmtNode = stmtNode;
		this.name = name;
	}

	public Specialization(SpecializedType type, ParseTree stmtNode, String name, ParseTree value) {
		this.specializedType = type;
		this.stmtNode = stmtNode;
		this.name = name;
		this.value = value;
	}

	/** Sets the display icon decoration type. */
	public void setDecoration(int decoration) {
		this.decoration = decoration & 0xFF;
	}

	@Override
	public ISpecializedType getSpecializedType() {
		return specializedType;
	}

	@Override
	public void setSpecializedType(ISpecializedType specializedType) {
		this.specializedType = (SpecializedType) specializedType;
	}

	@Override
	public Specialization copy() {
		try {
			return (Specialization) this.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException("Shoud never occur", e);
		}
	}

	@Override
	public String toString() {
		return String.format("ModelData [key=%s]", name);
	}
}
