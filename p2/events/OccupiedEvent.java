package p2.events;

public class OccupiedEvent extends Event
{

	/* once set are immutable */
	private String train;
	private boolean isEntry;

	/**
	 * Accepts objectID and time into super, and sets train and isEntry
	 *
	 * @param objectID
	 * @param time
	 * @param train
	 * @param isEntry
	 */

	public OccupiedEvent(String objectID, int time, String train, boolean isEntry)
	{
		super(objectID, time);
		this.train = train;
		this.isEntry = isEntry;
	}

	/**
	 * Checks if the object(oe) is equal to train and equal to isEntry
	 *
	 * @param oe
	 * @return
	 */

	@Override
	public boolean equals(Object oe)
	{
		if (oe instanceof OccupiedEvent)
		{
			return super.equals(oe) && train.equals(((OccupiedEvent) oe).train)
					&& isEntry == ((OccupiedEvent) oe).isEntry;
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
		return (isEntry? "Enter p2.logging.Station p2.events.Event" : "Left p2.logging.Station p2.events.Event") + "[" + super.toString() + ", p2.logging.Train="
				+ train + "]";
	}
}
