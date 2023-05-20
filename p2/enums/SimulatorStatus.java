package p2.enums;

public enum SimulatorStatus
{
	
	Uninitialised("Simulator is Uninitialised."),
	Initialised("Simulator is Initialised."),
	Paused("Simulator is Paused."),
	Working("Simulator is Working."),
	Finished(".Simulator has Stopped.");



	private String description;

	/**
	 * Sets description for simulator status
	 *
	 * @param description
	 */

	SimulatorStatus(String description)
	{
		this.description = description;
	}

	/**
	 * Gets description for simulator status
	 *
	 *
	 * @return
	 */

	public String getDescription()
	{
		return description;
	}

}
