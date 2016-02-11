package engine;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import commands.CommandInterface;
import commands.ExitCommand;
import communication.MessageReceiver;
import communication.MessageSender;
import communication.RainbowConsole;
import parser.Parser;
import threads.BroadcastAcceptorThread;
import threads.DirectAcceptorThread;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Marko on 28-Apr-15.
 * <p>
 * Class represents one client in chat. Each client executes in three threads:
 * - First thread is used for accepting direct (private) messages.
 * - Second thread is used for accepting broadcast messages.
 * - Third (main) thread execute user's commands in console, this includes
 * sending/broadcasting messages
 */
public class User {
    private UserContact userContact;

    // This socket is used for accepting direct (private) messages
    private DatagramSocket directSocket;
    // This socket is used for sending/receiving broadcast messages
    private MulticastSocket broadcastSocket;
    // This socket is user-controlled socket for sending messages
    private DatagramSocket mainSocket;

    private MessageSender messageSender;
    private MessageReceiver messageReceiver;

    // List of contacts. User can send private messages to contacts from this list.
    private HashMap contacts;

    public volatile boolean chatRunning;

    public volatile boolean pmOnly=false;
    public volatile UserContact favouriteUser=null; //New variables. Should i change it to private and add get/set methods?

    public User() {
        userContact = new UserContact();
        userContact.setUsername(Constants.UNKNOWN_USER);
        contacts = new HashMap();
        chatRunning = true;
        // Initialise sockets
        try {
            directSocket = new DatagramSocket();
            directSocket.setSoTimeout(Constants.RECEIVE_TIMEOUT);
            mainSocket = new DatagramSocket();
            mainSocket.setSoTimeout(Constants.RECEIVE_TIMEOUT);
            broadcastSocket = new MulticastSocket(Constants.BROADCAST_PORT);
            broadcastSocket.setSoTimeout(Constants.RECEIVE_TIMEOUT);
            broadcastSocket.joinGroup(Constants.BROADCAST_GROUP);
        } catch (Exception e) {
            new ExitCommand(this).handle();
            e.printStackTrace();
        }
        messageSender = new MessageSender(mainSocket, broadcastSocket);
        messageReceiver = new MessageReceiver(directSocket, broadcastSocket);
        // Get constants from sockets
        userContact.setPort(directSocket.getLocalPort());
        // TODO (Lazar): Change localhost address to real address
        userContact.setIpAddress(Constants.LOCALHOST_ADDRESS);
        // Run threads
        new Thread(new DirectAcceptorThread(this)).start();
        new Thread(new BroadcastAcceptorThread(this)).start();
        // Main thread
        startChat();
    }

    public void stopChat() {
        chatRunning = false;
        // This will stop two background threads too
    }

    private void startChat() {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        while (chatRunning) {
            String command = sc.nextLine();
            CommandInterface c = parser.parseCommand(command, this);
            c.handle();
        }
        sc.close();
    }

    public void setUsername(String username) {
        this.userContact.setUsername(username);
    }

    public void addContact(UserContact contact) {
        contacts.put(contact.getUsername(), contact);
    }

    public UserContact getContactByName(String username) {
        return (UserContact)contacts.get(username);
    }

    public void removeContact(String username) {
        contacts.remove(username);
    }

    public HashMap getContacts() {
        return contacts;
    }

    public DatagramSocket getMainSocket() {
        return mainSocket;
    }

    public MessageSender getMessageSender() {
        return messageSender;
    }

    public MessageReceiver getMessageReceiver() {
        return messageReceiver;
    }

    public UserContact getUserContact() {
        return userContact;
    }
}
