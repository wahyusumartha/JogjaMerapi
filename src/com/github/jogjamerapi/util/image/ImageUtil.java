package com.github.jogjamerapi.util.image;

import net.rim.device.api.math.Fixed32;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.ui.Graphics;

public class ImageUtil {
	private static int[] rescaleArray(int[] ini, int x, int y, int x2, int y2) {
		int out[] = new int[x2 * y2];
		for (int yy = 0; yy < y2; yy++) {
			int dy = yy * y / y2;
			for (int xx = 0; xx < x2; xx++) {
				int dx = xx * x / x2;
				out[(x2 * yy) + xx] = ini[(x * dy) + dx];
			}
		}
		return out;
	}

	public static Bitmap resizeBitmap(Bitmap image, int width, int height) {
		// Need an array (for RGB, with the size of original image)
		//
		int rgb[] = new int[image.getWidth() * image.getHeight()];

		// Get the RGB array of image into "rgb"
		//
		image.getARGB(rgb, 0, image.getWidth(), 0, 0, image.getWidth(), image
				.getHeight());

		// Call to our function and obtain RGB2
		//
		int rgb2[] = rescaleArray(rgb, image.getWidth(), image.getHeight(),
				width, height);

		// Create an image with that RGB array
		//
		Bitmap temp2 = new Bitmap(width, height);

		temp2.setARGB(rgb2, 0, width, 0, 0, width, height);

		return temp2;
	}

	public static Bitmap bestFit(Bitmap image, int maxWidth, int maxHeight) {

		// getting image properties
		int w = image.getWidth();
		int h = image.getHeight();

		// get the ratio
		int ratiow = 100 * maxWidth / w;
		int ratioh = 100 * maxHeight / h;

		// this is to find the best ratio to
		// resize the image without deformations
		int ratio = Math.min(ratiow, ratioh);

		// computing final desired dimensions
		int desiredWidth = w * ratio / 100;
		int desiredHeight = h * ratio / 100;
		// imgscale = ratio;

		// resizing
		return resizeBitmap(image, desiredWidth, desiredHeight);
	}

	public static EncodedImage scaleImageToWidth(EncodedImage encoded,
			int newWidth) {
		return scaleToFactor(encoded, encoded.getWidth(), newWidth);
	}

	public static EncodedImage scaleImageToHeight(EncodedImage encoded,
			int newHeight) {
		return scaleToFactor(encoded, encoded.getHeight(), newHeight);
	}

	public static EncodedImage scaleImage(EncodedImage image, double ratio) {
		int newWidth = (int) (image.getWidth() * ratio);
		return scaleToFactor(image, image.getWidth(), newWidth);
	}

	public static EncodedImage stretchFit(EncodedImage image, int newWidth,
			int newHeight) {
		int numerator = Fixed32.toFP(image.getWidth());
		int denominator = Fixed32.toFP(newWidth);
		int scale = Fixed32.div(numerator, denominator);

		int numerator2 = Fixed32.toFP(image.getHeight());
		int denominator2 = Fixed32.toFP(newHeight);
		int scale2 = Fixed32.div(numerator2, denominator2);

		return image.scaleImage32(scale, scale2);
	}

	public static EncodedImage scaleToFactor(EncodedImage encoded, int curSize,
			int newSize) {
		int numerator = Fixed32.toFP(curSize);
		int denominator = Fixed32.toFP(newSize);
		int scale = Fixed32.div(numerator, denominator);

		return encoded.scaleImage32(scale, scale);
	}

	/**
	 * Method to crop bitmap, but will having problem with cropping images with
	 * alpha transparency. Sorry, still needed to make it perfect. Will try to
	 * use techniques from:
	 * http://www.mobiyana.com/code/blackberry/PNGEncoder.java to make it work.
	 * 
	 * @param source
	 * @param left
	 * @param top
	 * @param width
	 * @param height
	 * @return
	 */
	public static Bitmap cropBitmap(Bitmap source, int left, int top,
			int width, int height) {
		Bitmap img = new Bitmap(width, height);
		Graphics g = new Graphics(img);
		// g.drawBitmap(0, 0, width, height, img, left, top);
		int[] argb = new int[width * height];
		source.getARGB(argb, 0, width, left, top, width, height);
		g.drawARGB(argb, 0, width, 0, 0, width, height);

		return img;
	}

	public static void drawTexture(Bitmap bmp, Graphics g, int left, int top,
			int width, int height) {
		int[] xPts = new int[] { left, left + width, left + width, left };
		int[] yPts = new int[] { top, top, top + height, top + height };
		byte[] pointTypes = new byte[] { Graphics.CURVEDPATH_END_POINT,
				Graphics.CURVEDPATH_END_POINT, Graphics.CURVEDPATH_END_POINT,
				Graphics.CURVEDPATH_END_POINT };
		int dux = Fixed32.toFP(1), dvx = Fixed32.toFP(0), duy = Fixed32.toFP(0), dvy = Fixed32
				.toFP(1);
		g.drawTexturedPath(xPts, yPts, pointTypes, null, left, top, dux, dvx,
				duy, dvy, bmp);
	}
}
