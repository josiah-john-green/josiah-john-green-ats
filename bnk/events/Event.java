package bnk.events;

abstract public class Event{
	
	private String object; 
	private int time; 

	public Event(String object, int time) {
		this.object = object;
		this.time = time;
	}

	public String getObject() {
		return object;
	}

	public int getTime() {
		return time;
	}

	@Override
	public boolean equals(Object evnt) {
		if (evnt instanceof Event)
			return object.equals(((Event) evnt).object) && time == (((Event) evnt).time);
		return false;
	}
	
	@Override
	public String toString() {
		return "Object=" + object + ", Time()=" + time;
	}
}
