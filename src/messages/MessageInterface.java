package messages;

/**
 * Created by Marko on 28-Apr-15.
 * <p>
 * Every message should implement this. It is used to invoke
 * action for specific message.
 */
public interface MessageInterface {
    void handle();
}
