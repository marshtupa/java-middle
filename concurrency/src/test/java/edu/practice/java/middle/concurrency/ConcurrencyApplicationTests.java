package edu.practice.java.middle.concurrency;

import edu.practice.java.middle.concurrency.matrix.MatrixMultiplier;
import edu.practice.java.middle.concurrency.matrix.MatrixReader;
import edu.practice.java.middle.concurrency.matrix.MatrixValidator;
import edu.practice.java.middle.concurrency.matrix.MatrixWriter;
import org.junit.jupiter.api.Test;

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

}
