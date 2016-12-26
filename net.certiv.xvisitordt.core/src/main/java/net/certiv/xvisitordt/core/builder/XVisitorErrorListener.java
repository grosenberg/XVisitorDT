package net.certiv.xvisitordt.core.builder;

import java.util.ArrayList;
import java.util.Iterator;

import net.certiv.antlr.xvisitor.IToolListener;
import net.certiv.antlr.xvisitor.Messages;
import net.certiv.antlr.xvisitor.Messages.ToolMessage;

public class XVisitorErrorListener implements IToolListener {

	public XVisitorErrorListener chainListener = null;

	public ArrayList<String> infoList = new ArrayList<>();
	public ArrayList<Messages> warnList = new ArrayList<>();
	public ArrayList<Messages> errsList = new ArrayList<>();

	public XVisitorErrorListener() {
		super();
	}

	@Override
	public void info(String msg) {
		infoList.add(msg);
		if (chainListener != null) chainListener.info(msg);
	}

	@Override
	public void warn(Messages msg) {
		warnList.add(msg);
		if (chainListener != null) chainListener.warn(msg);
	}

	@Override
	public void error(Messages msg) {
		errsList.add(msg);
		if (chainListener != null) chainListener.error(msg);
	}

	public void error(ToolMessage msg) {
		this.error((Messages) msg);
	}

	public void chainListener(XVisitorErrorListener listener) {
		this.chainListener = listener;
	}

	public boolean hasErrors() {
		return errsList.size() > 0;
	}

	public boolean hasWarnings() {
		return warnList.size() > 0;
	}

	public boolean hasInformation() {
		return infoList.size() > 0;
	}

	public Iterator<Messages> getErrIterator() {
		return errsList.iterator();
	}

	public ArrayList<Messages> getErrList() {
		return errsList;
	}

	public ArrayList<Messages> getWarnList() {
		return warnList;
	}

	public String getErrorMessage(int idx) {
		if (hasErrors() && idx < errsList.size()) return errsList.get(idx).toString();
		return null;
	}

	public void clear() {
		errsList.clear();
		warnList.clear();
		infoList.clear();
	}
}
