package bs.commons.objects.execution;

import java.lang.reflect.Field;

import bs.commons.objects.manipulation.XMLParser;

public class ExternalFieldUpdate
{

	public Field field;
	public Object parent;
	public Object value;
	public String name;

	public ExternalFieldUpdate(Field field, Object parent, String name)
	{
		this.field = field;
		this.parent = parent;
		this.name = name;
		try
		{
			this.value = field.get(parent);
		} catch (IllegalArgumentException | IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			this.value = null;
			e.printStackTrace();
		}
	}

	@MethodId(id = "Update")
	public void updateValue(Object new_value)
	{
		System.out.println("Updating " + new_value);
		System.out.println(XMLParser.serializeObject(parent));
		try
		{
			field.set(parent, new_value);
		} catch (IllegalArgumentException | IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
