import java.util.*;
import java.io.*;

public class BoggleSolver {
	HashSet<String> dictionary = new HashSet<String>();
	Scanner scan;

	public BoggleSolver(String fileName) {
		try {
			scan = new Scanner(new File(fileName));
		} catch (FileNotFoundException ex) {
			System.out.println(fileName + "not found.");
		}
		while (scan.hasNext()) {
			String s = scan.nextLine();
			dictionary.add(s);
		}

	}

	public Iterable<String> getAllValidWords(BoggleBoard board) {
		boolean[][] stat = new boolean[board.rows()][board.cols()];
		HashSet<String> valid = new HashSet<String>();
		for (int i = 0; i < board.rows(); i++) {
			for (int j = 0; j < board.cols(); j++) {
				getAllValidWordsHelper("", stat, valid, i, j, board);
			}
		}
		return valid;
	}

	public void getAllValidWordsHelper(String currentWord, boolean[][] stat, HashSet<String> valid, int i, int j,
			BoggleBoard board) {
		currentWord += board.getLetter(i, j);
		if (board.getLetter(i, j) == 'Q') {
			currentWord += 'U';
		}
		stat[i][j] = true;
		if (dictionary.contains(currentWord) && !valid.contains(currentWord)) {
			valid.add(currentWord);
		}
		for (int x = i - 1; x <= i + 1; x++) {
			for (int y = j - 1; y <= j + 1; y++) {
				if (x > -1 && x < board.rows() && y > -1 && y < board.cols() && !stat[x][y]) {
					getAllValidWordsHelper(currentWord, stat, valid, x, y, board);
				}
			}
		}
		stat[i][j] = false;
		currentWord = "";
	}

	public int scoreOf(String word) {
		if (dictionary.contains(word)) {
			if (word.length() >= 0 && word.length() <= 2) {
				return 0;
			} else if (word.length() == 3 || word.length() == 4) {
				return 1;
			} else if (word.length() == 5) {
				return 2;
			} else if (word.length() == 6) {
				return 3;
			} else if (word.length() == 7) {
				return 5;
			} else if (word.length() >= 8) {
				return 11;
			}
			return 0;
		}
		return 0;
	}

	public static void main(String[] args) {
		System.out.println("WORKING");

		final String PATH = "./data/";
		BoggleBoard board = new BoggleBoard(PATH + "board-q.txt");
		BoggleSolver solver = new BoggleSolver(PATH + "dictionary-algs4.txt");

		int totalPoints = 0;

		for (String s : solver.getAllValidWords(board)) {
			System.out.println(s + ", points = " + solver.scoreOf(s));
			totalPoints += solver.scoreOf(s);
		}

		System.out.println("Score = " + totalPoints); // should print 84

		new BoggleGame(6, 6);
	}

}