package commands;

import communication.RainbowConsole;
import engine.Constants;

/**
 * Created by Marko on 28-Apr-15.
 */
public class UnknownUserCommand implements CommandInterface {
    @Override
    public void handle() {
        RainbowConsole.systemPrint("Unknown username specified. Try /rpull.");
    }
}
