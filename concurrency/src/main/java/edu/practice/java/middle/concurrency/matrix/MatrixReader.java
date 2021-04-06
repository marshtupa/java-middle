package edu.practice.java.middle.concurrency.matrix;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class MatrixReader {

    private final String RESOURCES_PATH = "src/main/resources/";

    @SneakyThrows
    public Matrix readMatrices(String matrixFileName) {
        List<String> read = Files.readAllLines(Paths.get(RESOURCES_PATH + matrixFileName));
        int[][] matrix = read.stream()
                .map(line -> Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray())
                .toArray(int[][]::new);

        return new Matrix(matrix);
    }

}
