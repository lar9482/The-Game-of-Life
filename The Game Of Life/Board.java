import java.util.Stack;
import java.util.LinkedList;

public class Board {
	private Cell[][] grid;
	private Stack<Cell> toDelete;
	private Stack<Cell> toAdd;
	private LinkedList<Cell> aliveCells;
	private int gridSize = 50;

	public int getGridSize() {
		return this.gridSize;
	}

	public Cell[][] getGrid() {
		return this.grid;
	}
	
	public Board() {
		createBoard();
		this.toAdd = new Stack<Cell>();
		this.toDelete = new Stack<Cell>();
		this.aliveCells = new LinkedList<Cell>();
	}
	
	private void createBoard() {
		this.grid = new Cell[gridSize][gridSize];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				Cell temp = new Cell(i, j, false);
				this.grid[i][j] = temp;
			}
		}
	}

	public void addElement(Cell cell) {
		aliveCells.add(cell);
		updateGrid();
	}

	public void addMultipleElements(LinkedList<Cell> toBeAdded) {
		for (int i = 0; i < toBeAdded.size(); i++) {
			aliveCells.add(toBeAdded.get(i));
		}
		updateGrid();
	}

	private void updateGrid() {
		for (int i = 0; i < aliveCells.size(); i++) {
			Cell element = aliveCells.get(i);
			grid[element.getI()][element.getJ()] = element;
		}
	}
	
	//This method loops through 'aliveCells' and passes them into activeNeighborsScan.
	private void scanActiveCells() {
		for(int i = 0; i < aliveCells.size(); i++) {
			//System.out.println("Beginning Computation");
			Cell currentCell = aliveCells.get(i);
			int activeCellsCounted = findAllActiveNeighbors(currentCell);

			addToDelete(activeCellsCounted, currentCell);
			//System.out.println("Success computation");
		}
		//System.out.println("EXITED LOOP");
	}

	private void scanNeighborsToAdd() {
		LinkedList<Cell> allNeighbors = allNeighborsForActiveCells();

		for (int i = 0; i < allNeighbors.size(); i++) {

			Cell neighbor = allNeighbors.get(i);
			int activeNeighborsCounted = findAllActiveNeighbors(neighbor);
			
			addToAdd(activeNeighborsCounted, neighbor);
		}
	}

	//Counts all the neighbors of a cell which are alive.
	private int findAllActiveNeighbors(Cell cell) {
		int activeCellsCounted = 0;
		
		for (int i = cell.getI() - 1; i <= cell.getI() + 1; i++) {
			for (int j = cell.getJ() - 1; j <= cell.getJ() + 1; j++) {
				if (i == cell.getI() && j == cell.getJ()) {
					//System.out.println("SAME");
					continue;
				}
				else if (withinGrid(i, j)) {
					//System.out.println("Comparing" + i + " and " + j);

					activeCellsCounted += isAlive(grid[i][j]);
				}
				else {
					continue;
				}

			}
			//System.out.println("Finished i loop at: " + i);
		}
		
		//System.out.println("finished looping");
		return activeCellsCounted;
	}

	//For each active cell(i.e element in 'aliveCells'), all neighbors are founded and returned.
	private LinkedList<Cell> allNeighborsForActiveCells() {
		LinkedList<Cell> allNeighbors = new LinkedList<Cell>();

		for (int i = 0; i < aliveCells.size(); i++) {
			Cell curr = aliveCells.get(i);
			for (int l = curr.getI() - 1; l < curr.getI() + 2; l++) {
				for(int k = curr.getJ() - 1; k < curr.getJ() + 2; k++) {
					if (l == curr.getI() && k == curr.getJ()) {
						continue;
					}
					else if (withinGrid(l, k)) {
						allNeighbors.add(grid[l][k]);
					}
				}
			}

		}

		return allNeighbors;
	}

	private void addToDelete(int activeCellsCounted, Cell neighbor) {
		if (activeCellsCounted == 2 || activeCellsCounted == 3) {
			return;
		}
		else {
			toDelete.push(neighbor);
			aliveCells.remove(neighbor);
		}
	}

	private void addToAdd(int activeCellsCounted, Cell neighbor) {
		if (activeCellsCounted == 3 && !toAdd.contains(neighbor)) {
			toAdd.push(neighbor);
			aliveCells.add(neighbor);
		}
	}

	private int isAlive(Cell neighbor) {
		if (neighbor.getAliveOrDead()) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	private boolean withinGrid(int i, int j) {
		boolean result = true;

		if (i < 0 || i >= gridSize) {
			result = false;
		}

		if (j < 0 || j >= gridSize) {
			result = false;
		}
		return result;
	}

	private void addCellsToStorage() {
		//System.out.println("CALL IN ADDCELLSTOSTORAGE");
		while (!toAdd.empty()) {
			Cell element = toAdd.peek();
			element.setAliveOrDead(true);
			aliveCells.add(element);
			toAdd.pop();
		}
	}

	private void deleteCellsFromGrid() {
		//System.out.println("DELETECELLSFFROMGRID");
		while (!toDelete.empty()) {
			Cell element = toDelete.peek();
			if (aliveCells.contains(element)) {
				aliveCells.remove(element);
			}
			element.setAliveOrDead(false);
			grid[element.getI()][element.getJ()] = element;
			toDelete.pop();
		}
	}

	public void driverMethod() {
		scanActiveCells();
		scanNeighborsToAdd();
		addCellsToStorage();
		deleteCellsFromGrid();
		updateGrid();
	}

}