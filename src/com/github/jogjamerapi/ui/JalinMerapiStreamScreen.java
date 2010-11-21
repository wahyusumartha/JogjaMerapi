package com.github.jogjamerapi.ui;

import java.util.Timer;
import java.util.TimerTask;

import net.rim.device.api.system.Characters;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.SeparatorField;

import com.github.jogjamerapi.JalinMerapi;
import com.github.jogjamerapi.ServiceClient;
import com.github.jogjamerapi.log.Logger;
import com.github.jogjamerapi.ui.component.HeaderField;
import com.github.jogjamerapi.ui.component.LoadingPopupScreen;
import com.github.jogjamerapi.ui.component.TwitterListField;

public class JalinMerapiStreamScreen extends JogjaMerapiScreen {

	static final String ACTION_ENTER = "Jalin Merapi Stream";
	static final String ACTION_ERROR = "Error Jalin Merapi Stream";
	static final String ACTION_REFRESH = "Refresh Jalin Merapi Stream";

	private static final String BANNER_TITLE = "Merapi Twitter Stream";

	private TwitterListField twitterListField;

	private HeaderField banner;

	protected Logger log = Logger.getLogger(getClass());

	private Timer streamTimer = null;

	public JalinMerapiStreamScreen(ServiceClient serviceClient) {
		super(serviceClient);
		banner = new HeaderField(BANNER_TITLE);
		twitterListField = new TwitterListField();

		add(banner);
		// add(new SeparatorField());
		add(twitterListField);
	}

	public void loadList() {
		updateList();

	}

	private final void updateList() {

		BackgroundStream backgroundStream = new BackgroundStream(this);
		LoadingPopupScreen.showLoadingScreen(backgroundStream, "Loading...");

		streamTimer = new Timer();
		streamTimer.schedule(new TimerTask() {
			public void run() {
				updateList();
			}
		}, 10000);
	}

	protected boolean keyChar(char c, int status, int time) {
		if (c == Characters.ESCAPE) {
			streamTimer.cancel();
			UiApplication.getUiApplication().popScreen(this);
			return true;
		}
		return false;
	}

	public void close() {
		streamTimer.cancel();
		UiApplication.getUiApplication().popScreen(this);
	}

	private class BackgroundStream implements Runnable {

		private final JalinMerapiStreamScreen screen;

		public BackgroundStream(JalinMerapiStreamScreen screen) {
			this.screen = screen;
		}

		public void run() {
			// UiApplication.getUiApplication().invokeLater(new Runnable() {
			// public void run() {
			// while (twitterListField.getSize() > 0) {
			// twitterListField.delete(0);
			// }
			// }
			// });

			try {
				serviceClient.setParameter("jalinmerapi");
				final JalinMerapi[] jalinMerapis = serviceClient
						.getJalinMerapi().getResults();
				// On the Simulator, this is typically too fast, so we
				// slow it down a bit
				// try {
				// Thread.sleep(8000);
				// } catch (Exception e) {
				// }

				UiApplication.getUiApplication().invokeLater(new Runnable() {

					public void run() {
						while (twitterListField.getSize() > 0) {
							twitterListField.delete(0);
						}
						if (jalinMerapis.length > 0) {
							twitterListField.clear();

							for (int i = 0; i < jalinMerapis.length; i++) {
								twitterListField.insert(twitterListField
										.getSize());
								twitterListField.add(jalinMerapis[i]);
							}
						}
					}
				});

				// UiApplication.getUiApplication().invokeLater(new Runnable() {
				//
				// public void run() {
				// UiApplication.getUiApplication().pushScreen(screen);
				// }
				// });

			} catch (Exception e) {
				log.error(e.getMessage());
				fireAction(ACTION_ERROR, e.getMessage());
			}

		}

	}

}
