package me.inplex.jomapat;

public class WorldGenerator {



	public static World generateWorld(int width,int height){
		World world = new World(width, height);
		Block grass = new BlockGrass();
		Block dirt = new BlockDirt();

		dirt.load();
		grass.load();

		for (int x=0;x<=width-1;x++){
			for (int y=0;y<=height-1;y++){
              world.setBlock(x,y,dirt);
			}
		}
		return world;
	}

}