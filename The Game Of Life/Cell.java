

public class Cell {
	private boolean alive;
	private int i;
	private int j; 

	public Cell(int i, int j, boolean alive) {
		this.i = i;
		this.j = j;
		this.alive = alive;
	}

	public void setAliveOrDead(boolean result) {
		this.alive = result;
	}
	public int getI() {
		return this.i;
	}

	public int getJ() {
		return this.j;
	}

	public boolean getAliveOrDead() {
		return this.alive;
	}
}