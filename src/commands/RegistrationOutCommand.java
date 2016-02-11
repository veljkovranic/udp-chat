package commands;

import engine.Constants;
import engine.User;

/**
 * Created by Marko on 28-Apr-15.
 */
public class RegistrationOutCommand implements CommandInterface {
    private User owner;

    public RegistrationOutCommand(User owner) {
        this.owner = owner;
    }

    @Override
    public void handle() {
        owner.getMessageSender().broadcastMessage(Constants.REGISTRATION_OUT_PREFIX + owner.getUserContact().toString());
    }
}
