package me.inplex.jomapat;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BlockDirt extends Block {
	
	private BufferedImage sprite = null;

	@Override
	public void load() {
		try {
			sprite = ImageIO.read(new File("res/Assets/Sprites/Dummy.png"));
		} catch (IOException e) {
			System.out.println("Cannot load sprite");
		}
	}

	@Override
	public byte getId() {
		return 0x01;
	}

	@Override
	public BufferedImage getImage() {
		return sprite;
	}

	public boolean getProp(BlockProps prop) {
		switch (prop) {
		case PROP_COLLISION:
			return true;
		}
		return false;
	}

}
