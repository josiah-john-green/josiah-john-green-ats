package bnk.ts;

import bnk.enums.Light;

public class TrafficLight {

	private static int nextID = 1;
	private int id = nextID++;
	private Light colour = Light.Green;

	public TrafficLight() {
	}

	public void changeLight() {
		colour = colour == Light.Red ? Light.Green : Light.Red;
	}

	public Light getColour() {
		return colour;
	}

	@Override
	public String toString() {
		return "p2.ts.TrafficLight [id=" + id + ", colour=" + colour.getDescription() + ", verified="
				+ (verify() ? "Yes" : "No") + "]";
	}

	public boolean verify() {
		return colour != null;
	}
}
