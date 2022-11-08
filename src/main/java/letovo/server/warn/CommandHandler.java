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
import java.util.Arrays;
public class CommandHandler implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("warn")){
            Plugin.LOGGER.info("warn command issued by " + sender);
            String nickname = args[0];

            // Read list of warned players from file
            ArrayList<String> filedata = new ArrayList<String>();

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
            
            String[] users = filedata.get(0).split(", ");
            String[] warnsStringArray = filedata.get(1).split(", ");
            int[] warns = new int[warnsStringArray.length];
            for (int i = 0; i < warnsStringArray.length; i++){
                warns[i] = Integer.parseInt(warnsStringArray[i]);
            }

            int userwarns = 0;

            if(Arrays.asList(users).contains(nickname)){
                warns[Arrays.asList(users).indexOf(nickname)] = warns[Arrays.asList(users).indexOf(nickname)] + 1;
                userwarns = warns[Arrays.asList(users).indexOf(nickname)];
            }

            // TODO: Fix the addition of user to the list of warnings

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


            // TODO: Issue with Bukkit command sending

            if (userwarns >= 5){
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                String command = "ban " + nickname + " [LETOVO ANTICHEAT] SKILL ISSUE";
                Bukkit.dispatchCommand(console, command);
            }

            return true;
        }

        else{
            Plugin.LOGGER.info("command " + cmd + " not recognized");
        }

        return false;

    }

}
