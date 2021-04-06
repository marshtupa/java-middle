package edu.practice.java.middle.concurrency.matrix;

import java.util.Arrays;
import java.util.Optional;

public class MatrixValidator {

    public void validateMatrices(Matrix matrixA, Matrix matrixB) {
        validateMatrix(matrixA);
        validateMatrix(matrixB);

        if (matrixA.getWeight() != matrixB.getHeight()) {
            throw new IllegalStateException("Matrices cannot be multiplied");
        }
    }

    public void validateMatrix(Matrix matrix) {
        Optional<int[]> invalidRow = Arrays.stream(matrix.getMatrix())
                .filter(row -> row.length != matrix.getWeight())
                .findFirst();

        invalidRow.ifPresent(row -> {
            throw new IllegalStateException("Invalid row in matrix " + Arrays.toString(row));
        });
    }

}
