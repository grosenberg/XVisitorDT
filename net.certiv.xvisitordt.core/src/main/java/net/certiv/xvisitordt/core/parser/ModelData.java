package net.certiv.xvisitordt.core.parser;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

public class ModelData {

	// overlay type
	public static final int COMBINED = 1 << 0;
	public static final int LEXER = 1 << 1;
	public static final int PARSER = 1 << 2;
	public static final int TREE = 1 << 3;
	public static final int FRAGMENT = 1 << 4;
	public static final int PROTECTED = 1 << 5;
	public static final int PUBLIC = 1 << 6;
	public static final int PRIVATE = 1 << 7;

	public int decoration = 0;

	public ModelType type;
	public ParseTree rootNode;
	public ParseTree name;
	public String key;
	public ParseTree value;
	public ParseTree mod;
	public ParseTree var;
	public ParseTree num;
	public ParseTree id;
	public ParseTree lhs;
	public ParseTree rhs;
	public List<ParseTree> listName;
	public List<ParseTree> listRet;

	public ModelData(ModelType type, ParseTree rootNode, String key) {
		this.type = type;
		this.rootNode = rootNode;
		this.key = key;
	}

	public ModelData(ModelType type, ParseTree rootNode, String key, ParseTree value) {
		this.type = type;
		this.rootNode = rootNode;
		this.key = key;
		this.value = value;
	}

	public ModelData(ModelType type, ParseTree rootNode, ParseTree mod, List<ParseTree> listId) {
		this.type = type;
		this.mod = mod;
	}

	public ModelData(ModelType type, ParseTree rootNode, ParseTree mod, ParseTree t, ParseTree var, ParseTree num) {
		this.type = type;
		this.rootNode = rootNode;
		this.mod = mod;
		this.var = var;
		this.num = num;
	}

	public ModelData(ModelType type, ParseTree rootNode, ParseTree mod, ParseTree id, ParseTree num) {
		this.type = type;
		this.rootNode = rootNode;
		this.mod = mod;
		this.id = id;
		this.num = num;
	}

	public ModelData(ModelType type, ParseTree rootNode, ParseTree lhs, ParseTree rhs) {
		this.type = type;
		this.rootNode = rootNode;
		this.lhs = lhs;
		this.rhs = rhs;
	}

	public ModelData(ModelType type, ParseTree rootNode, ParseTree name,
			List<ParseTree> listName, List<ParseTree> listRet) {
		this.type = type;
		this.rootNode = rootNode;
		this.name = name;
		this.listName = listName;
		this.listRet = listRet;
	}

	public ModelData(ModelType type, ParseTree rootNode, List<ParseTree> listName) {
		this.type = type;
		this.rootNode = rootNode;
		this.listName = listName;
	}

	public void setDecoration(int decoration) {
		this.decoration = decoration;
	}
}
