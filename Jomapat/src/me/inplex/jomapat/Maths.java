package me.inplex.jomapat;

import java.util.Random;

public class Maths {
	
	public static int randomize(int min, int max){
	    Random random = new Random();
	    return random.nextInt(max - min + 1) + min;
	  }
	
}
