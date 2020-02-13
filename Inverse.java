/* input: the order nrow of a matrix
 * and its elements
 * output: the inverse of the matrix if its exists
 */

import java.util.*;
public class Inverse {
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

	public double[][] cofactors(double[][] X, int n) {
		double[][] Y = new double[n][n];
		if (n == 1)
			Y[0][0] = X[0][0];
		else {
			double cof;
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++) {
					double[][] S = new double[n - 1][n - 1];
					int row_no = 0, col_no = 0;
					for (int k = 0; k < n; k++)
						if (k != i) {
							for (int l = 0; l < n; l++)
								if (l != j) {
									S[row_no][col_no] = X[k][l];
									col_no++;
								}
							row_no++;
							col_no = 0;
						}
					cof = Math.pow(-1, i + j) * determinant(S, n - 1);
					Y[i][j] = cof;
				}
		}
		return Y;
	}

	public double[][] transpose(double[][] X, int nrow, int ncol) {
		double[][] Y = new double[ncol][nrow];
		for (int i = 0; i < nrow; i++)
			for (int j = 0; j < ncol; j++)
				Y[j][i] = X[i][j];
		return Y;
	}

	public double[][] adjoint(double[][] X, int n) {
		return transpose(cofactors(X, n), n, n);
	}

	public double[][] inverse(double[][] X, int n) {
		double[][] adj = adjoint(X, n);
		double[][] inv = new double[n][n];
		double det = determinant(X, n);
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				inv[i][j] = adj[i][j] / det;
		return inv;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of rows.");
		int nrow = scan.nextInt();
		System.out.println("Enter the number of columns.");
		int ncol = scan.nextInt();

		if (nrow != ncol) {
			System.out.println("Not applicable for this operation.");
			System.exit(0);
		}

		double[][] M = new double[nrow][ncol];

		Inverse obj = new Inverse();
		obj.input(M, nrow);

		if (obj.determinant(M, nrow) == 0) {
			System.out.println("Inverse does not exist.");
			System.exit(0);
		}
		
		double[][] ans = obj.inverse(M, nrow);

		for (int i = 0; i < nrow; i++) {
			for (int j = 0; j < ncol; j++)
				System.out.print(ans[i][j] + "\t");
			System.out.println();
		}
	}
}
