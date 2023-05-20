package p2.enums;

public enum Light
{
	
	Red("Light is Red"),
	Green("Light is Green!");

	private String description;

	/**
	 * Sets the string description of the light enum
	 *
	 * @param description
	 */

	Light(String description)
	{
		this.description = description;
	}

	/**
	 * Gets the string description of the light enum
	 *
	 * @return
	 */

	public String getDescription()
	{
		return description;
	}

}
