package communication;

import engine.Constants;

import java.io.IOException;
import java.net.*;

/**
 * Created by Marko on 28-Apr-15.
 * <p>
 * Each user have two MessageSender-s. One of them is used from main thread to send
 * messages that user type. The other one is used in background (receiver) thread, to
 * send responses for some protocol messages like request for contact list.
 */
public class MessageSender {
    private DatagramSocket datagramSocket;
    private MulticastSocket multicastSocket;

    public MessageSender(DatagramSocket datagramSocket, MulticastSocket multicastSocket) {
        this.datagramSocket = datagramSocket;
        this.multicastSocket = multicastSocket;
    }

    // Sends message to specific ipAddress:port
    public void sendMessage(String message, InetAddress ipAddress, int port) {
        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
        try {
            datagramSocket.send(sendPacket);
        } catch (IOException e) {
            // TODO (marko): Notify user with system message
        }
    }

    // Broadcast message to broadcast group defined in class Constants
    public void broadcastMessage(String message) {
        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
                Constants.BROADCAST_GROUP, Constants.BROADCAST_PORT);
        try {
            multicastSocket.send(sendPacket);
        } catch (IOException e) {
            // TODO (marko): Notify user with system message
        }
    }


}
