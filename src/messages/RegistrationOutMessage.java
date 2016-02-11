package messages;

import engine.Constants;
import engine.User;
import engine.UserContact;

/**
 * Created by Lazar on 09-May-15.
 */
public class RegistrationOutMessage implements MessageInterface {
    private User owner;
    private UserContact sender;

    public RegistrationOutMessage(User owner, UserContact sender) {
        this.owner = owner;
        this.sender = sender;
    }

    @Override
    public void handle() {
        owner.removeContact(sender.getUsername());
    }
}
