package me.nielsen.firestorm.entities;

import java.awt.Graphics2D;

import me.nielsen.firestorm.rendering.textures.Animation;
import me.nielsen.firestorm.rendering.textures.Texture;
import me.nielsen.firestorm.states.GameState;
import me.nielsen.firestorm.world.Tile;

public abstract class Mob extends Entity{
	
	protected double dx, dy; //velocity (d = delta)
	protected double maxDY;
	protected double gravity;
	protected boolean falling;
	protected boolean canJump;
	protected boolean moving;
	protected Animation anime;

	public Mob(Texture texture, double x, double y, GameState state, Animation anime) {
		super(texture, x, y, state);
		this.anime = anime;
		falling = true;
		gravity = 0.5;
		maxDY = 7;
	}
	
	@Override
	public void tick() {
		move();
		fall();
		if(dx != 0) moving = true;
		else moving = false;
		if(moving) anime.run();
	}
	
	@Override
	public void render(Graphics2D g) {
		if(!moving)
			super.render(g);
		else anime.render(g, x, y);
	}
	
	public void move() {
		if(!hasHorisontalCollision()) x += dx;
		if(!hasVerticalCollision()) y += dy;
	}
	
	protected boolean hasVerticalCollision() {
		for(int i = 0; i < state.getTiles().size(); i++) {
			Tile t = state.getTiles().get(i);
			if(getBottom().intersects(t.getTop()) && dy > 0) {
				canJump = true;
				falling = false;
				dy = 0;
				return true;
			} else falling = true;
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
	
	protected void fall() {
		if(falling) {
			dy += gravity;
			if(dy > maxDY) dy = maxDY;
		}
	}
	
	protected void jump(double jumpHeight) {
		if(canJump) {
			dy -= jumpHeight;
			canJump = false;
		}
	}

}
