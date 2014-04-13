package me.inplex.jomapat.extra;

import javax.swing.JOptionPane;

import me.inplex.jomapat.Jomapat;

public class Performance {
	
	public static void handlePerformance(){

		boolean showPrc = false;
		if (Runtime.getRuntime().maxMemory()<300000000){showPrc = true;}
		if (Runtime.getRuntime().availableProcessors()<3){showPrc = true;}
		
		if (showPrc){
		int result = JOptionPane.showConfirmDialog(null, "Das System scheint langsam zu arbeiten. Low Performace Modus aktivieren ?",
				"", JOptionPane.YES_NO_OPTION);
		
		Jomapat.game.minimalMode = (result==JOptionPane.YES_OPTION) ? true : false;
		
		}
		
	}

}
