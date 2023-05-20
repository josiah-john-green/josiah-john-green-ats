package bnk.interfaces;

import bnk.events.Event;

public interface OpenClose {
	Event open ();
	Event close();
	boolean isOpen();
}
