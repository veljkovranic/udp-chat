package messages;

import engine.Constants;
import engine.User;
import engine.UserContact;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Lazar on 09-May-15.
 */
public class RegistrationPullMessage implements MessageInterface {
    private User owner;
    private UserContact sender;

    public RegistrationPullMessage(User owner, UserContact sender) {
        this.owner = owner;
        this.sender = sender;
    }

    @Override
    public void handle() {
        if (owner.getUserContact().getUsername().equals(sender.getUsername()) && owner.getUserContact().getPort() == sender.getPort()) {
            return;
        }
        if (owner.getUserContact().getUsername().equals(Constants.UNKNOWN_USER)) {
            return;
        }
        StringBuffer sb = new StringBuffer();
        HashMap map = owner.getContacts();
        for (Object c : map.values()) {
            sb.append(c.toString()).append("/");
        }
        owner.getMessageSender().sendMessage(Constants.REGISTRATION_LIST_PREFIX + sb.toString(),
                sender.getIpAddress(), sender.getPort());
    }
}
