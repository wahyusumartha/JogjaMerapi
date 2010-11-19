package com.github.jogjamerapi.ui;

import com.github.jogjamerapi.ServiceClient;
import com.github.jogjamerapi.log.Logger;
import com.github.jogjamerapi.ui.component.BackgroundManager;
import com.github.jogjamerapi.ui.component.HeaderField;
import com.github.jogjamerapi.ui.component.ImageButton;
import com.github.jogjamerapi.ui.component.TransparanMenu;
import com.github.jogjamerapi.util.image.ImageUtil;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.container.MainScreen;

public class HomeScreen extends JogjaMerapiScreen  {

	private static final String BANNER_TITLE = "Jogja Jalin Merapi";

	private BackgroundManager backgroundManager;
	private HeaderField banner;
	private BitmapField image;

	private ImageUtil imageUtil = new ImageUtil();

	private boolean showMenu = false;

	private TransparanMenu transparanMenu;
	private ImageButton twitterButton;
	private ImageButton helpButton;
	private ImageButton mapsButton;

	protected Logger log = Logger.getLogger(getClass());

	public HomeScreen(ServiceClient serviceClient) {
		super(serviceClient);
		transparanMenu = new TransparanMenu();
	
		
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
			 twitterButton = new ImageButton(imageUtil.resizeBitmap(
						Bitmap.getBitmapResource("tweet-grey.png"), 35, 35),
						imageUtil.resizeBitmap(Bitmap
								.getBitmapResource("tweet-blue.png"), 35, 35)){

									protected void doAction() {
										fireAction(JalinMerapiStreamScreen.ACTION_ENTER);
									}
					
				};

				mapsButton = new ImageButton(
						imageUtil.resizeBitmap(Bitmap
								.getBitmapResource("NeedleWhite.png"), 35, 35),
						imageUtil
								.resizeBitmap(
										Bitmap
												.getBitmapResource("NeedleLeftYellow2.png"),
										35, 35));

				 helpButton = new ImageButton(imageUtil.resizeBitmap(
						Bitmap.getBitmapResource("file-help-icon.png"), 35, 35),
						imageUtil.resizeBitmap(Bitmap
								.getBitmapResource("help-icon.png"), 35, 35));
			transparanMenu.addButton(twitterButton);
			transparanMenu.addButton(mapsButton);
			transparanMenu.addButton(helpButton);
			UiApplication.getUiApplication().pushScreen(transparanMenu);

			return true;
		}
		return super.keyDown(keycode, time);
	}

	public TransparanMenu getTransparanMenu() {
		return transparanMenu;
	}

	

}
