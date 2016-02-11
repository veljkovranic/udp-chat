package messages;

import communication.RainbowConsole;
import engine.User;
import engine.UserContact;

/**
 * Created by Lazar on 09-May-15.
 */
public class RegistrationSuccessMessage implements MessageInterface {
    private User owner;
    private UserContact sender;

    public RegistrationSuccessMessage(User owner, UserContact sender) {
        this.owner = owner;
        this.sender = sender;
    }

    @Override
    public void handle() {
        if (owner.getUserContact().getUsername().equals(sender.getUsername()) && owner.getUserContact().getPort() == sender.getPort()) {
            return;
        }
        owner.addContact(sender);
    }
}
