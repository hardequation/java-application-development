package ooaddemo.message;

import java.util.Arrays;

public class ArrayMessage implements DecoratingMessage {
    private int[] body;

    public static final String ARRAY_PREFIX = "primitives array: ";
    public ArrayMessage(int[] body) {
        this.body = body;
    }


    @Override
    public String getDecoratedMessage() {
        return ARRAY_PREFIX + Arrays.toString(body).replace('[', '{').replace(']', '}');
    }

    @Override
    @Deprecated
    public void add(Object message) {
        throw new RuntimeException("This operation is invalid");
    }

    @Override
    public boolean shouldFlush(Object message) {
        return true;
    }
}
