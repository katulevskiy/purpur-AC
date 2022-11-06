package letovo.server;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import CommandHandler;

public class Plugin extends JavaPlugin
{
  private CommandHandler commandHandler;

  public static final Logger LOGGER=Logger.getLogger("warn-system");

  public void onEnable()
  {

    // Create warns database if does not exist on the server startup
    try {
      File myObj = new File("warns.txt");
      if (myObj.createNewFile()) {
        LOGGER.info("File created: " + myObj.getName());
      } else {
        LOGGER.info("File already exists.");
      }
    } catch (IOException e) {
      LOGGER.info("An error occurred in file creation (warns.txt).");
      e.printStackTrace();
    }

    // Assign command handler to custom class
    LOGGER.info("warn-system enabled");
    commandHandler = new CommandHandler();
    getCommand("warn").setExecutor(commandHandler);
  }

  public void onDisable()
  {
    LOGGER.info("warn-system disabled");
  }
}
