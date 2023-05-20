package p2.logging;

import p2.enums.RSStatus;
import p2.events.Event;
import p2.interfaces.OpenClose;

public class Station extends Logable implements Comparable<Station>, OpenClose
{

	private String name; /* once set, is immutable */
	private RSStatus status = RSStatus.Open;

	/**
	 *
	 *
	 * @param name
	 */

	public Station(String name)
	{
		setName(name);
	}

	/**
	 * Gets station name
	 *
	 * @return
	 */

	public String getName()
	{
		return name;
	}

	/**
	 * Sets p2.logging.Station Name
	 *
	 * @param name
	 */

	private void setName(String name)
	{
		this.name = name != null && !name.equals("") ? name : this.name;
	}

	/**
	 * Verifies to true, if "the train name is not equal to null, "", or " "" and false otherwise
	 *
	 * @return
	 */

	public boolean verify()
	{
		boolean result = false;

		if (this.name != null && !this.name.equals("") && !this.name.equals(" "))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 *
	 * @return
	 */

	@Override
	public boolean isOpen()
	{
		return status == RSStatus.Open;
	}

	/**
	 *
	 * @return
	 */

	@Override
	public Event close()
	{
		status = status == RSStatus.Open ? status = RSStatus.ClosedForMaintenance : status;
		return null;
	}

	/**
	 *
	 * @return
	 */

	@Override
	public Event open()
	{
		status = status == RSStatus.ClosedForMaintenance ? status = RSStatus.Open : status;
		return null;
	}

	/**
	 *
	 * @param station the object to be compared.
	 * @return
	 */

	@Override
	public int compareTo(Station station)
	{
		return name.compareTo(station.getName());
	}

	/**
	 *
	 * @return
	 */

	@Override
	public String toString()
	{
		return "Station [name=" + name + ", status=" + status.getDescription() + "]";
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
