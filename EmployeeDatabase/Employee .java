public class Employee {
	private int id;
	private String name;

	public Employee(String name, int id) {
		this.id = id;
		this.name = name;
	}

	@Override
	public boolean equals(Object other) {
		return this.getID() == ((Employee) other).getID() && this.getName().equals(((Employee) other).getName());
	}

	@Override
	public int hashCode() {
		return id;
	}

	public int getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}