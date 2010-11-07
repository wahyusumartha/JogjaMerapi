
package com.github.jogjamerapi.log;

import java.util.Enumeration;
import java.util.Vector;

public abstract class AbstractLogger implements ChainLoggable, AppenderAttachable, Controllable {

	protected String name;
	protected int level;
	protected boolean additive;
	protected ChainLoggable parent;

	protected Vector appendersVector;

	public AbstractLogger(String pName, int pLevel, boolean pAdditive, Appender[] pAppenders) {
		this(pName, pLevel, pAdditive, arrayToVector(pAppenders));
	}

	public AbstractLogger(String pName, int pLevel, boolean pAdditive, Vector pAppenders) {
		name = pName;
		level = pLevel;
		additive = pAdditive;
		appendersVector = pAppenders;
	}

	protected static Vector arrayToVector(Object[] a) {
		Vector v = new Vector();

		if ((a != null) && (a.length > 0)) {
			for (int i = 0; i < a.length; i++) {
				v.addElement(a[i]);
			}
		}

		return v;
	}

	protected Appender[] vectorToArray(Vector v) {
		Appender[] a = null;

		if ((v != null) && (v.size() > 0)) {
			a = new Appender[v.size()];
			v.copyInto(a);
		}

		return a;
	}

	// implements ChainLoggable

	public String getName() {
		return name;
	}

	public void setName(String pName) {
		name = pName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int pLevel) {
		level = pLevel;
	}

	public boolean getAdditive() {
		return additive;
	}

	public void setAdditive(boolean pAdditive) {
		additive = pAdditive;
	}

	public ChainLoggable getParent() {
		return parent;
	}

	public void setParent(ChainLoggable pParent) {
		parent = pParent;
	}

	public abstract void debug(String message);

	public abstract void error(String message);

	public abstract void fatal(String message);

	public abstract void info(String message);

	public abstract void warn(String message);

	// implements AppenderAttachable

	public Appender[] getAppenders() {
		return vectorToArray(appendersVector);
	}

	public void addAppender(Appender newAppender) {
		if (appendersVector != null) {
			appendersVector.addElement(newAppender);
		}
	}

	public Enumeration getAllAppenders() {
		return appendersVector.elements();
	}

	public Appender getAppender(String name) {
		Appender a = null;
		Enumeration e = appendersVector.elements();

		while (e.hasMoreElements()) {
			Appender b = (Appender) e.nextElement();
			if ((b != null) && b.getName().trim().equals(name)) {
				return b;
			}
		}

		return a;
	}

	public boolean isAttached(Appender appender) {
		return appendersVector.contains(appender);
	}

	public void removeAllAppenders() {
		appendersVector.removeAllElements();
	}

	public void removeAppender(Appender appender) {
		appendersVector.removeElement(appender);
	}

	public void removeAppender(String name) {
		Enumeration e = appendersVector.elements();

		while (e.hasMoreElements()) {
			Appender b = (Appender) e.nextElement();
			if ((b != null) && b.getName().trim().equals(name)) {
				appendersVector.removeElement(b);
			}
		}
	}

	// implements Controllable

	public abstract void close();

	public abstract void reset();

	public abstract void clear();

	public abstract void show();

}
