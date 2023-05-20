package p2.logging;

import p2.events.Event;
import p2.interfaces.SimulatorInterface;
import p2.ts.TrainSystem;
import p2.enums.SimulatorStatus;
import p2.enums.SystemStatus;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Simulator extends Logable implements SimulatorInterface
{

	private int currentTime = -1;
	private SimulatorStatus status = SimulatorStatus.Uninitialised;
	private TrainSystem trainSystem = new TrainSystem();
	private Scanner file;
	private ArrayList<String> flaggedEvents = new ArrayList<String>();

	/**
	 * Calls initialise and setInitialised functions
	 *
	 * @param initialisationFile
	 * @throws FileNotFoundException
	 */

	public Simulator(String initialisationFile) throws FileNotFoundException
	{

		initialise(initialisationFile);
		setInitialised();

	}

	/**
	 * Reads the given initialisationFile, accepting and storing the train, station, event, routes and segment
	 * components
	 *
	 * @param initialisationFile
	 * @throws FileNotFoundException
	 */

	private void initialise(String initialisationFile) throws FileNotFoundException
	{

		if (status == SimulatorStatus.Uninitialised || status == SimulatorStatus.Initialised)
		{

			/*
			 * use a loop to read in the initialisation data
			 *
			 * remember to only use the try-catch-finally constructs to catch and recover
			 * from exceptions
			 *
			 * create stations
			 *
			 * create segments
			 *
			 * create routes
			 *
			 * process open events, close events, and create and register trains
			 *
			 * ensure open and close events get added to the log
			 */

			 try
			 {

				 // create scanner to read from file
				 file = new Scanner(new File(initialisationFile));

				 while(file.hasNextLine())
				 {

					 //Variables

					 String line = file.nextLine();

					 String [] tokens = line.split(" ");

					 //Finding and Recording the Time

					 if (tokens.length == 1)
					 {

						 currentTime = Integer.parseInt(tokens[0]);

					 }
					 else
					 {

						 //Finding and Recording the Stations

						 if (tokens[0].contains("Stations"))
						 {

							 //Variables

							 int st = Integer.parseInt(tokens[1]) + 1;

							 //Loop

							 for (int i = 1; i < st; i++)
							 {

								 String station = file.nextLine();

								 trainSystem.addStation(station);

							 }

						 }

						 //Finding and Recording the Segments

						 if (tokens[0].contains("Segments"))
						 {

							 //Variables

							 int seg = Integer.parseInt(tokens[1]) + 1;

							 //Loop

							 for (int i = 1; i < seg; i++)
							 {

								 //Variables

								 String segment = file.nextLine();

								 //Splicing Segments into Segment

								 String[] segments = segment.split(":");

								 // Creating Individual Segment

								 trainSystem.addSegment(segments[0], segments[1], segments[2]);

							 }

						 }


						 //Finding and Recording the Routes

						 if (tokens[0].contains("Routes"))
						 {

							 //Variables

							 int rou = Integer.parseInt(tokens[1]) + 1;

							 //Loop

							 for (int i = 1; i < rou; i++)
							 {

								 //List

								 ArrayList<String> routes = new ArrayList<String>();

								 //Variables

								 String route = file.nextLine();

								 //Splicing Route, RoundTrip and Routes from Route Information

								 String[] routeinfo = route.split(":");

								 String[] routesegmt = route.split(";");

								 //Storing Routes from Route Information

								 for (int a = 2; a < line.length(); a++)
								 {

									 routes.add(routesegmt[a]);

								 }

								 // Creating a Route

								 trainSystem.addRoute(routeinfo[0], Boolean.parseBoolean(routeinfo[1]), routes);
							 }

						 }


						 //Finding and Recording the Trains

						 if (tokens[0].contains("Trains"))
						 {

							 //Variables

							 int tn = Integer.parseInt(tokens[1]) + 1;

							 //Loop

							 for (int i = 1; i < tn; i++)
							 {

								 //List

								 ArrayList<String> trains = new ArrayList<String>();

								 //Variables

								 String train = file.nextLine();

								 //Store Train Information

								 String[] traininfo = line.split(":");

								 String[] trnsystms = line.split(";");

								 for (int a = 3; a < line.length(); a++)
								 {

									 if (traininfo[3] == "all")
									 {

										 if (traininfo[2] == "R_WS_EP")
										 {

											 trains.add("North Action");
											 trains.add("Noting Hill Gate");
											 trains.add("Bond Street");
											 trains.add("Liverpool Street");
											 trains.add("Epping");

										 }

										 if (traininfo[2] == "R_EB_EP")
										 {

											 trains.add("North Action");
											 trains.add("Noting Hill Gate");
											 trains.add("Bond Street");
											 trains.add("Liverpool Street");
											 trains.add("Epping");

										 }

										 if (traininfo[2] == "R_BS_EP")
										 {

											 trains.add("Liverpool Street");
											 trains.add("Epping");

										 }

										 if (traininfo[2] == "R_BAS_EP")
										 {

											 trains.add("Bond Street");
											 trains.add("Liverpool Street");
											 trains.add("Epping");


										 }


									 }
									 else
									 {

										 trains.add(trnsystms[a]);
									 }
								 }

								 // Creating a Train

								 trainSystem.addTrain(traininfo[0], Integer.parseInt(traininfo[1]));
								 trainSystem.registerTrain(traininfo[0], traininfo[2], trains);


							 }

						 }

						 //Finding and Recording the Events

						 if (tokens[0].contains("Events"))
						 {

							 //Variables

							 int ent = Integer.parseInt(tokens[0].substring(tokens[0].indexOf(": ")));

							 //Loop

							 for (int i = 1; i < ent; i++)
							 {

								 //List

								 ArrayList<String> events = new ArrayList<String>();

								 //Variables

								 String event = file.nextLine();

								 //Adding Events to Log

								 flaggedEvents.add(event);



								 //Store Event Information

								 String[] eventinfo = event.split(":");

								 // Executing events

								 //Open Segment

								 if (eventinfo[0] == "Open")
								 {

									 if (eventinfo[1] == "Station")
									 {

										 trainSystem.openStation(eventinfo[2]);

									 }

									 if (eventinfo[1] == "Segment")
									 {

										 trainSystem.openSegment(eventinfo[2]);

									 }

									 if (eventinfo[1] == "Route")
									 {

										 trainSystem.openRoute(eventinfo[2]);

									 }


								 }


								 //Close Segment

								 if (eventinfo[0] == "Close")
								 {

									 if (eventinfo[1] == "Station")
									 {


										 trainSystem.closeStation(eventinfo[2]);

									 }

									 if (eventinfo[1] == "Segment")
									 {

										 trainSystem.closeSegment(eventinfo[2]);

									 }

									 if (eventinfo[1] == "Route")
									 {


										 trainSystem.closeRoute(eventinfo[2]);

									 }

								 }

							 }

						 }

					 }

				 }

			 }

			 catch (FileNotFoundException e)
			 {

				e.printStackTrace();

			 }



		}

		// otherwise do nothing as the simulation has started.
	}

	/*
	 * p2.logging.Simulator and initialiseFixed provided just to test your methods without
	 * having to read the input from the file
	 */

	/**
	 * Calls the initialisedFixed and the setInitialised functions
	 *
	 * @throws FileNotFoundException
	 */

	public Simulator() throws FileNotFoundException
	{
		initialiseFixed();
		setInitialised();
	}

	/**
	 * Calls the methods from p2.ts.TrainSystem inputting information about the train, station, event, routes and segment
	 * components
	 *
	 */

	private void initialiseFixed()
	{

		if (status == SimulatorStatus.Uninitialised || status == SimulatorStatus.Initialised)
		{

			trainSystem.addStation("Stationx");
			trainSystem.addStation("Alberg");
			trainSystem.addStation("Trusty"); //Made by Josiah-John Green
			trainSystem.addStation("1tationx");

			trainSystem.addSegment("Seg1", "Stationx", "Alberg");
			trainSystem.addSegment("Seg2", "Alberg", "Trusty");
			trainSystem.addSegment("Seg3", "Trusty", "1tationx");

			ArrayList<String> rs = new ArrayList<String>();
			rs.add("Seg1");
			rs.add("Seg2");
			rs.add("Seg3");
			trainSystem.addRoute("R1", false, rs);

			trainSystem.addTrain("train_1", 2);
			trainSystem.addTrain("train_2", 1);

			ArrayList<String> stops = new ArrayList<String>();
			stops.add("Alberg");
			trainSystem.registerTrain("train_1", "R1", stops);
			stops.remove(0);
			stops.add("Trusty");
			trainSystem.registerTrain("train_2", "R1", stops);

		}

		// otherwise do nothing as the simulation has started.
	}

	/**
	 * Gets the current time
	 *
	 * @return
	 */

	public int getCurrentTime()
	{
		return currentTime;
	}

	/**
	 * Sets the current time
	 *
	 * @param currentTime
	 */

	public void setCurrentTime(int currentTime)
	{

		this.currentTime = currentTime;

	}

	/**
	 * Sets simulator status to Initialised
	 *
	 *
	 */

	private void setInitialised()
	{
		this.status = SimulatorStatus.Initialised;
	}

	/**
	 * Sets system status to Finished
	 *
	 * @return
	 */

	@Override
	public boolean isFinished()
	{
		return trainSystem.currentStatus() == SystemStatus.Finished;
	}

	/**
	 *
	 */

	@Override
	public void simulate() throws FileNotFoundException
	{

		if (status == SimulatorStatus.Initialised)
		{
			currentTime = 0;
			status = SimulatorStatus.Working;
		}

		/*
		 * start a loop here and advance the train system until it is completed.
		 */

		// create scanner to read from file

		if (status == SimulatorStatus.Working)
		{

			currentTime++;

			// check file for additional trains or events at the current Time instant and
			// process them

			// tell the trainSystem to advance

			ArrayList<Event> events = trainSystem.advance();

			// process the events

			for (Event e : events)
			{

				if (e.getTime() != currentTime)
				{

					flaggedEvents.add(e.toString());

				}

				addToLog(e);

			}

			/*
			* Keep in mind:
			*
			* 1. when the train system can no longer advance, because there are closures
			* hindering movement and there are no other input in the file to process, end
			* the loop and call the appropriate method in the train system to finish the
			* execution/simulation
			*
			*/



		}

		else
		{

			// do nothing...

		}


		// loop ends

		// outside the loop, close the scanner

		if (file != null)
		{
			file.close();

		}
    }
	
	// each worth 4 marks

	/**
	 *
	 * @return
	 */

	@Override
	public boolean validate()
	{
		boolean isValidExecution = true;

		isValidExecution = isValidExecution && super.validate();

		/*
		 * From here, see notes in superclass on printing what you are doing
		 */

		isValidExecution = isValidExecution && flaggedEvents.size() > 0;

		/*
		 * get all distinct objects from log, filter log to get events for each object
		 * ask train system to validate against the log of the object and use the value
		 * returned to update the isValidExecution variable.
		 */

		return isValidExecution;
	}

	/**
	 * Prints the contents of helper strings
	 *
	 * @return
	 */

	@Override
	public String toString()
	{

		String str = "";
		str += helperString(str) + "\n";
		str += "--- Events --\n";
		str += logSize() == 0 ? " \tno events" : "";

		for (String object : getObjects()) {
			str += "Object=[" + object + ", events=" + logSize() + "]\n";

			for (String event : getEvents(object)) {
				str += "\t" + event + "\n";

			}
		}
		str = helperString2(str);
		return str;
	}

	/**
	 * Prints the logsize and the number of events with distinct objects
	 *
	 * @return
	 */

	public String toShortString()
	{
		String str = "";
		str = helperString(str);
		str += "There are " + logSize() + " events with " + distinctObjects() + " distinct objects.\n";
		str = helperString2(str);
		return str;
	}

	/**
	 * Prints the currentTime and the station
	 *
	 * @param str
	 * @return
	 */

	private String helperString(String str)
	{
		str += "The current time instant is: " + currentTime + "\n";
		str += "The current status is: " + status.getDescription() + "\n";
		return str;
	}

	/**
	 * Prints the checks whether or not the current time is present, and whether or not validation has passed
	 * or failed
	 *
	 * @param str
	 * @return
	 */

	private String helperString2(String str)
	{
		str += currentTime <= 0 ? "\nNothing to validate as yet."
				: "\n\nValidation has " + (validate() ? "passed" : "failed");
		return str;
	}

	/*
	 * just a tester for you, will not be graded your UI/GUI class should be a
	 * different class that calls the methods in the simulator class.
	 */

	/**
	 * Executes simulator methods
	 *
	 * @param args
	 * @throws FileNotFoundException
	 */

	public static void main(String[] args) throws FileNotFoundException
	{

		Simulator simulator;

		if (args.length > 0)
		{
			simulator = new Simulator(args[0]);
		}
		else
		{
			simulator = new Simulator();
		}

		simulator.simulate();
		simulator.validate();

		//Create File
		PrintStream out = new PrintStream(new FileOutputStream("C:\\Users\\joshg\\OneDrive - The University of the West Indies, Mona Campus\\University Of The West Indies\\School Files\\Year 2 - Two\\Semester One\\Object-Oriented Programming\\Programming\\Program\\Project Two - ATMS - Modified - Josiah-John Green_JVAX\\src\\file\\simulatorFile"));

		System.setOut(out);

		System.out.println(simulator.toShortString());
		System.out.println(simulator); // uncomment to print all the events


	}
}
