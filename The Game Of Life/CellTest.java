package GameOfLife;

public class CellTest {
	public static void main(String args[]) {
		Cell test = new Cell(2, 2, true);

		System.out.println("The Cell's i is " + test.getI());
		System.out.println("The Cell's j is " + test.getJ());
		System.out.println("The Cell's i is " + test.getAliveOrDead());

		test.setI(3);
		test.setJ(3);
		test.setAliveOrDead(false);

		System.out.println("The Cell's i is " + test.getI());
		System.out.println("The Cell's j is " + test.getJ());
		System.out.println("The Cell's i is " + test.getAliveOrDead());
	}
}