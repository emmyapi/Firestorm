package me.nielsen.firestorm.world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import me.nielsen.firestorm.Game;
import me.nielsen.firestorm.rendering.textures.Sprite;

public class Tile {
	
	protected float x, y;
	protected Sprite sprite;
	protected boolean solid;
	
	public Tile(float x, float y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.solid = true;
	}
	
	public void render(Graphics2D g) {
		sprite.render(g, x, y);
		if(Game.DEBUG) {
			g.setColor(Color.RED);
			g.draw(getTop());
			g.setColor(Color.BLUE);
			g.draw(getBottom());
			g.setColor(Color.MAGENTA);
			g.draw(getLeft());
			g.setColor(Color.ORANGE);
			g.draw(getRight());
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, sprite.getWidth(), 
				sprite.getHeight());
	}

	public Rectangle getTop() {
		return new Rectangle((int) x + 4, (int) y, sprite.getWidth() - 8, 
				4);
	}

	public Rectangle getBottom() {
		return new Rectangle((int) x + 4, (int)y + sprite.getHeight() - 4, 
				sprite.getWidth() - 8, 
				4);
	}

	public Rectangle getRight() {
		return new Rectangle((int) x + sprite.getWidth() - 4, (int) y + 4, 
				4, 
				sprite.getHeight() - 8);
	}

	public Rectangle getLeft() {
		return new Rectangle((int) x, (int) y + 4, 
				4, 
				sprite.getHeight() - 8);
	}

}
