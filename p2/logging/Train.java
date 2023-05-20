package p2.logging;

import p2.events.Event;
import p2.enums.TrainStatus;

import java.util.ArrayList;

public class Train extends Logable implements Comparable<Train>
{
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

	public Train()
	{

	}


	/**
	 * Sets the name of the train, and the start time of the train.
	 *
	 * @param name
	 * @param startTime
	 */

	public Train(String name, int startTime)
	{
		this.name = name != null ? name : "";
		this.startTime = startTime >= 0 ? startTime : this.startTime;
	}

	/**
	 * Gets the id of the train.
	 *
	 * @return
	 */

	public int getId()
	{
		return id;
	}

	/**
	 * Gets the name of the train.
	 *
	 * @return
	 */

	public String getName()
	{
		return name;
	}

	/**
	 * Gets the start time of the train.
	 *
	 * @return
	 */

	public int getStartTime()
	{
		return timeRegistered > 0 && startTime >= 0 ? timeRegistered + startTime : -1;
	}

	/**
	 * Checks if the train is registered or not.
	 *
	 * @return
	 */

	public boolean isRegistered()
	{
		return timeRegistered > 0;
	}

	/**
	 * Checks when the train was registered.
	 *
	 * @return
	 */

	public int whenRegistered()
	{
		return timeRegistered;
	}

	/**
	 * Registers the train.
	 *
	 * @param timeRegistered
	 */

	public void register(int timeRegistered)
	{

		if (status != TrainStatus.Initialised)
		{
			return;
		}

		/* ensures immutability until deregistered */
		this.timeRegistered = isRegistered() ? this.timeRegistered
				: timeRegistered >= 0 ? timeRegistered : this.timeRegistered;
	}

	/**
	 * De-registers the train.
	 *
	 * @param timeRegistered
	 */

	public void deregister(int timeRegistered)
	{

		if (status != TrainStatus.Initialised)
		{
			return;
		}

		if (!isRegistered() || currentStation != null)
		{
			return;
		}

		this.timeRegistered = -1;
		route = null;
		currentStation = null;

	}


	/**
	 *
	 *
	 */

	public boolean isWaiting()
	{
		return waitTimeRemaining > 0;
	}

	/**
	 *
	 *
	 */

	public Event start()
	{

		if (status != TrainStatus.Initialised || !verify()) {
			return null;
		}

		status = TrainStatus.Started;
		currentStation = route.getNextStation(route.getStart().getName(), isAtStart);
		return null;
	}

	/**
	 * Checks if the status of the train is "started", and sets it to completed if it has
	 *
	 *
	 */

	public Event finish()
	{
		if (status != TrainStatus.Started)
			return null;

		status = TrainStatus.Completed;
		currentStation = null;

		return null;
	}

	/**
	 *
	 *
	 */

	public Event advance(int time)
	{

		if (status != TrainStatus.Started)
		{
			return null;
		}

		isAtStart = false;

		if (waitTimeRemaining > 0)
		{
			waitTimeRemaining -= 1;
			return null;
		}
		else
		{
			currentStation = currentStation == null ? null : route.getNextStation(currentStation, isAtStart);
			return null;
		}
	}

	/**
	 * Returns the current station if it`s empty or equal to null
	 *
	 * @return
	 */

	public String currentStation()
	{
		if (currentStation != null)
		{
			return currentStation;
		}
		return null;
	}

	/**
	 * Returns if current station is equal to null, amd the name of the
	 *
	 * @return
	 */

	public String nextStation()
	{

		if (!verify())
		{
			return null;
		}
		if (currentStation != null)
		{
			return route.getNextStation(currentStation, isAtStart);
		}
		else if (status != TrainStatus.Completed)
		{
			return route.getStart().getName();
		}
		else
		{
			return null;
		}
	}

	/**
	 * Changes the route of the current station
	 *
	 * @param r
	 */

	public void changeRoute(Route r)
	{

		if (status != TrainStatus.Initialised)
		{
			return;
		}
		if (currentStation == null && r != null && status == TrainStatus.Initialised)
		{
			route = r;
		}
	}

	/**
	 * Stops the train if stop isn`t equal to null, and the train status is initialised
	 *
	 */

	public void addStop(String stop)
	{

		if (status != TrainStatus.Initialised)
		{
			return;
		}
		if (stop != null && !stop.trim().equals("") && status == TrainStatus.Initialised)
		{
			stopsAt.add(stop);
		}
	}

	/**
	 * Verifies to true, if "the name of the train is not equal to null, "" and " ", as well as if the
	 * time Registered is more than zero (0)
	 *
	 */

	public boolean verify()
	{
		boolean result = false;

		if ((this.name != null && !this.name.equals("") && !this.name.equals(" ")) && (this.timeRegistered > 0))
		{

			result = true;

		}
		else
		{

			result = false;

		}

		return result;


	}

	/**
	 * Prints the id for the train, the time registered, the current station, and the name of the route.
	 *
	 * @return
	 */

	@Override
	public String toString()
	{
		return "Train [id=" + id + ", name=" + name + ", " + "timeRegistered="
				+ (timeRegistered <= 0 ? "unregistered" : timeRegistered) + ", startTime="
				+ (timeRegistered >= 0 ? getStartTime() : "unregistered") + ", currentStation="
				+ (currentStation == null ? "none" : currentStation) + ", route="
				+ (route == null ? "none" : route.getName()) + ", stopsAt="
				+ (stopsAt.size() > 0 ? stopsAt.toString() : "All") + ", status=" + status.getDescription()
				+ ", verified=" + (verify() ? "Yes" : "No") + "]";
	}

	/**
	 * Compares the given/inputted id to the id of the train
	 *
	 * @param train the object to be compared.
	 * @return
	 */

	@Override /* Can be used to check equality, and to sort */
	public int compareTo(Train train)
	{
		return (name).compareTo(train.getName());
	}

	/**
	 *
	 *
	 */

	@Override
	public boolean validate()
	{
		return super.validate();
	}
}
