package com.github.jogjamerapi.ui.component;

import com.github.jogjamerapi.util.image.AnimatedGIFField;

import net.rim.device.api.system.GIFEncodedImage;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class LoadingPopupScreen extends PopupScreen {

	private AnimatedGIFField animationImage;
	private LabelField labelText = null;

	public LoadingPopupScreen(String text) {
		super(new VerticalFieldManager(VerticalFieldManager.VERTICAL_SCROLL
				| VerticalFieldManager.VERTICAL_SCROLLBAR));
		GIFEncodedImage gifEncodedImage = (GIFEncodedImage) GIFEncodedImage
				.getEncodedImageResource("loader.gif");

		animationImage = new AnimatedGIFField(gifEncodedImage,
				Field.FIELD_HCENTER);
		this.add(animationImage);

		labelText = new LabelField(text, Field.FIELD_HCENTER);
		this.add(labelText);
	}

	public static void showLoadingScreen(final Runnable runThis, String text) {
		final LoadingPopupScreen loadingPopupScreen = new LoadingPopupScreen(
				text);

		Thread thread = new Thread() {
			public void run() {
				// Display Loading Screen, First
				UiApplication.getUiApplication().invokeLater(new Runnable() {
					public void run() {
						UiApplication.getUiApplication().pushScreen(
								loadingPopupScreen);
					}
				});

				// Run The code that must be executed in background
				try {
					runThis.run();
				} catch (Throwable e) {
					e.printStackTrace();
					new RuntimeException("Exception Detect while waiting : "
							+ e.toString());
				}

				// And then dismiss the screen
				UiApplication.getUiApplication().invokeLater(new Runnable() {
					public void run() {
						UiApplication.getUiApplication().popScreen(
								loadingPopupScreen);
					}
				});
			}
		};
		thread.start();
	}
}
