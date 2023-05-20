package bnk.enums;

public enum SimulatorStatus {
	
	Uninitialised("p2.logging.p2.logging.Simulator is Uninitialised."),
	Initialised("p2.logging.p2.logging.Simulator is Initialised."),
	Paused("p2.logging.p2.logging.Simulator is Paused."),
	Working("p2.logging.p2.logging.Simulator is Working."),
	Finished("p2.logging.p2.logging.Simulator has Stopped.");

	private String description;

	SimulatorStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
