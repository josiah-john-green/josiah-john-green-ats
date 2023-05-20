package bnk.enums;

public enum SystemStatus {
	
	Initialised("System is Initialised"),
	Operational("System is Operational"),
	Deadlocked("System is Deadlocked"),
	Finished("No More trains!");

	private String description;

	SystemStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
