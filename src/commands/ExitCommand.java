package commands;

import communication.RainbowConsole;
import engine.Constants;
import engine.User;

/**
 * Created by Marko on 28-Apr-15.
 */
public class ExitCommand implements CommandInterface {
    private User owner;

    public ExitCommand(User owner) {
        this.owner = owner;
    }

    @Override
    public void handle() {
        if (!owner.getUserContact().getUsername().equals(Constants.UNKNOWN_USER)) {
            owner.getMessageSender().broadcastMessage(Constants.REGISTRATION_OUT_PREFIX + owner.getUserContact().toString());
        }
        owner.stopChat();
    }
}
