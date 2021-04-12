package ooaddemo.message;

public class StringMessage implements DecoratingMessage {
    private String body;
    private int duplicateMessageCount;
    public static final String STRING_PREFIX = "string: ";

    public StringMessage(String body) {
        duplicateMessageCount = 1;
        this.body = body;
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
