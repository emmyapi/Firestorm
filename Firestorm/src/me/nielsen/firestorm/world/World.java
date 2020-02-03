package me.nielsen.firestorm.world;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import me.nielsen.firestorm.entities.Player;
import me.nielsen.firestorm.rendering.textures.Texture;
import me.nielsen.firestorm.states.GameState;

public class World {
	
	private String name;
	private int width;
	private int height;
	private int[] pixels;
	
	public World(String name, GameState state) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("./resources/levels/" + name + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.name = name;
		this.width = image.getWidth();
		this.height = image.getHeight();
		pixels = image.getRGB(0, 0, width, height, null, 0, width);
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				int id = pixels[x + y * width];
				if(id == 0xFF0000FF)
					new Player(x * 32, y * 32, state);
				else if(Tile.getFromID(id) != null)
					state.addTile(new Tile(id, x, y));
			}
		}
	}

}
