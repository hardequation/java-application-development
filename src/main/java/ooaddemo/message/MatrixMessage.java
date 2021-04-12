package ooaddemo.message;

import java.util.Arrays;


public class MatrixMessage implements DecoratingMessage {
    private static final String MATRIX_PREFIX = "primitives matrix: ";

    private int[][] body;

    public MatrixMessage(int[][] body) {
        this.body = body;
    }


    @Override
    public String getDecoratedMessage() {
        StringBuilder result = new StringBuilder();
        result.append(MATRIX_PREFIX);
        result.append("{");
        result.append(System.lineSeparator());
        for (int[] elem : body) {
            result.append(formatArray(elem));
        }
        result.append("}");
        return result.toString();
    }

    private String formatArray(int[] elem) {
        return Arrays.toString(elem).replace('[', '{').replace(']', '}') + (System.lineSeparator());
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
