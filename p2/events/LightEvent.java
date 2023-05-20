package p2.events;

import p2.enums.Light;

public class LightEvent extends Event
{

	private Light fromColour, toColour; /* once set are immutable */

	/**
	 * Accepts string objectID, and time into super and sets the fromColour and toColour
	 *
	 * @param objectID
	 * @param time
	 * @param fromColour
	 * @param toColour
	 */

	public LightEvent(String objectID, int time, Light fromColour, Light toColour)
	{
		super(objectID, time);
		this.fromColour = fromColour;
		this.toColour = toColour;
	}

	/**
	 * Prints super (containing objectID and time), fromColout and toColour
	 *
	 * @return
	 */

	@Override
	public String toString()
	{
		return "p2.events.LightEvent [" + super.toString() + ", From colour=" + fromColour.getDescription() + ", To colour="
				+ toColour.getDescription() + "]";
	}

	/**
	 * Checks if object equal fromColour
	 *
	 * @param le
	 * @return
	 */

	@Override
	public boolean equals(Object le)
	{
		if (le instanceof LightEvent)
		{
			return super.equals(le) && fromColour == ((LightEvent) le).fromColour
					&& toColour == ((LightEvent) le).toColour;
		}
		return false;
	}	
	
}
