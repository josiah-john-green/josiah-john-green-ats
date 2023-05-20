package bnk.logging;

import bnk.enums.SimulatorStatus;
import bnk.enums.SystemStatus;
import bnk.events.Event;
import bnk.interfaces.SimulatorInterface;
import bnk.ts.TrainSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Simulator extends Logable implements SimulatorInterface {

	private int currentTime = -1;
	private SimulatorStatus status = SimulatorStatus.Uninitialised;
	private TrainSystem trainSystem = new TrainSystem();
	private Scanner file;
	private ArrayList<String> flaggedEvents = new ArrayList<String>();

	public Simulator(String initialisationFile) throws FileNotFoundException {
		initialise(initialisationFile);
		setInitialised();
	}

	private void initialise(String initialisationFile) throws FileNotFoundException {

		if (status == SimulatorStatus.Uninitialised || status == SimulatorStatus.Initialised) {

			// create scanner to read from file
			file = new Scanner(new File(initialisationFile));

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
		}

		// otherwise do nothing as the simulation has started.
	}

	/*
	 * p2.logging.Simulator and initialiseFixed provided just to test your methods without
	 * having to read the input from the file
	 */
	public Simulator() throws FileNotFoundException {
		initialiseFixed();
		setInitialised();
	}

	private void initialiseFixed() {

		if (status == SimulatorStatus.Uninitialised || status == SimulatorStatus.Initialised) {

			trainSystem.addStation("Stationx");
			trainSystem.addStation("Alberb");
			trainSystem.addStation("Trusty");
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

	public int getCurrentTime() {
		return currentTime;
	}

	private void setInitialised() {
		this.status = SimulatorStatus.Initialised;
	}

	@Override
	public boolean isFinished() {
		return trainSystem.currentStatus() == SystemStatus.Finished;
	}

	@Override
	public void simulate() {
		if (status == SimulatorStatus.Initialised) {
			currentTime = 0;
			status = SimulatorStatus.Working;
		}

		/*
		 * start a loop here and advance the train system until it is completed.
		 */

		if (status == SimulatorStatus.Working) {
			currentTime++;

			// check file for additional trains or events at the currenttTime instant and
			// process them

			// tell the trainSystem to advance
			ArrayList<Event> events = trainSystem.advance();

			/* process the events */
			for (Event e : events) {
				if (e.getTime() != currentTime)
					flaggedEvents.add(e.toString());
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

		} else {
			/* do nothing... */
		}

		// loop ends

		// outside the loop, close the scanner
		if (file != null)
			file.close();
	}
	
	// each worth 4 marks

	@Override
	public boolean validate() {
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

	@Override
	public String toString() {
		String str = "";
		str += helperString(str) + "\n";
		str += "--- Events --\n";
		str += logSize() == 0 ? " \tno events" : "";
		for (String object : getObjects()) {
			str += "Object=[" + object + ", events=" + logSize() + "]\n";
			for (String event : getEvents(object))
				str += "\t" + event + "\n";
		}
		str = helperString2(str);
		return str;
	}

	public String toShortString() {
		String str = "";
		str = helperString(str);
		str += "There are " + logSize() + " events with " + distinctObjects() + " distinct objects.\n";
		str = helperString2(str);
		return str;
	}

	private String helperString(String str) {
		str += "The current time instant is: " + currentTime + "\n";
		str += "The current status is: " + status.getDescription() + "\n";
		return str;
	}

	private String helperString2(String str) {
		str += currentTime <= 0 ? "\nNothing to validate as yet."
				: "\n\nValidation has " + (validate() ? "passed" : "failed");
		return str;
	}

	/*
	 * just a tester for you, will not be graded your UI/GUI class should be a
	 * different class that calls the methods in the simulator class.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Simulator simulator;

		if (args.length > 0)
			simulator = new Simulator(args[0]);
		else
			simulator = new Simulator();

		simulator.simulate();
		simulator.validate();
		System.out.println(simulator.toShortString());
		// System.out.println(simulator); // uncomment to print all the events
	}
}
