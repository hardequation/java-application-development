package ooaddemo.message;

import java.util.Objects;

/**
 * Record = immutable POJO
 */
public class StringMessage implements DecoratingMessage {
    private final String body;
    private int duplicateMessageCount;
    public static final String STRING_PREFIX = "string: ";

    public StringMessage(String body) {
        duplicateMessageCount = 1;
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringMessage that = (StringMessage) o;
        return Objects.equals(getBody(), that.getBody());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBody());
    }

    @Override
    public String toString() {
        return "StringMessage{" +
                "body='" + body + '\'' +
                '}';
    }

    /**
     * JavaBeans -> POJO
     * OCP
     */
    public String getBody() {
        return body;
    }

    public boolean shouldFlush(Object message) {
        if (this.getClass() != message.getClass()) {
            return true;

        } else {
            return !((StringMessage) message).getBody().equals(this.getBody());
        }
    }

    @Override
    public String getDecoratedMessage() {
        String postfix = "";

        if (duplicateMessageCount > 1) {
            postfix = " (x" + duplicateMessageCount + ")";
        }
        return STRING_PREFIX + body + postfix;
    }

    @Override
    public void add(Object newMessage) {
        duplicateMessageCount++;
    }

}
