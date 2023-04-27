public class Person {
	private String name;

	public Person(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Math.abs(name.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		return name.equals(((Person) obj).name);
	}

}