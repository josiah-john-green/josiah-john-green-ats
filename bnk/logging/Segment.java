package bnk.logging;

import bnk.enums.Light;
import bnk.enums.RSStatus;
import bnk.events.Event;
import bnk.interfaces.OpenClose;
import bnk.ts.TrafficLight;

public class Segment extends Logable implements Comparable<Segment>, OpenClose {

	private Station segmentStart, /* once set, is immutable */
			segmentEnd; /* once set, is immutable */
	private String name; /* once set, is immutable */
	private RSStatus status = RSStatus.Open;
	private TrafficLight trafficLight = new TrafficLight();
	private String train = "-1";

	public Segment(String name, Station segmentStart, Station segmentEnd) {
		setSegmentStart(segmentStart);
		setSegmentEnd(segmentEnd);
		setName(name);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name != null && !name.equals(this.name) ? name.trim() : this.name;
	}

	public Station getSegmentStart() {
		return segmentStart;
	}

	private void setSegmentStart(Station segmentStart) {
		this.segmentStart = segmentStart;
	}

	public Station getSegmentEnd() {
		return segmentEnd;
	}

	private void setSegmentEnd(Station segmentEnd) {
		this.segmentEnd = segmentEnd;
	}

	public boolean hasTrain() {
		return !train.equals("-1");
	}

	@Override
	public boolean isOpen() {
		return status == RSStatus.Open;
	}

	public Event acceptTrain(String train) {
		if ((train != null && train.trim() != "") && isOpen())
			this.train = train;
		return null;
	}

	public Event releaseTrain() {
		if (isOpen())
			train = "-1";
		return null;
	}

	public Event changeLight() {
		trafficLight.changeLight();
		return null;
	}

	public Light lightColour() {
		return trafficLight.getColour();
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
		return "p2.logging.Segment [name=" + name + ", segmentStart=" + (segmentStart == null ? "none" : segmentStart.getName())
				+ ", segmentEnd=" + (segmentEnd == null ? "none" : segmentEnd.getName()) + ", status="
				+ status.getDescription() + ", trafficLight=" + trafficLight + ", train="
				+ (train.equals("-1") ? "none" : train) + ", verified=" + (verify() ? "Yes" : "No") + "]";
	}

	@Override /* two segments with the same start and end stations are the same segment */
	public int compareTo(Segment segment) {
		String s = segmentStart.getName().concat(segmentEnd.getName()),
				e = segment.getSegmentStart().getName().concat(segment.getSegmentEnd().getName());
		return s.compareTo(e);
	}

	@Override
	public boolean validate() {
		return super.validate();
	}
}
