package p2.interfaces;

import java.io.FileNotFoundException;

public interface SimulatorInterface
{
	public boolean isFinished();

	public void simulate() throws FileNotFoundException;

	public boolean validate();

}
