package me.nielsen.firestorm.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import me.nielsen.firestorm.Game;
import me.nielsen.firestorm.rendering.textures.Texture;
import me.nielsen.firestorm.states.GameState;

public abstract class Entity {
	
	protected double x, y;
	protected Texture texture;
	protected GameState state;
	
	public Entity(Texture texture, double x, double y, GameState state) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.state = state;
		state.addEntity(this);
	}
	
	public abstract void tick();
	
	public void render(Graphics2D g) {
		texture.render(g, x, y);
/*		if(Game.DEBUG) {
			g.setColor(Color.RED);
			g.draw(getTop());
			g.setColor(Color.BLUE);
			g.draw(getBottom());
			g.setColor(Color.MAGENTA);
			g.draw(getLeft());
			g.setColor(Color.ORANGE);
			g.draw(getRight());
		}*/
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, texture.getWidth(), 
				texture.getHeight());
	}

	public Rectangle getTop() {
		return new Rectangle((int) x + 4, (int) y, texture.getWidth() - 8, 
				4);
	}

	public Rectangle getBottom() {
		return new Rectangle((int) x + 4, (int)y + texture.getHeight() - 4, 
				texture.getWidth() - 8, 
				4);
	}

	public Rectangle getRight() {
		return new Rectangle((int) x + texture.getWidth() - 4, (int) y + 4, 
				4, 
				texture.getHeight() - 8);
	}

	public Rectangle getLeft() {
		return new Rectangle((int) x, (int) y + 4, 
				4, 
				texture.getHeight() - 8);
	}

}
