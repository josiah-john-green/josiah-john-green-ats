package bnk.enums;

public enum Action {
	 Close("Close"), Finish("Finish"),Open("Open"),Start("Start");
	
	private String description;

	Action(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}


}
