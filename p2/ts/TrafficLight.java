package p2.ts;

import p2.enums.Light;

public class TrafficLight
{

	private static int nextID = 1;
	private int id = nextID++;
	private Light colour = Light.Green;

	/**
	 * Set the traffic light.
	 *
	 */

	public TrafficLight()
	{
	}


	/**
	 * Changes the colour of the light.
	 *
	 */

	public void changeLight()
	{
		colour = colour == Light.Red ? Light.Green : Light.Red;
	}

	/**
	 * Gets the colour of the light.
	 *
	 * @return
	 */

	public Light getColour()
	{
		return colour;
	}

	/**
	 * Prints the id of the train, the colour description of the traffic light
	 *
	 * @return
	 */

	@Override
	public String toString()
	{
		return "p2.ts.TrafficLight [id=" + id + ", colour=" + colour.getDescription() + ", verified="
				+ (verify() ? "Yes" : "No") + "]";
	}

	/**
	 * Verified is true, if the colour is not equal to null and false otherwise.
	 *
	 * @return
	 */

	public boolean verify()
	{

		boolean result = false;

		if (this.colour != null && (this.colour.equals(Light.Red) || this.colour.equals(Light.Red)))
		{

			result = true;

		}
		else
		{

			result = false;

		}

		return result;

	}
}
