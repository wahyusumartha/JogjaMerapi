package com.github.jogjamerapi.ui;

import com.github.jogjamerapi.AppSetting;
import com.github.jogjamerapi.ServiceClient;
import com.github.jogjamerapi.network.HttpConnectionFactory;
import net.rim.device.api.ui.UiApplication;

public class JogjaMerapi extends UiApplication implements ActionListener {

	private static final String url = "http://search.twitter.com/search.json?q=";

	private HttpConnectionFactory factory;
	private ServiceClient serviceClient;

	private HomeScreen homeScreen;

	public JogjaMerapi() {
		init();
		if (serviceClient != null) {
			homeScreen = new HomeScreen(serviceClient);
			pushScreen(homeScreen);
		}
	}

	public static void main(String[] args) {
		JogjaMerapi jogjaMerapi = new JogjaMerapi();
		jogjaMerapi.enterEventDispatcher();
	}

	private void init() {
		factory = new HttpConnectionFactory();
		AppSetting setting = new AppSetting(url);
		serviceClient = new ServiceClient(setting, factory);
	}

	public void onAction(Action event) {
		// TODO Auto-generated method stub

	}
}
