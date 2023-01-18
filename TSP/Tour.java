
public class Tour {
	int size;
	Node head;

	private class Node {
		Point p;
		Node next;

		public Node(Point p) {
			this.p = p;
		}

		public Point getPoint() {
			return p;
		}
	}

	/** create an empty tour */
	public Tour() {
		head = null;
		size = 0;
	}

	/** create a four-point tour, for debugging */
	public Tour(Point a, Point b, Point c, Point d) {
		head = new Node(a);
		head.next = new Node(b);
		head.next.next = new Node(c);
		head.next.next.next = new Node(d);
		head.next.next.next.next = head;
		size = 4;
	}

	/** print tour (one point per line) to std output */
	public void show() {
		Node current = head;
		for (int i = 0; i < size(); i++) {
			System.out.println(current.getPoint().toString());
			current = current.next;
		}
	}

	/** draw the tour using StdDraw */
	public void draw() {
		Node current = head;
		for (int i = 0; i < size(); i++) {
			current.getPoint().drawTo(current.next.getPoint());
			current = current.next;
		}
	}

	/** return number of nodes in the tour */
	public int size() {
		return size;
	}

	/**
	 * return the total distance "traveled", from start to all nodes and back to
	 * start
	 */
	public double distance() {
		Node current = head;
		double total = 0;
		for (int i = 0; i < size; i++) {
			total += current.getPoint().distanceTo(current.next.getPoint());
			current = current.next;
		}
		return total;
	}

	/** insert p using nearest neighbor heuristic */
	public void insertNearest(Point p) {
		if (head == null) {
			head = new Node(p);
		}
		if (head.next == null) {
			head.next = new Node(p);
		} else {
			double minDist = head.getPoint().distanceTo(p);
			Node current = head.next;
			Node min = head;
			for (int i = 0; i < size; i++) {
				if (current.getPoint().distanceTo(p) < minDist) {
					min = current;
					minDist = current.getPoint().distanceTo(p);
				}
				current = current.next;
			}

			Node temp = min;
			if (min.next != null) {
				temp = min.next;
				min.next = new Node(p);
				min.next.next = temp;
			} else {
				min.next = new Node(p);
			}
		}
		size++;
	}

	/** insert p using smallest increase heuristic */
	public void insertSmallest(Point p) {
		if (head == null) {
			head = new Node(p);
		}
		if (head.next == null) {
			head.next = new Node(p);
		} else {
			Node min = head;
			Node current = head;
			double difference = (current.getPoint().distanceTo(p) + current.next.getPoint().distanceTo(p))
					- current.getPoint().distanceTo(current.next.getPoint());
			while (current.next != null) {
				if ((current.getPoint().distanceTo(p) + current.next.getPoint().distanceTo(p))
						- current.getPoint().distanceTo(current.next.getPoint()) < difference) {
					difference = (current.getPoint().distanceTo(p) + current.next.getPoint().distanceTo(p))
							- current.getPoint().distanceTo(current.next.getPoint());
					min = current;
				}
				current = current.next;
			}
			Node temp=min.next;
			min.next = new Node(p);
			min.next.next = temp;

		}
		size++;
	}
}