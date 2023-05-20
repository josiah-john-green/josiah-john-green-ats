package p2.logging;

import p2.enums.RSStatus;
import p2.events.Event;
import p2.interfaces.OpenClose;

import java.util.ArrayList;
import java.util.Iterator;

public class Route extends Logable implements Comparable<Route>, OpenClose {

	private String name; /* once set, name is immutable */
	private boolean isRoundTrip = false; /* once set, isRoundTrip is immutable */
	private RSStatus status = RSStatus.Open;
	private ArrayList<Segment> segments = new ArrayList<Segment>();

	/**
	 * Sets the name of the round and checks whether or not it is a round trip
	 *
	 * @param name
	 * @param isRoundTrip
	 */

	public Route(String name, boolean isRoundTrip)
	{
		setName(name);
		setRoundTrip(isRoundTrip);
	}

	/**
	 * Set the name of the route
	 *
	 * @param name
	 */

	public Route(String name)
	{
		setName(name);
	}

	/**
	 * Gets the name of the route
	 *
	 * @return
	 */

	public String getName()
	{
		return name;
	}

	/**
	 * Sets the name of the route
	 *
	 * @param name
	 */

	private void setName(String name)
	{
		this.name = name;
	}

	@Override
	public boolean isOpen()
	{
		return status == RSStatus.Open;
	}

	/**
	 * Sets the status of the route whether opened or closed
	 *
	 * @return status
	 */


	public void setStatus(RSStatus status)
	{
		this.status = status;
	}

	/**
	 * Gets the Route round trip to be true or false
	 *
	 * @param isRoundTrip
	 */

	private void setRoundTrip(boolean isRoundTrip)
	{
		this.isRoundTrip = isRoundTrip;
	}

	/**
	 * Checks whether or not the route is a round trip
	 *
	 * @return
	 */

	public boolean isRoundTrip()
	{
		return isRoundTrip;
	}

	/**
	 * Iterated through the Station List and get the names of the start and the end station
	 *
	 * @return
	 */

	private ArrayList<Station> getStationList()
	{
		ArrayList<Station> sss = new ArrayList<Station>();

		return sss;
	}

	/**
	 *
	 * @return
	 */

	public ArrayList<String> getStationStrings()
	{
		ArrayList<String> sss = new ArrayList<String>();

		for (Station s : getStationList())
		{
			sss.add(s.getName());
		}
		return sss;

	}

	/**
	 * Gets the start station, checking the segemnt list to see whether or not one is present
	 *
	 * @return
	 */

	public Station getStart()
	{
		return segments.size() > 0 ? segments.get(0).getSegmentStart() : null;
	}

	/**
	 * Gets the end station, checking the segment list to see whether or not one is present
	 *
	 * @return
	 */

	public Station getEnd()
	{
		return segments.size() > 0 ? segments.get(segments.size() - 1).getSegmentEnd() : null;
	}

	/**
	 * Gets the next p2.logging.Station in the station list, iteratng through the station list,
	 * and increment the index by 1, to get the station after the first found.
	 *
	 * @param station
	 * @param isAtStart
	 * @return
	 */

	public String getNextStation(String station, boolean isAtStart)
	{

		String empty = "";

		if (segments.size() > 0 && station != null)
		{

			if (isRoundTrip)
			{

				if (isAtStart)
				{
					return segments.get(0).getSegmentEnd().getName();
				}
				else if (station.equals(getStart().getName()))
				{
					return empty;
				}

			}


			ArrayList<Station> sss = getStationList();

			for (Station x : sss)
			{

				if (x.getName().equals(station))
				{

					if (sss.indexOf(x) + 1 == sss.size())
					{
						return empty;
					}
					else
					{
						return sss.get(sss.indexOf(x) + 1).getName();

					}
				}
			}
		}
		return empty;
	}

	/**
	 * Gets the previous Station in the station list, iterating through the station list,
	 * and decrement the index by 1, to get the station before the current one found.
	 *
	 * @param station
	 * @return
	 */

	public String getPreviousStation(String station, boolean isAtStart)
	{

		String result = "";

		if(segments.size() > 0 && station != null)
		{

			ArrayList <Station> sss = getStationList();

			for(Station x: sss)
			{
				if (x.getName().equals(station))
				{

					if (sss.indexOf(x) == 0)
					{

						result = "";

					}
					else
					{

						result = sss.get(sss.indexOf(x) - 1).getName();

					}

				}


			}


		}
		return result;

	}

	/**
	 * Checks whether or not a specific station is in the p2.logging.Station list
	 *
	 * @param station
	 * @return
	 */

	public boolean canGetTo(String station)
	{
		for (Station x : getStationList())
		{
			if (x.getName().equals(station))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 *
	 * @param station
	 * @return
	 */

	public boolean isInnerStation(String station)
	{
		// returns true if station is not a start or end station for the route

		for (Station x : getStationList())
		{
			if ((!getStart().equals(station)) && (!getEnd().equals(station)))
			{
				return true;
			}
		}
		return false;

	}

	/**
	 * Adds a segment to the route, for a "segment".
	 *
	 * @param segment
	 */

	public void addSegment(Segment segment)
	{

		if (segments.size() == 0)
		{
			segments.add(segment);
			return;
		}

		if (segments.size() > 0 && !containsSegmentName(segment.getName())
				&& segment.getSegmentStart().compareTo(segments.get(segments.size() - 1).getSegmentEnd()) == 0)
		{
			segments.add(segment);

		}

	}

	/**
	 * Add a segment to the route, for an "array of segments".
	 *
	 * @param sgmnts
	 */

	public void addSegments(ArrayList<Segment> sgmnts)
	{

		if (sgmnts.size() > 0)
		{
			sgmnts.forEach(s -> addSegment(s));
		}
	}

	/**
	 * Removes a segment(s) from the route
	 *
	 * @param segment
	 */

	public void removeSegment(String segment)
	{

		if (segments.size() == 0)
		{
			return;
		}

		if (segments.size() > 0 && containsSegmentName(segment.trim()))
		{
			int i = 0;

			do
			{
				if (segments.get(i).getName().equals(segment.trim()))
				{
					break;
				}
				i++;
			}
			while (i < segments.size());

			segments.remove(i);
		}
	}

	/**
	 * Checks if a route contains a segment
	 *
	 * @param segment
	 * @return
	 */

	public boolean containsSegmentName(String segment)
	{
		Iterator<Segment> sgs = segments.iterator();

		while (sgs.hasNext())
		{
			if (sgs.next().getName().equals(segment))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Changes the light if the start of the segment is equal to the segment name
	 *
	 * @param startOfSegment
	 */

	public Event changeLight(String startOfSegment)
	{
		for (Segment s : segments)
		{
			if (s.getName().equals(startOfSegment))
			{
				s.changeLight();
				break;
			}
		}
		return null;
	}

	/**
	 * Verifies to true, if the round trip, and the getStart is equal to the getEnd or if it`s not a round trip,
	 * and the getStart is not equal to the getEnd; if the previous getStart is equal to the current getEnd,
	 * and the current getEnd is equal to the next getStart and false otherwise.
	 *
	 * @return
	 */

	public boolean verify()
	{
		boolean verified = false;

		if(this.name != null && segments.size() > 0)
		{

			if (this.isRoundTrip)
			{
				if(getStart().equals(getEnd()))
				{
					verified = true;
				}

			}

			else
			{

				if(!getStart().equals(getEnd()))
				{
					verified = true;
				}

				for (int i = 0; i < segments.size() + 1; i++)
				{

					if (segments.get(i).verify() && segments.get(i).getSegmentEnd().equals(segments.get(i + 1).getSegmentStart()))
					{

						verified = true;

					}
					else
					{

						verified = false;
						break;

					}

				}

			}
		}


		return verified;
	}

	/**
	 * Closes the route
	 *
	 */

	@Override
	public Event close()
	{
		status = status == RSStatus.Open ? status = RSStatus.ClosedForMaintenance : status;
		return null;
	}

	/**
	 * Opens the route
	 */

	@Override
	public Event open()
	{
		status = status == RSStatus.ClosedForMaintenance ? status = RSStatus.Open : status;
		return null;
	}

	/**
	 * Prints the name of the route, whether or not it`s a round trip, the status of the route,
	 * and the segments in the route.
	 *
	 * @return
	 */

	@Override
	public String toString()
	{
		String str = "[";

		for (Segment s : segments)
		{
			str += s.getName() + (segments.get(segments.size() - 1) == s ? "]" : ", ");
		}

		return "Route [name=" + name + ", isRoundTrip=" + isRoundTrip + ", status=" + status + ", segments="
				+ (segments == null || segments.size() == 0 ? "none" : str) + ", verified=" + (verify() ? "Yes" : "No")
				+ "]";
	}

	/**
	 * Compares name given/inputted to the name of the route
	 *
	 * @param route the object to be compared.
	 * @return
	 */

	@Override /* mostly used to sort a collection of p2.logging.Route Objects */
	public int compareTo(Route route)
	{
		return name.compareTo(route.getName());
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
