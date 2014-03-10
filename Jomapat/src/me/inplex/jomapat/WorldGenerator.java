package me.inplex.jomapat;

public class WorldGenerator {
	

	
	public static void generateWorld(World world,int width,int height){
		Block grass = new BlockGrass();
		Block dirt = new BlockDirt();
		
		dirt.load();
		grass.load();
		
		for (int x=0;x<=width-1;x++){
			for (int y=0;y<=height-1;y++){
              world.setBlock(x,y,dirt);
			}
		}
	}

}
