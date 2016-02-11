package threads;

import engine.User;
import engine.UserContact;
import messages.DirectMessage;
import messages.IgnoreMessage;
import messages.MessageInterface;
import parser.Parser;

/**
 * Created by Marko on 28-Apr-15.
 * <p>
 * This class is run in background thread. Accept packages send to owner from
 * other users and handle them.
 */
public class DirectAcceptorThread implements Runnable {
    private User owner;

    public DirectAcceptorThread(User owner) {
        this.owner = owner;
    }

    @Override
    public void run() {
        Parser parser = new Parser();
        while (owner.chatRunning) {
            String msg = owner.getMessageReceiver().acceptDirectMessage();
            MessageInterface i = parser.parseMessage(msg, owner);
            if (i.getClass().getName() == "messages.DirectMessage") {
                DirectMessage tmp = (DirectMessage) i;
                if (owner.favouriteUser != null) {
                    i = new IgnoreMessage();
                    if (tmp.getSender().getUsername().equals(owner.favouriteUser.getUsername())) i = tmp;

                }
            }
            i.handle();
        }
    }
}
