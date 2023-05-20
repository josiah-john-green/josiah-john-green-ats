package bnk.enums;

public enum TrainStatus {
	Completed("Completed"), Initialised("Initialised"), Started("Started"); 
	
	private String description;

	TrainStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}


}
