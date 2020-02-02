package me.nielsen.firestorm.world;

import java.awt.Graphics;

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
	
	public void render(Graphics g) {
		sprite.render(g, x, y);
	}

}
