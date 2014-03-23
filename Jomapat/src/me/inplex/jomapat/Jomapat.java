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

import me.inplex.jomapat.extra.InputHandler;
import me.inplex.jomapat.extra.Util;
import me.inplex.jomapat.gfx.ParticleManager;
import me.inplex.jomapat.gfx.Renderer;
import me.inplex.jomapat.gfx.SpriteManager;
import me.inplex.jomapat.player.Inventory;
import me.inplex.jomapat.player.Player;
import me.inplex.jomapat.world.World;
import me.inplex.jomapat.world.WorldGenerator;

public class Jomapat extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private final String title = "Jomapat";
	private int width = 900;
	private int height = 600;
	private boolean running = false;
	private Thread thread;
	private JFrame frame;
	private long ticks;

	private InputHandler input;
	private World world;
	private Player player;
	private Inventory inventory;

	public float skyR = 0.2f;
	public float skyG = 0.6f;
	public float skyB = 1.0f;
	public boolean timeDown = true;

	public static Jomapat game;

	public static void main(String[] args) {
		System.out.println("Starting with " + Runtime.getRuntime().freeMemory() / 1048576 + "MB");
		System.out.println("Data Path is " + Util.getDataPath());
		game = new Jomapat();
		game.frame.setTitle(game.title + " | Starting ..");
		game.frame.setFocusable(true);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// TODO: Set icon here
		game.frame.setLocationRelativeTo(null);
		game.addKeyListener(game.input);
		game.addMouseListener(game.input);
		game.addMouseMotionListener(game.input);
		game.addMouseWheelListener(game.input);
		game.requestFocus();
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
			SpriteManager.setSheets(ImageIO.read(new File("res//Assets//Sprites//blocks.png")), ImageIO.read(new File("res//Assets//Sprites//player.png")));
		} catch (IOException e) {
			e.printStackTrace();
			stop();
		}
		ParticleManager.load();
		world = WorldGenerator.generateWorld(1000, 500);
		player = new Player(world.getWidth()*64 / 2, 128);
		input = new InputHandler();
		inventory = new Inventory();
		ticks = 0;
	}

	public void resized() {
		this.width = game.getWidth();
		this.height = game.getHeight();
		Renderer.renderDist = width / 64 / 2 + 2;
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
			render();
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}

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

		if (ticks % 2500 == 0) {
			if (timeDown)
				timeDown = false;
			else if (!timeDown)
				timeDown = true;
		}
		if (timeDown) {
			skyR += 0.0001f;
			skyG += 0.0001f;
			skyB += 0.0001f;
		} else {
			skyR -= 0.0001f;
			skyG -= 0.0001f;
			skyB -= 0.0001f;
		}
		if(skyR > 1.0f)
			skyR = 1.0f;
		if(skyG > 1.0f)
			skyG = 1.0f;
		if(skyB > 1.0f)
			skyB = 1.0f;

		if(skyR < 0.0f)
			skyR = 0.0f;
		if(skyG < 0.0f)
			skyG = 0.0f;
		if(skyB < 0.0f)
			skyB = 0.0f;
		player.update();
		world.update();
		ParticleManager.update();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		GradientPaint gp = new GradientPaint(getWidth() / 2, 0, new Color(skyR, skyG, skyB, 1.0f), getWidth() / 2, getHeight(), new Color(skyR, skyG, skyB, 1.0f));
		((Graphics2D) g).setPaint(gp);
		((Graphics2D) g).fillRect(0, 0, getWidth(), getHeight());
		Renderer.renderGame(g);
		g.dispose();
		bs.show();
	}

	public World getWorld() {
		return world;
	}
	
	public Inventory getInventory() {
		return inventory;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public long getTicks() {
		return ticks;
	}

	public void setTicks(long ticks) {
		this.ticks = ticks;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public InputHandler getInput() {
		return input;
	}

	public void setInput(InputHandler input) {
		this.input = input;
	}

}