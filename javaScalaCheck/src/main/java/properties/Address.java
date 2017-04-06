package properties;

public class Address {
	public String street;
	public int zipcode;

	@Override
	public String toString() {
		return "Address [street=" + street + ", zipcode=" + zipcode + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + zipcode;
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
		Address other = (Address) obj;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (zipcode != other.zipcode)
			return false;
		return true;
	}

}
