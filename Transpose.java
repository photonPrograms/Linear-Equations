/* input: number of rows and columns in the matrix
 * and the matrix elements
 * output: the transpose of the matrix
 */

import java.util.*;
public class Transpose {
	public void input(double[][] M, int nrow, int ncol) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the matrix elements.");
		for (int i = 0; i < nrow; i++)
			for (int j = 0; j < ncol; j++)
				M[i][j] = scan.nextDouble();
	}

	public double[][] transpose(double[][] X, int nrow, int ncol) {
		double[][] Y = new double[ncol][nrow];
		for (int i = 0; i < nrow; i++)
			for (int j = 0; j < ncol; j++)
				Y[j][i] = X[i][j];
		return Y;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of rows.");
		int nrow = scan.nextInt();
		System.out.println("Enter the number of columns.");
		int ncol = scan.nextInt();

		double[][] M = new double[nrow][ncol];

		Transpose obj = new Transpose();
		obj.input(M, nrow, ncol);
		double[][] ans = obj.transpose(M, nrow, ncol);

		for (int i = 0; i < ncol; i++) {
			for (int j = 0; j < nrow; j++)
				System.out.print(ans[i][j] + "\t");
			System.out.println();
		}
	}
}
