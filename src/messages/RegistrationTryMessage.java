package messages;

import engine.Constants;
import engine.User;
import engine.UserContact;

/**
 * Created by Lazar on 09-May-15.
 */
public class RegistrationTryMessage implements MessageInterface {
    private User owner;
    private UserContact sender;

    public RegistrationTryMessage(User owner, UserContact sender) {
        this.owner = owner;
        this.sender = sender;
    }

    @Override
    public void handle() {
        // This is special case where we pick local port of main socket
        if (owner.getUserContact().getUsername().equals(sender.getUsername()) && owner.getMainSocket().getLocalPort() == sender.getPort()) {
            return;
        }
        if (owner.getUserContact().getUsername().equals(sender.getUsername())) {
            owner.getMessageSender().sendMessage(Constants.REGISTRATION_DENIED_PREFIX,
                    sender.getIpAddress(), sender.getPort());
        }
    }
}
