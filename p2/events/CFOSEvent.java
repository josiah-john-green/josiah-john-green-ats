package p2.events;

import p2.enums.Action;

public class CFOSEvent extends Event
{

	private Action action;

	/**
	 * Accepts the string object, and time into super and sets the action
	 *
	 * @param object
	 * @param time
	 * @param action
	 */

	public CFOSEvent(String object, int time, Action action)
	{
		super(object, time);
		this.action = action;
	}

	/**
	 * Checks if p2.events.CFOSEvent is equal to the action
	 *
	 * @param oe
	 * @return
	 */

	@Override
	public boolean equals(Object oe)
	{
		if (oe instanceof CFOSEvent)
		{
			return super.equals(oe) && action == ((CFOSEvent) oe).action;
		}
		return false;
	}

	/**
	 * Returns action and prints the event to the toString
	 *
	 * @return
	 */

	@Override
	public String toString()
	{
		return action.getDescription() + " p2.events.Event [" + super.toString() + "]";
	}

}
