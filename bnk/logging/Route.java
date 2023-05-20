package bnk.logging;

import bnk.enums.RSStatus;
import bnk.events.Event;
import bnk.interfaces.OpenClose;

import java.util.ArrayList;
import java.util.Iterator;

public class Route extends Logable implements Comparable<Route>, OpenClose {

	private String name; /* once set, name is immutable */
	private boolean isRoundTrip = false; /* once set, isRoundTrip is immutable */
	private RSStatus status = RSStatus.Open;
	private ArrayList<Segment> segments = new ArrayList<Segment>();

	public Route(String name, boolean isRoundTrip) {
		setName(name);
		setRoundTrip(isRoundTrip);
	}

	public Route(String name) {
		setName(name);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean isOpen() {
		return status == RSStatus.Open;
	}

	public void setStatus(RSStatus status) {
		this.status = status;
	}

	private void setRoundTrip(boolean isRoundTrip) {
		this.isRoundTrip = isRoundTrip;
	}

	public boolean isRoundTrip() {
		return isRoundTrip;
	}

	private ArrayList<Station> getStationList() {
		ArrayList<Station> sss = new ArrayList<Station>();

		return sss;
	}

	public ArrayList<String> getStationStrings() {
		ArrayList<String> sss = new ArrayList<String>();
		for (Station s : getStationList())
			sss.add(s.getName());
		return sss;
	}

	public Station getStart() {
		return segments.size() > 0 ? segments.get(0).getSegmentStart() : null;
	}

	public Station getEnd() {
		return segments.size() > 0 ? segments.get(segments.size() - 1).getSegmentEnd() : null;
	}

	public String getNextStation(String station, boolean isAtStart) {

		String empty = "";
		if (segments.size() > 0 && station != null) {

			if (isRoundTrip)
				if (isAtStart)
					return segments.get(0).getSegmentEnd().getName();
				else if (station.equals(getStart().getName()))
					return empty;

			ArrayList<Station> sss = getStationList();
			for (Station x : sss)
				if (x.getName().equals(station))
					if (sss.indexOf(x) + 1 == sss.size())
						return empty;
					else
						return sss.get(sss.indexOf(x) + 1).getName();
		}
		return empty;
	}

	public String getPreviousStation(String station, boolean isAtStart) {
		return "";
	}

	public boolean canGetTo(String station) {
		for (Station x : getStationList())
			if (x.getName().equals(station))
				return true;
		return false;
	}

	public boolean isInnerStation(String station) {
		// returns true if station is not a start or end station for the route
		return true;
	}

	public void addSegment(Segment segment) {

		if (segments.size() == 0) {
			segments.add(segment);
			return;
		}

		if (segments.size() > 0 && !containsSegmentName(segment.getName())
				&& segment.getSegmentStart().compareTo(segments.get(segments.size() - 1).getSegmentEnd()) == 0)
			segments.add(segment);
	}

	public void addSegments(ArrayList<Segment> sgmnts) {
		if (sgmnts.size() > 0)
			sgmnts.forEach(s -> addSegment(s));
	}

	public void removeSegment(String segment) {

		if (segments.size() == 0)
			return;

		if (segments.size() > 0 && containsSegmentName(segment.trim())) {
			int i = 0;
			do {
				if (segments.get(i).getName().equals(segment.trim()))
					break;
				i++;
			} while (i < segments.size());

			segments.remove(i);
		}
	}

	public boolean containsSegmentName(String segment) {
		Iterator<Segment> sgs = segments.iterator();
		while (sgs.hasNext()) {
			if (sgs.next().getName().equals(segment))
				return true;
		}
		return false;
	}

	public Event changeLight(String startOfSegment) {
		for (Segment s : segments) {
			if (s.getName().equals(startOfSegment)) {
				s.changeLight();
				break;
			}
		}
		return null;
	}

	public boolean verify() {
		boolean verified = true;
		return verified;
	}

	@Override
	public Event close() {
		status = status == RSStatus.Open ? status = RSStatus.ClosedForMaintenance : status;
		return null;
	}

	@Override
	public Event open() {
		status = status == RSStatus.ClosedForMaintenance ? status = RSStatus.Open : status;
		return null;
	}

	@Override
	public String toString() {
		String str = "[";
		for (Segment s : segments)
			str += s.getName() + (segments.get(segments.size() - 1) == s ? "]" : ", ");

		return "p2.logging.Route [name=" + name + ", isRoundTrip=" + isRoundTrip + ", status=" + status + ", segments="
				+ (segments == null || segments.size() == 0 ? "none" : str) + ", verified=" + (verify() ? "Yes" : "No")
				+ "]";
	}

	@Override /* mostly used to sort a collection of p2.logging.Route Objects */
	public int compareTo(Route route) {
		return name.compareTo(route.getName());
	}

	@Override
	public boolean validate() {
		return super.validate();
	}
}
