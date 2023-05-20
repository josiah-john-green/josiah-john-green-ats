package bnk.enums;

public enum RSStatus {
	
	Open("Open"),
	ClosedForMaintenance("Closed for Maintenance!");

	private String description;

	RSStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}


}
