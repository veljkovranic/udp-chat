package commands;

import communication.RainbowConsole;
import engine.Constants;
import engine.User;

/**
 * Created by Marko on 28-Apr-15.
 */
public class PrivateOffCommand implements CommandInterface {
    private User owner;

    public PrivateOffCommand(User owner) {
        this.owner = owner;
    }

    @Override
    public void handle() {
        owner.pmOnly = false;
        owner.favouriteUser = null;
    }
}
