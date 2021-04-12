package ooaddemo.message;

public interface DecoratingMessage {
    String getDecoratedMessage();

    void add(Object message);

    boolean shouldFlush(Object message);

}
