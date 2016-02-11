package commands;

import communication.RainbowConsole;
import engine.Constants;
import engine.User;
import engine.UserContact;

/**
 * Created by Marko on 28-Apr-15.
 */
public class PrivateOnlyFromCommand implements CommandInterface {
    private User owner;
    private UserContact contact;

    public PrivateOnlyFromCommand(User owner, UserContact userContact) {
        this.owner = owner;
        this.contact = userContact;
    }

    @Override
    public void handle() {
        if (owner.getUserContact().getUsername().equals(contact.getUsername())) {
            RainbowConsole.debugPrint("Invalid command.s");
            return;
        }
        owner.favouriteUser = contact;
        owner.pmOnly = true;
    }
}
