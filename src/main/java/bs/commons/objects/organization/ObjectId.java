package bs.commons.objects.organization;

public class ObjectId
{

	private ObjectType type; // type of object this id represents
	private Address address; // address of the object represented by this id
	private VariableName name; // name of the object represented by this id, which can contain additional data that can be displayed or hidden depending on the usage

	public ObjectId(String name, ObjectType type)
	{

		this.type = type;
		this.name = new VariableName(name);
		this.address = new Address();
	}

	public ObjectType type()
	{
		return type;
	}

	public String getName()
	{
		return null;
	}

	public String getName(boolean simple)
	{
		if (simple)
		{
			return null;//description;
		} else
		{
			return null;//description;//+ count;
		}
	}

	public String getNamePrefix()
	{
		return null;//prefix;
	}

	public Integer getIndex()
	{
		return null;//localIndex;
	}

	public Integer getAddress()
	{
		return null;//address;
	}

	public void setAddress(Integer address)
	{
		this.address.address.set(address);
	}

	private static class IDFactory
	{

		//		public static ArrayList<ID> assignedIds = new ArrayList<ID>();// names already assigned based on type
		//
		//		public static ID getID(String name, ComponentType type)
		//		{
		//
		//		}
		//
		//		private static ID adjustID(ID old)
		//		{
		//			for (ID check : assignedIds)
		//			{
		//				if (check.type.equals(old.type)&&check.name.equals(old.name))
		//				{
		//				}
		//			}
		//		}
		//		public static String getAdjustedName(String name, ComponentType type)
		//		{
		//			Integer append = 1;
		//			String newName = name;
		//			while (typeNames.get(type).)
		//		}

	}
}
