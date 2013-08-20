import interfaces.Client;
import interfaces.Constants;
import structures.Point;

public class HelloRobot
{

	public static void main(String[] args)
	{
		new Client(Constants.localhost, Constants.PortUsarasim, "P2AT", "1",
				new Point(47.17f, -55.7f, -2.25f)).start();
	}

}
