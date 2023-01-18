import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.Timer;

public class LifeModel implements ActionListener {

	/*
	 * This is the Model component.
	 */

	private static int SIZE = 60;
	private LifeCell[][] grid;

	LifeView myView;
	Timer timer;

	/** Construct a new model using a particular file */
	public LifeModel(LifeView view, String fileName) throws IOException {
		int r, c;
		grid = new LifeCell[SIZE][SIZE];
		for (r = 0; r < SIZE; r++)
			for (c = 0; c < SIZE; c++)
				grid[r][c] = new LifeCell();

		if (fileName == null) // use random population
		{
			for (r = 0; r < SIZE; r++) {
				for (c = 0; c < SIZE; c++) {
					if (Math.random() > 0.85) // 15% chance of a cell starting alive
						grid[r][c].setAliveNow(true);
				}
			}
		} else {
			Scanner input = new Scanner(new File(fileName));
			int numInitialCells = input.nextInt();
			for (int count = 0; count < numInitialCells; count++) {
				r = input.nextInt();
				c = input.nextInt();
				grid[r][c].setAliveNow(true);
			}
			input.close();
		}

		myView = view;
		myView.updateView(grid);

	}

	/** Constructor a randomized model */
	public LifeModel(LifeView view) throws IOException {
		this(view, null);
	}

	/** pause the simulation (the pause button in the GUI */
	public void pause() {
		timer.stop();
	}

	/** resume the simulation (the pause button in the GUI */
	public void resume() {
		timer.restart();
	}

	/** run the simulation (the pause button in the GUI */
	public void run() {
		timer = new Timer(50, this);
		timer.setCoalesce(true);
		timer.start();
	}

	/** called each time timer fires */
	public void actionPerformed(ActionEvent e) {
		oneGeneration();
		myView.updateView(grid);
	}

	/** main logic method for updating the state of the grid / simulation */
	private void oneGeneration() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j].isAliveNow() && (neighbors(grid, i, j) == 2 || neighbors(grid, i, j) == 3)) {
					grid[i][j].setAliveNext(true);
				} else if (grid[i][j].isAliveNow() && ((neighbors(grid, i, j) >= 4 && neighbors(grid, i, j) <= 8)
						|| (neighbors(grid, i, j) == 0 || neighbors(grid, i, j) == 1))) {
					grid[i][j].setAliveNext(false);
				} else if (grid[i][j].isAliveNow() == false && neighbors(grid, i, j) == 3) {
					grid[i][j].setAliveNext(true);
				}
			}
		}
		for (LifeCell[] cells : grid) {
			for (LifeCell c : cells) {
				c.setAliveNow(c.isAliveNext());
			}
		}

	}

	private int neighbors(LifeCell[][] grid, int r, int c) {
		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (Math.abs(i - r) <= 1 && Math.abs(j - c) <= 1) {
					if (c != j || r != i) {
						if (grid[i][j].isAliveNow()) {
							count++;
						}
					}
				}
			}
		}
		return count;
	}

}
