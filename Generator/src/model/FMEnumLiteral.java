package model;

public class FMEnumLiteral extends FMNamedElement {
    
    private String name;
    private String displayId;

    public FMEnumLiteral(String id, String name) {
        super(id);
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayId() {
        return displayId;
    }

    public void setDisplayId(String displayId) {
        this.displayId = displayId;
    }
    
}
