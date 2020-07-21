package net.certiv.xvisitor.dt.core.builder;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.tree.RuleNode;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import net.certiv.dsl.core.model.ModelException;
import net.certiv.dsl.core.parser.DslParseRecord;
import net.certiv.dsl.core.util.Chars;
import net.certiv.dsl.core.util.antlr.GrammarUtil;
import net.certiv.xvisitor.dt.core.parser.gen.XVisitorParser.ActionContext;
import net.certiv.xvisitor.dt.core.parser.gen.XVisitorParser.OptionContext;
import net.certiv.xvisitor.dt.core.parser.gen.XVisitorParser.TextContext;
import net.certiv.xvisitor.dt.core.parser.gen.XVisitorParserBaseVisitor;

/** @see GrammarUtil */
public class BuildUtil {

	private static final String HEADER = "header";

	private static final String HDRSpec = "^\\h*@(?:(?:parser|lexer)\\:\\:)?header\\s*\\{(.*?)\\}";
	private static final String PKGSpec = "^\\h*package\\h+(\\S+?)\\h*;";
	private static final Pattern HDR = Pattern.compile(HDRSpec, Pattern.MULTILINE | Pattern.DOTALL);
	private static final Pattern PKG = Pattern.compile(PKGSpec, Pattern.MULTILINE);

	private static final String OPTSpec = "^\\h*options\\s*\\{(.*?)\\}";
	private static final String KEYSpec = "^\\h*(\\S+?)\\h*=\\h*(\\S+?)\\h*;";
	private static final Pattern OPT = Pattern.compile(OPTSpec, Pattern.MULTILINE | Pattern.DOTALL);
	private static final Pattern KEY = Pattern.compile(KEYSpec, Pattern.MULTILINE);

	private BuildUtil() {}

	/**
	 * Returns the absolute filesystem path to the build output location for the current
	 * grammar.
	 * <p>
	 * Resolves the location on (1) the package name specified in the first header block
	 * of the grammar; or (2) the corresponding code unit default build output location.
	 *
	 * @param record the grammar parse record
	 * @return an absolute output location path
	 * @throws ModelException exception code details cause
	 */
	public static IPath resolveOutputPath(DslParseRecord record) throws ModelException {
		String pkg = grammarDefinedPackage(record);

		IPath path = null;
		if (pkg != null) {
			path = new Path(pkg.replace(Chars.DOT, Chars.SLASH));
			IPath root = record.unit.getSourceRoot();
			if (root != null) path = root.append(path);

		} else {
			path = record.unit.getLanguageMgr().getBuildOutputPath(record.unit);
		}

		path = record.unit.getProject().getLocation().append(path);
		return path;
	}

	/**
	 * Returns the dotted-form package name defined in the grammar top-most header block,
	 * or {@code null} if not found.
	 * <p>
	 * Visits the reconcile parse-tree, if present, or {@code regex}s the full grammar
	 * source content.
	 *
	 * @param record the grammar parse record
	 * @return the resolved package name or {@code null} if not found
	 * @throws ModelException exception code details cause
	 */
	public static String grammarDefinedPackage(DslParseRecord record) {
		if (record.hasTree()) {
			XVisitorParserBaseVisitor<String> visitor = new XVisitorParserBaseVisitor<String>() {

				@Override
				protected boolean shouldVisitNextChild(RuleNode node, String result) {
					return result == null ? true : false;
				}

				@Override
				public String visitAction(ActionContext ctx) {
					String name = ctx.ID().getText();
					if (HEADER.equals(name)) {
						List<TextContext> blocks = ctx.actionBlock().text();
						if (blocks != null && blocks.size() == 1) {
							String text = GrammarUtil.getText(blocks.get(0).TEXT());
							Matcher m = PKG.matcher(text.trim());
							if (m.find()) return m.group(1).trim();
						}
					}
					return null;
				}
			};

			return visitor.visit(record.getTree());
		}

		String content = record.unit.getContent();
		Matcher m = HDR.matcher(content);
		if (m.find()) {
			String hdrText = m.group(1);
			m = PKG.matcher(hdrText);
			if (m.find()) return m.group(1).trim();
		}
		return null;
	}

	/**
	 * Returns the value defined for the given option name, or {@code null} if the options
	 * block or named key is not found.
	 * <p>
	 * Visits the reconcile parse-tree, if present, or {@code regex}s the full grammar
	 * source content.
	 *
	 * @param record the grammar parse record
	 * @param keyname the option name key
	 * @return the resolved option value or {@code null} if not resolved
	 */
	public static String resolveGrammarDefinedOptionValue(DslParseRecord record, String keyname) {
		if (record.hasTree()) {
			XVisitorParserBaseVisitor<String> visitor = new XVisitorParserBaseVisitor<String>() {

				@Override
				protected boolean shouldVisitNextChild(RuleNode node, String result) {
					return result == null ? true : false;
				}

				@Override
				public String visitOption(OptionContext ctx) {
					if (ctx.ID().getText().equals(keyname)) {
						return ctx.optionValue().getText();
					}
					return null;
				}
			};

			return visitor.visit(record.getTree());
		}

		String content = record.unit.getContent();
		Matcher m = OPT.matcher(content);
		if (m.find()) {
			String optText = m.group(1);
			m = KEY.matcher(optText);
			if (m.find()) {
				if (m.group(1).equals(keyname)) {
					return m.group(2);
				}
			}
		}
		return null;
	}
}
