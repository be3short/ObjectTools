package bs.commons.objects.manipulation;

import java.io.File;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;

import bs.commons.io.system.IO.MessageCategory;
import bs.commons.io.system.IOFilter;
import bs.commons.objects.access.CallerRetriever;

public class XMLParser
{

	private static ArrayList<String> ownClassFilter = getOwnPackageFilter();
	public static IOFilter speedFilter;
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

	public static String serializeObject(Object obj, MessageCategory category)
	{

		String returnString = null;
		boolean filterParse = false;
		if (speedFilter != null)
		{
			filterParse = speedFilter.printStatus(CallerRetriever.retriever.getCallingClass(ownClassFilter), category);
		}
		if (!filterParse)
		{
			try
			{
				returnString = xstream.toXML(obj);
			} catch (Exception e)
			{

			}
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

	private static ArrayList<String> getOwnPackageFilter()
	{
		ArrayList<String> filterz = new ArrayList<String>();
		filterz.add("bs.commons.objects.manipulation");
		return filterz;
	}

}
