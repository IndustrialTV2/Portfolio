import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Case3 {
	
	private Scanner scanner = new Scanner(System.in);
	private int gridWidth = scanner.nextInt();
	private int gridHeight = scanner.nextInt();
	private Cell[] widthArray;
	private Cell[][] heightArray;
	private ArrayList<Cell> currentAdjCells = new ArrayList<Cell>();
	
	public static void main(String[] args) {
		
		Case3 Case3 = new Case3();
		Case3.start();
		
	}
		
	private void start() {

		int total = 0;
		Stack<Cell> stack = new Stack<>();
		
		CreateGrid();

		//Loops for each Y
		for(int y = 0; y < gridHeight; y++) {
			Cell[] currentRow = heightArray[y];
			
			//Loops for each X
			for(int x = 0; x < gridWidth; x++) {
				Cell currentCell = currentRow[x];
				int lowestHeightIndex = 0;
				int totalToAdd = 1;
				int timesToRun = 2;
				boolean lowerHeightFound = false;
				
				if(!currentCell.isVisited()) {
					
					GetAdjCells(currentCell);
					currentCell.setVisited();
					stack.push(currentCell);
					
					while(!stack.isEmpty()) {
						currentCell = stack.peek();
						System.out.println("current cell: " + currentCell.getHeight() + "     " + currentCell.getX() + ", " + currentCell.getY());
						
						//Checks adjacent cells twice to find if a lower height cell is connected
						for(int a = 0; a < 2; a++) {
							GetAdjCells(currentCell);
							
							if(currentAdjCells.isEmpty()) {
								stack.pop();
								break;
							}
							
							//Checks adjacent cells
							for(int i = 0; i < currentAdjCells.size(); i++){
								
								//First run: Checks for a cell of lower height
								if(currentAdjCells.get(i).getHeight() < currentCell.height) {
									lowerHeightFound = true;
								}
						
								//Second run: Checks for cells of the same height
								if(a > 0 && currentAdjCells.get(i).getHeight() == currentCell.getHeight() && !(currentAdjCells.get(i).isVisited()) ){
									
									currentCell = currentAdjCells.get(i);
									currentCell.setVisited();
									totalToAdd++;
									stack.push(currentCell);
									System.out.println("rain cell: " + currentCell.getHeight() + "    " + currentCell.getX() + ", " + currentCell.getY());
									break;
								}
								else if(a > 0 && i == currentAdjCells.size() - 1) {
									stack.pop();
								}
								
							}
							
						}
					}
					
					//After all tiles of same size have been checked:
					//If a connected tile had a lower height, it is impossible for these cells to add to the total
					if(!lowerHeightFound) {
						//If there was no connected tile of lower height, these cells must add to the total
						total += totalToAdd;
						stack.clear();
					}
					
					//Reset parameters for next run of cells
					totalToAdd = 0;
					timesToRun = 2;
					lowerHeightFound = false;
					
				}
			}
		}
		
		System.out.println(total);
		
	}
		
		
	private void GetAdjCells(Cell currentCell) {
		
		int x = currentCell.getX();
		int y = currentCell.getY();
		Cell[] currentRow = heightArray[y];
		currentAdjCells.clear();
		
		//Adds right cell
		if(x + 1 != gridWidth) {
			
			if(currentRow[x + 1].getHeight() <= currentCell.getHeight()) {
				currentAdjCells.add(currentRow[x + 1]);
			}
		}
		
		//Adds lower cell
		if(y + 1 != gridHeight) {
			Cell[] belowRow = heightArray[y + 1];
			
			if(belowRow[x].getHeight() <= currentCell.getHeight()) {
				currentAdjCells.add(belowRow[x]);
			}
		}
		
		//Adds left cell
		if(x - 1 != -1) {
			
			if(currentRow[x - 1].getHeight() <= currentCell.getHeight()) {
				currentAdjCells.add(currentRow[x - 1]);
			}
		}
		
		//Adds top cell
		if(y - 1 != -1) {
			Cell[] aboveRow = heightArray[y - 1];
			
			if(aboveRow[x].getHeight() <= currentCell.getHeight()) {
				currentAdjCells.add(aboveRow[x]);
			}
		}
	}
		
	
	private void CreateGrid() {
		
		heightArray = new Cell[gridHeight][];
		
		for(int y = 0; y < gridHeight; y++) {
			widthArray = new Cell[gridWidth];
			
			for(int x = 0; x < gridWidth; x++) {
				Cell newCell = new Cell(scanner.nextInt(), x, y);
				widthArray[x] = newCell;
			}
			
			heightArray[y] = widthArray;
		}
	}

	
	public class Cell {
		
		private int x;
		private int y;
		private int height;
		private boolean visited;
		private ArrayList<Cell> adjCells = new ArrayList<Cell>();
		
		public Cell(int height, int x, int y) {
			this.x = x;
			this.y = y;
			this.height = height;
		}
		
		public int getHeight() {
			return height;
		}
		
		public void addAdjCell(Cell cellToAdd) {
			adjCells.add(cellToAdd);
		}
		
		
		public boolean isVisited() {
			return visited;
		}
		
		public void setVisited(){
			visited = true;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
	}
	

}
