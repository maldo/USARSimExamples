import interfaces.Client;
import interfaces.Constants;
import interfaces.position2d.Position2dInterface;
import interfaces.position3d.Position3dInterface;
import structures.Point;
import structures.position3d.Pose3d;

public class MoveRobot
{
	public static void main(String[] args)
	{
		Client cl = new Client(Constants.localhost, Constants.PortUsarasim,
				"AirRobot", "1", new Point(47.17f, -55.7f, -2.25f));

		cl.start();

		Position3dInterface p3d = cl.requestPosition3dInterface();

		while (true)
		{
			Pose3d vel = new Pose3d(1,1,0);
			
			p3d.setSpeed(vel);
		}
	}

}
