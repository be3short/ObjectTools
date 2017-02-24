package bs.commons.objects.access;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.HashMap;

import bs.commons.objects.execution.MethodId;

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

	public static Method getEventMethod(Object method_class, String identifier) throws Exception
	{
		Method methods[] = method_class.getClass().getDeclaredMethods(); // methods of the access class

		for (Integer i = 0; i < methods.length; i++) // iterate through the methods
		{
			try
			{
				Method method = methods[i]; // store current method

				MethodId annotation = (MethodId) method.getAnnotation(MethodId.class); // check to see if it is method
																						// to be executed
				if (annotation.id().equals(identifier)) // method is to be executed
				{
					return method;
				}
			} catch (Exception e)
			{
				// throw new Exception();
			}
		}
		throw new Exception();
		// return null;
	}

	public static Object executeMethod(Object method_class, Method method, Object... args)
	{
		try
		{
			method.setAccessible(true);
			System.out.println(method.getName() + " " + method.getParameterCount());
			return method.invoke(method_class, args);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Object executeMethod(Object method_class, String method_id, Object... args)
	{
		try
		{
			Method method = getEventMethod(method_class, method_id);
			method.setAccessible(true);
			System.out.println(method.getName() + " " + method.getParameterCount());
			return method.invoke(method_class, args);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
