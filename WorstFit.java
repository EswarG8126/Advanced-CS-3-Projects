import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class WorstFit {
	private static Queue<Disk> disks = new PriorityQueue<Disk>();
	private static int size = 0;

	public static void worstFit(File file) {
		disks = new PriorityQueue<Disk>();
		try {
			Scanner scanner = new Scanner(file);
			int id = 0;
			while (scanner.hasNext()) {
				int n = scanner.nextInt();
				size += n;
				if (disks.size() < 1) {
					Disk d = new Disk(id);
					id++;
					d.add(n);
					disks.offer(d);
					continue;
				}
				if (disks.peek().getSpace() < n) {
					Disk d = new Disk(id);
					id++;
					d.add(n);
					disks.offer(d);
				} else {
					Disk d = disks.poll();
					d.add(n);
					disks.offer(d);
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void worstFitDecreasing(File file) {
		disks = new PriorityQueue<Disk>();
		PriorityQueue<Double> comparing = new PriorityQueue<>(new Comparator<Double>() {
			@Override
			public int compare(Double d1rs, Double d2rs) {
				if (d1rs < d2rs) {
					return 1;
				} else if (d2rs < d1rs) {
					return -1;
				}
				return 0;
			}
		});
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				comparing.offer((double) scanner.nextInt());
			}
			int id = 0;
			while (!comparing.isEmpty()) {
				int n = comparing.poll().intValue();
				if (disks.size() < 1) {
					Disk d = new Disk(id);
					id++;
					d.add(n);
					disks.offer(d);
					continue;
				}
				if (disks.peek().getSpace() < n) {
					Disk d = new Disk(id);
					id++;
					d.add(n);
					disks.offer(d);
				} else {
					Disk d = disks.poll();
					d.add(n);
					disks.offer(d);
				}

			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		File file = new File("input20.txt");
		worstFit(file);
		Queue<Disk> temp = new PriorityQueue<Disk>();
		System.out.println("Total size = " + (double) size / 1000000 + "GB");
		System.out.println("Disks req'd = " + disks.size());
		temp = disks;
		while (!temp.isEmpty() && disks.size() < 100) {
			Disk d = temp.poll();
			System.out.println(d.toString());
		}
		worstFitDecreasing(file);
		temp = new PriorityQueue<Disk>();
		System.out.println("Total size = " + (double) size / 1000000 + "GB");
		System.out.println("Disks req'd = " + disks.size());
		temp = disks;
		while (!temp.isEmpty() && disks.size() < 100) {
			Disk d = temp.poll();
			System.out.println(d.toString());
		}

	}
}
