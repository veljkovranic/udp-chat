package messages;

import communication.RainbowConsole;

/**
 * Created by Lazar on 09-May-15.
 */
public class InvalidMessage implements MessageInterface {
    public InvalidMessage() {
    }

    @Override
    public void handle() {
        RainbowConsole.systemPrint("INVALID COMMAND!");
    }
}
