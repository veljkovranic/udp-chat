package messages;

import communication.RainbowConsole;
import engine.UserContact;

/**
 * Created by Lazar on 09-May-15.
 */
public class DirectMessage implements MessageInterface {
    private String message;
    private UserContact sender;

    public DirectMessage(String message, UserContact sender) {
        this.message = message;
        this.sender = sender;
    }

    public UserContact getSender() {
        return sender;
    }

    @Override
    public void handle() {
        RainbowConsole.directMessagePrint(message, sender.getUsername());
    }
}
