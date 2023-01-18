import java.util.ArrayList;

public class FireModel {
	public static int SIZE = 47;
	private FireCell[][] myGrid;
	private FireView myView;

	public FireModel(FireView view) {
		myGrid = new FireCell[SIZE][SIZE];
		for (int r = 0; r < SIZE; r++) {
			for (int c = 0; c < SIZE; c++) {
				myGrid[r][c] = new FireCell();
			}
		}
		myView = view;
		myView.updateView(myGrid);
	}

	public void recursiveFire(int r, int c) {
		if (r > myGrid.length - 1 || r < 0 || c > myGrid[0].length - 1 || c < 0) {
			return;
		}
		if (r == myGrid.length - 1) {
			if (myGrid[r][c].getStatus() != Status.DIRT) {
				myGrid[r][c].setStatus(Status.BURNING);
			}
			recursiveFire(r, c + 1);
			recursiveFire(r - 1, c);
		}
		if (myGrid[r][c].getStatus() == Status.DIRT) {
			return;
		}
		if (myGrid[r][c].getStatus() == Status.GREEN) {
			myGrid[r][c].setStatus(Status.BURNING);
			recursiveFire(r, c + 1);
			recursiveFire(r, c - 1);
			recursiveFire(r + 1, c);
			recursiveFire(r - 1, c);
		}
	}

	public void solve() {
		ArrayList<FireCell> list = new ArrayList<FireCell>();
		for (int i = 0; i < SIZE; i++) {
			if (myGrid[SIZE - 1][i].getStatus() == Status.GREEN) {
				myGrid[SIZE - 1][i].setStatus(Status.BURNING);
			}
			list.add(myGrid[SIZE - 1][i]);
			recursiveFire(i, SIZE - 1);
		}
		boolean trouble = false;
		myView.updateView(myGrid);
		for (int i = 0; i < SIZE; i++) {
			if (myGrid[0][i].getStatus() == Status.BURNING) {
				trouble = true;
			} else {
				trouble = false;
			}
			if (trouble) {
				break;
			}
		}
		if (trouble) {
			System.out.println("Onett is in trouble!");
		} else {
			System.out.println("Onett is safe");
		}
		myView.updateView(myGrid);
	}

}