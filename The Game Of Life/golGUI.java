import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import java.util.Timer; 
import java.util.TimerTask; 

import java.util.LinkedList;
import java.lang.Math;

public class golGUI extends Application{
	private Board game = new Board();
	private Group golComponents;
	private static final int cellSize = 8; //EDIT THIS TO CHANGE THE CELLSIZES
	private int numGrids = game.getGridSize();
	private final int windowLength = numGrids*cellSize;


	public void start(Stage stage) {
		golComponents = createComponents();
		Scene window = new Scene(golComponents, windowLength, windowLength);

		tetromino4(); //TYPE ONE OF THE FUNCTIONS BELOW TO GET A DIFFERENT STARTING SET ON THE BOARD.
		Timer timer = new Timer();
		Task maintask = new Task();
		timer.schedule(maintask, 0, 100l); //EDIT 100L TO CHANGE THE TICKSPEED

		stage.setScene(window);
		stage.show();	
	}

	private Group createComponents() {
		Group group = new Group();

		for (int i = 0; i < windowLength; i = i + cellSize) {
			for (int j = 0; j < windowLength; j = j + cellSize) {
				Rectangle cell = new Rectangle((double) j, (double) i, (double) cellSize, (double) cellSize);
				cell.setFill(Color.BLACK);
				cell.setStroke(Color.WHITE);
				group.getChildren().add(cell);
			}
		}

		return group;
	}
	private void updateBoard() {
		Cell[][] gameGrid = game.getGrid();
		int cellCount = 0;
		for (int i = 0; i < numGrids; i++) {
			for (int j = 0; j < numGrids; j++) {
				if (gameGrid[i][j].getAliveOrDead()) {
					Rectangle cell = (Rectangle) golComponents.getChildren().get(cellCount);
					cell.setStroke(Color.BLACK);
					
					cell.setFill(Color.WHITE);
					
				}
				else {
					Rectangle cell = (Rectangle) golComponents.getChildren().get(cellCount);
					cell.setStroke(Color.WHITE);
					cell.setFill(Color.BLACK);
				}
				cellCount++;
			}
		}
	}
	
	class Task extends TimerTask {
		public void run() {
			game.driverMethod();
			updateBoard();
		}
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	private void gosperGun() {
		LinkedList<Cell> components = new LinkedList<Cell>();
		//+3
		components.add(new Cell(8, 4, true)); //1
		components.add(new Cell(8, 5, true));//2
		components.add(new Cell(9, 4, true));//1
		components.add(new Cell(9, 5, true));//2

		components.add(new Cell(6, 16, true));//13
		components.add(new Cell(6, 17, true));//14
		components.add(new Cell(7, 15, true));//12
		components.add(new Cell(7, 19, true));//16
		components.add(new Cell(8, 14, true));//11
		components.add(new Cell(8, 20, true));//17
		components.add(new Cell(9, 14, true));//11
		components.add(new Cell(9, 18, true));//15
		components.add(new Cell(9, 20, true));//17
		components.add(new Cell(9, 21, true));//18
		components.add(new Cell(10, 14, true));//11
		components.add(new Cell(10, 20, true));//17
		components.add(new Cell(11, 15, true));//12
		components.add(new Cell(11, 19, true));//16
		components.add(new Cell(12, 16, true));//13
		components.add(new Cell(12, 17, true));//14

		components.add(new Cell(4, 28, true));//25
		components.add(new Cell(5, 26, true));//23
		components.add(new Cell(5, 28, true));//25
		components.add(new Cell(6, 25, true));//22
		components.add(new Cell(6, 24, true));//21
		components.add(new Cell(7, 25, true));//22
		components.add(new Cell(7, 24, true));//21
		components.add(new Cell(8, 25, true));//22
		components.add(new Cell(8, 24, true));//21
		components.add(new Cell(9, 26, true));//23
		components.add(new Cell(9, 28, true));//25
		components.add(new Cell(10, 28, true));//25

		components.add(new Cell(6, 38, true));//35
		components.add(new Cell(6, 39, true));//36
		components.add(new Cell(7, 38, true));//35
		components.add(new Cell(7, 39, true));//36

		game.addMultipleElements(components);
		updateBoard();
	}

	private void tetromino4() {
		LinkedList<Cell> components = new LinkedList<Cell>();
		components.add(new Cell(24, 24, true));
		components.add(new Cell(24, 25, true));
		components.add(new Cell(24, 26, true));
		components.add(new Cell(23, 25, true));
		game.addMultipleElements(components);

		updateBoard();
	}
	private void setGlider() {
		Cell input1 = new Cell(4, 4, true);
		Cell input2 = new Cell(5, 5, true);
		Cell input3 = new Cell(6, 3, true);
		Cell input4 = new Cell(6, 4, true);
		Cell input5 = new Cell(6, 5, true);

		game.addElement(input1);
		game.addElement(input2);
		game.addElement(input3);
		game.addElement(input4);
		game.addElement(input5);

		updateBoard();
	}

	private void pentomino1103() {
		LinkedList<Cell> components = new LinkedList<Cell>();
		int centerOfBoard = game.getGridSize() / 2; 
		components.add(new Cell(centerOfBoard - 2, centerOfBoard, true)); 
		components.add(new Cell(centerOfBoard - 2, centerOfBoard + 1, true)); 
		components.add(new Cell(centerOfBoard - 1, centerOfBoard - 1, true)); 
		components.add(new Cell(centerOfBoard - 1, centerOfBoard, true)); 
		components.add(new Cell(centerOfBoard, centerOfBoard, true)); 
		game.addMultipleElements(components);
		updateBoard();

	}
}
