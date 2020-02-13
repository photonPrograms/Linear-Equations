/* input: a matrix of order nrow
 * output: its determinant
 */

import java.util.*;
public class Determinant {
	public void input(double[][] M, int n) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the matrix elements.");
		for (int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				M[i][j] = scan.nextDouble();
	}

	public double determinant(double[][] M, int n) {
		if (n == 1)
			return M[0][0];
		else {
			double cof, sum = 0;
			for (int i = 0; i < n; i++) {
				double[][] S = new double[n - 1][n - 1];
				int row_no = 0, col_no = 0;
				for (int k = 1; k < n; k++) {
					for (int l = 0; l < n; l++)
						if (l != i) {
							S[row_no][col_no] = M[k][l];
							col_no++;
						}
					row_no++;
					col_no = 0;
				}
				cof = Math.pow(-1, i) * determinant(S, n - 1);
				sum += M[0][i] * cof;
			}
			return sum;
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of rows.");
		int nrow = scan.nextInt();
		System.out.println("Enter the number of columns.");
		int ncol = scan.nextInt();

		if (nrow != ncol) {
			System.out.println("Not applicable for determinant operations.");
			System.exit(0);
		}

		double[][] M = new double[nrow][ncol];

		Determinant obj = new Determinant();
		obj.input(M, nrow);

		System.out.println("determinant = " + obj.determinant(M, nrow));
	}
}
