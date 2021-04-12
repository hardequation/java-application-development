package ooaddemo.message;

public class ByteMessage implements DecoratingMessage {
    private byte body;

    public ByteMessage(byte body) {
        this.body = body;
    }

    /**
     * JavaBeans -> POJO
     * OCP
     */

    @Override
    public String getDecoratedMessage() {
        return "primitive: " + body;
    }

    @Override
    public void add(Object message) {
        this.body += ((IntegerMessage) message).getBody();
    }

    @Override
    public boolean shouldFlush(Object message) {
        return this.getClass() != message.getClass();
    }

}
