package communication;

import engine.Constants;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;

/**
 * Created by Marko on 28-Apr-15.
 */
public class MessageReceiver {
    private DatagramSocket datagramSocket;
    private MulticastSocket multicastSocket;

    public MessageReceiver(DatagramSocket datagramSocket, MulticastSocket multicastSocket) {
        this.datagramSocket = datagramSocket;
        this.multicastSocket = multicastSocket;
    }

    public String acceptDirectMessage() {
        byte[] buf = new byte[Constants.BUFFER_SIZE];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        try {
            datagramSocket.receive(packet);
        } catch (Exception e) {
            // TODO (Lazar): implement this exception
            //e.printStackTrace();
        }
        String message = new String(packet.getData()).trim();
        return message;
    }

    public String acceptBroadcastMessage() {
        byte[] buf = new byte[Constants.BUFFER_SIZE];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        try {
            multicastSocket.receive(packet);
        } catch (Exception e) {

            // TODO (Lazar): implement this exception
            //e.printStackTrace();
        }
        String message = new String(packet.getData()).trim();
        return message;
    }
}
