package p2.enums;

public enum SystemStatus
{
	
	Initialised("System is Initialised"),
	Operational("System is Operational"),
	Deadlocked("System is Deadlocked"),
	Finished("No More trains!");


	private String description;

	/**
	 * Sets the system description
	 *
	 * @param description
	 */

	SystemStatus(String description)
	{
		this.description = description;
	}

	/**
	 * Gets the system description
	 *
	 * @return
	 */

	public String getDescription()
	{
		return description;
	}

}
