package model;

import java.util.List;

public class Group {
	
	private FMProperty parent;
	private List<UIElement> elements;
	
	public Group() {
		super();
	}

	public Group(FMProperty parent, List<UIElement> elements) {
		super();
		this.parent = parent;
		this.elements = elements;
	}
	
	public List<UIElement> getElements() {
		return elements;
	}

	public void setElements(List<UIElement> elements) {
		this.elements = elements;
	}

	public FMProperty getParent() {
		return parent;
	}

	public void setParent(FMProperty parent) {
		this.parent = parent;
	}
	
	
}
