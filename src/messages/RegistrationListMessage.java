package messages;

import engine.User;
import engine.UserContact;

import java.util.HashMap;

/**
 * Created by Lazar on 09-May-15.
 */
public class RegistrationListMessage implements MessageInterface {
    private User owner;
    HashMap<String, UserContact> contacts;

    public RegistrationListMessage(User owner, HashMap<String, UserContact> contacts) {
        this.owner = owner;
        this.contacts = contacts;
    }

    @Override
    public void handle() {
        for (UserContact c : contacts.values()) {
            owner.addContact(c);
        }
    }
}
