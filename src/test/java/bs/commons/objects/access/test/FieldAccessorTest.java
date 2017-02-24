package bs.commons.objects.access.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.HashMap;

import org.junit.Test;

import bs.commons.objects.access.FieldAccessor;

public class FieldAccessorTest
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
	public void testPublicFieldAccess()
	{
		HashMap<String, Field> publicFields = FieldAccessor.getObjectFields(testObject, false);
		assertTrue(publicFields.containsKey("publicDouble"));
		assertTrue(publicFields.containsKey("publicString"));
		assertFalse(publicFields.containsKey("privateDouble"));
		assertFalse(publicFields.containsKey("privateString"));
	}

	@Test
	public void testPublicAndPrivateFieldAccess()
	{
		HashMap<String, Field> publicFields = FieldAccessor.getObjectFields(testObject, true);
		assertTrue(publicFields.containsKey("publicDouble"));
		assertTrue(publicFields.containsKey("publicString"));
		assertTrue(publicFields.containsKey("privateDouble"));
		assertTrue(publicFields.containsKey("privateString"));
	}

	@Test
	public void testFilteredFieldAccess()
	{
		HashMap<String, Field> publicFields = FieldAccessor.getObjectFields(testObject, true, Double.class);
		assertTrue(publicFields.containsKey("publicDouble"));
		assertFalse(publicFields.containsKey("publicString"));
		assertTrue(publicFields.containsKey("privateDouble"));
		assertFalse(publicFields.containsKey("privateString"));
	}

	@Test
	public void testFieldValueAccess()
	{
		HashMap<String, Object> publicFields = FieldAccessor.getObjectFieldValues(testObject, true);
		assertEquals(publicFields.get("publicDouble"), 0.0);
		assertEquals(publicFields.get("publicString"), "Public");
		assertEquals(publicFields.get("privateDouble"), 1.0);
		assertEquals(publicFields.get("privateString"), "Private");
	}

}
