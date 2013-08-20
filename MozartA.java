import java.util.Arrays;

import interfaces.Client;
import interfaces.Constants;
import interfaces.gps.GPSInterface;
import interfaces.position3d.Position3dInterface;

import org.mozartspaces.capi3.FifoCoordinator;
import org.mozartspaces.core.Capi;
import org.mozartspaces.core.ContainerReference;
import org.mozartspaces.core.Entry;
import org.mozartspaces.core.MzsCoreException;

import structures.Point;
import structures.gps.GPSSensorData;
import structures.position3d.Pose3d;

public class MozartA
{

	public MozartA(Capi capi) throws MzsCoreException
	{

		Client cl = new Client(Constants.localhost, Constants.PortUsarasim,
				"AirRobot", "1", new Point(47.17f, -55.7f, -2.25f));

		cl.start();

		Position3dInterface p3d = cl.requestPosition3dInterface();
		GPSInterface gps = cl.requestGpsInterface();

		ContainerReference usar = capi.lookupContainer("USAR");

		Pose3d vel = new Pose3d(0.5f, 1, 0);

		while (true)
		{
			GPSSensorData gpsd = gps.getData();

			Entry x = new Entry(gpsd, Arrays.asList(FifoCoordinator
					.newCoordinationData()));
			capi.write(usar, 0, null, x);

			p3d.setSpeed(vel);

			try
			{
				this.wait(100);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// capi.destroyContainer(usar, null);
	}

}
