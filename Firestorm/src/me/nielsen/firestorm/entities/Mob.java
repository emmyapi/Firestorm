package me.nielsen.firestorm.entities;

import me.nielsen.firestorm.rendering.textures.Sprite;
import me.nielsen.firestorm.states.GameState;
import me.nielsen.firestorm.world.Tile;

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
		if(!hasHorisontalCollision()) x += dx;
		if(!hasVerticalCollision()) y += dy;
	}
	
	protected boolean hasVerticalCollision() {
		for(int i = 0; i < state.getTiles().size(); i++) {
			Tile t = state.getTiles().get(i);
			if(getBounds().intersects(t.getTop()) && dy > 0) {
				dy = 0;
				return true;
			}
			if(getBounds().intersects(t.getBottom()) && dy < 0) {
				dy = 0;
				return true;
			}
		}
		
		return false;
	}
	
	protected boolean hasHorisontalCollision() {
		for(int i = 0; i < state.getTiles().size(); i++) {
			Tile t = state.getTiles().get(i);
			if(getBounds().intersects(t.getRight()) && dx < 0) {
				dx = 0;
				return true;
			}
			if(getBounds().intersects(t.getLeft()) && dx > 0) {
				dx = 0;
				return true;
			}
		}
		
		return false;
	}

}
