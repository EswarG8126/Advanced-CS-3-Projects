import java.util.Arrays;

public class EmployeeDatabase {
	private int size;
	private Employee[] hs;

	public EmployeeDatabase() {
		size = 0;
		hs = new Employee[11];
	}

	public EmployeeDatabase(int capacity) {
		size = 0;
		hs = new Employee[capacity];
	}

	public boolean contains(Employee key) {
		if (key != null) {
			int index = Math.abs(key.hashCode() % hs.length);
			/* Linear Code:
			while (hs[index] != null) {
				if (hs[index].equals(key)) {
					return true;
				}
				index = (index + 1) % hs.length;
			}*/
			
			//Quadratic Code: 
			for (int i = 0; hs[index] != null; i++) {
				if (hs[index].equals(key)) {
					return true;
				}
				index = Math.abs((index + (i * i)) % hs.length);
			}	 
			return false;
		}
		return false;
	}

	public boolean add(Employee key) {
		if (size() == hs.length) {
			return false;
		}
		if (contains(key)) {
			return false;
		}
		if (key != null) {
			int index = Math.abs(key.hashCode() % hs.length);
            /* Linear Code:
			while (hs[index] != null) {
				index = (index + 1) % hs.length;
			}
			*/
		
			//Quadratic Code: 
			for (int i = 0; hs[index] != null; i++) {
				index = Math.abs((index + (i * i)) % hs.length);
			}
			hs[index] = key;
			size++;
			return true;
		}
		return false;
	}

	public boolean remove(Employee key) {
		if (size() == hs.length) {
			return false;
		}
		if (!contains(key)) {
			return false;
		}
		if (key != null) {
			int index = Math.abs(key.hashCode() % hs.length);
            /*Linear Code:    
			while (!(hs[index].equals(key))) {
				index = (index + 1) % hs.length;
			}
            */
			
			//Quadratic Code: 
			for (int i = 0; !hs[index].equals(key); i++) {
				index = Math.abs((index + (i * i)) % hs.length);
			}
			hs[index] = null;
			size--;
			return true;
		}
		return false;
	}

	public int size() {
		return size;
	}

	@Override
	public String toString() {
		return Arrays.toString(hs);
	}
}