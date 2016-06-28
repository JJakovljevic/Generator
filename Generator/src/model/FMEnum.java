package model;

import java.util.ArrayList;
import java.util.List;

public class FMEnum extends FMType {

    private List<FMEnumLiteral> literals;
    
    public FMEnum(String name, String typePackage) {
        super(name, typePackage);
        this.literals = new ArrayList<FMEnumLiteral>();
    }
    
    public List <FMEnumLiteral> getLiterals() {
        return literals;
    }
    
}
