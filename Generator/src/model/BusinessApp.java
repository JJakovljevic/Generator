package model;

public class BusinessApp {
	
	private FMPackage parent;

	public BusinessApp() {
		super();
	}

	public BusinessApp(FMPackage parent) {
		super();
		this.parent = parent;
	}

	public FMPackage getParent() {
		return parent;
	}

	public void setParent(FMPackage parent) {
		this.parent = parent;
	}
	
}
