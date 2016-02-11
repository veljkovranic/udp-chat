package communication;

import engine.Constants;

/**
 * Created by Lazar on 09-May-15.
 */
public class RainbowConsole {

    public static void systemPrint(String message) {
        System.out.println(Constants.ANSI_BLACK + Constants.SYSTEM_NOTIFICATION + " " + message + Constants.ANSI_RESET);
    }

    public static void broadcastMessagePrint(String message) {
        System.out.println(Constants.ANSI_YELLOW + Constants.MESSAGE_BROADCAST_NOTIFICATION + " " +
                message + Constants.ANSI_RESET);
    }

    public static void directMessagePrint(String message, String username) {
        System.out.println(Constants.ANSI_PURPLE + "[" + username + "]" + " " + message + Constants.ANSI_RESET);
    }

    public static void debugPrint(String message) {
        System.out.println(Constants.ANSI_BLUE + message + Constants.ANSI_RESET);
    }

}
