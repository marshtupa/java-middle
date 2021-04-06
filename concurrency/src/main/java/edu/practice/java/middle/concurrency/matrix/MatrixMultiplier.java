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

    private Matrix multiplyMatrix(Matrix matrixA, Matrix matrixB) {
        Matrix result = new Matrix(matrixA.getHeight(), matrixB.getWeight());
        for (int rowCounter = 0; rowCounter < matrixA.getHeight(); rowCounter++) {
            int[] row = matrixA.getRow(rowCounter);
            for (int columnCounter = 0; columnCounter < matrixB.getWeight(); columnCounter++) {
                int[] column = matrixB.getColumn(columnCounter);
                result.getMatrix()[rowCounter][columnCounter] = dotProduct(row, column);
            }
        }
        return result;
    }

    private int dotProduct(int[] row, int[] column) {
        int result = 0;

        for (int i = 0; i < row.length; i++) {
            result += row[i] * column[i];
        }
        return result;
    }

    public MatrixMultiplier(MatrixValidator validator, MatrixReader reader, MatrixWriter writer) {
        this.validator = validator;
        this.reader = reader;
        this.writer = writer;
    }
}
