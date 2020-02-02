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

import me.nielsen.firestorm.utils.managers.TextureManager;

public class Texture {
	
	private final static Map<String, TextureManager> texMap = new HashMap<String, TextureManager>();
	private TextureManager manager;
	private String fileName;
	
	public Texture(String fileName) {
		this.fileName = fileName;
		TextureManager oldTexture = texMap.get(fileName);
		if(oldTexture != null) {
			manager = oldTexture;
			manager.addReference();
		} else {
			try {
				System.out.println("Loading texture: " + fileName);
				manager = new TextureManager(ImageIO.read(new File("./resources/textures/" + fileName + ".png")));
				texMap.put(fileName, manager);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	@Override
	protected void finalize() throws Throwable {
		if(manager.removeReference() && !fileName.isEmpty()) {
			texMap.remove(fileName);
		super.finalize();
	}
	
	}
	
	public void render(Graphics g, double x, double y) {
		g.drawImage(manager.getImage(), (int) x, (int) y, null);
	}
	
	public BufferedImage getImage(){
		return manager.getImage();
	}
	
}
