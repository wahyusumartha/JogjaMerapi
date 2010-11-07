package com.github.jogjamerapi.test;

import com.github.jogjamerapi.AppSetting;
import com.github.jogjamerapi.JalinMerapi;
import com.github.jogjamerapi.ServiceClient;
import com.github.jogjamerapi.network.HttpConnectionFactory;
import com.github.jogjamerapi.ui.component.BackgroundManager;
import com.github.jogjamerapi.ui.component.HeaderField;
import com.github.jogjamerapi.ui.component.WLabelField;
import com.github.jogjamerapi.util.image.ImageUtil;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class TestScreen extends MainScreen {

	private ServiceClient serviceClient;
	private HttpConnectionFactory factory;

	private WLabelField labelField;
	private BackgroundManager backgroundManager;

	public TestScreen() {
		// factory = new HttpConnectionFactory();
		// AppSetting setting = new AppSetting(
		// "http://search.twitter.com/search.json?q=");
		// serviceClient = new ServiceClient(setting, factory);
		// JalinMerapi[] results;
		// serviceClient.setParameter("jalinmerapi");
		// results = serviceClient.getJalinMerapi().getResults();
		// StringBuffer response = new StringBuffer();
		// for (int i = 0; i < results.length; i++) {
		// response.append(results[i].getText()).append("\n");
		// }
		ImageUtil imageUtil = new ImageUtil();
		backgroundManager = new BackgroundManager();
		BitmapField bitmapField = new BitmapField(imageUtil.bestFit(Bitmap
				.getBitmapResource("merapi2.png"), Display.getWidth()- 75,
				Display.getHeight() - 40),BitmapField.FIELD_HCENTER | BitmapField.FIELD_VCENTER);
		bitmapField.setMargin(5,0,5,0);
		// labelField = new WLabelField();
		// labelField.setText(response.toString());
		// backgroundManager.add(labelField);
		
		setBanner(new HeaderField("Jogja and Merapi"));
		//backgroundManager.add(labelField);
		backgroundManager.add(bitmapField);
		add(backgroundManager);
	}
}
