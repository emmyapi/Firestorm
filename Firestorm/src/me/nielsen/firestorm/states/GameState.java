package me.nielsen.firestorm.states;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import me.nielsen.firestorm.Game;
import me.nielsen.firestorm.entities.Entity;
import me.nielsen.firestorm.entities.Player;
import me.nielsen.firestorm.rendering.textures.Texture;
import me.nielsen.firestorm.world.Tile;
import me.nielsen.firestorm.world.World;

public class GameState implements State{
	
	private ArrayList<Entity> entities;
	private ArrayList<Tile> tiles;
	private World world;

	@Override
	public void init() {
		entities = new ArrayList<Entity>();
		tiles = new ArrayList<Tile>();
		//new Player(new Sprite("player"), 100, 100, this);
		world = new World("level1", this);
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
	
	public void addTile(Tile tile) {
		tiles.add(tile);
	}
	
	public ArrayList<Tile> getTiles() {
		return tiles;
	}

}
