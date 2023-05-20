package bnk.events;

import bnk.enums.Action;

public class CFOSEvent extends Event {

	private Action action;

	public CFOSEvent(String object, int time, Action action) {
		super(object, time);
		this.action = action;
	}

	@Override
	public boolean equals(Object oe) {
		if (oe instanceof CFOSEvent)
			return super.equals(oe) && action == ((CFOSEvent) oe).action;
		return false;
	}

	@Override
	public String toString() {
		return action.getDescription() + " p2.events.p2.events.Event [" + super.toString() + "]";
	}

}
