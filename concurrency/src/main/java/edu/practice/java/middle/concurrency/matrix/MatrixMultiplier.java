package edu.practice.java.middle.concurrency.matrix;

public class MatrixMultiplier {

    private final MatrixValidator validator;

    private final MatrixReader reader;

    private final MatrixWriter writer;

    public void multiplyMatrix(String matrixAFileName, String matrixBFileName) {
        Matrix matrixA = reader.readMatrices(matrixAFileName);
        Matrix matrixB = reader.readMatrices(matrixBFileName);

        validator.validateMatrices(matrixA, matrixB);

        Matrix result = multiplyMatrix(matrixA, matrixB);
        writer.writeMatrix(result);
    }

    public Matrix multiplyMatrix(Matrix matrixA, Matrix matrixB) {
        Matrix result = new Matrix(matrixA.getHeight(), matrixB.getWeight());
        for (int row = 0; row < matrixA.getHeight(); row++) {
            for (int column = 0; column < matrixB.getWeight(); column++) {
                dotProduct(matrixA, matrixB, row, column, result);
            }
        }
        return result;
    }

    public Void dotProduct(Matrix matrixA, Matrix matrixB, int row, int column, Matrix matrix) {
        int result = 0;

        for (int i = 0; i < matrixA.getWeight(); i++) {
            result += matrixA.getElement(row, i) * matrixB.getElement(i, column);
        }
        matrix.getMatrix()[row][column] = result;
        return null;
    }

    public MatrixMultiplier(MatrixValidator validator, MatrixReader reader, MatrixWriter writer) {
        this.validator = validator;
        this.reader = reader;
        this.writer = writer;
    }
}
