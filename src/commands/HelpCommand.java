package commands;

import communication.RainbowConsole;
import engine.Constants;

/**
 * Created by Marko on 28-Apr-15.
 */
public class HelpCommand implements CommandInterface {
    @Override
    public void handle() {
        RainbowConsole.systemPrint("This is help menu. Available options are:");
        RainbowConsole.systemPrint(" '/r username' - let you pick your username");
        RainbowConsole.systemPrint(" '/rpull' - manualy gets list of all users");
        RainbowConsole.systemPrint(" '/rout' - user becomes anonymous");
        RainbowConsole.systemPrint(" '/p username message' - sends private message to given user");
        RainbowConsole.systemPrint(" '/ponly' - shows only private messages");
        RainbowConsole.systemPrint(" '/ponlyfrom username' - shows only private messages from given user");
        RainbowConsole.systemPrint(" '/poff' - reset action for two previous commands");
        RainbowConsole.systemPrint(" '/exit' - turns off chat system");
        RainbowConsole.systemPrint(" '/help' - gets you here !");
    }
}
