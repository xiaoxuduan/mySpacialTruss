package mySpacialTruss;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MatrixOperTest {
	
	MatrixOper t =new MatrixOper();
	double[][] a=new double[][]{{1, 2, 3},{2, 3, 4}};
	double[][] b=new double[][]{{3, 4, 10},{4, 22, 44},{5, 6, 4}};

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMatrixPlus() throws Exception {
//		fail("Not yet implemented");
		String s=t.matrixPrint(t.matrixPlus(a, a));
		System.out.println(s);
	}

	
	@Test
	public void testMatrixMaltiply() throws Exception {
//		fail("Not yet implemented");
		String s=t.matrixPrint(t.matrixMultiply(a, b));
		System.out.println(s);
	}

	@Test
	public void testMatrixTranspose() {
//		fail("Not yet implemented");
		String s=t.matrixPrint(t.matrixTranspose(a));
		System.out.println(s);
	}

	@Test
	public void testMatrixPrint() {
//		fail("Not yet implemented");
		String s=t.matrixPrint(a);
		System.out.println(s);
		
	}

}
