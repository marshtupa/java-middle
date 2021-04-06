package edu.practice.java.middle.concurrency;

import edu.practice.java.middle.concurrency.matrix.*;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.Executors;

class ConcurrencyApplicationTests {

	@Test
	void testMatricesMultiplication() {
		String matrixA = "matrixA.txt";
		String matrixB = "matrixB.txt";

		MatrixMultiplier matrixMultiplier = new MatrixMultiplier(
				new MatrixValidator(),
				new MatrixReader(),
				new MatrixWriter());
		matrixMultiplier.multiplyMatrix(matrixA, matrixB);
	}

	@Test
	void testGenerateMatricesMultithreadingMultiplication() {
		Matrix matrixTestA = createMatrix();
		Matrix matrixTestB = createMatrix();

		long start = System.currentTimeMillis();
		MatrixMultiplier matrixMultiplier = new MatrixMultithreadingMultiplier(
				new MatrixValidator(),
				new MatrixReader(),
				new MatrixWriter(),
				Executors.newFixedThreadPool(6));
		matrixMultiplier.multiplyMatrix(matrixTestA, matrixTestB);
		long spentTime = System.currentTimeMillis() - start;
		System.out.println("Spent time " + spentTime + "ms");
	}

	@Test
	void testGenerateMatricesMultiplication() {
		Matrix matrixTestA = createMatrix();
		Matrix matrixTestB = createMatrix();

		long start = System.currentTimeMillis();
		MatrixMultiplier matrixMultiplier = new MatrixMultiplier(
				new MatrixValidator(),
				new MatrixReader(),
				new MatrixWriter());
		matrixMultiplier.multiplyMatrix(matrixTestA, matrixTestB);
		long spentTime = System.currentTimeMillis() - start;
		System.out.println("Spent time " + spentTime + "ms");
	}

	private Matrix createMatrix() {
		Random random = new Random();
		Matrix matrix = new Matrix(2000, 2000);
		int[][] matrixTable = matrix.getMatrix();

		for (int row = 0; row < matrix.getHeight(); row++) {
			for (int col = 0; col < matrix.getWeight(); col++) {
				matrixTable[row][col] = random.nextInt();
			}
		}
		return matrix;
	}
}
