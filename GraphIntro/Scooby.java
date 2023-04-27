import java.io.*;
import java.util.*;

public class Scooby {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("scooby.dat"));
		int n = scan.nextInt();
		scan.nextLine();
		for (int i = 0; i < n; i++) {
			int[][] adjMatrix = new int[26][26];
			String[] rooms = scan.nextLine().split(" ");
			String check = scan.nextLine();
			char origin = check.charAt(0);
			char destination = check.charAt(1);
			for (int j = 0; j < rooms.length; j++) {
				adjMatrix[rooms[j].charAt(0) - 'A'][rooms[j].charAt(1) - 'A'] = 1;
				adjMatrix[rooms[j].charAt(1) - 'A'][rooms[j].charAt(0) - 'A'] = 1;
			}
			System.out.println(dfs(adjMatrix, origin, destination));
		}
	}

	public static String dfs(int[][] adjMatrix, char origin, char dest) {
		Stack<Character> stack = new Stack<Character>();
		Set<Character> visited = new HashSet<Character>();
		String neighbors = findNeighbors(adjMatrix, origin, dest);
		stack.push(origin);
		while (!stack.isEmpty()) {
			origin = stack.pop();
			if (origin == dest) {
				return "yes";
			}
			if (!visited.contains(origin)) {
				neighbors = findNeighbors(adjMatrix, origin, dest);
				visited.add(origin);
				for (int i = 0; i < neighbors.length(); i++) {
					stack.push(neighbors.charAt(i));
				}
			}
		}
		return "no";
	}

	public static String findNeighbors(int[][] adjMatrix, char u, char v) {
		String s = "";
		char c = ' ';
		for (int i = 0; i < adjMatrix[u - 'A'].length; i++) {
			if (adjMatrix[u - 'A'][i] == 1) {
				c = (char) (i + 'A');
				s = s + "" + c;
			}
		}
		return s;
	}
}