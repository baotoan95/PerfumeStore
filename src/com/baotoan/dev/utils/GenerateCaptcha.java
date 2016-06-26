package com.baotoan.dev.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GenerateCaptcha {
	public static void createCaptcha(String str, int width, int height, String pathFile) {
		Color bg = Color.BLUE;
		Color fg = Color.RED;
		Font font = new Font("Tahoma", Font.ITALIC, width/str.length());
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.OPAQUE);
		Graphics g = bufferedImage.getGraphics();
		g.setFont(font);
		g.setColor(bg);
		g.fillRect(0, 0, width, height);
		g.setColor(fg);
		g.drawString(str, 15, 25);
		try {
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(pathFile)));
			ImageIO.write(bufferedImage, "jpeg", out);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
