package bs.commons.objects.organization;

import bs.commons.objects.access.Protected;

public abstract class Identified
{

	private Protected<ObjectId> id;

	public Identified(String name, ObjectType type)
	{
		id = new Protected<ObjectId>(IdFactory.generateId(name, type), true);
	}

	public ObjectId id()

	{
		return id.get();
	}

	public static Protected<ObjectId> idAccess(Identified i)
	{
		return i.id;
	}
}
