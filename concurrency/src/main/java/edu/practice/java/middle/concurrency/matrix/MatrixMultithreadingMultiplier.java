package edu.practice.java.middle.concurrency.matrix;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MatrixMultithreadingMultiplier extends MatrixMultiplier {

    private final ExecutorService executorService;

    private final List<Callable<Void>> tasks;

    @Override
    @SneakyThrows
    public Matrix multiplyMatrix(Matrix matrixA, Matrix matrixB) {
        Matrix result = new Matrix(matrixA.getHeight(), matrixB.getWeight());
        for (int row = 0; row < matrixA.getHeight(); row++) {
            for (int column = 0; column < matrixB.getWeight(); column++) {
                submit(matrixA, matrixB, row, column, result);
            }
        }
        executorService.invokeAll(tasks);
        return result;
    }

    private void submit(Matrix matrixA, Matrix matrixB, int row, int column, Matrix matrix) {
        tasks.add(() -> dotProduct(matrixA, matrixB, row, column, matrix));
    }

    public MatrixMultithreadingMultiplier(MatrixValidator validator, MatrixReader reader, MatrixWriter writer) {
        super(validator, reader, writer);
        this.executorService = Executors.newFixedThreadPool(10);
        this.tasks = new ArrayList<>();
    }

    public MatrixMultithreadingMultiplier(MatrixValidator validator, MatrixReader reader, MatrixWriter writer, ExecutorService executorService) {
        super(validator, reader, writer);
        this.executorService = executorService;
        this.tasks = new ArrayList<>();
    }

}
