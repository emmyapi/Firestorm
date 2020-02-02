package me.nielsen.firestorm.states;

import java.awt.Graphics;

public interface State {
	
	public void init();
	public void enter();
	public void tick(StateManager stateManager);
	public void render(Graphics g);
	public void exit();
	public String getName();
	

}
