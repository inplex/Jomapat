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
		File dir = new File(Util.getDataPath());
		if (!dir.exists())
			dir.mkdir();
		File f = new File(Util.getDataPath() + File.separator + "world" + num + ".jmp");
		if (f.exists())
			f.delete();
		f.createNewFile();
		DataOutputStream out = new DataOutputStream(new FileOutputStream(f));
		out.writeInt(PREFIX);
		out.writeInt(p.getX());
		out.writeInt(p.getY());
		out.writeInt(w.getWidth());
		out.writeInt(w.getHeight());
		for (int y = 0; y < w.getHeight(); y++) {
			for (int x = 0; x < w.getWidth(); x++) {
				out.writeByte(w.getBlockAt(x, y) == null ? ((byte) 0) : ((byte) w.getBlockAt(x, y).ordinal()));
			}
		}
		out.close();
	}

	public static void load(int num) throws IOException {
		File f = new File(Util.getDataPath() + File.separator + "world" + num + ".jmp");
		if (!f.exists()) {
			System.out.println("Could not find file: " + f.getAbsolutePath());
			return;
		}
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
				byte id = (byte) in.readByte();
				if (id != (byte) 0)
					Jomapat.game.getWorld().setBlock(x, y, BlockType.values()[id]);
			}
		}
		Jomapat.game.getPlayer().setX(pX);
		Jomapat.game.getPlayer().setY(pY);

		in.close();
	}

}