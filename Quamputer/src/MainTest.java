
public class MainTest {

	public static void main(String args[])
    {
       System.out.println("Quantum Computer");
       System.out.println();
       Matrix m1 = new DenseMatrix(new double[][] {{1, 2, 3}, {4, 5, 6}});
       Matrix m2 = new DenseMatrix(new double[][] {{2, 2}, {2, 2}});
       m1.tensor(m2);
       System.out.println(m1);
    }

}
