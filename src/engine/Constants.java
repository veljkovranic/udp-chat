package engine;

import javax.print.DocFlavor;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Marko on 28-Apr-15.
 * <p>
 * All constants used in application should be placed and accessed from this class.
 */
public class Constants {
    // Users should wait for message that is being broadcast on this port
    public static final int BROADCAST_PORT = 7777;
    // Messages are being broadcast inside of this group
    public static InetAddress BROADCAST_GROUP;
    // Localhost address
    public static InetAddress LOCALHOST_ADDRESS;

    // String representation of unknown user
    public static final String UNKNOWN_USER = "unknown";
    // Each username should match this
    public static final String USERNAME_REGEX = "[a-zA-Z0-9_.]+";

    // Command constants
    public static final String REGISTRATION_TAG = "/r";
    public static final String REGISTRATION_PULL_TAG = "/rpull";
    public static final String REGISTRATION_OUT_TAG = "/rout";
    public static final String PRIVATE_TAG = "/p";
    public static final String PRIVATE_ONLY_TAG = "/ponly";
    public static final String PRIVATE_ONLY_FROM_TAG = "/ponlyfrom";
    public static final String PRIVATE_OFF_TAG = "/poff";
    public static final String HELP_TAG = "/help";
    public static final String EXIT_TAG = "/exit";

    // Message constants
    public static final String MESSAGE_PUBLIC_PREFIX = "message/public/";
    public static final String MESSAGE_PRIVATE_PREFIX = "message/private/";
    public static final String REGISTRATION_TRY_PREFIX = "registration/try/";
    public static final String REGISTRATION_DENIED_PREFIX = "registration/denied/";
    public static final String REGISTRATION_SUCCESS_PREFIX = "registration/success/";
    public static final String REGISTRATION_PULL_PREFIX = "registration/pull/";
    public static final String REGISTRATION_LIST_PREFIX = "registration/list/";
    public static final String REGISTRATION_OUT_PREFIX = "registration/out/";

    // Different types of messages and colors, just for printing in console
    public static final String SYSTEM_NOTIFICATION = "[SYSTEM]";
    public static final String MESSAGE_BROADCAST_NOTIFICATION = "[BROADCAST]";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m"; // try to avoid this one
    public static final String ANSI_RED = "\u001B[31m"; // Already used for dumping exception stack trace
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m"; // try to avoid this one

    // Size of socket receiving buffer
    public static final int BUFFER_SIZE = 5555;

    // Message receiving timeout in milliseconds
    public static final int RECEIVE_TIMEOUT = 1000;

    static {
        try {
            // This is some usual multicast address
            BROADCAST_GROUP = InetAddress.getByName("230.0.0.1");
            LOCALHOST_ADDRESS = InetAddress.getLocalHost();
        } catch (UnknownHostException ignored) {
        }
    }
}
