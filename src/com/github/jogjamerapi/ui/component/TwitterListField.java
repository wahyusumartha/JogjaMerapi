package com.github.jogjamerapi.ui.component;

import java.util.Vector;

import com.github.jogjamerapi.JalinMerapi;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontFamily;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ListFieldCallback;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class TwitterListField extends ListField implements ListFieldCallback {

	private Vector rows;
	private LabelField twitterText;
	private LabelField userInfo;

	private TableRowManager rowManager;
	public TwitterListField() {
		super();
		setEmptyString("Nothing Feed For Jalin Merapi", DrawStyle.HCENTER);
		setCallback(this);
		

		// rowManager = new TableRowManager();
		setRowHeight(50);

		rows = new Vector();
	}
	
	

	/*
	 * Starting Implement ListFieldCallBack
	 */





	public void drawListRow(ListField listField, Graphics graphics, int index,
			int y, int width) {

	 rowManager = new TableRowManager();
		if (index < rows.size()) {
			JalinMerapi jalinMerapi = (JalinMerapi) rows.elementAt(index);
			int height = listField.getRowHeight();
		
			
			/*
			 * Draw List Cell every Row
			 */
			int[] upperX_PTS = new int[] { 0, 0, width, width };
			int[] upperY_PTS = new int[] { 0, y + (height / 2),
					y + (height / 2), 0 };

			int[] lowerX_PTS = new int[] { 0, 0, width, width };
			int[] lowerY_PTS = new int[] { y + (height / 2), y + height,
					y + height, y + (height / 2) };

			int[] upperDrawColors = new int[] {  0x5d5e61, 0x3d3f45, 0x3d3f45, 0x5d5e61 };
			int[] lowerDrawColors = new int[] { 0x2d2f33, 0x1e2126, 0x1e2126, 0x2d2f33 };
			graphics.drawShadedFilledPath(upperX_PTS, upperY_PTS, null,
					upperDrawColors, null);
			graphics.drawShadedFilledPath(lowerX_PTS, lowerY_PTS, null,
					lowerDrawColors, null);
			/*
			 * End of Draw List Cell
			 */
			
			twitterText = new LabelField(){

				protected void paint(Graphics graphics) {
					graphics.setColor(Color.WHITE);
					super.paint(graphics);
				}
				
			};
			userInfo = new LabelField(){

				protected void paint(Graphics graphics) {
					graphics.setColor(Color.WHITE);
					super.paint(graphics);
				}
			};
			try {
				twitterText.setFont(FontFamily.forName("System").getFont(Font.PLAIN, 5, Ui.UNITS_pt));
				userInfo.setFont(FontFamily.forName("System").getFont(Font.BOLD, 4, Ui.UNITS_pt));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		
			
			twitterText.setText(jalinMerapi.getText());
			rowManager.add(twitterText);

			userInfo.setText("From : " + jalinMerapi.fromUser());
			rowManager.add(userInfo);
			
			rowManager.drawRow(graphics, 0, y + 1, width,listField.getRowHeight());
			
		}
	}

	public Object get(ListField listField, int index) {
		if (index < rows.size()) {
			return rows.elementAt(index);
		}
		return null;
	}

	public int getPreferredWidth(ListField listField) {
		return Display.getWidth() - 2;
	}

	public int indexOfList(ListField listField, String prefix, int start) {
		for (int i = 0; i < rows.size(); i++) {
			JalinMerapi jalinMerapi = (JalinMerapi) rows.elementAt(i);

			if (jalinMerapi.getText().indexOf(prefix) > -1) {
				return i;
			}
		}
		return -1;
	}

	/*
	 * End Of Implement ListFieldCallBack
	 */

	/*
	 * Method to handle data in vector
	 */
	public void add(JalinMerapi jalinMerapi) {
		rows.addElement(jalinMerapi);
	}

	public void clear() {
		rows.removeAllElements();
	}

	public void insert(JalinMerapi jalinMerapi, int index) {
		rows.insertElementAt(jalinMerapi, index);
	}

	/*
	 * End of Method that handle data in vector
	 */

	
	
	
	private class TableRowManager extends VerticalFieldManager {

		public TableRowManager() {
			super(VERTICAL_SCROLL);
		}

		/*
		 * Cause the fields within this row manager to be layed out then painted
		 */
		public void drawRow(Graphics graphics, int x, int y, int width,
				int height) {
			// arrange the cell with this layout
			layout(width, height);

			// Place this row manager with its enclosing list
			setPosition(x, y);

			// Apply translating/clipping transformation to the graphics
			// context so that this row paints in the right area
			graphics.pushRegion(getExtent());

			// paint this manager
			subpaint(graphics);

			graphics.setColor(0x00CACACA);
			graphics.drawLine(0, 0, getPreferredWidth(), 0);

			// Restore the graphics context
			graphics.popContext();
		}

		/*
		 * Arrange This manager's controlled fields from left to rightwithin the
		 * enclosing table's column
		 */
		protected void sublayout(int arg0, int arg1) {
			// Set the size position of each field
			int fontHeight = Font.getDefault().getHeight();
			int preferredWidth = getPreferredWidth();

			// start with Label Field of Twitter Message
			Field field = getField(0);
			layoutChild(field, getPreferredWidth(), getPreferredHeight());
			setPositionChild(field, 5, 0);

			// set the User sender label Field
			field = getField(1);
			layoutChild(field, getPreferredWidth(), getPreferredHeight());
			setPositionChild(field, 5, getField(0).getHeight() + 1);

			setExtent(preferredWidth, getPreferredHeight());

		}

		public int getPreferredHeight() {
			return Graphics.getScreenHeight();
		}

		public int getPreferredWidth() {
			return Graphics.getScreenWidth();
		}

	
		
		

	}
}
