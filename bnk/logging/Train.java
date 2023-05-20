package bnk.logging;

import bnk.enums.TrainStatus;
import bnk.events.Event;

import java.util.ArrayList;

public class Train extends Logable implements Comparable<Train> {
	private int id = nextID++;
	private static int nextID = 1;
	private String name;
	private int timeRegistered = -1; /* once first set, is "immutable", and only deregister can change its value */
	private int startTime = -1; /* once first set, is "immutable" */
	private String currentStation;
	private Route route;
	private boolean isAtStart = true;
	private int waitTimeRemaining = 0; /* only changes with advance */
	private ArrayList<String> stopsAt = new ArrayList<String>();
	private TrainStatus status = TrainStatus.Initialised;

	public Train(String name, int startTime) {
		this.name = name != null ? name : "";
		this.startTime = startTime >= 0 ? startTime : this.startTime;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getStartTime() {
		return timeRegistered > 0 && startTime >= 0 ? timeRegistered + startTime : -1;
	}

	public boolean isRegistered() {
		return timeRegistered > 0;
	}

	public int whenRegistered() {
		return timeRegistered;
	}

	public void register(int timeRegistered) {

		if (status != TrainStatus.Initialised)
			return;

		/* ensures immutability until deregistered */
		this.timeRegistered = isRegistered() ? this.timeRegistered
				: timeRegistered >= 0 ? timeRegistered : this.timeRegistered;
	}

	public void deregister() {

		if (status != TrainStatus.Initialised)
			return;

		if (!isRegistered() || currentStation != null)
			return;

		this.timeRegistered = -1;
		route = null;
		currentStation = null;
	}

	public boolean isWaiting() {
		return waitTimeRemaining > 0;
	}

	public Event start() {

		if (status != TrainStatus.Initialised || !verify())
			return null;

		status = TrainStatus.Started;
		currentStation = route.getNextStation(route.getStart().getName(), isAtStart);
		return null;
	}

	public Event finish() {
		if (status != TrainStatus.Started)
			return null;

		status = TrainStatus.Completed;
		currentStation = null;

		return null;
	}

	public Event advance(int time) {

		if (status != TrainStatus.Started)
			return null;

		isAtStart = false;

		if (waitTimeRemaining > 0) {
			waitTimeRemaining -= 1;
			return null;
		} else {
			currentStation = currentStation == null ? null : route.getNextStation(currentStation, isAtStart);
			return null;
		}
	}

	public String currentStation() {
		if (currentStation != null)
			return currentStation;
		return null;
	}

	public String nextStation() {

		if (!verify())
			return null;

		if (currentStation != null)
			return route.getNextStation(currentStation, isAtStart);
		else if (status != TrainStatus.Completed)
			return route.getStart().getName();
		else
			return null;
	}

	public void changeRoute(Route r) {

		if (status != TrainStatus.Initialised)
			return;

		if (currentStation == null && r != null && status == TrainStatus.Initialised)
			route = r;
	}

	public void addStop(String stop) {

		if (status != TrainStatus.Initialised)
			return;

		if (stop != null && !stop.trim().equals("") && status == TrainStatus.Initialised)
			stopsAt.add(stop);
	}

	public boolean verify() {
		boolean verified = true;
		return verified ;
	}

	@Override
	public String toString() {
		return "p2.logging.Train [id=" + id + ", name=" + name + ", " + "timeRegistered="
				+ (timeRegistered <= 0 ? "unregistered" : timeRegistered) + ", startTime="
				+ (timeRegistered >= 0 ? getStartTime() : "unregistered") + ", currentStation="
				+ (currentStation == null ? "none" : currentStation) + ", route="
				+ (route == null ? "none" : route.getName()) + ", stopsAt="
				+ (stopsAt.size() > 0 ? stopsAt.toString() : "All") + ", status=" + status.getDescription()
				+ ", verified=" + (verify() ? "Yes" : "No") + "]";
	}

	@Override /* Can be used to check equality, and to sort */
	public int compareTo(Train train) {
		return (name).compareTo(train.getName());
	}

	@Override
	public boolean validate() {
		return super.validate();
	}
}
