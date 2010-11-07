package com.github.jogjamerapi.ui;

import java.util.Enumeration;
import java.util.Vector;

import net.rim.device.api.ui.container.MainScreen;

public class ActionScreen extends MainScreen {

	protected Vector actionListeners = new Vector();

	public void addActionListener(ActionListener actionListener) {
		if (actionListener != null) {
			actionListeners.addElement(actionListener);
		}
	}

	protected void fireAction(String action) {
		fireAction(action, null);
	}

	protected void fireAction(String action, Object data) {
		Enumeration listenersEnum = actionListeners.elements();
		while (listenersEnum.hasMoreElements()) {
			((ActionListener) listenersEnum.nextElement()).onAction(new Action(
					this, action, data));
		}
	}

}
