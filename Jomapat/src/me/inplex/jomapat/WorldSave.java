package me.inplex.jomapat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WorldSave {

	public static final int PREFIX = 0x57504D4A;

	public static void save(int num) throws IOException {
		World w = Jomapat.game.getWorld();
		Player p = Jomapat.game.getPlayer();

		File f = new File(Util.getDataPath() + File.separator + "world" + num + ".jmp");
		DataOutputStream out = new DataOutputStream(new FileOutputStream(f));
		out.writeInt(PREFIX);
		out.writeInt(p.getX());
		out.writeInt(p.getY());
		out.writeInt(w.getWidth());
		out.writeInt(w.getHeight());
		for (int y = 0; y < w.getHeight(); y++) {
			for (int x = 0; x < w.getWidth(); x++) {
				out.writeByte((byte) w.getBlockAt(x, y).ordinal());
			}
		}
		out.close();
	}

	public static void load(int num) throws IOException {
		File f = new File(Util.getDataPath() + File.separator + "world" + num + ".jmp");
		DataInputStream in = new DataInputStream(new FileInputStream(f));
		int pre = in.readInt();
		if (pre != PREFIX) {
			System.out.println("Invalid World Prefix. Cancelling World Load Process!");
			in.close();
			return;
		}
		int pX = in.readInt();
		int pY = in.readInt();
		int width = in.readInt();
		int height = in.readInt();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Jomapat.game.getWorld().setBlock(x, y, BlockType.values()[(byte) in.readByte()]);
			}
		}
		Jomapat.game.getPlayer().setX(pX);
		Jomapat.game.getPlayer().setY(pY);

		in.close();
	}

}