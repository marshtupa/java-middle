package edu.practice.java.middle.concurrency.matrix;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MatrixWriter {

    private final String RESOURCES_PATH = "src/main/resources/";

    public void writeMatrix(Matrix matrix) {
        String matrixString = Arrays.stream(matrix.getMatrix())
                .map(row -> Arrays.stream(row)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(" ")))
                .collect(Collectors.joining("\n"));

        System.out.println(matrixString);
        try {
            Files.write(Paths.get(RESOURCES_PATH + "MatrixResult.txt"),
                    matrixString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Can't write to file");
        }
    }
}
