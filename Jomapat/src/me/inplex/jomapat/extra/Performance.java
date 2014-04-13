package me.inplex.jomapat.extra;

import javax.swing.JOptionPane;

import me.inplex.jomapat.Jomapat;

public class Performance {

	public static void handlePerformance() {

		boolean showPrc = true;
		showPrc |= Runtime.getRuntime().maxMemory() < 300000000;
		showPrc |= Runtime.getRuntime().availableProcessors() < 3;

		if (showPrc) {
			int result = JOptionPane.showConfirmDialog(null, "Your computer seems to run slowly. Activate low performance mode?",
					"low performance mode", JOptionPane.YES_NO_OPTION);
			Jomapat.minimalMode = (result == JOptionPane.YES_OPTION);
		}

	}

}
