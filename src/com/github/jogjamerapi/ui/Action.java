package com.github.jogjamerapi.ui;

public class Action {

	/*
	 * Event Source
	 */
	private Object source;

	/*
	 * Event Action eg. "success, failure, error"
	 */
	private String action;

	/*
	 * Even data
	 */
	private Object data = null;

	public Action(Object source, String action, Object data) {
		this.source = source;
		this.action = action;
		this.data = data;
	}

	public Object getSource() {
		return source;
	}

	public String getAction() {
		return action;
	}

	public Object getData() {
		return data;
	}

}
