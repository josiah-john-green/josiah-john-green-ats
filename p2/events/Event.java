package p2.events;

abstract public class Event{
	
	private String object; 
	private int time;

	/**
	 * Sets the string object and time
	 *
	 * @param object
	 * @param time
	 */

	public Event(String object, int time)
	{
		this.object = object;
		this.time = time;
	}

	/**
	 * Gets string object
	 *
	 * @return
	 */

	public String getObject()
	{
		return object;
	}

	/**
	 *
	 * @return
	 */

	public int getTime()
	{
		return time;
	}

	/**
	 *
	 * @param evnt
	 * @return
	 */

	@Override
	public boolean equals(Object evnt)
	{
		if (evnt instanceof Event)
		{
			return object.equals(((Event) evnt).object) && time == (((Event) evnt).time);
		}
		return false;
	}

	/**
	 *
	 * @return
	 */

	@Override
	public String toString()
	{
		return "Object=" + object + ", Time()=" + time;
	}
}
