//I'M LEARNING JAVA THUS THIS CODE IS MORE OR LESS A COPY FROM MATTHEW ROGERS "BOSSLETSPLAYS"
//https://www.youtube.com/watch?v=qWVUQPWa67M&list=PLzM5baL2UjtLEewQScGTWNgeoGXMxG7pc&index=1

package me.nielsen.firestorm.rendering.textures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;


public class Texture {
	
	private final static Map<String, BufferedImage> texMap = new HashMap<String, BufferedImage>();
	private BufferedImage image;
	private String fileName;
	private int width, height;
	
	public Texture(String fileName) {
		this.fileName = fileName;
		BufferedImage oldTexture = texMap.get(fileName);
		if(oldTexture != null) {
			this.image = oldTexture;
		} else {
			try {
				System.out.println("Loading texture: " + fileName);
				this.image = ImageIO.read(new File("./resources/textures/" + fileName + ".png"));
				texMap.put(fileName, image);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.width = image.getWidth();
		this.height = image.getHeight();
		
	}
	
	public Texture(Texture spriteSheet, int x, int y, int width, int height) {
		this.width = width;
		this.height = height;
		String key = spriteSheet.fileName + "_" + x + "_" + y;
		BufferedImage old = texMap.get(key);
		if(old != null) this.image = old;
		else this.image = spriteSheet.image.getSubimage(x * width - width, y * height - height, width, height);
	}
	
	public Texture(Texture spriteSheet, int x, int y, int size) {
		this(spriteSheet, x, y, size, size);
	}
		
	public void render(Graphics g, double x, double y) {
		g.drawImage(image, (int) x, (int) y, null);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	
}
