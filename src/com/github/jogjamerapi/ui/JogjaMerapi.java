package com.github.jogjamerapi.ui;

import com.github.jogjamerapi.AppSetting;
import com.github.jogjamerapi.ServiceClient;
import com.github.jogjamerapi.network.HttpConnectionFactory;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;

public class JogjaMerapi extends UiApplication implements ActionListener {

	private static final String url = "http://search.twitter.com/search.json?q=";

	private HttpConnectionFactory factory;
	private ServiceClient serviceClient;

	private HomeScreen homeScreen;
	private JalinMerapiStreamScreen jalinMerapiStreamScreen;

	public JogjaMerapi() {
		init();
		if (serviceClient != null) {
			homeScreen = new HomeScreen(serviceClient);
			homeScreen.addActionListener(this);
			pushScreen(homeScreen);
			// jalinMerapiStreamScreen = new
			// JalinMerapiStreamScreen(serviceClient);
			// pushScreen(jalinMerapiStreamScreen);
			//			
			// jalinMerapiStreamScreen.loadList();
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
		if (event.getSource() == homeScreen) {
			if (event.getAction().equals(JalinMerapiStreamScreen.ACTION_ENTER)) {
				if (jalinMerapiStreamScreen == null) {
					jalinMerapiStreamScreen = new JalinMerapiStreamScreen(
							serviceClient);
					jalinMerapiStreamScreen.addActionListener(this);
				}
				pushScreen(jalinMerapiStreamScreen);
				jalinMerapiStreamScreen.loadList();
			}

		}
	}
}
