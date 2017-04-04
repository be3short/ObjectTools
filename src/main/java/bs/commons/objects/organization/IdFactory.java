package bs.commons.objects.organization;

import java.util.HashMap;

public class IdFactory
{

	private static class LocalIdFactory
	{

		HashMap<ObjectType, Integer> globalIndicies; // indicies based on the type of component
		public static Integer overallIndex = 0;
	}
}
