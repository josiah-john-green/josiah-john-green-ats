package p2.interfaces;

import p2.events.Event;

public interface OpenClose
{
	Event open ();
	Event close();
	boolean isOpen();
}
