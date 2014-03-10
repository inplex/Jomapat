package me.inplex.jomapat;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class BlockDirt extends Block {
	
	public static final byte PROP_COLLISION = 0x01;
	public static final byte PROP_LIGHT = 0x02;

	private BufferedImage sprite =  ImageIO.read(bla);

	@Override
	public byte getId() {
		return 0x01;
	}

	@Override
	public BufferedImage getImage() {
		return sprite;
	}
	
	public boolean getProp(byte prop){
		switch (prop){
		case PROP_COLLISION:
			return true;
		case PROP_LIGHT:
			return false;
		}
	}
	
	
		
}