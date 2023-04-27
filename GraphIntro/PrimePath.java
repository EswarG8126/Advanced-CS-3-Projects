import java.io.*;
import java.util.*;

public class PrimePath {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Map<Integer, Integer> primes = new HashMap<Integer, Integer>();
		for (int i = 2; i <= 9999; i++) {
			if (isPrime(i)) {
				primes.put(i, 0);
			}
		}
		int origin = scan.nextInt();
		int dest = scan.nextInt();
		System.out.print(bfs(origin, dest, primes));
		scan.close();
	}

	public static int bfs(int origin, int dest, Map<Integer, Integer> primes) {
		Queue<Integer> bfs = new LinkedList<Integer>();
		Set<Integer> visited = new HashSet<Integer>();
		if (isPrime(origin) && isPrime(dest) && (dest > 999 && dest < 10000) && (origin > 999 && origin < 10000)) {
			bfs.add(origin);
			visited.add(origin);
		} else {
			return 0;
		}
		while (!bfs.isEmpty()) {
			int num = bfs.poll();
			if (num == dest) {
				bfs.clear();
				return primes.get(num);
			}
			char[] dig = ("" + num).toCharArray();
			for (int i = 0; i < dig.length; i++) {
				dig = ("" + num).toCharArray();
				for (int j = 0; j <= 9; j++) {
					dig[i] = (char) (j + '0');
					int n = Integer.parseInt(new String(dig));
					if (isPrime(n) && !visited.contains(n)) {
						primes.put(n, primes.get(num) + 1);
						visited.add(n);
						bfs.add(n);
					}
				}
			}
		}
		return 0;
	}

	public static boolean isPrime(int n) {
		boolean prime = false;
		for (int i = 2; i <= n / 2; i++) {
			if (n % i == 0) {
				return prime;
			}
		}
		return true;
	}
}