package bnk.events;

public class OccupiedEvent extends Event {

	/* once set are immutable */
	private String train;
	private boolean isEntry;

	public OccupiedEvent(String objectID, int time, String train, boolean isEntry) {
		super(objectID, time);
		this.train = train;
		this.isEntry = isEntry;
	}

	@Override
	public boolean equals(Object oe) {
		if (oe instanceof OccupiedEvent)
			return super.equals(oe) && train.equals(((OccupiedEvent) oe).train)
					&& isEntry == ((OccupiedEvent) oe).isEntry;
		return false;
	}

	@Override
	public String toString() {
		return (isEntry? "Enter p2.logging.p2.logging.Station p2.events.p2.events.Event" : "Left p2.logging.p2.logging.Station p2.events.p2.events.Event") + "[" + super.toString() + ", p2.logging.p2.logging.Train="
				+ train + "]";
	}
}
