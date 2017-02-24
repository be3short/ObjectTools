package bs.commons.objects.manipulation;

public class ObjectCloner
{

	public static Object xmlClone(Object object)
	{
		String xmlString = XMLParser.serializeObject(object);
		Object clonedObject = XMLParser.getObjectFromString(xmlString);
		return clonedObject;
	}
}
