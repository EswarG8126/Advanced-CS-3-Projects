import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class WordLadder {
	public static void main(String[] args) throws FileNotFoundException {
		Set<String> dictionary = new HashSet<String>();
		Scanner dict = new Scanner(new File("dictionary.txt"));
		while (dict.hasNext()) {
			dictionary.add(dict.next().toLowerCase());
		}
		dict.close();
		Scanner input = new Scanner(new File("input.txt"));
		while (input.hasNext()) {
			String first = input.next();
			String last = input.next();
			Stack<String> ladder = findLadder(first, last, dictionary);
			if (ladder == null) {
				System.out.println("No ladder between " + first + " and " + last);
			} else {
				System.out.println("Found a ladder! >>> " + ladder);
			}
		}
		input.close();
	}
	public static Stack<String> findLadder(String starting, String ending, Set<String> dict) {
		if (starting.length() != ending.length()) {
			return null;
		}
		if (!(dict.contains(starting))) {
			return null;
		}
		Stack<String> initial = new Stack<>();
		Queue<Stack<String>> queue = new LinkedList<>();
		Set<String> used = new HashSet<String>();
		initial.push(starting);
		queue.offer(initial);
		if (initial.peek().equalsIgnoreCase(ending)) {
			return initial;
		}
		while (queue.size() > 0) {
			Stack<String> stack = queue.poll();
			String s = stack.peek();
			for (int i = 0; i < s.length(); i++) {
				for (char c = 'a'; c <= 'z'; c++) {
					String temp = s.substring(0, i) + c + s.substring(i + 1);
					if (used.contains(temp)) {
						continue;
					}
					if (dict.contains(temp)) {
						Stack<String> copy = new Stack<>();
						copy.addAll(stack);
						copy.push(temp);
						used.add(temp);
						if (temp.equalsIgnoreCase(ending)) {
							return copy;
						}
						queue.offer(copy);
					}
				}
			}
		}
		return null;
	}
}