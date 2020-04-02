package fr.lennydu91.btto.mysql;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	/** Objet DataBase */
	private static DataBase bdd1 = new DataBase("127.0.0.1:3306", "test-plugin", "test-plugin", "mot de passe"); // IP | BDD | USER | MDP
	
	public void onEnable(){
		bdd1.connection();
	}
	
	public void onDisable(){
		bdd1.disconnection();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("getstats")) { // Si c'est la commande "getstats" qui a été tapée:
			Boolean presentBdd = bdd1.isInBdd("SELECT * FROM stats WHERE user = '" + sender.getName() + "'");
			if (!presentBdd) {
				sender.sendMessage("Il n'y est pas ! Je l'installe tout de suite :)");
				bdd1.sendRequest("INSERT INTO `stats` (`user`, `stone_m`, `stone_p`, `dirt_m`, `dirt_p`) VALUES ('" + sender.getName() + "', '0', '0', '0', '0')");
			} else {
				sender.sendMessage("Il y est !");
			}
			//sender.sendMessage("Bonjour !");
			//String stats = bdd.getString("SELECT * FROM stats WHERE dirt_m = 17;", "user");
			//sender.sendMessage("La réponse est : " + stats);
			return true;
		} else if (cmd.getName().equalsIgnoreCase("site-web")) { // Si c'est la commande "site-web" qui a été tapée:
			
		return true;
	}
		return false;
	}
}
