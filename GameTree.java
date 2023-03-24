import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A model for the game of 20 questions
 *
 * @author Rick Mercer
 */
public class GameTree {
	private BTNode root;
	private BTNode current;
	private String filename;

	private class BTNode {
		String data;
		BTNode left;
		BTNode right;

		public BTNode(String data) {
			this.data = data;
			left = right = null;
		}

		@Override
		public String toString() {
			return "" + this.data;
		}
	}

	/**
	 * Constructor needed to create the game.
	 *
	 * @param fileName this is the name of the file we need to import the game
	 *                 questions and answers from.
	 */
	public GameTree(String fileName) {
		this.filename = fileName;
		Scanner scanner;
		try {
			scanner = new Scanner(new File(fileName));
			if (root == null) {
				root = new BTNode(scanner.nextLine().trim());
			}
			preorder(scanner, root);
			current = root;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void preorder(Scanner scanner, BTNode current) {
		if (!current.data.endsWith("?")) {
			return;
		}
		if (scanner.hasNext()) {
			current.left = new BTNode(scanner.nextLine().trim());
			preorder(scanner, current.left);
			current.right = new BTNode(scanner.nextLine().trim());
			preorder(scanner, current.right);
		}
		return;
	}

	/*
	 * Add a new question and answer to the currentNode. If the current node has the
	 * answer chicken, theGame.add("Does it swim?", "goose"); should change that
	 * node like this:
	 */
	// -----------Feathers?-----------------Feathers?------
	// -------------/----\------------------/-------\------
	// ------- chicken horse-----Does it swim?-----horse--
	// -----------------------------/------\---------------
	// --------------------------goose--chicken-----------
	/**
	 * @param newQ The question to add where the old answer was.
	 * @param newA The new Yes answer for the new question.
	 */
	public void add(String newQ, String newA) {
		String temp = current.data;
		current.data = newQ;
		current.left = new BTNode(newA);
		current.right = new BTNode(temp);
	}

	/**
	 * True if getCurrent() returns an answer rather than a question.
	 *
	 * @return False if the current node is an internal node rather than an answer
	 *         at a leaf.
	 */
	public boolean foundAnswer() {
		if (current.right == null && current.left == null && !getCurrent().endsWith("?")) {
			return true;
		} else if (current.left != null && current.right != null && getCurrent().endsWith("?")) {
			return false;
		}
		return false;
	}

	/**
	 * Return the data for the current node, which could be a question or an answer.
	 * Current will change based on the users progress through the game.
	 *
	 * @return The current question or answer.
	 */
	public String getCurrent() {
		return current.data;
	}

	/**
	 * Ask the game to update the current node by going left for Choice.yes or right
	 * for Choice.no Example code: theGame.playerSelected(Choice.Yes);
	 *
	 * @param yesOrNo
	 */
	public void playerSelected(Choice yesOrNo) {
		if (yesOrNo == Choice.Yes) {
			current = current.left;
		}
		if (yesOrNo == Choice.No) {
			current = current.right;
		}
	}

	/**
	 * Begin a game at the root of the tree. getCurrent should return the question
	 * at the root of this GameTree.
	 */
	public void reStart() {
		current = root;
	}

	@Override
	public String toString() {
		return toStringHelp(root, "", 0);

	}

	private String toStringHelp(BTNode current, String s, int level) {
		String hyphens = "";
		for (int i = 0; i < level; i++) {
			hyphens += "- ";
		}
		if (current.left != null && current.right != null) {
			s += hyphens + current.data + "\n" + toStringHelp(current.left, s, level + 1)
					+ toStringHelp(current.right, s, level + 1);
		}
		return s;
	}

	/**
	 * Overwrite the old file for this gameTree with the current state that may have
	 * new questions added since the game started.
	 *
	 */
	public void saveGame() {
		try {
			PrintWriter diskFile = new PrintWriter(new File(filename));
			if (current != null) {
				diskFile.print(preorder(root));
			}
			diskFile.close();

		} catch (IOException io) {
			System.out.println("Could not create file: " + filename);
		}
	}

	public String preorder(BTNode current) {
		if (current.right == null && current.left == null) {
			return current.data + "\n";
		} else
			return current.data + "\n" + preorder(current.left) + preorder(current.right);

	}
}
