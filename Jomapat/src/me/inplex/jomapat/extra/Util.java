package me.inplex.jomapat.extra;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

public class Util {

	public static String getDataPath() {
		String os = System.getProperty("os.name").toUpperCase();
		String dir = "";
		if (os.contains("WIN"))
			dir = System.getenv("APPDATA");
		else if (os.contains("MAC"))
			dir = System.getProperty("user.home") + File.separator + "Library" + File.separator + "Application Support";
		else if (os.contains("NUX") || os.contains("NIX"))
			dir = System.getProperty("user.home");
		else
			System.getProperty("user.dir");
		dir += File.separator + "Jomapat" + File.separator;
		return dir;
	}

	public static BufferedImage getScaledImage(BufferedImage src, int w, int h) {
		int finalw = w;
		int finalh = h;
		double factor = 1.0d;
		if (src.getWidth() > src.getHeight()) {
			factor = ((double) src.getHeight() / (double) src.getWidth());
			finalh = (int) (finalw * factor);
		} else {
			factor = ((double) src.getWidth() / (double) src.getHeight());
			finalw = (int) (finalh * factor);
		}

		BufferedImage resizedImg = new BufferedImage(finalw, finalh, BufferedImage.TRANSLUCENT);
		Graphics2D g2 = resizedImg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(src, 0, 0, finalw, finalh, null);
		g2.dispose();
		return resizedImg;
	}

}