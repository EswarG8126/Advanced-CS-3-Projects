import java.io.*;
import java.util.*;

public class BinaryMaze {
	private static class Location {
		int x;
		int y;
		int distance;

		public Location(int x, int y, int distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}

		@Override
		public int hashCode() {
			return x + y;
		}

		@Override
		public boolean equals(Object obj) {
			Location loc = (Location)obj;
			if (loc.x == this.x && loc.y == this.y) {
				return true;
			}
			return false;
		}

	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("maze.dat"));
		String[] info = scan.nextLine().split(" ");
		int r = Integer.parseInt(info[0]);
		int c = Integer.parseInt(info[1]);
		int n = Integer.parseInt(info[2]);
		int[][] adjMatrix = new int[r][c];
		for (int i = 0; i < r; i++) {
			String temp[] = scan.nextLine().split(" ");
			for (int j = 0; j < c; j++) {
				adjMatrix[i][j] = Integer.parseInt(temp[j]);
			}
		}
		for (int i = 0; i < n; i++) {
			System.out.println(bfs(scan, adjMatrix, r, c));
		}
	}

	public static int bfs(Scanner scan, int[][] adjMatrix, int r, int c) {
		int[] rowNeighbors = { -1, 0, 1, 0 };
		int[] columnNeighbors = { 0, -1, 0, 1 };
		int path = -1;
		Queue<Location> bfs = new LinkedList<Location>();
		Set<Location> visited = new HashSet<Location>();
		Location origin = new Location(scan.nextInt(), scan.nextInt(), 0);
		Location dest = new Location(scan.nextInt(), scan.nextInt(), 1);
		bfs.add(origin);
		visited.add(origin);
		while (!bfs.isEmpty()) {
			Location node = bfs.poll();
			if (node.equals(dest)) {
				path = node.distance;
				bfs.clear();
				return path;
			}
			
			for (int j = 0; j < 4; j++) {
				Location temp = new Location(node.x + rowNeighbors[j], node.y + columnNeighbors[j], 
						node.distance + 1);
				if (!visited.contains(temp) && temp.x >= 0 && temp.x < r && temp.y >= 0 && temp.y < c
						&& adjMatrix[temp.x][temp.y] == 1) {
					bfs.add(temp);
					visited.add(temp);
				}
			}
		}
		return path;
	}
}