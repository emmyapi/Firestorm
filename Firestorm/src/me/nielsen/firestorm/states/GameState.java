package me.nielsen.firestorm.states;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import me.nielsen.firestorm.Game;
import me.nielsen.firestorm.entities.Entity;
import me.nielsen.firestorm.entities.Player;
import me.nielsen.firestorm.rendering.textures.Sprite;
import me.nielsen.firestorm.rendering.textures.SpriteSheet;
import me.nielsen.firestorm.rendering.textures.Texture;
import me.nielsen.firestorm.world.Tile;

public class GameState implements State{
	
	private ArrayList<Entity> entities;
	private ArrayList<Tile> tiles;

	@Override
	public void init() {
		entities = new ArrayList<Entity>();
		tiles = new ArrayList<Tile>();
		new Player(new Sprite("spagnetosmallpp"), 100, 100, this);
		float x = 0;
		float y = Game.HEIGHT - 64;

		tiles.add(new Tile(200, 200, new Sprite(new SpriteSheet(new Texture("terrain"), 64), 1, 1)));
		tiles.add(new Tile(100, 480, new Sprite(new SpriteSheet(new Texture("terrain"), 64), 1, 1)));
		tiles.add(new Tile(400, 50, new Sprite(new SpriteSheet(new Texture("terrain"), 64), 1, 1)));
		tiles.add(new Tile(300, 300, new Sprite(new SpriteSheet(new Texture("terrain"), 64), 1, 1)));
		tiles.add(new Tile(640 - 64, 300, new Sprite(new SpriteSheet(new Texture("terrain"), 64), 1, 1)));
		
		for(int i = 0; i < 10; i++) {
			tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain"), 64), 1, 1)));
			x += 70;
		}
	}

	@Override
	public void enter() {
		
	}

	@Override
	public void tick(StateManager stateManager) {
		for(Entity e : entities)
			e.tick();
	}

	@Override
	public void render(Graphics2D g) {
		for(Entity e : entities)
			e.render(g);
		for(Tile e : tiles)
			e.render(g);
	}

	@Override
	public void exit() {
		entities.clear();
	}

	@Override
	public String getName() {
		return "level1";
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	public ArrayList<Tile> getTiles() {
		return tiles;
	}

}
