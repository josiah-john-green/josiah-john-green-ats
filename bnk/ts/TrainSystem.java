package bnk.ts;

import bnk.enums.ObjectType;
import bnk.enums.SystemStatus;
import bnk.events.Event;
import bnk.logging.Route;
import bnk.logging.Segment;
import bnk.logging.Station;
import bnk.logging.Train;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrainSystem {

	/* default values have been set */
	private SystemStatus status = SystemStatus.Initialised;
	private int currentTime = -1;

	private ArrayList<Route> routes = new ArrayList<Route>();
	private ArrayList<Train> trains = new ArrayList<Train>();
	private ArrayList<Station> stations = new ArrayList<Station>();
	private ArrayList<Segment> segments = new ArrayList<Segment>();

	public TrainSystem() {
	}

	public void addStation(String sname) {
	
	}

	public void removeStation(String sname) {
	}

	public Event openStation(String sname) {
		// ensure you collect the event and return it
		return null;
	}

	public Event closeStation(String sname) {
		// ensure you collect the event and return it
		return null;
	}

	public void addSegment(String sname, String start, String sEnd) {
	}

	public void removeSegment(String sname) {
	}

	public Event openSegment(String sname) {
		// ensure you collect the event and return it
		return null;
	}

	public Event closeSegment(String sname) {
		// ensure you collect the event and return it
		return null;
	}

	public void addRoute(String rname, boolean isRoundTrip, ArrayList<String> segs) {
	}

	public void removeRoute(String rname) {
	}

	public Event openRoute(String rname) {
		/* for P2, renamed to OpenRoute from OpenSRoute */

		// ensure you collect the event and return it
		return null;
	}

	public Event closeRoute(String rname) {
		// ensure you collect the event and return it
		return null;
	}

	public void addTrain(String name, int startTime) {
		
	}

	public void removeTrain(String name) {

	}

	public void registerTrain(String train, String route, ArrayList<String> stops) {
	}

	public void deRegisterTrain(String train) {
	}

	public boolean containsStation(String station) {
		return true;
	}

	public boolean containsSegment(String segment) {
		return true;
	}

	public boolean containsRoute(String route) {
		return false;
	}

	public boolean containsTrain(int train) {
		return false;
	}

	public String getStationInfo(String station) {
		return "";
	}

	public String getSegmentInfo(String segment) {
		return "";
	}

	public String getRouteInfo(String route) {
		return "";
	}

	public String getTrainInfo(String train) {
		return "";
	}

	public void openAll() {
	}

	public void closeAll(String name) {
	}

	public void setToWorking() {
		// ensure you set the status to Operational
		// ensure you set the current time to 0
	}

	public void setStopped() {
		/* ensure you set the status to Finished or Deadlocked as appropriate

		 if this method is called and there are closures hindering movement,
		 we know that the system is deadlocked */

	}

	public SystemStatus currentStatus() {
		return status;
	}

	public boolean verify() {
		return false;
	}

	public boolean closuresHinderingMovement() {
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

	public ArrayList<Event> advance() {
		if (status == SystemStatus.Operational) {
			currentTime += 1;

			/* tell the trains that can move to advance

			 ensure that you change the lights in appropriate segments

			 ensure you collect the events and return them */

		}
		
		return new ArrayList<Event>();
	}

	public boolean validateObjectLog(ObjectType object, String name, ArrayList<String> events) {
		return true;
	}

	@Override
	public String toString() {
		String sts = "[";
		if (stations == null || stations.size() == 0)
			sts += "none]";
		else { // extract helper method for this
			Station[] acc1 = stations.toArray(new Station[0]);
			Arrays.sort(acc1);
			List<Station> ss = Arrays.asList(acc1);
			for (Station s : ss)
				sts += (ss.indexOf(s) == 0 ? "\n\t" : "\t") + s + (ss.indexOf(s) != ss.size() - 1 ? "\n" : "\n\t]");
		}

		String sgs = "[";
		if (segments == null || segments.size() == 0)
			sgs += "none]";
		else {
			Segment[] acc1 = segments.toArray(new Segment[0]);
			Arrays.sort(acc1);
			List<Segment> ss = Arrays.asList(acc1);
			for (Segment s : ss)
				sgs += (ss.indexOf(s) == 0 ? "\n\t" : "\t") + s + (ss.indexOf(s) != ss.size() - 1 ? "\n" : "\n\t]");
		}

		String rts = "[";
		if (routes == null || routes.size() == 0)
			rts += "none]";
		else {
			Route[] acc1 = routes.toArray(new Route[0]);
			Arrays.sort(acc1);
			List<Route> ss = Arrays.asList(acc1);
			for (Route s : ss)
				rts += (ss.indexOf(s) == 0 ? "\n\t" : "\t") + s + (ss.indexOf(s) != ss.size() - 1 ? "\n" : "\n\t]");
		}

		String tns = "[";

		if (trains == null || trains.size() == 0)
			tns += "none]";
		else {
			Train[] acc1 = trains.toArray(new Train[0]);
			Arrays.sort(acc1);
			List<Train> ss = Arrays.asList(acc1);
			for (Train s : trains)
				tns += (ss.indexOf(s) == 0 ? "\n\t" : "\t") + s + (ss.indexOf(s) != ss.size() - 1 ? "\n" : "\n\t]");
		}
		return "---------- ---------- ---------- ---------- ---------- ----------\np2.ts.TrainSystem [\n\nstatus="
				+ status.getDescription() + "\nverified=" + (verify() ? "Yes" : "No") + "\n\ntrains=" + tns
				+ "\n\nroutes=" + rts + "\n\nsegments=" + sgs + "\n\nstations=" + sts
				+ "\n]\n---------- ---------- ---------- ---------- ---------- ----------";
	}

	public static void main(String[] args) {
		TrainSystem ts = new TrainSystem();

		ts.addStation("Stationx");
		ts.addStation("Alberb");
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
