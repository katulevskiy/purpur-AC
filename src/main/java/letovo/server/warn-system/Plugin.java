package letovo.server;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * warn-system java plugin
 */
public class Plugin extends JavaPlugin
{
  public static final Logger LOGGER=Logger.getLogger("warn-system");

  public void onEnable()
  {
    LOGGER.info("warn-system enabled");
  }

  public void onDisable()
  {
    LOGGER.info("warn-system disabled");
  }
}
