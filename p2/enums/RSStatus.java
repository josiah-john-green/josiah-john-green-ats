package p2.enums;

public enum RSStatus
{
	
	Open("Open"),
	ClosedForMaintenance("Closed for Maintenance!");

	private String description;

	/**
	 * Sets the status description of the route
	 *
	 * @param description
	 */

	RSStatus(String description)
	{
		this.description = description;
	}

	/**
	 * Gets the status description of the route
	 *
	 * @return
	 */

	public String getDescription()
	{
		return description;
	}


}
