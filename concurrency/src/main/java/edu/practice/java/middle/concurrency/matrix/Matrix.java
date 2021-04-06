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

    public int getElement(int row, int column) {
        if (row > height)
            throw new IllegalArgumentException("Illegal height: " + height);
        if (column > weight)
            throw new IllegalArgumentException("Illegal weight: " + height);
        return matrix[row][column];
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
