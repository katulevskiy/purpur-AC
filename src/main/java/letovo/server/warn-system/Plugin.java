package letovo.server;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * warn-system java plugin
 */
public class Plugin extends JavaPlugin
{
  private CommandHandler commandHandler;

  public static final Logger LOGGER=Logger.getLogger("warn-system");

  public void onEnable()
  {
    LOGGER.info("warn-system enabled");
    commandHandler = new CommandHandler();
    getCommand("warn").setExecutor(commandHandler);
  }

  public void onDisable()
  {
    LOGGER.info("warn-system disabled");
  }
}
