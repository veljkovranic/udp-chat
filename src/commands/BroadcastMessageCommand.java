package commands;

import communication.RainbowConsole;
import engine.Constants;
import engine.User;

/**
 * Created by Marko on 28-Apr-15.
 */
public class BroadcastMessageCommand implements CommandInterface {
    private User owner;
    private String message;

    public BroadcastMessageCommand(String message, User owner) {
        this.owner = owner;
        this.message = Constants.MESSAGE_PUBLIC_PREFIX + message;
    }

    @Override
    public void handle() {
        owner.getMessageSender().broadcastMessage(message);
    }
}
