//I'M LEARNING JAVA THUS THIS CODE IS MORE OR LESS A COPY FROM MATTHEW ROGERS "BOSSLETSPLAYS"
//https://www.youtube.com/watch?v=qWVUQPWa67M&list=PLzM5baL2UjtLEewQScGTWNgeoGXMxG7pc&index=1

package me.nielsen.firestorm.rendering;

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
	
	public Texture(String fileName) {
		
		TextureManager oldTexture = texMap.get(fileName);
		if(oldTexture != null) {
			manager = oldTexture;
			manager.addReference();
			
		}else {
			try {
				manager = new TextureManager(ImageIO.read(new File("./resources/textures/" + fileName + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	

}
