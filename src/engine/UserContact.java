package engine;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Marko on 28-Apr-15.
 * <p>
 * Store username, ipAddress and port of other registered users.
 */
public class UserContact {
    private String username;
    private InetAddress ipAddress;
    private int port;

    public UserContact() {

    }

    public UserContact(String username, InetAddress ipAddress, int port) {
        this.username = username;
        this.ipAddress = ipAddress;
        this.port = port;
    }

    // Deserialization
    public UserContact(String s) {
        String[] parts = s.split(":");
        username = parts[0];
        try {
            ipAddress = InetAddress.getByName(parts[1]);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        port = Integer.parseInt(parts[2]);
    }

    // Serialization
    @Override
    public String toString() {
        return username + ":" + ipAddress.getHostAddress() + ":" + port;
    }

    public String getUsername() {
        return username;
    }

    public InetAddress getIpAddress() {
        return ipAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setIpAddress(InetAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

}
