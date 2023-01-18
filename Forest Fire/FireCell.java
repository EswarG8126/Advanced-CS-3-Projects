enum Status {
	DIRT, GREEN, BURNING
}

public class FireCell {
	private Status status;

	public FireCell() {
		status = Status.DIRT;
		if (Math.random() <= 0.60) {
			status = Status.GREEN;
		}
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status s) {
		status = s;
	}
}