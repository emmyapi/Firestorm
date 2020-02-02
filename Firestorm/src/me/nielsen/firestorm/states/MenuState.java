package me.nielsen.firestorm.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import me.nielsen.firestorm.Game;
import me.nielsen.firestorm.input.KeyInput;
import me.nielsen.firestorm.input.MouseInput;
import me.nielsen.firestorm.rendering.ui.Button;
import me.nielsen.firestorm.utils.Fonts;

public class MenuState implements State {
	
	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return "menu";
	}

	private Button[] options;
	private int currentSelection;
	private final Font font1 = new Font("Arial", Font.PLAIN, 32);
	private final Font font2 = new Font("Arial", Font.BOLD, 48);
	
	@Override
	public void init() {
		options = new Button[3];
		options[0] = new Button("Play", 200 + 0 * 80, font1, font2, Color.WHITE, Color.YELLOW);
		options[1] = new Button("Options", 200 + 1 * 80, font1, font2, Color.WHITE, Color.YELLOW);
		options[2] = new Button("Exit", 200 + 2 * 80, font1, font2, Color.WHITE, Color.YELLOW);
	}

	@Override
	public void enter() {}
	
	@Override
	public void tick(StateManager stateManager) {
		
		if(KeyInput.wasPressed(KeyEvent.VK_UP) || KeyInput.wasPressed(KeyEvent.VK_W)) {
			currentSelection--;
			if(currentSelection < 0) currentSelection = options.length - 1;
		}
		
		if(KeyInput.wasPressed(KeyEvent.VK_DOWN) || KeyInput.wasPressed(KeyEvent.VK_S)) {
			currentSelection++;
			if(currentSelection > options.length-1) currentSelection = 0;
		}
		
		boolean clicked = false;
		for(int i = 0; i < options.length; i++) {
			if(options[i].intersects(new Rectangle(MouseInput.getX(), MouseInput.getY(), 1, 1))) {
				currentSelection = i;
				clicked = MouseInput.wasPressed(MouseEvent.BUTTON1);
			}
		}
		
		if(clicked || KeyInput.wasPressed(KeyEvent.VK_ENTER)) 
			select(stateManager);
	}
	
	private void select(StateManager stateManager) {
		switch (currentSelection) {
			case 0:
				stateManager.setState("level1");
				//System.out.println("Play");
				break;
			case 1:
				System.out.println("Options");
				break;
			case 2:
				System.out.println("Exit");
				Game.INSTANCE.stop();
				break;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		Fonts.drawString(g, new Font("Arial", Font.BOLD, 72), Color.ORANGE, Game.TITLE, 80);
		
		for(int i = 0; i < options.length; i++) {
			if(i == currentSelection)
				options[i].setSelected(true);
			else options[i].setSelected(false);
		
			options[i].render(g);
		}
	}
}
