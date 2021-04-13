package ooaddemo.message;

public class ByteMessage implements DecoratingMessage {
    private byte body;
    public static final String PRIMITIVE_PREFIX = "primitive: ";

    public ByteMessage(byte body) {
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
        this.body += ((ByteMessage) message).getBody();
    }

    @Override
    public boolean shouldFlush(Object message) {
        if (this.getClass() != message.getClass()) {
            return true;
        } else {
            return ((ByteMessage) message).getBody() + this.body > Byte.MAX_VALUE;
        }
    }

}
