package me.inplex.jomapat.extra;


public class CommandExecutor {

	public static void executeRaw(String cmd) {
		if (!cmd.contains(" ")) {
			execute(cmd, new String[] {});
			return;
		}
		String[] spaceSplit = cmd.split(" ");
		String command = spaceSplit[0];
		if(spaceSplit.length < 2) {
			execute(command, new String[] {});
			return;
		}
		String[] args = new String[spaceSplit.length-1];
		
		for (int i = 1; i < spaceSplit.length; i++) {
			args[i-1] = spaceSplit[i];
		}
		execute(command, args);
	}

	public static void execute(String cmd, String[] args) {
		// TODO: Implement commands here
	}

}