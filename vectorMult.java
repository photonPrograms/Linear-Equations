/* input: a matrix M of order nrow by ncol
 * and a column vector V of length len
 * output: their product
 */

import java.util.*;
public class vectorMult {
	public void input(double[][] M, double[] V, int nrow, int ncol, int len) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the matrix elements.");
		for (int i = 0; i < nrow; i++)
			for(int j = 0; j < ncol; j++)
				M[i][j] = scan.nextDouble();
		System.out.println("Enter the column vector elements.");
		for (int i = 0; i < len; i++)
			V[i] = scan.nextDouble();
	}

	public double[] colmultiply(double[][] M, double[] V, int nrow, int ncol, int len) {
		if (ncol != len) {
			System.out.println("Not conformable for multiplication.");
			System.exit(0);
		}
		double[] P = new double[nrow];
		for (int i = 0; i < nrow; i++) {
			P[i] = 0;
			for (int j = 0; j < ncol; j++)
				P[i] += M[i][j] * V[j];
		}
		return P;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of rows in matrix M.");
		int nrow = scan.nextInt();
		System.out.println("Enter the number of columns in matrix M.");
		int ncol = scan.nextInt();
		System.out.println("Enter the length of column vector V.");
		int len = scan.nextInt();

		double[][] M = new double[nrow][ncol];
		double[] V = new double[len];

		vectorMult obj = new vectorMult();
		obj.input(M, V, nrow, ncol, len);

		double[] ans = obj.colmultiply(M, V, nrow, ncol, len);

		for (int i = 0; i < len; i++)
			System.out.println(ans[i]);
	}
}
