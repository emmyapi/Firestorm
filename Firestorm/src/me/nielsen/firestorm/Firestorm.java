//I'M LEARNING JAVA THUS THIS CODE IS MORE OR LESS A COPY FROM MATTHEW ROGERS "BOSSLETSPLAYS"
//https://www.youtube.com/watch?v=qWVUQPWa67M&list=PLzM5baL2UjtLEewQScGTWNgeoGXMxG7pc&index=1

package me.nielsen.firestorm;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import java.awt.event.MouseEvent;
import me.nielsen.firestorm.input.KeyInput;
import me.nielsen.firestorm.input.MouseInput;
import me.nielsen.firestorm.rendering.Texture;
import me.nielsen.firestorm.rendering.textures.Sprite;
import me.nielsen.firestorm.rendering.textures.SpriteSheet;
import me.nielsen.firestorm.states.MenuState;
import me.nielsen.firestorm.states.StateManager;

public class Firestorm extends Canvas implements Runnable{

	public static final String TITLE = "Firestorm";
	public static final int WIDTH = 640;
	public static final int HEIGHT = WIDTH / 4 * 3;
	
	private boolean running;
	
	private StateManager stateManager;
	
	public static Firestorm INSTANCE;
	
	
	public Firestorm() {
		addKeyListener(new KeyInput());
		MouseInput mi = new MouseInput();
		addMouseListener(mi);
		addMouseMotionListener(mi);
		stateManager = new StateManager();
		
		stateManager.addState(new MenuState());
		
		INSTANCE = this;
	}
	
	private void tick() {
		stateManager.tick();
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.red);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		stateManager.render(g);
		
		g.dispose();
		bs.show();
	}
	
	private void start() {
		if(running) return;
		running = true;
		new Thread(this, "FirestormMain-Thread").start();
	}
	
	public void stop() {
		if(!running) return;
		running = false;
	}

	@Override
	public void run() {
		requestFocus();
		double target = 60.0;
		double nsPerTick = 1000000000.0 / target;
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double unprocessed = 0.0;
		int fps = 0;
		int tps = 0;
		boolean canRender = false; 
		
		while (running) {
			long now = System.nanoTime();
			unprocessed += (now - lastTime) / nsPerTick;
			lastTime = now;
			
			if(unprocessed >= 1.0) {
				tick();
				KeyInput.update();
				MouseInput.update();
				unprocessed--;
				tps++;
				canRender = true;
			}else canRender = false;
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(canRender) {
				render();
				fps++;
			}
			
			if(System.currentTimeMillis() - 1000 > timer) {
				timer += 1000;
				System.out.printf("FPS: %d | TPS: %d\n", fps, tps);
				fps = 0;
				tps = 0;
			}
		}

		System.exit(0);
	}
	
	public static void main(String[] args) {
		Firestorm game = new Firestorm();
		JFrame frame = new JFrame(TITLE);
		frame.add(game);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setFocusable(true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.err.println("Exiting Game");
				game.stop();
			}
		});
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();
	}
	

}
