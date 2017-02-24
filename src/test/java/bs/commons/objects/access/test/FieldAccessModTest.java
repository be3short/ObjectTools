package bs.commons.objects.access.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.HashMap;

import org.junit.Test;

import bs.commons.objects.access.FieldAccessor;
import bs.commons.objects.access.test.FieldAccessorTest.FieldAccessorTestObject;

public class FieldAccessModTest
{

	public static class FieldAccessorTestObject
	{

		public Double publicDouble = 0.0;
		@SuppressWarnings("unused")
		private Double privateDouble = 1.0;
		public String publicString = "Public";
		@SuppressWarnings("unused")
		private String privateString = "Private";
	}

	public FieldAccessorTestObject testObject = new FieldAccessorTestObject();

	@Test
	public void testFieldValueAccess()
	{
		HashMap<String, Object> publicFields = FieldAccessor.getObjectFieldValues(testObject, true);
		Object dub = publicFields.get("publicDouble");
		dub = 10.0;
		publicFields = FieldAccessor.getObjectFieldValues(testObject, true);
		System.out.println(publicFields.get("publicDouble"));
		assertEquals(publicFields.get("publicDouble"), 10.0);

	}
}
