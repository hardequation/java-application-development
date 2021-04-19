package ooaddemo.message;

public class IntegerMessage implements DecoratingMessage {
    private int body;
    public static final String PRIMITIVE_PREFIX = "primitive: ";

    public IntegerMessage(int body) {
        this.body = body;
    }

    /**
     * JavaBeans -> POJO
     * OCP
     */

    public int getBody() {
        return body;
    }

    @Override
    public String getDecoratedMessage() {
        return PRIMITIVE_PREFIX + body;
    }

    @Override
    public void add(Object message) {
        this.body += ((IntegerMessage) message).getBody();
    }

    @Override
    public boolean shouldFlush(Object message) {
        if (this.getClass() != message.getClass()) {
            return true;
        } else {
            return ((IntegerMessage) message).getBody() + new Long(this.body) > Integer.MAX_VALUE;
        }
    }
}