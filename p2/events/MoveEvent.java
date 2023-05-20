package p2.events;

public class MoveEvent extends Event
{

	private String fromStation, toStation; /* once set are immutable */

	/**
	 * Accepts objectID and time into super, and sets fromStation and toStation
	 *
	 * @param objectID
	 * @param time
	 * @param fromStation
	 * @param toStation
	 */

	public MoveEvent(String objectID, int time, String fromStation, String toStation)
	{
		super(objectID, time);
		this.fromStation = fromStation;
	 	this.toStation = toStation;
	}

	/**
	 * Prints super (containing objectID and time), fromStation and toStation
	 *
	 * @return
	 */

	@Override
	public String toString()
	{
		return "p2.events.MoveEvent [" + super.toString() + ", From p2.logging.Station=" + fromStation + ", To p2.logging.Station=" + toStation + "]";
	}

	/**
	 * Checks if object is equal to fromStation
	 *
	 * @param me
	 * @return
	 */

	@Override
	public boolean equals(Object me)
	{
		if (me instanceof MoveEvent)
		{
			return super.equals(me) && fromStation.equals(((MoveEvent) me).fromStation)
					&& toStation.equals(((MoveEvent) me).toStation);
		}
		return false;
	}

}
