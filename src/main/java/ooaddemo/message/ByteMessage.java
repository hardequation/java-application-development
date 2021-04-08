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
    public byte getBody() {
        return body;
    }

    public void setBody(byte body) {
        this.body = body;
    }

    @Override
    public String getDecoratedMessage() {
        return "primitive: " + body;
    }
}
