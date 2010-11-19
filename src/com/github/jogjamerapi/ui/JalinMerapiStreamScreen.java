package com.github.jogjamerapi.ui;

import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.SeparatorField;

import com.github.jogjamerapi.JalinMerapi;
import com.github.jogjamerapi.ServiceClient;
import com.github.jogjamerapi.log.Logger;
import com.github.jogjamerapi.ui.component.HeaderField;
import com.github.jogjamerapi.ui.component.TwitterListField;

public class JalinMerapiStreamScreen extends JogjaMerapiScreen {

	static final String ACTION_ENTER = "Jalin Merapi Stream";
	static final String ACTION_ERROR = "Error Jalin Merapi Stream";
	static final String ACTION_REFRESH = "Refresh Jalin Merapi Stream";

	private static final String BANNER_TITLE = "Merapi Twitter Stream";

	private TwitterListField twitterListField;

	private HeaderField banner;

	protected Logger log = Logger.getLogger(getClass());

	public JalinMerapiStreamScreen(ServiceClient serviceClient) {
		super(serviceClient);
		banner = new HeaderField(BANNER_TITLE);
		twitterListField = new TwitterListField();

		add(banner);
		// add(new SeparatorField());
		add(twitterListField);
	}

	public void loadList() {
		while (twitterListField.getSize() > 0) {
			twitterListField.delete(0);
		}

		try {
			serviceClient.setParameter("jalinmerapi");
			JalinMerapi[] jalinMerapis = serviceClient.getJalinMerapi()
					.getResults();

			if (jalinMerapis.length > 0) {
				twitterListField.clear();

				for (int i = 0; i < jalinMerapis.length; i++) {
					twitterListField.insert(twitterListField.getSize());
					twitterListField.add(jalinMerapis[i]);
				}
			}
		
		} catch (Exception e) {
			log.error(e.getMessage());
			fireAction(ACTION_ERROR, e.getMessage());
		}
	}



	
}
