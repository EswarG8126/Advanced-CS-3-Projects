import java.util.ArrayList;

public class Disk implements Comparable<Disk> {
	private int space = 1_000_000;
	private int id;
	private ArrayList<Integer> fileSizes;

	public Disk(int id) {
		this.id = id;
		fileSizes = new ArrayList<Integer>();
	}

	@Override
	public int compareTo(Disk other) {
		if (this.getSpace() < other.getSpace()) {
			return 1;
		} else if (this.getSpace() > other.getSpace()) {
			return -1;
		}
		return 0;
	}

	public void add(int fileSize) {
		if (space - fileSize > 0) {
			fileSizes.add(fileSize);
			space -= fileSize;
		}
	}

	@Override
	public String toString() {
		String s = "\t" + id + "\t" + space + ":\t";
		for (int i : fileSizes) {
			s += " " + i;
		}
		return s;
	}

	public int getSpace() {
		return space;
	}

	public int getID() {
		return id;
	}
}