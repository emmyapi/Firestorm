package me.nielsen.firestorm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import me.nielsen.firestorm.utils.Fonts;

public class Menu {
	
	private final String[] options = {"Play", "Options", "Exit"}; //selection 0, 1, or 2
	private int currentSelection;
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Firestorm.WIDTH, Firestorm.HEIGHT);
		Fonts.drawString(g, new Font("Arial", Font.BOLD, 72), Color.ORANGE, Firestorm.TITLE, 80);
		
		for(int i = 0; i < options.length; i++)
			if(i == currentSelection)
				Fonts.drawString(g, new Font("Arial", Font.BOLD, 48), Color.YELLOW, options[i], 200 + i * 80);
			else Fonts.drawString(g, new Font("Arial", Font.PLAIN, 32), Color.WHITE, options[i], 200 + i * 80);
	}

}
