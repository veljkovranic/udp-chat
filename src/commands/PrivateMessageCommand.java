package commands;

import communication.RainbowConsole;
import engine.Constants;
import engine.User;
import engine.UserContact;

/**
 * Created by Marko on 28-Apr-15.
 */
public class PrivateMessageCommand implements CommandInterface {
    private User owner;
    private UserContact receiver;
    private String message;

    public PrivateMessageCommand(User owner, UserContact receiver, String message) {
        this.owner = owner;
        this.receiver = receiver;
        this.message = message;
    }

    @Override
    public void handle() {
        if (owner.getUserContact().getUsername().equals(Constants.UNKNOWN_USER)) {
            return;
        }
        owner.getMessageSender().sendMessage(Constants.MESSAGE_PRIVATE_PREFIX + owner.getUserContact().toString() + "/" + message,
                receiver.getIpAddress(), receiver.getPort());
    }
}
