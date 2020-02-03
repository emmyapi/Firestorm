package me.nielsen.firestorm.entities;

import java.awt.event.KeyEvent;

import me.nielsen.firestorm.input.KeyInput;
import me.nielsen.firestorm.rendering.textures.Animation;
import me.nielsen.firestorm.rendering.textures.Texture;
import me.nielsen.firestorm.states.GameState;

public class Player extends Mob {

	public Player(double x, double y, GameState state) {
		super(new Texture(new Texture("player_sheet"), 1, 1, 64), x, y, state, 
				new Animation(10, 
						new Texture(new Texture("player_sheet"), 1, 1, 64),
						new Texture(new Texture("player_sheet"), 2, 1, 64),
						new Texture(new Texture("player_sheet"), 3, 1, 64),
						new Texture(new Texture("player_sheet"), 4, 1, 64),
						new Texture(new Texture("player_sheet"), 1, 2, 64),
						new Texture(new Texture("player_sheet"), 2, 2, 64)));
	}

	@Override
	public void tick() {
		if(KeyInput.isDown(KeyEvent.VK_W)) jump(10);
		//if(KeyInput.isDown(KeyEvent.VK_S)) dy = 2;
		if(KeyInput.isDown(KeyEvent.VK_A)) dx = -2;
		if(KeyInput.isDown(KeyEvent.VK_D)) dx = 2;

		//if(KeyInput.wasReleased(KeyEvent.VK_W) || KeyInput.wasReleased(KeyEvent.VK_S)) dy = 0;
		if(KeyInput.wasReleased(KeyEvent.VK_A) || KeyInput.wasReleased(KeyEvent.VK_D)) dx = 0;
		super.tick();
	}

}
