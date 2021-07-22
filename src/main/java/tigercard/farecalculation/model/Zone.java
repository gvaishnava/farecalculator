package tigercard.farecalculation.model;

/**
 *  Zone is a logical group of stations, and commuters travels
 *  between zones a and fare is calculated based on travel between them.
 */
public class Zone {

	private int fromzone;
	private int tozone;

	public Zone(int fromzone, int tozone) {
		this.fromzone = fromzone;
		this.tozone = tozone;
	}

	public int getFromzone() {
		return fromzone;
	}

	public void setFromzone(int fromzone) {
		this.fromzone = fromzone;
	}

	public int getTozone() {
		return tozone;
	}

	public void setTozone(int tozone) {
		this.tozone = tozone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fromzone;
		result = prime * result + tozone;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Zone other = (Zone) obj;
		if (fromzone != other.fromzone)
			return false;
		if (tozone != other.tozone)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Zone [fromzone=" + fromzone + ", tozone=" + tozone + "]";
	}

}
