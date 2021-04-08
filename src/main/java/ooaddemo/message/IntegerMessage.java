package ooaddemo.message;

public class IntegerMessage implements DecoratingMessage {
    private int body;

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

    public void setBody(int body) {
        this.body = body;
    }

    @Override
    public String getDecoratedMessage() {
        return "int: " + body;
    }
}
