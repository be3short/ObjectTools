package bs.commons.objects.access;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import bs.commons.objects.identifiers.ChoiceName;

public class ChoiceAccessor
{

	Object[] choiceEnums;
	Field choiceField;

	public static HashMap<String, Object> getChoiceNames(Object... enums)
	{
		HashMap<String, Object> names = new HashMap<String, Object>();
		HashMap<String, Field> fields = FieldAccessor.getAnnotationFields(enums[0], true, ChoiceName.class);
		Field choiceName = fields.values().toArray(new Field[fields.size()])[0];
		for (Object cenum : enums)
		{
			try
			{
				names.put((String) choiceName.get(cenum), cenum);
			} catch (IllegalArgumentException | IllegalAccessException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return names;
	}

}
