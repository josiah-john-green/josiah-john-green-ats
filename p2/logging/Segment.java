package p2.logging;

import p2.ts.TrafficLight;
import p2.enums.Light;
import p2.enums.RSStatus;
import p2.events.Event;
import p2.interfaces.OpenClose;

public class Segment extends Logable implements Comparable<Segment>, OpenClose
{

	private Station segmentStart; /* once set, is immutable */
	private Station segmentEnd; /* once set, is immutable */
	private String name; /* once set, is immutable */
	private RSStatus status = RSStatus.Open;
	private TrafficLight trafficLight = new TrafficLight();
	private String train = "-1";

	/**
	 * Sets the name of the segment, the start segment and the end segment.
	 *
	 * @param name
	 * @param segmentStart
	 * @param segmentEnd
	 */

	public Segment(String name, Station segmentStart, Station segmentEnd)
	{
		setName(name);
		setSegmentStart(segmentStart);
		setSegmentEnd(segmentEnd);
	}

	/**
	 * Gets the segment name.
	 *
	 * @return
	 */

	public String getName()
	{
		return name;
	}

	/**
	 * Sets the name for the segment.
	 *
	 * @param name
	 */

	private void setName(String name)
	{
		this.name = name != null && !name.equals(this.name) ? name.trim() : this.name;
	}

	/**
	 * Gets the start segment.
	 *
	 * @return
	 */

	public Station getSegmentStart()
	{
		return segmentStart;
	}

	/**
	 * Sets the start segment.
	 *
	 * @param segmentStart
	 */

	private void setSegmentStart(Station segmentStart)
	{
		this.segmentStart = segmentStart;
	}

	/**
	 * Gets the end segment.
	 *
	 * @return
	 */

	public Station getSegmentEnd()
	{
		return segmentEnd;
	}

	/**
	 * Sets the end segment.
	 *
	 * @param segmentEnd
	 */

	private void setSegmentEnd(Station segmentEnd)
	{
		this.segmentEnd = segmentEnd;
	}

	/**
	 * Checks whether or not the end segment has a train in it.
	 *
	 * @return
	 */

	public boolean hasTrain()
	{
		return !train.equals("-1");
	}

	/**
	 * Opens the segment.
	 *
	 * @return
	 */

	@Override
	public boolean isOpen()
	{
		return status == RSStatus.Open;
	}

	/**
	 * Accepts the train, if the status of the route is open and the train is more than zero (0).
	 *
	 * @param train
	 */

	public Event acceptTrain(String train)
	{
		if ((train != null && train.trim() != "") && isOpen())
		{
			this.train = train;
		}
		return null;
	}

	/**
	 * Releases the train, if the status of the route is open.
	 *
	 */

	public Event releaseTrain()
	{
		if (isOpen())
		{
			train = "-1";
		}
		return null;
	}

	/**
	 * Changes the traffic light in the segment.
	 *
	 */

	public Event changeLight()
	{
		trafficLight.changeLight();
		return null;
	}

	/**
	 * Gets the traffic light colour.
	 *
	 * @return
	 */

	public Light lightColour()
	{
		return trafficLight.getColour();
	}

	/**
	 * Verifies true, "if the start segment is not equal to the end segment, if the status of the route
	 * is equal to open, the status of the end of the segment is equal to open" or "if the status of the
	 * route is equal to closed and the end of the segment is equal to closed" or " if the start of the
	 * segment is equal to the end of the segment" and false otherwise.
	 *
	 * @return
	 */


	public boolean verify()
	{

		boolean verified = false;

		if (this.name != null && this.segmentStart.verify() && this.segmentEnd.verify() && this.trafficLight.verify())
		{
			if (!this.segmentStart.equals(this.segmentEnd))
			{
				if (this.status == RSStatus.Open)
				{
					if (this.segmentEnd.isOpen())
					{
						verified = true;
					}
				}
				else if (this.status == RSStatus.ClosedForMaintenance)
				{
					if (!this.segmentEnd.isOpen())
					{

						verified = true;

					}

				}
				else
				{

					verified = false;

				}

				verified = false;
			}
			else
			{

				verified = true;

			}

			verified = true;

		}

		return verified;

	}

	/**
	 * Closes the segment.
	 *
	 */

	@Override
	public Event close()
	{
		status = status == RSStatus.Open ? status = RSStatus.ClosedForMaintenance : status;
		return null;
	}

	/**
	 * Opens the segment.
	 *
	 */

	@Override
	public Event open()
	{
		status = status == RSStatus.ClosedForMaintenance ? status = RSStatus.Open : status;
		return null;
	}

	/**
	 * Prints the segment name, the start of the segment, the end of the segment, the status of the segment
	 * and the colour of the traffic light in the segment.
	 *
	 * @return
	 */

	@Override
	public String toString()
	{
		return "Segment [name=" + name + ", segmentStart=" + (segmentStart == null ? "none" : segmentStart.getName())
				+ ", segmentEnd=" + (segmentEnd == null ? "none" : segmentEnd.getName()) + ", status="
				+ status.getDescription() + ", trafficLight=" + trafficLight + ", train="
				+ (train.equals("-1") ? "none" : train) + ", verified=" + (verify() ? "Yes" : "No") + "]";
	}

	/**
	 * Compares the name given/inputted to the name in the segment
	 *
	 * @param segment the object to be compared.
	 * @return
	 */

	@Override /* two segments with the same start and end stations are the same segment */
	public int compareTo(Segment segment)
	{
		String s = segmentStart.getName().concat(segmentEnd.getName()),
				e = segment.getSegmentStart().getName().concat(segment.getSegmentEnd().getName());
		return s.compareTo(e);
	}

	/**
	 *
	 * @return
	 */

	@Override
	public boolean validate()
	{
		return super.validate();
	}
}
