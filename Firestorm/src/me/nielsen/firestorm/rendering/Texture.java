//I'M LEARNING JAVA THUS THIS CODE IS MORE OR LESS A COPY FROM MATTHEW ROGERS "BOSSLETSPLAYS"
//https://www.youtube.com/watch?v=qWVUQPWa67M&list=PLzM5baL2UjtLEewQScGTWNgeoGXMxG7pc&index=1

package me.nielsen.firestorm.rendering;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class Texture {
	
	private final static Map<String, Texture> texMap = new HashMap<String, Texture>();
	private BufferedImage image;
	
	public Texture(String fileName) {
		try {
			image = ImageIO.read(new File("./resources/textures/" + fileName + ".png"));
			texMap.put(fileName, this);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
