package cs414.a1.sanjusam;

public class Qualification {

	private final String description;

	Qualification (final String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object other) {
		if (this == other) {
			return true;
		}
		if (other == null)
			return false;
		if (this.getClass() != other.getClass())
			return false;
		final Qualification that = (Qualification) other;
		if (this.description == null) {
			if (that.description != null)
				return false;
		} else if (!this.description.equals(that.description))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return description;
	}
}
