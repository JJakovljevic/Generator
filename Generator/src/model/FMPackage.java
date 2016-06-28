package model;

public class FMPackage {
	private BusinessApp businessApp;
	
	public FMPackage() {
		super();
	}

	public FMPackage(BusinessApp businessApp) {
		super();
		this.businessApp = businessApp;
	}

	public BusinessApp getBusinessApp() {
		return businessApp;
	}

	public void setBusinessApp(BusinessApp businessApp) {
		this.businessApp = businessApp;
	}
	
	
}
