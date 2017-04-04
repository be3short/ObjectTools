//package bs.commons.objects.access;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//
//public class ObjectFinder
//{
//
//	public static ArrayList<Class> deeperSearchClasses = new ArrayList<Class>();
//
//	public static void setDeeperSearchClasses(Class[] deeper)
//	{
//		deeperSearchClasses.addAll(Arrays.asList(deeper));
//	}
//
//	public static <S> HashMap<String, S> findSuperClassSubObjects(Object search_obj, Class<S> search_class)
//	{
//		HashMap<String, S> states = new HashMap<String, S>();
//		HashMap<String, Object> fieldObjects = FieldAccessor.getObjectFieldValues(search_obj, true, true);
//		for (String fieldName : fieldObjects.keySet())
//		{
//			try
//			{
//				boolean searchDeeper = false;
//				//System.out.println(fieldName);
//				Object sysObj = fieldObjects.get(fieldName);
//				Class superClass = sysObj.getClass();
//
//				while (superClass != Object.class)
//				{
//					Object newSysObj = superClass.cast(sysObj);
//					if (superClass.equals(search_class))
//					{
//						S potentialState = (S) newSysObj;
//						if (!states.values().contains(potentialState))
//						{
//							states.put(fieldName, potentialState);
//
//						}
//					} else if (deeperSearchClasses.contains(superClass))
//					{
//						searchDeeper = true;
//
//					}
//					sysObj = newSysObj;
//					superClass = superClass.getSuperclass();
//					//	System.out.println(superClass.getName());
//
//				}
//				if (searchDeeper)
//				{
//					HashMap<String, S> deeperClasses = findSuperClassSubObjects(fieldObjects.get(fieldName),
//					search_class);
//					for (String deeperClass : deeperClasses.keySet())
//					{
//						if (!states.values().contains(deeperClasses.get(deeperClass)))
//						{
//							states.put(deeperClass, deeperClasses.get(deeperClass));
//
//						}
//					}
//				}
//
//			} catch (Exception e)
//			{
//				e.printStackTrace();
//			}
//
//		}
//		return states;
//	}
//
//	public static <S> HashMap<String, S> getHybridSystems(Object search_obj, Class<S> search_class)
//	{
//		HashMap<String, S> states = new HashMap<String, S>();
//		HashMap<String, Object> fieldObjects = FieldAccessor.getObjectFieldValues(search_obj, true, true);
//		for (String fieldName : fieldObjects.keySet())
//		{
//			try
//			{
//				//System.out.println(fieldName);
//				Object sysObj = fieldObjects.get(fieldName);
//				Class superClass = sysObj.getClass();
//
//				while (superClass != Object.class)
//				{
//					Object newSysObj = superClass.cast(sysObj);
//					if (superClass.equals(search_class))
//					{
//						S potentialState = (S) newSysObj;
//						if (!states.values().contains(potentialState))
//						{
//							states.put(fieldName, potentialState);
//
//						}
//					}
//					sysObj = newSysObj;
//					superClass = superClass.getSuperclass();
//					//	System.out.println(superClass.getName());
//
//				}
//			} catch (Exception e)
//			{
//				e.printStackTrace();
//			}
//
//		}
//		return states;
//	}
//}

package bs.commons.objects.access;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import bs.commons.io.system.StringFormatter;

public class ObjectFinder
{

	public static ArrayList<Class> deeperSearchClasses = new ArrayList<Class>();

	public static void setDeeperSearchClasses(Class[] deeper)
	{
		deeperSearchClasses.addAll(Arrays.asList(deeper));
	}

	public static <S> HashMap<String, S> findSuperClassSubObjects(Object search_obj, Class<S> search_class)
	{
		HashMap<String, S> states = new HashMap<String, S>();
		HashMap<String, Object> fieldObjects = FieldAccessor.getObjectFieldValues(search_obj, true, true);
		for (String fieldName : fieldObjects.keySet())
		{
			try
			{
				boolean searchDeeper = false;
				//System.out.println(fieldName);
				Object sysObj = fieldObjects.get(fieldName);
				Class superClass = sysObj.getClass();

				while (superClass != Object.class)
				{
					Object newSysObj = superClass.cast(sysObj);
					if (superClass.equals(search_class))
					{
						S potentialState = (S) newSysObj;
						if (!states.values().contains(potentialState))
						{
							if (potentialState != null)
							{
								states.put(StringFormatter.getAppendedName(fieldName, states.keySet()), potentialState);
							}
						}
					} else if (deeperSearchClasses.contains(superClass))
					{
						searchDeeper = true;
						if (searchDeeper)
						{
							HashMap<String, S> deeperClasses = findSuperClassSubObjects(sysObj, search_class);
							for (String deeperClass : deeperClasses.keySet())
							{
								if (!states.values().contains(deeperClasses.get(deeperClass)))
								{
									states.put(StringFormatter.getAppendedName(deeperClass, states.keySet()),
									deeperClasses.get(deeperClass));

								}
							}
						}
					}
					sysObj = newSysObj;
					superClass = superClass.getSuperclass();
					//	System.out.println(superClass.getName());

				}

			} catch (Exception e)
			{
				//e.printStackTrace();
			}

		}
		return states;
	}

	public static <S> HashMap<String, S> getHybridSystems(Object search_obj, Class<S> search_class)
	{
		HashMap<String, S> states = new HashMap<String, S>();
		HashMap<String, Object> fieldObjects = FieldAccessor.getObjectFieldValues(search_obj, true, true);
		for (String fieldName : fieldObjects.keySet())
		{
			try
			{
				//System.out.println(fieldName);
				Object sysObj = fieldObjects.get(fieldName);
				Class superClass = sysObj.getClass();

				while (superClass != Object.class)
				{
					Object newSysObj = superClass.cast(sysObj);
					if (superClass.equals(search_class))
					{
						S potentialState = (S) newSysObj;
						if (!states.values().contains(potentialState))
						{
							states.put(fieldName, potentialState);

						}
					}
					sysObj = newSysObj;
					superClass = superClass.getSuperclass();
					//	System.out.println(superClass.getName());

				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}

		}
		return states;
	}
}
