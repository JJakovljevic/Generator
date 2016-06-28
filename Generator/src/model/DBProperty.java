package model;

public class DBProperty {
	
	private FMProperty parent;
	private boolean id;
	private boolean mandatory;
	private boolean unique;
	
	public DBProperty() {
		super();
	}

	public DBProperty(boolean id, boolean mandatory, boolean unique) {
		super();
		this.id = id;
		this.mandatory = mandatory;
		this.unique = unique;
	}

	public FMProperty getParent() {
		return parent;
	}

	public void setParent(FMProperty parent) {
		this.parent = parent;
	}

	public boolean isId() {
		return id;
	}

	public void setId(boolean id) {
		this.id = id;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}
	
	
	
}
