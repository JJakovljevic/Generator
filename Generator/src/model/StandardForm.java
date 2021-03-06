package model;

public class StandardForm extends UIClass {

	private Boolean add;
	private Boolean delete;
	private Boolean update;
	private Boolean copy;

	public StandardForm(String label, Boolean add, Boolean delete, Boolean update, Boolean copy) {
		super(label);
		this.add = add;
		this.delete = delete;
		this.update = update;
		this.copy = copy;
	}

	public Boolean getAdd() {
		return add;
	}

	public void setAdd(Boolean add) {
		this.add = add;
	}

	public Boolean getDelete() {
		return delete;
	}

	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

	public Boolean getUpdate() {
		return update;
	}

	public void setUpdate(Boolean update) {
		this.update = update;
	}

	public Boolean getCopy() {
		return copy;
	}

	public void setCopy(Boolean copy) {
		this.copy = copy;
	}

}
