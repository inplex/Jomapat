package me.inplex.jomapat.world;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;

import me.inplex.jomapat.Jomapat;
import me.inplex.jomapat.extra.Util;
import me.inplex.jomapat.gfx.Gui;
import me.inplex.jomapat.player.Player;

public class WorldSave {

	public static final short WORLDSAVE_VERSION = (short) 2;
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
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(baos);
		out.writeInt(PREFIX);
		out.writeShort(WORLDSAVE_VERSION);
		out.writeInt(p.getX());
		out.writeInt(p.getY());
		out.writeInt(w.getWidth());
		out.writeInt(w.getHeight());
		for (int y = 0; y < w.getHeight(); y++) {
			for (int x = 0; x < w.getWidth(); x++) {
				out.writeByte(w.getBlockAt(x, y) == null ? ((byte) 0xFF) : ((byte) w.getBlockAt(x, y).ordinal()));
			}
		}
		out.close();
		byte[] result = baos.toByteArray();
		result = Util.compress(result);
		FileOutputStream fos = new FileOutputStream(f);
		fos.write(result);
		fos.flush();
		fos.close();
		Gui.addMessage("World Saved!");
	}

	public static void load(int num) throws IOException {
		File f = new File(Util.getDataPath() + File.separator + "world" + num + ".jmp");
		if (!f.exists()) {
			System.out.println("Could not find file: " + f.getAbsolutePath());
			return;
		}

		FileInputStream fis = new FileInputStream(f);
		byte[] compressed = new byte[(int) f.length()];
		fis.read(compressed);
		fis.close();
		
		byte[] decompressed = null;
		try {
			decompressed = Util.decompress(compressed);
		} catch (DataFormatException e) {
			e.printStackTrace();
			Gui.addMessage("Failed to decompress bytes");
			return;
		}
		
		// Decompressed
		DataInputStream in = new DataInputStream(new ByteArrayInputStream(decompressed));
		int pre = in.readInt();
		if (pre != PREFIX) {
			System.out.println("Invalid World Prefix. Cancelling World Load Process!");
			in.close();
			return;
		}
		int ver = in.readShort();
		if (ver != WORLDSAVE_VERSION) {
			Gui.addMessage("Invalid World Version!");
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
				Jomapat.game.getWorld().setBlock(x, y, id != (byte)0xFF ? BlockType.values()[id] : null);
					
			}
		}
		Jomapat.game.getPlayer().setX(pX);
		Jomapat.game.getPlayer().setY(pY);

		in.close();
		Gui.addMessage("World Loaded!");
	}

}