package p2.enums;

public enum TrainStatus
{
	Completed("Completed"), Initialised("Initialised"), Started("Started"); 
	
	private String description;

	/**
	 * Sets the description of the train status
	 *
	 * @param description
	 */

	TrainStatus(String description)
	{
		this.description = description;
	}

	/**
	 * Gets the description of the train status
	 *
	 * @return
	 */

	public String getDescription()
	{
		return description;
	}


}
