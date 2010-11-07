package com.github.jogjamerapi.ui;

import com.github.jogjamerapi.ServiceClient;

public abstract class JogjaMerapiScreen extends ActionScreen {
	protected ServiceClient serviceClient;

	public JogjaMerapiScreen(ServiceClient serviceClient) {
		super();
		this.serviceClient = serviceClient;
	}

}
