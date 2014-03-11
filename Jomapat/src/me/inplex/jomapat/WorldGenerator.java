package me.inplex.jomapat;

public class WorldGenerator {



	public static World generateWorld(int width,int height){
		World world = new World(width, height);
		Block grass = new BlockGrass();
		Block dirt = new BlockDirt();

		dirt.load();
		grass.load();

		int actualStartY = 2;

		for (int x=0;x<=width-1;x++){
			switch (Maths.randomize(1, 4))
			{
			case 1:break;
			case 2:case 3:actualStartY=actualStartY+1;break;
			case 4:       actualStartY=actualStartY-1;break;
			}

			if (actualStartY<0){actualStartY=0;}
			for (int y=actualStartY;y<=height-1;y++){

				
				if (y==actualStartY ){
					world.setBlock(x,y,grass);
				}else{
					world.setBlock(x,y,dirt);	
				}
				
				

			}
		}
	
	return world;
}

}
