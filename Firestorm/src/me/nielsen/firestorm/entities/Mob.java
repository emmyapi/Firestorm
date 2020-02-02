package me.nielsen.firestorm.entities;

import me.nielsen.firestorm.rendering.textures.Sprite;
import me.nielsen.firestorm.states.GameState;

public abstract class Mob extends Entity{
	
	protected double dx, dy; //velocity (d = delta)

	public Mob(Sprite sprite, double x, double y, GameState state) {
		super(sprite, x, y, state);
	}
	
	@Override
	public void tick() {
		move();
	}
	
	public void move() {
		x += dx;
		y += dy;
	}

}
