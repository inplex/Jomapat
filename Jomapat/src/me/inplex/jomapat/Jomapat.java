package me.inplex.jomapat;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Jomapat extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private final String title = "Jomapat";
	private int width = 900;
	private int height = 600;
	private boolean running = false;
	private Thread thread;
	private JFrame frame;
	private long ticks;

	public static Jomapat game;
	//
	public static void main(String[] args) {
		System.out.println("Starting with " + Runtime.getRuntime().freeMemory() / 1048576 + "MB");
		game = new Jomapat();
		game.frame.setTitle(game.title + " | Starting ..");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// TODO: Set icon here -----> Patrick
		game.frame.setLocationRelativeTo(null);
		game.frame.setResizable(true);
		// add input listeners!
		game.frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				game.resized();
			}
		});

		game.frame.setVisible(true);

		game.start();
	}

	public Jomapat() {
		Dimension size = new Dimension(width, height);
		setPreferredSize(size);
		frame = new JFrame();
		try {
			SpriteManager.loadSprites(ImageIO.read(new File("res//Assets//Sprites//sprites.png")));
		} catch (IOException e) {
			e.printStackTrace();
			stop();
		}
	}

	public void resized() {
		// handle resize here!
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Game");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + " | " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
	}

	public void update() {
		ticks++;
		// game logic here
	}
	


	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		GradientPaint gp = new GradientPaint(getWidth() / 2, 0, new Color(0x8FD0FF), getWidth() / 2, getHeight(), new Color(0x5CA0CC));
		((Graphics2D) g).setPaint(gp);
		((Graphics2D) g).fillRect(0, 0, getWidth(), getHeight());
		Renderer.renderGame(g);
		g.dispose();
		bs.show();
	}

}
