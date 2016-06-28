package model;

public class UIClass extends UIElement {

	private FMClass parent;
	public UIClass(String label) {
		super(label);
	}
	public UIClass(String label, FMClass parent) {
		super(label);
		this.parent = parent;
	}
	public FMClass getParent() {
		return parent;
	}
	public void setParent(FMClass parent) {
		this.parent = parent;
	}
}
