public class PhoneNumber {
	String number;

	public PhoneNumber(String number)
	{
		this.number = number;
	}
	
	@Override
	public int hashCode() {
		return number.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return this.number.equals(((PhoneNumber) obj).number);
	}
}