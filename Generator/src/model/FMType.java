package model;

public class FMType extends FMNamedElement {	

	public String getTypePackage() {
		return typePackage;
	}

	/**
	 * 
	 * @param name naziv tipa
	 * @param typePackage kvalifikovano ime paketa koji treba ukljuciti, 
				prazan string ako je tip podataka iz stadnardne biblioteke
	 */
	public FMType(String name, String typePackage) {
		super(name);
		this.typePackage = typePackage;
	}

	public void setTypePackage(String typePackage) {
		this.typePackage = typePackage;
	}
	
	//kvalifikovano ime paketa koji treba ukljuciti, 
	//prazan string ako je tip podataka iz stadnardne biblioteke
	private String typePackage;

}
