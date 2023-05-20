package p2.ts;

import p2.enums.ObjectType;
import p2.enums.SystemStatus;
import p2.events.Event;

import p2.logging.Segment;
import p2.logging.Station;
import p2.logging.Route;
import p2.logging.Train;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrainSystem
{

	/* default values have been set */
	private SystemStatus status = SystemStatus.Initialised;
	private int currentTime = -1;

	private ArrayList<Route> routes = new ArrayList<Route>();
	private ArrayList<Train> trains = new ArrayList<Train>();
	private ArrayList<Station> stations = new ArrayList<Station>();
	private ArrayList<Segment> segments = new ArrayList<Segment>();

	public TrainSystem()
	{
	}

	/**
	 * Adds a station
	 *
	 * @param sname
	 */

	public void addStation(String sname)
	{

		Station station = new Station(sname);
		stations.add(station);

		System.out.println("Station Added.");

	}

	/**
	 * Removes a station.
	 *
	 * @param sname
	 */

	public void removeStation(String sname)
	{

		for (Station station: stations)
		{

			if (station.getName().equals(sname))
			{

				stations.remove(station);

			}

		}
		System.out.println("Station Removed.");

	}

	/**
	 * Opens a station.
	 *
	 * @param sname
	 */

	public Event openStation(String sname)
	{

		Event event = null;

		for (Station station: stations)
		{

			if (station.getName().equals(sname))
			{

				event = station.open();

			}

		}
		System.out.println("Station Opened.");

		return event;
	}

	/**
	 * Closes a station.
	 *
	 * @param sname
	 */

	public Event closeStation(String sname)
	{

		Event event = null;

		for (Station station: stations)
		{

			if (station.getName().equals(sname))
			{

				event = station.close();

			}

		}
		System.out.println("Station Closed");

		return event;
	}

	/**
	 * Adds a segment ot a route.
	 *
	 * @param sname
	 * @param start
	 * @param sEnd
	 */

	public void addSegment(String sname, String start, String sEnd)
	{

		Station strtStation = null;
		Station edStation = null;

		for(Station stat: stations)
		{

			if (stat.getName().equals(start))
			{

				strtStation = stat;

			}

			if (stat.getName().equals(sEnd))
			{

				edStation = stat;

			}


		}

		Segment segment = new Segment(sname, strtStation, edStation);
		segments.add(segment);

	}

	/**
	 * Removes a segment from a route.
	 *
	 * @param sname
	 */

	public void removeSegment(String sname)
	{

		for (Segment segment: segments)
		{

			if (segment.getName().equals(sname))
			{

				segments.remove(segment);

			}

		}
		System.out.println("Segment Removed.");


	}

	/**
	 * Opens a segment.
	 *
	 * @param sname
	 */

	public Event openSegment(String sname)
	{

		Event event = null;

		for (Segment segment: segments)
		{

			if (segment.getName().equals(sname))
			{

				event = segment.open();

			}

		}
		System.out.println("Segment Opened");

		return event;
	}

	/**
	 * Closes a segment.
	 *
	 * @param sname
	 */

	public Event closeSegment(String sname)
	{

		Event event = null;

		for (Segment segment: segments)
		{

			if (segment.getName().equals(sname))
			{

				event = segment.close();

			}

		}
		System.out.println("Segment Closed.");

		return event;
	}

	/**
	 * Adds a route to the system.
	 *
	 * @param rname
	 * @param isRoundTrip
	 * @param segs
	 */

	public void addRoute(String rname, boolean isRoundTrip, ArrayList<String> segs)
	{

		Route route = new Route(rname, isRoundTrip);

		for (String segt: segs)
		{

			for (Segment segment: segments)
			{

				if(segment.getName().equals(segt))
				{

					route.addSegment(segment);

				}

			}


		}
		routes.add(route);

		System.out.println("Route Added.");

	}

	/**
	 * Removes a route from the system.
	 *
	 * @param rname
	 */

	public void removeRoute(String rname)
	{

		for (Route route: routes)
		{

			if (route.getName().equals(rname))
			{

				routes.remove(route);

			}

		}
		System.out.println("Route Removed.");

	}

	/**
	 * Opens a route in the system.
	 *
	 * @param rname
	 */

	public Event openRoute(String rname)
	{

		Event event = null;

		for (Route route: routes)
		{

			if (route.getName().equals(rname))
			{

				event = route.open();

			}

		}
		System.out.println("Route Opened");

		return event;

	}

	/**
	 * Closes a route in the system.
	 *
	 * @param rname
	 */

	public Event closeRoute(String rname)
	{

		Event event = null;

		for (Route rout: routes)
		{

			if (rout.getName().equals(rname))
			{

				event = rout.close();

			}

		}
		System.out.println("Route Closed.");

		return event;
	}

	/**
	 * Adds a train to the system.
	 *
	 * @param name
	 * @param startTime
	 */

	public void addTrain(String name, int startTime)
	{

		Train train = new Train(name, startTime);

		trains.add(train);

		System.out.println("Train Added.");

	}

	/**
	 * Removes a train from the system.
	 *
	 * @param name
	 */

	public void removeTrain(String name)
	{

		for (Train train: trains)
		{

			if (train.getName() == name)
			{

				trains.remove(train);

			}

		}
		System.out.println("Train Removed.");


	}

	/**
	 * Registers a train in the system.
	 *
	 * @param train
	 * @param route
	 * @param stops
	 */

	public void registerTrain(String train, String route, ArrayList<String> stops)
	{


		for (String tn: stops)
		{

			for (Train trn: trains)
			{

				if((trn.getName().equals(tn)) && (trn.getName().equals(train)))
				{

					    int strt = trn.getStartTime();

						trn.register(strt);

						for (Route rt : routes)
						{

							if (rt.getName().equals(route))
							{

								trn.changeRoute(rt);

							}

						}

				}

			}


		}
		System.out.println("Train Registered.");

	}

	/**
	 * De-registers a train from the system.
	 *
	 * @param train
	 */

	public void deRegisterTrain(String train)
	{

		for (Train trn: trains)
		{

			if (trn.getName().equals(train))
			{

				int strt = trn.getStartTime();

				trn.deregister(strt);

			}



		}

		System.out.println("Train Deregistered.");

	}

	/**
	 * Checks if the system contains a station.
	 *
	 * @param station
	 * @return
	 */

	public boolean containsStation(String station)
	{

		for (Station stat : stations)
		{
			if (stat.getName().equals(station))
			{

				return true;

			}
		}
		return false;


	}

	/**
	 * Checks if the system contains a segment.
	 *
	 * @param segment
	 * @return
	 */

	public boolean containsSegment(String segment)
	{

		for (Segment segt : segments)
		{
			if (segt.getName().equals(segment))
			{

				return true;

			}
		}
		return false;

	}

	/**
	 * Checks if the system contains a route.
	 *
	 * @param route
	 * @return
	 */

	public boolean containsRoute(String route)
	{

		for (Route rt : routes)
		{
			if (rt.getName().equals(route))
			{

				return true;

			}
		}
		return false;

	}

	/**
	 * Checks if the system contains a train.
	 *
	 * @param train
	 * @return
	 */

	public boolean containsTrain(int train)
	{

		for (Train trn : trains)
		{
			if (trn.getId() == train)
			{

				return true;

			}
		}
		return false;

	}

	/**
	 * Gets information about a specific station.
	 *
	 * @param station
	 * @return
	 */

	public String getStationInfo(String station)
	{
		for (Station stat: stations)
		{

			if (stat.getName().equals(station))
			{

				return stat.toString();

			}

		}
		return "Station Not Found.";

	}

	/**
	 * Gets information about a specific segment.
	 *
	 * @param segment
	 * @return
	 */

	public String getSegmentInfo(String segment)
	{

		for (Segment segt: segments)
		{

			if (segt.getName().equals(segment))
			{

				return segt.toString();

			}

		}
		return "Segment Not Found.";

	}

	/**
	 * Gets information about a specific route.
	 *
	 * @param route
	 * @return
	 */

	public String getRouteInfo(String route)
	{

		for (Route rt: routes)
		{

			if (rt.getName().equals(route))
			{

				return rt.toString();

			}

		}
		return "Route Not Found.";

	}

	/**
	 * Gets information about a specific train.
	 *
	 * @param train
	 * @return
	 */

	public String getTrainInfo(String train)
	{

		for (Train trn: trains)
		{

			if (trn.getName().equals(train))
			{

				return trn.toString();

			}

		}
		return "Train Not Found.";

	}

	/**
	 * Opens the routes, trains, stations and segments
	 *
	 */

	public void openAll()
	{

		for (Route rt: routes)
		{

			rt.open();

		}

		for (Segment segt: segments)
		{

			segt.open();

		}

		for (Station stat: stations)
		{

			stat.open();

		}

		System.out.println("Routes, Stations and Segments are Opened.");


	}

	/**
	 * Closes all the routes, stations, train and segment of a specific name
	 *
	 * @param name
	 */

	public void closeAll(String name)
	{


		for (Route rt: routes)
		{

			if (rt.getName().equals(name))
			{

				rt.close();

			}

		}

		for (Segment segt: segments)
		{

			if (segt.getName().equals(name))
			{

				segt.close();

			}

		}

		for (Station stat: stations)
		{

			if (stat.getName().equals(name))
			{

				stat.close();

			}

		}

		System.out.println("Routes, Stations and Segments are Closed.");


	}

	/**
	 * Sets status to "Operational".
	 *
	 */

	public void setToWorking()
	{

		// ensure you set the status to Operational
		// ensure you set the current time to 0

		status = status.Operational;


	}

	/**
	 * Sets status to "Finished", if there are closures hindering movement the system will be
	 * "Deadlocked".
	 *
	 */

	public void setStopped()
	{

		/* ensure you set the status to Finished or Deadlocked as appropriate

		 if this method is called and there are closures hindering movement,
		 we know that the system is deadlocked */

		status = status.Finished;

	}

	/**
	 * Sets status to "Initialised".
	 *
	 */

	public SystemStatus currentStatus()
	{
		return status;
	}

	/**
	 * Verifies to true, if "the verifies of the train, segment, station and route", and
	 * "the names of station, the train, the route and the segment are equal to their respective
	 * given/inputted stations, trains, routes and segments" and false otherwise.
	 *
	 * @return
	 */

	public boolean verify()
	{

		for (Station stat: stations)
		{

			if(!stat.verify())
			{

				return false;

			}


		}

		for (Segment segt: segments)
		{

			if(!segt.verify())
			{

				return false;

			}


		}

		for (Route rt: routes)
		{

			if(!rt.verify())
			{

				return false;

			}


		}

		for (Train trn: trains)
		{

			if(!trn.verify())
			{

				return false;

			}


		}

		for (Station stat: stations)
		{

			for (Station stat_two: stations)
			{

				if(!stat.equals(stat_two))
				{
					if (stat.getName().equals(stat_two.getName()))
					{

						return false;

					}

				}

			}

		}

		for (Segment segt: segments)
		{

			for (Segment segt_two: segments)
			{

				if(!segt.equals(segt_two))
				{
					if (segt.getName().equals(segt_two.getName()))
					{

						return false;

					}

				}

			}

		}

		for (Route rt: routes)
		{

			for (Route rt_two: routes)
			{

				if(!rt.equals(rt_two))
				{
					if (rt.getName().equals(rt_two.getName()))
					{

						return false;

					}

				}

			}

		}

		for (Train trn: trains)
		{

			for (Train trn_two: trains)
			{

				if(!trn.equals(trn_two))
				{
					if (trn.getId() == trn_two.getId())
					{

						return false;

					}

				}

			}

		}

		return true;

	}

	/**
	 *
	 *
	 */

	public boolean closuresHinderingMovement()
	{
		/*
		 * call this method if advance returns no events and you want to determine if
		 * the system is deadlocked, i.e. there are trains to move but none of them can
		 * move because of closures.
		 * 
		 * this method should not return true if trains have stopped at a station and
		 * are waiting on a future time instant to move.
		 * 
		 */

		return false;
	}

	/**
	 *
	 *
	 */

	public ArrayList<Event> advance()
	{
		if (status == SystemStatus.Operational)
		{
			currentTime += 1;

			/* tell the trains that can move to advance

			 ensure that you change the lights in appropriate segments

			 ensure you collect the events and return them */

		}
		
		return new ArrayList<Event>();
	}

	/**
	 *
	 *
	 */

	public boolean validateObjectLog(ObjectType object, String name, ArrayList<String> events)
	{
		return true;
	}

	/**
	 * Prints the trains, routes, segments and stations in the system.
	 *
	 * @return
	 */

	@Override
	public String toString()
	{
		String sts = "[";

		if (stations == null || stations.size() == 0)
		{
			sts += "none]";
		}
		else
		{ // extract helper method for this

			Station[] acc1 = stations.toArray(new Station[0]);
			Arrays.sort(acc1);
			List<Station> ss = Arrays.asList(acc1);

			for (Station s : ss)
			{
				sts += (ss.indexOf(s) == 0 ? "\n\t" : "\t") + s + (ss.indexOf(s) != ss.size() - 1 ? "\n" : "\n\t]");
			}
		}

		String sgs = "[";

		if (segments == null || segments.size() == 0)
		{
			sgs += "none]";
		}
		else
		{

			Segment[] acc1 = segments.toArray(new Segment[0]);
			Arrays.sort(acc1);
			List<Segment> ss = Arrays.asList(acc1);

			for (Segment s : ss)
			{
				sgs += (ss.indexOf(s) == 0 ? "\n\t" : "\t") + s + (ss.indexOf(s) != ss.size() - 1 ? "\n" : "\n\t]");
			}
		}

		String rts = "[";

		if (routes == null || routes.size() == 0)
		{
			rts += "none]";
		}

		else
		{

			Route[] acc1 = routes.toArray(new Route[0]);
			Arrays.sort(acc1);
			List<Route> ss = Arrays.asList(acc1);

			for (Route s : ss)
			{
				rts += (ss.indexOf(s) == 0 ? "\n\t" : "\t") + s + (ss.indexOf(s) != ss.size() - 1 ? "\n" : "\n\t]");
			}
		}

		String tns = "[";

		if (trains == null || trains.size() == 0)
		{
			tns += "none]";
		}
		else
		{

			Train[] acc1 = trains.toArray(new Train[0]);
			Arrays.sort(acc1);
			List<Train> ss = Arrays.asList(acc1);

			for (Train s : trains)
			{
				tns += (ss.indexOf(s) == 0 ? "\n\t" : "\t") + s + (ss.indexOf(s) != ss.size() - 1 ? "\n" : "\n\t]");
			}
		}

		return "---------- ---------- ---------- ---------- ---------- ----------\nTrainSystem [\n\nstatus="
				+ status.getDescription() + "\nverified=" + (verify() ? "Yes" : "No") + "\n\ntrains=" + tns
				+ "\n\nroutes=" + rts + "\n\nsegments=" + sgs + "\n\nstations=" + sts
				+ "\n]\n---------- ---------- ---------- ---------- ---------- ----------";

	}

	public static void main(String[] args)
	{

		TrainSystem ts = new TrainSystem();

		ts.addStation("Stationx");
		ts.addStation("Alberg");
		ts.addStation("Trusty");
		ts.addStation("1tationx");

		ts.addSegment("Seg1", "Stationx", "Alberg");
		ts.addSegment("Seg2", "Alberg", "Trusty");
		ts.addSegment("Seg3", "Trusty", "1tationx");

		ArrayList<String> rs = new ArrayList<String>();
		rs.add("Seg1");
		rs.add("Seg2");
		rs.add("Seg3");
		ts.addRoute("R1", false, rs);

		ts.addTrain("train_1", 2);
		ts.addTrain("train_2", 1);

		ArrayList<String> stops = new ArrayList<String>();
		stops.add("Alberg");
		ts.registerTrain("train_1", "R1", stops);
		stops.remove(0);
		stops.add("Trusty");
		ts.registerTrain("train_2", "R1", stops);

		System.out.println(ts);

	}
}
