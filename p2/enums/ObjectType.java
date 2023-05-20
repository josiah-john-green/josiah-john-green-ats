package p2.enums;

public enum ObjectType
{

	Route_("Route"), Segment_("Segment"), Station_("Station"), Train_("Train");

	private String description;

	/**
	 * Sets object description
	 *
	 * @param description
	 */

	ObjectType(String description)
	{
		this.description = description;
	}

	/**
	 * Gets object description
	 *
	 * @return
	 */

	public String getDescription()
	{
		return description;
	}

}
