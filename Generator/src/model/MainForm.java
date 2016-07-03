package model;

public class MainForm extends UIClass {

	private String putanjaDoSlike;
	private String putanjaDoIkonice;
	
	public MainForm(String label) {
		super(label);
	}

	public String getPutanjaDoSlike() {
		return putanjaDoSlike;
	}

	public void setPutanjaDoSlike(String putanjaDoSlike) {
		this.putanjaDoSlike = putanjaDoSlike;
	}

	public String getPutanjaDoIkonice() {
		return putanjaDoIkonice;
	}

	public void setPutanjaDoIkonice(String putanjaDoIkonice) {
		this.putanjaDoIkonice = putanjaDoIkonice;
	}

}
