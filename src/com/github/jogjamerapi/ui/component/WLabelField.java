package com.github.jogjamerapi.ui.component;

import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontFamily;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.component.LabelField;

public class WLabelField extends LabelField {

	private FontFamily preferredFontFamily;
	private Font preferredFont;

	public WLabelField() {
		super();
		getCustomizeFont();
		setFont(preferredFont);
	}

	public WLabelField(String text) {
		super(text);
		getCustomizeFont();
		setFont(preferredFont);
	}

	public WLabelField(String text, long style) {
		super(text, style);
		getCustomizeFont();
		setFont(preferredFont);
	}

	private Font getCustomizeFont() {
		try {
			preferredFontFamily = FontFamily.forName("System");
			preferredFont = preferredFontFamily.getFont(Font.BOLD, 8,
					Ui.UNITS_pt);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			preferredFont = Font.getDefault();
		}
		return preferredFont;
	}

	protected void paint(Graphics graphics) {
		graphics.setColor(Color.WHITE);
		super.paint(graphics);
	}

}
