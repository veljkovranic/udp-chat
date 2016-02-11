package commands;

import engine.Constants;
import engine.User;
import engine.UserContact;

/**
 * Created by Marko on 28-Apr-15.
 */
public class PrivateOnlyCommand implements CommandInterface {
    private User owner;

    public PrivateOnlyCommand(User owner) {
        this.owner = owner;
    }

    @Override
    public void handle() {
        owner.pmOnly = true;
    }
}
