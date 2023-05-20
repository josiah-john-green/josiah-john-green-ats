package bnk.logging;

import bnk.enums.RSStatus;
import bnk.events.Event;
import bnk.interfaces.OpenClose;

public class Station extends Logable implements Comparable<Station>, OpenClose {

	private String name; /* once set, is immutable */
	private RSStatus status = RSStatus.Open;

	public Station(String name) {
		setName(name);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name != null && !name.equals("") ? name : this.name;
	}

	public boolean verify() {
		boolean verified = true;
		return verified;
	}

	@Override
	public boolean isOpen() {
		return status == RSStatus.Open;
	}

	@Override
	public Event close() {
		status = status == RSStatus.Open ? status = RSStatus.ClosedForMaintenance : status;
		return null;
	}

	@Override
	public Event open() {
		status = status == RSStatus.ClosedForMaintenance ? status = RSStatus.Open : status;
		return null;
	}

	@Override
	public int compareTo(Station station) {
		return name.compareTo(station.getName());
	}

	@Override
	public String toString() {
		return "p2.logging.Station [name=" + name + ", status=" + status.getDescription() + "]";
	}

	@Override
	public boolean validate() {
		return super.validate();
	}

}
