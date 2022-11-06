

public class CommandHandler implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("warn")){
            LOGGER.info("warn command issued by " + sender);
            return true;
        }

        return false;

    }

}
