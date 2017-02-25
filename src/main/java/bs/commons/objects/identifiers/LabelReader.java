package bs.commons.objects.identifiers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class LabelReader
{

	public static String getLabel(Annotation annotation)
	{
		if (annotation.getClass().equals(Settings.class))
		{
			String label = ((Settings) annotation).label();
			return label;
		}
		return null;
	}

	public static String getLabel(Class annotation, Field field)
	{
		if (annotation.equals(Settings.class))
		{
			return getSettingsLabel(field);
		} else
		{
			return null;
		}
	}

	public static String getSettingsLabel(Field field)
	{
		String label = field.getName();
		try
		{
			Settings param = (Settings) field.getDeclaredAnnotation(Settings.class);
			if (!param.label().equals(""))
			{
				System.out.println("label" + param.label());
				label = param.label();
			}
		} catch (Exception e)
		{
		}
		return label;
	}

}
