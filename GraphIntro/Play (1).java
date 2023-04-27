import java.io.*;
import java.util.*;

public class Play {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("play.dat"));
		int n = Integer.parseInt(scan.nextLine());
		for (int i = 0; i < n; i++) {
			int a = Integer.parseInt(scan.next());
			int b = Integer.parseInt(scan.next());
			int c = Integer.parseInt(scan.next());
			LinkedList<Integer>[] adjList = new LinkedList[a + 1];
			for (int j = 0; j < b; j++) {
				int x = scan.nextInt();
				int y = scan.nextInt();
				if (adjList[x] == null) {
					LinkedList<Integer> domino = new LinkedList<Integer>();
					domino.add(y);
					adjList[x] = domino;
				} else {
					adjList[x].add(y);
				}
			}
			System.out.println(dfs(scan, adjList, c));
		}
	}

	public static int dfs(Scanner scan, LinkedList<Integer>[] adjList, int c) {
		int tipped = 0;
		Set<Integer> visited = new HashSet<Integer>();
		Stack<Integer> dfs = new Stack<Integer>();
		for (int j = 0; j < c; j++) {
			dfs.push(Integer.parseInt(scan.next()));
			while (!dfs.isEmpty()) {
				int current = dfs.pop();
				visited.add(current);
				tipped++;
				if (adjList[current] != null) {
					LinkedList<Integer> adj = adjList[current];
					for (Integer d : adj) {
						if (!visited.contains(d)) {
							dfs.push(d);
						}
					}
				}
			}
		}
		return tipped;
	}
}