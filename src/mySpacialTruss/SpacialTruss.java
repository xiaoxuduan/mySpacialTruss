package mySpacialTruss;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
/**
 * @description Elements number and nodes number are counted from 1;
 * @author duan xiaoxu
 *
 */
public class SpacialTruss {
	int nf; // single node's dof;
	int np; // number of nodes;
	int ne; // number of elements;
	int nm; // number of element type;
	int nr; // number of restrained nodes;
	int ncf; // number of external forces;

	double[] x; // x[0:np-1] coordinate of nodes;
	double[] y; // y[0:np-1] coordinate of nodes;
	double[] z; // z[0:np-1] coordinate of nodes;

	// ae[1][j]: Eo of the jth element type;
	// ae[1][j]: Area of the jth element type;
	double[][] ae = new double[2][nm]; // ae[0:1][0:nm-1] ;

	// me[0][j]: node number of element i end;
	// me[1][j]: node number of element j end;
	int[][] me = new int[2][ne]; // me[0:1][0:ne-1];

	// nae[i]=element type of (i+1)th element;
	// nae[]=1->nm;
	int[] nae = new int[ne];

	// rr[0][j]: node number;
	// rr[1][j]: node restrained status;
	double[][] rr = new double[2][nr]; // rr[0:1][0:nr-1];

	// pf[0][j]: node number where external forces loaded;
	// pf[1][j]: Fx; pf[2][j]: Fy; pf[3][j]: Fz;
	double[][] pf = new double[4][ncf]; // pf[0:3][0:ncf-1];
	
	// element's local stiffness matrix
	double[][] ake=new double[2][2];
	
	// coordinate transfer matrix;
	double[][] T;
	
	// joint&element numbering matrix it&lmt;
	int[][] it=new int[nf][np];
	int nd=2; // single element's nodes number;
	int ndf=nd*nf;
	int[][] lmt=new int[ndf][ne];
	
	int nn, nn1;
	
	

	public static void main(String[] args) throws IOException {
		SpacialTruss t = new SpacialTruss();
		t.dataInFromFile();
	}

	// input data from file;
	@SuppressWarnings("resource")
	public void dataInFromFile() throws IOException {
		System.out.println("name of input file(e.g. input):");
		Scanner sysIn = new Scanner(System.in);
		String input = sysIn.nextLine().trim();
		Scanner fileIn = new Scanner(Paths.get(input));
		
		nf = fileIn.nextInt();
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
		for (int i = 0; i < nae.length; i++) {
			nae[i] = fileIn.nextInt();
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

	// caculate element's local stiffness matrix;
	// ie is element's number;
	public void fke(int ie) {
		int n1 = me[0][ie];
		int n2 = me[1][ie];
		double x1 = x[n1 - 1], y1 = y[n1 - 1], z1 = z[n1 - 1];
		double x2 = x[n2 - 1], y2 = y[n2 - 1], z2 = z[n2 - 1];
		double bl = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 - z2, 2));
		int nmi = nae[ie - 1]; // the element type number of the ie element;
		double E=ae[0][nmi-1];
		double A=ae[1][nmi-1];
		double temp =E*A/bl;
		ake[0][0]=temp;
		ake[0][1]=-temp;
		ake[1][0]=-temp;
		ake[1][1]=temp;
	}
	// caculate coordinate transfer matrix for ie element;
	public void ft(int ie)
	{
		int n1 = me[0][ie];
		int n2 = me[1][ie];
		double x1 = x[n1 - 1], y1 = y[n1 - 1], z1 = z[n1 - 1];
		double x2 = x[n2 - 1], y2 = y[n2 - 1], z2 = z[n2 - 1];
		double bl = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 - z2, 2));
		double cx=(x2-x1)/bl;
		double cy=(y2-y1)/bl;
		double cz=(z2-z1)/bl;
		T[0][0]=cx;
		T[0][1]=cy;
		T[0][2]=cz;
		T[1][3]=cx;
		T[1][4]=cy;
		T[1][6]=cz;
	}
	
	public void flmt()
	{

	}
}
