package messages;

import communication.RainbowConsole;

/**
 * Created by Lazar on 09-May-15.
 */
public class BroadcastMessage implements MessageInterface {
    private String message;

    public BroadcastMessage(String message) {
        this.message = message;
    }

    @Override
    public void handle() {
        RainbowConsole.broadcastMessagePrint(message);
    }
}
