package model;

public class UIProperty extends UIElement {

	private FMProperty parent;
	private Integer length;
	private Integer precision;
	private ComponentKind componentKind;
	private Boolean display;
	
	public UIProperty(String label) {
		super(label);
	}

	public UIProperty(String label, Integer length, Integer precision, ComponentKind componentKind, Boolean display) {
		super(label);
		this.length = length;
		this.precision = precision;
		this.componentKind = componentKind;
		this.display = display;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getPrecision() {
		return precision;
	}

	public void setPrecision(Integer precision) {
		this.precision = precision;
	}

	public ComponentKind getComponentKind() {
		return componentKind;
	}

	public void setComponentKind(ComponentKind componentKind) {
		this.componentKind = componentKind;
	}

	public Boolean getDisplay() {
		return display;
	}

	public void setDisplay(Boolean display) {
		this.display = display;
	}

	public FMProperty getParent() {
		return parent;
	}

	public void setParent(FMProperty parent) {
		this.parent = parent;
	}
	
}
