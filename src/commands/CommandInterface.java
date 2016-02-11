package commands;

import engine.User;

/**
 * Created by Marko on 28-Apr-15.
 * <p>
 * Every command should implement this interface. It is used to invoke
 * action for specific command.
 */
public interface CommandInterface {
    void handle();
}
