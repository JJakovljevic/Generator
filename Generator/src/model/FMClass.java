package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FMClass extends FMType {	
	
	private String visibility;
	private List<FMProperty> properties = new ArrayList<FMProperty>();
	private List<String> importedPackages = new ArrayList<String>();
	private UIClass uiClass;
	
	
	
	public FMClass(String name, String classPackage, String visibility) {
		super(name, classPackage);		
		this.visibility = visibility;  
	}	
	
	public void setProperties(List<FMProperty> properties) {
		this.properties = properties;
	}

	public void setImportedPackages(List<String> importedPackages) {
		this.importedPackages = importedPackages;
	}

	public UIClass getUiClass() {
		return uiClass;
	}

	public void setUiClass(UIClass uiClass) {
		this.uiClass = uiClass;
	}

	public List<FMProperty> getProperties(){
		return properties;
	}
	
	public Iterator<FMProperty> getPropertyIterator(){
		return properties.iterator();
	}
	
	public void addProperty(FMProperty property){
		properties.add(property);		
	}
	
	public int getPropertyCount(){
		return properties.size();
	}
	
	public List<String> getImportedPackages(){
		return importedPackages;
	}

	public Iterator<String> getImportedIterator(){
		return importedPackages.iterator();
	}
	
	public void addImportedPackage(String importedPackage){
		importedPackages.add(importedPackage);		
	}
	
	public int getImportedCount(){
		return properties.size();
	}
	
	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}	

	
	
}
