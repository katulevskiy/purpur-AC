package letovo.server.warn;

import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.ArrayList;
public class CommandHandler implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("warn")){
            Plugin.LOGGER.info("warn command issued by " + sender);
            String nickname = args[0];

            // Read list of warned players from file
            ArrayList<ArrayList<String>> filedata = new ArrayList<ArrayList<String>>();
            try {
                File myObj = new File("warns.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    filedata.add(data);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            
            ArrayList <String> users = filedata[0];
            ArrayList <String> warns = filedata[1];

            int userwarns = 0;

            if(users.contais(nickname)){
                warns[users.indexOf(nickname)] += 1;
                userwarns = warns[users.indexOf(nickname)];
            }

            else{
                users.add(nickname);
                warns.add(1);
                userwarns += 1;
                // Add a warn to person
                try {
                    FileWriter myWriter = new FileWriter("warns.txt");
                    myWriter.write(users);
                    myWriter.write(warns);
                    myWriter.close();
                } catch (IOException e) {
                    Plugin.LOGGER.info("An error occurred while adding a person to warned list.");
                    e.printStackTrace();
                }
            }

            if (userwarns >= 5){
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                String command = "ban " + nickname + " [LETOVO ANTICHEAT] SKILL ISSUE";
                Bukkit.dispatchCommand(console, command);
            }

            return true;
        }

        else{
            LOGGER.info("command " + cmd + " not recognized");
        }

        return false;

    }

}
