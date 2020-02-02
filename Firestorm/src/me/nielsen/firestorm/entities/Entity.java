package me.nielsen.firestorm.entities;

import java.awt.Graphics;

import me.nielsen.firestorm.rendering.textures.Sprite;
import me.nielsen.firestorm.states.GameState;

public abstract class Entity {
	
	protected double x, y;
	protected Sprite sprite;
	protected GameState state;
	
	public Entity(Sprite sprite, double x, double y, GameState state) {
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		this.state = state;
		state.addEntity(this);
	}
	
	public abstract void tick();
	
	public void render(Graphics g) {
		sprite.render(g, x, y);
	}

}
