package mySpacialTruss;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * @description using Static is to avoid transfer parameter; It can be done by
 *              multi constructors as well;
 * @author duan xiaoxu
 *
 */
public class DataIn {
	static int np; // number of nodes;
	static int ne; // number of elements;
	static int nm; // number of element type;
	static int nr; // number of restrained nodes;
	static int ncf; // number of external forces;

	static double[] x; // x[0:np-1] coordinate of nodes;
	static double[] y; // y[0:np-1] coordinate of nodes;
	static double[] z; // z[0:np-1] coordinate of nodes;

	// ae[1][j]: Eo of the jth element type;
	// ae[1][j]: Area of the jth element type;
	static double[][] ae = new double[2][nm]; // ae[0:1][0:nm-1] ;

	// me[0][j]: node number of element i end;
	// me[1][j]: node number of element j end;
	static int[][] me = new int[2][ne]; // me[0:1][0:ne-1];

	// rr[0][j]: node number;
	// rr[1][j]: node restrained status;
	static double[][] rr = new double[2][nr]; // rr[0:1][0:nr-1];

	// pf[0][j]: node number where external forces loaded;
	// pf[1][j]: Fx; pf[2][j]: Fy; pf[3][j]: Fz;
	static double[][] pf = new double[4][ncf]; // pf[0:3][0:ncf-1];

	public static void dataInFromFile() throws IOException {
		System.out.println("name of input file(e.g. input):");
		Scanner sysIn = new Scanner(System.in);
		String input = sysIn.nextLine().trim();
		Scanner fileIn = new Scanner(Paths.get(input));

		np = fileIn.nextInt();
		ne = fileIn.nextInt();
		nm = fileIn.nextInt();
		nr = fileIn.nextInt();
		ncf = fileIn.nextInt();

		for (int i = 0; i < ae.length; i++) {
			for (int j = 0; j < ae[0].length; j++) {
				ae[i][j] = fileIn.nextDouble();
			}
		}
		for (int i = 0; i < me.length; i++) {
			for (int j = 0; j < me[0].length; j++) {
				me[i][j] = fileIn.nextInt();
			}
		}
		for (int i = 0; i < rr.length; i++) {
			for (int j = 0; j < rr[0].length; j++) {
				rr[i][j] = fileIn.nextDouble();
			}
		}
		for (int i = 0; i < pf.length; i++) {
			for (int j = 0; j < pf[0].length; j++) {
				pf[i][j] = fileIn.nextDouble();
			}
		}
	}

}
