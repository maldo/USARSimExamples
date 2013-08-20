import interfaces.Client;
import interfaces.Constants;
import interfaces.position2d.Position2dInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import org.mozartspaces.capi3.FifoCoordinator;
import org.mozartspaces.core.Capi;
import org.mozartspaces.core.ContainerReference;
import org.mozartspaces.core.MzsConstants.RequestTimeout;
import org.mozartspaces.core.MzsCoreException;

import structures.Point;
import structures.gps.GPSSensorData;

public class MozartB
{

	public MozartB(Capi capi) throws MzsCoreException
	{

		Client cl = new Client(Constants.localhost, Constants.PortUsarasim,
				"P2AT", "2", new Point(47.17f, -55.7f, -2.25f));

		cl.start();

		Position2dInterface p2d = cl.requestPosition2dInterface();

		ContainerReference usar = capi.lookupContainer("USAR");

		while (true)
		{
			ArrayList<Serializable> entry = capi.take(usar,
					Arrays.asList(FifoCoordinator.newSelector()),
					RequestTimeout.INFINITE, null);

			if (entry.get(0).getClass().isAssignableFrom(GPSSensorData.class))
			{
				GPSSensorData gpsd = (GPSSensorData) entry.get(0);

				if (gpsd.getFix() % 2 == 0)
				{
					p2d.setSpeed(3, 3);
				}
				else
				{
					p2d.setSpeed(-1, -1);
				}

			}

		}

	}

}
