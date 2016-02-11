package commands;

import communication.RainbowConsole;
import engine.Constants;
import engine.User;
import engine.UserContact;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.SocketTimeoutException;

/**
 * Created by Marko on 28-Apr-15.
 */
public class RegistrationCommand implements CommandInterface {
    private User owner;
    private String username;

    public RegistrationCommand(String username, User owner) {
        this.owner = owner;
        this.username = username;
    }

    @Override
    public void handle() {
        if (!owner.getUserContact().getUsername().equals(Constants.UNKNOWN_USER)) {
            RainbowConsole.systemPrint("Username already set!");
            return;
        }
        // TODO (Lazar): Change localhost address to real address
        UserContact fakeUserContact = new UserContact(username, Constants.LOCALHOST_ADDRESS,
                owner.getMainSocket().getLocalPort());
        owner.getMessageSender().broadcastMessage(Constants.REGISTRATION_TRY_PREFIX +
                fakeUserContact.toString());
        // [Lazar] The code below is kinda hack.
        // I pasted code for socket receiver.
        byte[] buf = new byte[Constants.BUFFER_SIZE];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        try {
            owner.getMainSocket().receive(packet);
        } catch (IOException e) {
            //e.printStackTrace();
        }
        String message = new String(packet.getData()).trim();
        if (message != null && !message.isEmpty()) {
            RainbowConsole.systemPrint("Username is in use");
            return;
        }
        owner.setUsername(username);
        owner.getMessageSender().broadcastMessage(Constants.REGISTRATION_SUCCESS_PREFIX +
                owner.getUserContact().toString());
        new RegistrationPullCommand(owner).handle();

    }
}
