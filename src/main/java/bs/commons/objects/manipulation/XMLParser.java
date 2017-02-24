package bs.commons.objects.manipulation;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

import com.thoughtworks.xstream.XStream;

public class XMLParser
{

	public static XStream xstream = new XStream();

	public static String serializeObject(Object obj)
	{
		String returnString = null;
		try
		{
			returnString = xstream.toXML(obj);
		} catch (Exception e)
		{

		}
		return returnString;
	}

	public static Object getObject(String path)
	{
		try
		{
			return xstream.fromXML(new File(path));
		} catch (Exception e)
		{

		}
		return null;
	}

	public static Object getObject(File file)
	{
		try
		{
			if (file.exists())
			{
				return xstream.fromXML(file);
			} else
			{
				return null;
			}
		} catch (Exception e)
		{

		}
		return null;
	}

	public static Object getObjectFromString(String obj_string)
	{

		return xstream.fromXML(obj_string);
	}

}
