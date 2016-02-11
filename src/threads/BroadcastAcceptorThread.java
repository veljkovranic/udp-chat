package threads;

import commands.CommandInterface;
import engine.User;
import messages.BroadcastMessage;
import messages.IgnoreMessage;
import messages.MessageInterface;
import parser.Parser;

import java.io.IOException;
import java.net.DatagramPacket;

/**
 * Created by Marko on 28-Apr-15.
 * <p>
 * This class is run in background thread. Accept packages broadcast from
 * other users and handle them.
 */
public class BroadcastAcceptorThread implements Runnable {
    private User owner;

    public BroadcastAcceptorThread(User owner) {
        this.owner = owner;
    }

    @Override
    public void run() {
        Parser parser = new Parser();
        while (owner.chatRunning) {

            String msg = owner.getMessageReceiver().acceptBroadcastMessage();
            MessageInterface i = parser.parseMessage(msg, owner);
            if (owner.pmOnly) i = new IgnoreMessage();
            i.handle();

        }
    }
}
