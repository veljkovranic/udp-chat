package commands;

import engine.Constants;
import engine.User;

/**
 * Created by Marko on 28-Apr-15.
 */
public class RegistrationPullCommand implements CommandInterface {
    private User owner;

    public RegistrationPullCommand(User owner) {
        this.owner = owner;
    }

    @Override
    public void handle() {
        if (owner.getUserContact().getUsername().equals(Constants.UNKNOWN_USER)) {
            return;
        }
        owner.getMessageSender().broadcastMessage(Constants.REGISTRATION_PULL_PREFIX + owner.getUserContact().toString());
    }
}
