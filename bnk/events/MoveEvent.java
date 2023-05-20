package bnk.events;

public class MoveEvent extends Event {
	private String fromStation, toStation; /* once set are immutable */

	public MoveEvent(String objectID, int time, String fromStation, String toStation) {
		super(objectID, time);
		this.fromStation = fromStation;
		this.toStation = toStation;
	}

	@Override
	public String toString() {
		return "p2.events.p2.events.MoveEvent [" + super.toString() + ", From p2.logging.p2.logging.Station=" + fromStation + ", To p2.logging.p2.logging.Station=" + toStation + "]";
	}

	@Override
	public boolean equals(Object me) {
		if (me instanceof MoveEvent)
			return super.equals(me) && fromStation.equals(((MoveEvent) me).fromStation)
					&& toStation.equals(((MoveEvent) me).toStation);
		return false;
	}
}
