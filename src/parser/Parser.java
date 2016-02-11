package parser;

import commands.*;
import communication.RainbowConsole;
import engine.Constants;
import engine.User;
import engine.UserContact;
import messages.*;

import java.util.HashMap;

/**
 * Created by Marko on 28-Apr-15.
 */
public class Parser {
    public Parser() {
    }

    // Parse receive message
    public MessageInterface parseMessage(String message, User owner) {
        message = message.trim();
        String[] parts = message.split("/");
        if (parts.length < 2) {
            return new IgnoreMessage();
        }
        // TODO (Lazar): remove this debug print
        RainbowConsole.debugPrint(message);
        String messageHeader = parts[0] + "/" + parts[1] + "/";
        switch (messageHeader) {
            case Constants.MESSAGE_PRIVATE_PREFIX:
                if (parts.length < 3 || parts[2] == null || parts[2].isEmpty()) {
                    return new InvalidMessage();
                }
                return new DirectMessage(parts[3], new UserContact(parts[2]));
            case Constants.MESSAGE_PUBLIC_PREFIX:
                return new BroadcastMessage(parts[2]);
            case Constants.REGISTRATION_TRY_PREFIX:
                if (parts.length < 3 || parts[2] == null || parts[2].isEmpty()) {
                    return new InvalidMessage();
                }
                return new RegistrationTryMessage(owner, new UserContact(parts[2]));
            case Constants.REGISTRATION_OUT_PREFIX:
                if (parts.length < 3 || parts[2] == null || parts[2].isEmpty()) {
                    return new InvalidMessage();
                }
                return new RegistrationOutMessage(owner, new UserContact(parts[2]));
            case Constants.REGISTRATION_PULL_PREFIX:
                if (parts.length < 3 || parts[2] == null || parts[2].isEmpty()) {
                    return new InvalidMessage();
                }
                return new RegistrationPullMessage(owner, new UserContact(parts[2]));
            case Constants.REGISTRATION_SUCCESS_PREFIX:
                if (parts.length < 3 || parts[2] == null || parts[2].isEmpty()) {
                    return new InvalidMessage();
                }
                return new RegistrationSuccessMessage(owner, new UserContact(parts[2]));
            case Constants.REGISTRATION_LIST_PREFIX:
                if (parts.length < 3 || parts[2] == null || parts[2].isEmpty()) {
                    return new InvalidMessage();
                }
                HashMap<String, UserContact> contacts;
                contacts = new HashMap<>();
                for (int i = 2; i < parts.length; i++) {
                    UserContact newUser = new UserContact(parts[i]);
                    contacts.put(newUser.getUsername(), newUser);
                }
                return new RegistrationListMessage(owner, contacts);
            default: // No tag
                return new IgnoreMessage();
        }
    }

    // Parse user-typed command and generate CommandInterface to be handled
    public CommandInterface parseCommand(String command, User owner) {
        command = command.trim();
        if (command.isEmpty()) {
            return new IgnoreCommand();
        }
        String[] parts = command.split(" ");
        if (parts.length == 0) {
            return new IgnoreCommand();
        }
        StringBuffer sb = new StringBuffer();
        switch (parts[0]) {
            case Constants.REGISTRATION_TAG:
                if (parts.length != 2 || !parts[1].matches(Constants.USERNAME_REGEX)) {
                    return new InvalidCommand();
                }
                return new RegistrationCommand(parts[1], owner);
            case Constants.REGISTRATION_PULL_TAG:
                if (parts.length != 1) {
                    return new InvalidCommand();
                }
                return new RegistrationPullCommand(owner);
            case Constants.REGISTRATION_OUT_TAG:
                if (parts.length != 1) {
                    return new InvalidCommand();
                }
                return new RegistrationOutCommand(owner);
            case Constants.PRIVATE_TAG:
                if (parts.length < 3) {
                    return new InvalidCommand();
                }
                UserContact receiver = owner.getContactByName(parts[1]);
                if (receiver == null) {
                    return new UnknownUserCommand();
                }
                for (int i = 2; i < parts.length; i++) {
                    sb.append(parts[i] + " ");
                }
                return new PrivateMessageCommand(owner, receiver, sb.toString());
            case Constants.PRIVATE_ONLY_TAG:
                if (parts.length != 1) {
                    return new InvalidCommand();
                }
                return new PrivateOnlyCommand(owner);
            case Constants.PRIVATE_OFF_TAG:
                if (parts.length != 1) {
                    return new InvalidCommand();
                }
                return new PrivateOffCommand(owner);
            case Constants.PRIVATE_ONLY_FROM_TAG:
                if (parts.length != 2) {
                    return new InvalidCommand();
                }
                UserContact onlyfrom = owner.getContactByName(parts[1]);
                if (onlyfrom == null) {
                    return new UnknownUserCommand();
                }
                return new PrivateOnlyFromCommand(owner, onlyfrom);
            case Constants.HELP_TAG:
                if (parts.length != 1) {
                    return new InvalidCommand();
                }
                return new HelpCommand();
            case Constants.EXIT_TAG:
                if (parts.length != 1) {
                    return new InvalidCommand();
                }
                return new ExitCommand(owner);
            default: // No tag
                for (int i = 0; i < parts.length; i++) {
                    sb.append(parts[i] + " ");
                }
                return new BroadcastMessageCommand(sb.toString(), owner);
        }
    }
}
