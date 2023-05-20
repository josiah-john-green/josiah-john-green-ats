package p2.logging;

import p2.events.Event;

import java.util.ArrayList;

import java.util.*;

public abstract class Logable
{

	/* once an event is added to the log it cannot be removed or changed. */
	protected ArrayList<Event> events = new ArrayList<Event>();

	/**
	 *
	 * @param event
	 */

	public void addToLog(Event event)
	{
		events.add(event);
	}

	/**
	 *
	 * @return
	 */

	public int logSize()
	{
		return events.size();
	}

	/**
	 *
	 * @param evnts
	 * @return
	 */

	public boolean contains(ArrayList<String> evnts)
	{
		/*
		 * in any order
		 * 
		 * evnts already contain the string representation of the events
		 * 
		 * ensure that you return the correct result
		 */

		boolean contained = false;

		Iterator <String> evt = evnts.iterator();

		while (evt.hasNext())
		{

			String event = evt.next();

			for (Event e: events)
			{

				if (e.toString().equals(event))
				{

					contained = true;
					break;

				}

			}

			if (!contained)
			{

					return false;

			}


		}

		return true;
	}

	/**
	 *
	 * @param evnts
	 * @return
	 */

	public boolean containsInSequence(ArrayList<String> evnts)
	{
		/*
		 * in ordered sequence,
		 * 
		 * evnts already contain the string representation of the events
		 * 
		 * ensure that you return the correct result
		 */

         boolean contained = true;

		 if (evnts.size() > 0)
		 {

			 for (int a = 0; a < events.size(); a++)
			 {

				 if (events.get(a).toString().equals(evnts.get(0)))
				 {

					 contained = true;

					 for (int b = 1; b < evnts.size(); b++)
					 {

						 if (events.get(a + b).toString().equals(events.get(b)))
						 {

							 contained = true;

						 }

						 else
						 {

							 contained = false;
							 break;

						 }

					 }

				 }

			 }

		 }

		 return contained;
	}

	/**
	 *
	 * @return
	 */

	public boolean validate()
	{
		/*
		 * 1. for the UI/GUI that you use, print information to show what step in the
		 * validation you are doing . You must print the result of that step. This must
		 * also be done in overridden methods in the concrete subclasses.
		 * 
		 * 2. No duplicate events in log, aliases or otherwise.
		 * 
		 * 3. Ask yourself, how many events can each object have per time instant?
		 * ensure that you check for that here.
		 * 
		 * 4. ensure that you return the correct result
		 */

		 System.out.println("\nValidate Loggable");

		 boolean valid = true;

		 for (int a = 0; a < events.size(); a++)
		 {

			for (int b = a + 1; b < events.size(); b++)
			{

				if (events.get(a).toString().equals(events.get(b).toString()))
				{

					System.out.println("Duplicate events in the log");
					valid = false;
					break;

				}

			}

		}

		System.out.println("\nLog Validated");

		return valid;
	}

	/**
	 *
	 * @return
	 */

	public ArrayList<String> getEvents()
	{

		ArrayList<String> evnts = new ArrayList<String>();

		if (events == null || events.size() == 0)
		{
			return evnts;
	    }
		else
		{
			for (Event e : events)
			{
				evnts.add(e.toString());
			}

		}
		return evnts;
	}

	/**
	 *
	 * @param time
	 * @return
	 */

	public ArrayList<String> getEvents(int time)
	{

		ArrayList<String> evnts = new ArrayList<String>();

		if (events == null || events.size() == 0)
		{
			return evnts;
		}

		else
		{

			ArrayList<Event> ets = new ArrayList<Event>();

			for (Event e : events)
			{
				if (e.getTime() == time)
				{
					ets.add(e);
				}
			}

			for (Event e : ets)
			{
				evnts.add(e.toString());
			}
			return evnts;
		}
	}

	/**
	 *
	 * @param object
	 * @return
	 */

	public ArrayList<String> getEvents(String object)
	{
		ArrayList<String> evnts = new ArrayList<String>();

		if (events == null || events.size() == 0)
		{
			return evnts;
		}

		else
		{

			ArrayList<Event> ets = new ArrayList<Event>();

			for (Event e : events) {
				if (e.getObject().equals(object)) {
					ets.add(e);
				}
			}

			for (Event e : ets) {
				evnts.add(e.toString());
			}
			return evnts;
		}
	}

	/**
	 *
	 * @return
	 */

	public ArrayList<String> getObjects()
	{
		ArrayList<String> objects = new ArrayList<String>();

		for (Event e : events)
		{
			if (!objects.contains(e.getObject()))
			{
				objects.add(e.getObject());
			}
		}
    	return objects;
	}

	/**
	 *
	 * @return
	 */

	public int distinctObjects()
	{
		return getObjects().size();
	}

	/**
	 *
	 * @return
	 */

	@Override
	public String toString()
	{

		String evnts = "Events Log[";

		if (events == null || events.size() == 0)
		{
			evnts += "no events]";
	    }
		else
		{
			for (Event e : events)
			{
				evnts += (events.indexOf(e) == 0 ? "\n\t" : "\t") + e
						+ (events.indexOf(e) != events.size() - 1 ? "\n" : "\n\t]");
			}
		}
		return evnts;
	}
}
