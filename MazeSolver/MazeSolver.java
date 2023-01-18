import java.util.List;
import java.util.Stack;

public abstract class MazeSolver {
	private Maze maze;
	private boolean solved = false;
	private boolean solvable = true;

	public MazeSolver(Maze maze) {
		this.maze = maze;
		makeEmpty();
		add(maze.getStart());
	}

	public abstract void makeEmpty();

	public abstract boolean isEmpty();

	public abstract void add(Square s);

	public abstract Square next();

	public boolean isSolved() {
		return solved;
	}

	public void step() {
		if (isEmpty()) {
			solvable = false;
			solved = true;
			return;
		}
		Square square = next();
		if (maze.getExit().equals(square)) {
			solved = true;
			solvable = true;
			return;
		}
		List<Square> neighbors = maze.getNeighbors(square);
		for (Square neighbor : neighbors) {
			if ((neighbor.getType() == Square.EMPTY && neighbor.getStatus() == Square.UNKNOWN)
					|| neighbor.getType() == Square.EXIT) {
				neighbor.setPrevious(square);
				add(neighbor);
				neighbor.setStatus(Square.WORKING);
			}
		}
		square.setStatus(Square.EXPLORED);
	}

	public String getPath() {
		if (solved) {
			if (solvable) {
				String result = "Maze is solved. This is the path:\n";
				result += displayPath();
				return result;
			} else {
				return "Maze is unsolvable. No path exists.";
			}
		} else {
			return "Maze is not yet solved.";
		}
	}

	public String displayPath() {
		Square current = maze.getExit();
		String path = "";
		Stack<String> coordinates = new Stack<String>(); // without stack - prints end to start, with stack - start to end
		while (current != null) {
			coordinates.add("[" + current.getRow() + "," + current.getCol() + "] -> "); 
			current.setStatus(Square.ON_EXIT_PATH);
			current = current.getPrevious();
		}
		while (!coordinates.isEmpty()) {
			path += coordinates.pop();
		}
		return path;
	}

	public void solve() {
		while (!solved) {
			step();
		}
	}
}
