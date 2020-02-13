/* input: n equations in n unknowns
 * output: solution of the system, if it exists
 */

import java.util.*;
public class equations {
	/* to input coefficients as well as constant terms in the system */
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

	/* to multiply coefficient matrix with column vector of constants */
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

	/* to calculate determinant of a matrix */
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

	/* to give the matrix of cofactors */
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

	/* to give the transpose of a matrix */
	public double[][] transpose(double[][] X, int nrow, int ncol) {
		double[][] Y = new double[ncol][nrow];
		for (int i = 0; i < nrow; i++)
			for (int j = 0; j < ncol; j++)
				Y[j][i] = X[i][j];
		return Y;
	}

	/* to obtain the adjoint of a matrix */
	public double[][] adjoint(double[][] X, int n) {
		return transpose(cofactors(X, n), n, n);
	}

	/* to obtain the inverse of a matrix */
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

		System.out.println("Enter the number of rows in the coefficient matrix (number of equations).");
		int nrow = scan.nextInt();
		System.out.println("Though we have n eq and n unknowns, lets just tally.");
		System.out.println("Enter the number of columns in the coefficient matrix (number of unknowns).");
		int ncol = scan.nextInt();
		System.out.println("Just for tally.");
		System.out.println("Enter the number of rows in the vector of constants (again number of equations).");
		int len = scan.nextInt();

		if (nrow != ncol) {
			System.out.println("Not applicable.");
			System.exit(0);
		}
		int n = ncol;

		double[][] M = new double[nrow][ncol];
		double[] V = new double[len];

		equations obj = new equations();
		obj.input(M, V, n, n, len);

		if (obj.determinant(M, n) == 0) {
			double[] X = obj.colmultiply(obj.adjoint(M, n), V, n, n, len);
			boolean flag = true;
			for (int i = 0; i < X.length; i++)
				if (X[i] != 0)
					flag = false;
			if (!flag)
				System.out.println("No solution exists.");
			else
				System.out.println("No unique solution. Infinite solutions may exist.");
			System.exit(0);
		}

		System.out.println("A unique solution does exist.");
		double[] soln = obj.colmultiply(obj.inverse(M, n), V, n, n, len);
		for (int i = 0; i < soln.length; i++)
			System.out.println("x_" + (i + 1) + " = " + soln[i]);
	}
}
