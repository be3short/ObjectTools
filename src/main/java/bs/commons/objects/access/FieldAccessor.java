package bs.commons.objects.access;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;

import bs.commons.objects.identifiers.LabelReader;

public class FieldAccessor
{

	/*
	 * Gets the fields of an object
	 * 
	 * @param object - Object to gather fields from
	 * 
	 * @param include_private - Flag to include private fields
	 * 
	 * @param - class_filter - Desired field classes to search for - all other
	 * classes filtered
	 * 
	 * @return Mapping of fields indexed by field name
	 */
	public static HashMap<String, Field> getObjectFields(Object object, boolean include_private,
	@SuppressWarnings("rawtypes") Class... class_filter)
	{
		HashMap<String, Field> fields = new HashMap<String, Field>(); // initialize field map
		Field[] objectFields = object.getClass().getDeclaredFields(); // get all declared fields of object]

		for (Field field : objectFields) // iterate through fields
		{
			boolean filter = false; // initialize filter flag

			if (class_filter.length > 0) // there are specific field classes  
			{
				filter = true; // filter the current field unless class matches filter

				for (@SuppressWarnings("rawtypes")
				Class fieldClass : class_filter) // iterate through filter classes
				{
					if (field.getType().equals(fieldClass)) // the field class matches
					{
						filter = false; // don't filter the field
					}
				}
			}
			if (!filter) // the field is not filtered
			{
				if (Modifier.isPrivate(field.getModifiers())) // the field is private
				{
					if (include_private) // include private fields
					{
						fields.put(field.getName(), field); // add the field to the map
					}
				} else // the field is public
				{
					fields.put(field.getName(), field); // add the field to the map
				}
				field.setAccessible(true); // set the field to be accessable
			}
		}
		return fields; // return the map of fields
	}

	/*
	 * Gets the field values of an object
	 * 
	 * @param object - Object to gather fields from
	 * 
	 * @param include_private - Flag to include private fields
	 * 
	 * @param - field_classes - Desired field classes to search for - all other
	 * classes filtered
	 * 
	 * @return Mapping of fields indexed by field name
	 */
	public static HashMap<String, Object> getObjectFieldValues(Object object, boolean include_private,
	@SuppressWarnings("rawtypes") Class... field_classes)
	{
		HashMap<String, Field> fields = getObjectFields(object, include_private, field_classes); // get the fields of the object
		HashMap<String, Object> fieldValues = new HashMap<String, Object>(); // initialize field value map

		for (String fieldName : fields.keySet()) // iterate through the field names
		{
			try
			{
				Field field = fields.get(fieldName); // get the current field
				Object fieldValue = field.get(object); // get the value of the current field
				fieldValues.put(fieldName, fieldValue); // store the value in the field value map

			} catch (Exception noFieldAccess) // couldn't access the value of the field
			{
				System.err.println("Unable to access field " + fieldName + " of " + object.getClass()); // print error notice

				noFieldAccess.printStackTrace(); // print stack trace
			}
		}
		return fieldValues; // return the field value map
	}

	/*
	 * Gets the field values of an object that match a specific annotation
	 * 
	 * @param object - Object to gather fields from
	 * 
	 * @param include_private - Flag to include private fields
	 * 
	 * @param - field_annotation - Desired field classes to search for - all
	 * other classes filtered
	 * 
	 * @return Mapping of fields indexed by field name
	 */
	public static HashMap<String, Object> getAnnotationObjectFieldValues(Object object, boolean include_private,
	Class annotation)
	{
		HashMap<String, Field> fields = getObjectFields(object, include_private); // get the fields of the object
		HashMap<String, Object> fieldValues = new HashMap<String, Object>(); // initialize field value map

		for (String fieldName : fields.keySet()) // iterate through the field names
		{

			try
			{
				Field field = fields.get(fieldName); // get the current field
				field.setAccessible(true);
				if (field.getDeclaredAnnotation(annotation) != null)
				{
					System.out.println(field.getType());
					String name = LabelReader.getLabel(annotation, field);
					if (name == null)
					{
						System.out.println(name);
						name = fieldName;
					}

					Object fieldValue = field.get(object); // get the value of the current field
					fieldValues.put(name, fieldValue); // store the value in the field value map

				}
			} catch (Exception noFieldAccess) // couldn't access the value of the field
			{
				System.err.println("Unable to access field " + fieldName + " of " + object.getClass()); // print error notice

				noFieldAccess.printStackTrace(); // print stack trace
			}
		}
		return fieldValues; // return the field value map

	}

	/*
	 * Gets the field values of an object that match a specific annotation
	 * 
	 * @param object - Object to gather fields from
	 * 
	 * @param include_private - Flag to include private fields
	 * 
	 * @param - field_annotation - Desired field classes to search for - all
	 * other classes filtered
	 * 
	 * @return Mapping of fields indexed by field name
	 */
	public static HashMap<String, Field> getAnnotationFields(Object object, boolean include_private, Class annotation)
	{
		HashMap<String, Field> fields = getObjectFields(object, include_private); // get the fields of the object
		HashMap<String, Field> filteredFields = new HashMap<String, Field>(); // initialize field value map

		for (String fieldName : fields.keySet()) // iterate through the field names
		{
			try
			{
				Field field = fields.get(fieldName); // get the current field
				if (field.getDeclaredAnnotation(annotation) != null)
				{

					filteredFields.put(fieldName, field); // store the value in the field value map
				}
			} catch (Exception noFieldAccess) // couldn't access the value of the field
			{
				System.err.println("Unable to access field " + fieldName + " of " + object.getClass()); // print error notice

				noFieldAccess.printStackTrace(); // print stack trace
			}
		}
		return filteredFields; // return the field value map
	}
}
