package com.be3short.obj.manipulation;

import com.be3short.io.file.FileSystemOperator;
import com.be3short.obj.access.FieldFinder;
import com.be3short.obj.modification.XMLParser;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.reflections.util.Utils;

public class FieldMapper
{

	private ArrayList<Field> skipFields;
	public static Reflections reflections;
	public HashMap<String, ArrayList<Field>> elements;

	private ArrayList<Field> getSkipFields(Class<?>... skip_classes)
	{
		ArrayList<Field> fieldz = new ArrayList<Field>();
		for (Class<?> skip_class : skip_classes)
		{
			fieldz.addAll(Arrays.asList(skip_class.getDeclaredFields()));
		}
		return fieldz;
	}

	private void getReflections()
	{
		if (reflections == null)
		{

			Reflections.log = null;
			reflections = new Reflections();

		}
	}

	public boolean isState(Class<?> potential_state)
	{
		String clas = potential_state.getName();
		return elements.containsKey(potential_state);
	}

	public ArrayList<Field> getClassFields(Class<?> class_to_find)
	{
		String clas = class_to_find.getName();
		ArrayList<Field> fields = null;
		try
		{
			if (elements.containsKey(clas))
			{
				fields = elements.get(clas);
			} else
			{
				getReflections();
				HashMap<String, ArrayList<Field>> newStates = getClassFieldsMappingOfType(class_to_find);
				for (String object : newStates.keySet())
				{
					if (!elements.containsKey(object))
					{
						elements.put(object, newStates.get(object));
					}
				}
				FileSystemOperator.createOutputFile("build/.fieldmapping.xml", XMLParser.serializeObject(elements));
				// System.out.println(XMLParser.serializeObject(elements));
				fields = elements.get(clas);
			}
		} catch (Exception notSearched)
		{
			notSearched.printStackTrace();

		}
		return fields;
	}

	public <T extends Annotation> Set<Class<?>> getPackageClassesAnnotated(Class<T> clas)
	{
		return reflections.getTypesAnnotatedWith(clas);
	}

	/*
	 * Creates a set containing all classes included within the project packages and dependencies that are a sub type of
	 * the specified input. This is used to find specific types of classes so that their properties, ie fields and
	 * methods, can be determined for later use
	 */
	public <T> Set<Class<? extends T>> getPackageClassesOfType(Class<T> clas)
	{

		return reflections.getSubTypesOf(clas);
	}

	/*
	 * Creates a mapping containing all declared fields for every state class included in the project and dependencies.
	 * This mapping allows for values to be updated without changing pointers.
	 */
	public void makeAllFieldsAccessable(HashMap<String, ArrayList<Field>> elements)
	{

		for (String clas : elements.keySet())
		{
			for (Field field : elements.get(clas))
			{
				field.setAccessible(true);
			}
		}
		// System.out.println(XMLParser.serializeObject(elements));
	}

	public <T> HashMap<String, ArrayList<Field>> initializeClassFieldMap(Set<Class<?>> search_classes)
	{
		HashMap<String, ArrayList<Field>> elements = new HashMap<String, ArrayList<Field>>();
		for (Class<?> clas : search_classes)
		{

			elements.put(clas.getName(), new ArrayList<Field>());
		}
		return elements;
	}

	/*
	 * Creates a mapping containing all declared fields for every state class included in the project and dependencies.
	 * This mapping allows for values to be updated without changing pointers.
	 */
	public void getClassFieldMapping(HashMap<String, ArrayList<Field>> elements, Set<Class<?>> search_classes,
	Class<?> search_class)
	{

		for (Class<?> clas : search_classes)
		{
			for (Field fields : clas.getDeclaredFields())
			{
				if (!fields.getName().contains("$SWITCH_TABLE$") && !fields.getType().equals(search_class)
				&& !skipFields.contains(fields))// &&
				// !skipFields.contains(fields))
				{
					if (!elements.get(clas.getName()).contains(fields))
					{
						elements.get(clas.getName()).add(fields);
					}
				}
			}
			if (search_classes.contains(clas.getSuperclass()))
			{
				Class<?> superClass = clas.getSuperclass();
				ArrayList<Field> extendedFields = new ArrayList<Field>(Arrays.asList(superClass.getDeclaredFields()));
				while (search_classes.contains(superClass))
				{
					for (Field fi : extendedFields)
					{
						if (!fi.getName().contains("$SWITCH_TABLE$") && !fi.getType().equals(search_class)
						&& !skipFields.contains(fi))// &&
						{
							if (!elements.get(clas.getName()).contains(fi))
							{
								elements.get(clas.getName()).add(fi);
							}
						}
					}
					clas = superClass;
					superClass = superClass.getSuperclass();
					extendedFields.addAll(Arrays.asList(superClass.getDeclaredFields()));

				}
				// elements.put(clas, extendedFields.toArray(new Field[extendedFields.size()]));
			}

		}
		makeAllFieldsAccessable(elements);

	}

	public <T> HashMap<String, ArrayList<Field>> getClassFieldsMappingOfType(Class<T> class_search)
	{

		HashMap<String, ArrayList<Field>> eles = new HashMap<String, ArrayList<Field>>();

		try

		{
			Object elefie = XMLParser.getObject(new File("build/.fieldmapping.xml"));
			if (elefie != null)
			{
				if (elefie.getClass().equals(HashMap.class))
				{
					HashMap<Object, Object> map = (HashMap<Object, Object>) elefie;

					for (Object classObj : map.keySet())
					{
						String cl = (String) classObj;
						eles.put(cl, (ArrayList<Field>) map.get(classObj));
					}
					return eles;
				}
			}
			throw new Exception();

		} catch (Exception e)
		{
			getReflections();
			Set<Class<? extends T>> classes = getPackageClassesOfType(class_search);//
			// getPackageClassesAnnotated(State.class);
			HashSet<Class<?>> classSet = new HashSet<Class<?>>();
			classSet.addAll(classes);
			classSet.add(class_search);
			HashMap<String, ArrayList<Field>> elems = initializeClassFieldMap(classSet);
			getClassFieldMapping(elems, classSet, class_search);
			while (fields(eles) < fields(elems))
			{
				getClassFieldMapping(elems, classSet, class_search);
				eles = elems;
			}
			FileSystemOperator.createOutputFile("build/.fieldmapping.xml", XMLParser.serializeObject(elems));
			// System.out.println(XMLParser.serializeObject(elems));
			return elems;
		}

	}

	private static Integer fields(HashMap<String, ArrayList<Field>> test)
	{
		Integer count = 0;
		for (ArrayList<Field> clas : test.values())
		{
			count += clas.size();
		}
		return count;
	}

	public FieldMapper(Class<?> base_class)
	{
		skipFields = getSkipFields(base_class);
		elements = getClassFieldsMappingOfType(base_class);
	}
}
