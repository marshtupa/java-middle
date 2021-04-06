package edu.practice.java.middle.concurrency.matrix;

import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
public class Matrix {

    private final int[][] matrix;

    private final int height;

    private final int weight;

    public int[] getRow(int numberOfRow) {
        if (numberOfRow > height)
            throw new IllegalArgumentException("Can't get row with such number " + numberOfRow);
        return matrix[numberOfRow];
    }

    public int[] getColumn(int numberOfColumn) {
        if (numberOfColumn > height)
            throw new IllegalArgumentException("Can't get column with such number " + numberOfColumn);

        int i = 0;
        int[] result = new int[height];
        for (int[] row : matrix) {
            result[i++] = row[numberOfColumn];
        }
        return result;
    }

    public Matrix(int[][] matrix) {
        this.matrix = Objects.requireNonNull(matrix, "Can't create empty Matrix");
        if (matrix.length != 0 && matrix[0].length != 0) {
            this.height = matrix.length;
            this.weight = matrix[0].length;
        } else {
            throw new IllegalStateException("Matrix have to have at least one element");
        }
    }

    public Matrix(int height, int weight) {
        if (height < 1)
            throw new IllegalArgumentException("Illegal height: " + height);
        if (weight < 1)
            throw new IllegalArgumentException("Illegal weight: " + height);
        this.height = height;
        this.weight = weight;
        this.matrix = new int[height][weight];
    }
}
