package commands;

import communication.RainbowConsole;
import engine.Constants;

/**
 * Created by Marko on 28-Apr-15.
 */
public class InvalidCommand implements CommandInterface {
    @Override
    public void handle() {
        RainbowConsole.systemPrint("Invalid command. Use /help to get help.");
    }
}
