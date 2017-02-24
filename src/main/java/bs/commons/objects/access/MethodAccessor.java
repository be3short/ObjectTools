package bs.commons.objects.access;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.HashMap;

public class MethodAccessor
{

	/*
	 * Gets the methods of an object that match the annotation
	 * 
	 * @param object - Object to gather methods from
	 * 
	 * @param - annotation_filter - Desired method annotations to search for -
	 * all other methods filtered
	 * 
	 * @return Mapping of methods indexed by name
	 */
	public static HashMap<String, Method> getEventMethod(Object object, Class... method_annotations)
	{
		Method methods[] = object.getClass().getDeclaredMethods(); // methods of the access class
		HashMap<String, Method> returnMethods = new HashMap<String, Method>();
		for (Integer i = 0; i < methods.length; i++) // iterate through the methods
		{

			Method method = methods[i]; // store current method

			if (method_annotations.length > 0)
				for (Class annotation : method_annotations)
				{
					try
					{
						if (method.getAnnotation(annotation).annotationType().equals(annotation))
						{
							returnMethods.put(method.getName(), method);
						}
					} catch (Exception e)
					{
						// throw new Exception();
					}
				}
			else
			{
				returnMethods.put(method.getName(), method);
			}

		}
		return returnMethods;
	}

}
