import java.util.Arrays;

import org.mozartspaces.capi3.FifoCoordinator;
import org.mozartspaces.core.Capi;
import org.mozartspaces.core.DefaultMzsCore;
import org.mozartspaces.core.MzsConstants.Container;
import org.mozartspaces.core.MzsCore;
import org.mozartspaces.core.MzsCoreException;

public class Mozart
{
	public static void main(String[] args) throws MzsCoreException
	{
		MzsCore core = DefaultMzsCore.newInstance();
		Capi capi = new Capi(core);

		capi.createContainer("USAR", null, Container.UNBOUNDED, null,
				Arrays.asList(new FifoCoordinator()), null);

		new MozartA(capi);
		new MozartB(capi);

	}

}
