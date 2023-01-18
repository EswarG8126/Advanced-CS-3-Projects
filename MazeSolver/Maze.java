import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Maze {
	private Square[][] squares;
	private Square start;
	private Square exit;

	public Maze() {

	}

	public boolean loadMaze(String filename) {
		try {
			File file = new File (filename);
			Scanner scan = new Scanner(file);
			int r = scan.nextInt();
			int c = scan.nextInt();
			squares = new Square[r][c];
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					int type = scan.nextInt();
					squares[i][j] = new Square(i, j, type);
					if (type == Square.START) {
						start = squares[i][j];
					}
					if (type == Square.EXIT) {
						exit =  squares[i][j];
					}
				}
			}
			if (scan.hasNextInt()) {
				System.out.println(("Error loading maze at " + filename));
				scan.close();
				return false;
			}
			scan.close();
			return true;
		} catch (Exception e) {
			System.out.println(("Error loading maze at " + filename));
			return false;
		}


	}

	public List<Square> getNeighbors(Square s) {
		List<Square> neighbors = new ArrayList<>();
		int r = s.getRow(), c = s.getCol();
		if (r >= squares.length || c >= squares[0].length) {

			return neighbors;
		}
		if (squares[r][c].getType() != s.getType()) {

			return neighbors;
		}
		if (r - 1 >= 0) {
			neighbors.add(squares[r - 1][c]);
		}
		if (c + 1 < squares[0].length) {
			neighbors.add(squares[r][c + 1]);
		}
		if (r + 1 < squares.length) {
			neighbors.add(squares[r + 1][c]);
		}
		if (c - 1 >= 0) {
			neighbors.add(squares[r][c - 1]);
		}
		return neighbors;
	}

	public Square getStart() {
		return start;
	}

	public Square getExit() {
		return exit;
	}

	public void reset() {
		if (squares == null)
			return;
		for (Square[] arr : squares) {
			for (Square sqr : arr) {
				sqr.reset();
			}
		}
	}

	@Override
	public String toString() {
		String result = "";
		for (Square[] arr : squares) {
			for (Square sqr : arr) {
				result += sqr + " ";
			}
			result += "\n";
		}
		return result;
	}
}
