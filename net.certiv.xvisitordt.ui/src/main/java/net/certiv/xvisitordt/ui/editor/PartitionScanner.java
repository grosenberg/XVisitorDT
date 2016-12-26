package net.certiv.xvisitordt.ui.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;

import net.certiv.dsl.ui.text.AbstractRuleBasedPartitionScanner;
import net.certiv.dsl.ui.text.rules.BalancedBraceRule;

public class PartitionScanner extends AbstractRuleBasedPartitionScanner {

	public PartitionScanner() {

		IToken jdComment = new Token(Partitions.COMMENT_JD);
		IToken mlComment = new Token(Partitions.COMMENT_ML);
		IToken slComment = new Token(Partitions.COMMENT_SL);
		IToken string = new Token(Partitions.STRING);
		IToken action = new Token(Partitions.ACTION);

		List<IRule> rules = new ArrayList<IRule>();

		rules.add(new MultiLineRule("/**", "*/", jdComment));
		rules.add(new MultiLineRule("/#", "#/", mlComment));
		rules.add(new EndOfLineRule("#", slComment));
		rules.add(new SingleLineRule("\"", "\"", string, '\\'));
		rules.add(new SingleLineRule("'", "'", string, '\\'));
		rules.add(new BalancedBraceRule(Partitions.PREFIXES, Partitions.PREDICATES, action));

		IPredicateRule[] rule = new IPredicateRule[rules.size()];
		setPredicateRules(rules.toArray(rule));
	}
}
