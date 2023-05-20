package p2.enums;

public enum Action
{

	Close("Close"), Finish("Finish"),Open("Open"),Start("Start");
	
	private String description;

	/**
	 * Sets action description
	 *
	 * @param description
	 */

	Action(String description)
	{
		this.description = description;
	}

	/**
	 * Gets action description
	 *
	 * @return
	 */

	public String getDescription()
	{
		return description;
	}


}
