package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FMModel {
    private Map<String, FMNamedElement> elements;
    private String packagePrefix;

	public FMModel() {
	    elements = new HashMap<String, FMNamedElement>();
	}
	
	public FMNamedElement getElementById(String id) {
	    return elements.get(id);
	}
	
	public void addElement(String id,FMNamedElement element) {
	    elements.put(id, element);
	}

	public List<FMClass> getClasses() {
	    List<FMClass> classes = new ArrayList<FMClass>();
	    
	    for (FMNamedElement element : elements.values()) {
	        if (element instanceof FMClass) {
	            classes.add((FMClass)element);
	        }
	    }
	    
		return classes;
	}

    public List<FMEnum> getEnums() {
        List<FMEnum> enums = new ArrayList<FMEnum>();
        
        for (Object element : elements.values()) {
            if (element instanceof FMEnum) {
                enums.add((FMEnum)element);
            }
        }
        
        return enums;
    }

    public String getPackagePrefix() {
        return packagePrefix;
    }

    public void setPackagePrefix(String packagePrefix) {
        this.packagePrefix = packagePrefix;
    }

}
