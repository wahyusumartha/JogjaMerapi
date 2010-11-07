package com.github.jogjamerapi.ui;

import com.github.jogjamerapi.ServiceClient;
import com.github.jogjamerapi.log.Logger;
import com.github.jogjamerapi.ui.component.BackgroundManager;
import com.github.jogjamerapi.ui.component.HeaderField;
import com.github.jogjamerapi.ui.component.TransparanMenu;
import com.github.jogjamerapi.util.image.ImageUtil;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.container.MainScreen;

public class HomeScreen extends JogjaMerapiScreen {

	private static final String BANNER_TITLE = "Jogja Jalin Merapi";

	private BackgroundManager backgroundManager;
	private HeaderField banner;
	private BitmapField image;

	private ImageUtil imageUtil = new ImageUtil();

	private boolean showMenu = false;

	protected Logger log = Logger.getLogger(getClass());

	public HomeScreen(ServiceClient serviceClient) {
		super(serviceClient);
		backgroundManager = new BackgroundManager();
		banner = new HeaderField(BANNER_TITLE);

		image = new BitmapField(imageUtil.bestFit(Bitmap
				.getBitmapResource("merapi2.png"), Display.getWidth() - 75,
				Display.getHeight() - 40), BitmapField.FIELD_HCENTER
				| BitmapField.FIELD_VCENTER);
		image.setMargin(5, 0, 5, 0);

		setBanner(banner);
		backgroundManager.add(image);
		add(backgroundManager);
	}

	protected boolean keyDown(int keycode, int time) {

		if (keycode == 268566528) {
			UiApplication.getUiApplication().pushScreen(new TransparanMenu());
			return true;
		}
		return super.keyDown(keycode, time);
	}

}
