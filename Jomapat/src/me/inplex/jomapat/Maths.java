package me.inplex.jomapat;

import java.util.Random;

public class Maths {
	
	public static int randomize(int min, int max){
	    Random random = new Random();
	    return random.nextInt(max - min + 1) + min;
	  }
	
	
	//TODO optimize this method. really.
	public static int positionToGrid(int pos){
		int retVal = -1;
		for (int i=0;i<pos+128;i=i+64){
			if (i<= pos && pos-i<=63){
				retVal = i;
				break;
			}
		}
		return retVal;
	}
	//
	//public static boolean isVisible(int x,int y){
	//	return x
	//}
}
