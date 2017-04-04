package bs.commons.objects.organization;

import java.util.HashMap;

public class IdFactory
{

	private static HashMap<Scope, LocalIdFactory> regionalFactories = initializeDefaultFactory();

	private static HashMap<Scope, LocalIdFactory> initializeDefaultFactory()
	{
		HashMap<Scope, LocalIdFactory> defaultFactories = new HashMap<Scope, LocalIdFactory>();
		defaultFactories.put(Scope.BasicScope.GLOBAL, new LocalIdFactory());
		return defaultFactories;
	}

	public static ObjectId generateId(String name, ObjectType type)
	{
		return regionalFactories.get(Scope.BasicScope.GLOBAL).generateId(name, type);
	}

	private static class LocalIdFactory
	{

		HashMap<ObjectType, Integer> globalIndicies; // indicies based on the type of component
		Integer overallIndex;

		public LocalIdFactory()
		{
			globalIndicies = new HashMap<ObjectType, Integer>();
			overallIndex = 0;
		}

		public ObjectId generateId(String name, ObjectType type)
		{
			ObjectId id = new ObjectId(name, type);
			if (globalIndicies.containsKey(type))
			{
				id.address().globalIndex.set(globalIndicies.get(type));
				globalIndicies.put(type, globalIndicies.get(type) + 1);
			} else
			{
				id.address().globalIndex.set(0);
				globalIndicies.put(type, 0);
			}
			return id;
		}

	}
}
