package bs.commons.objects.access.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import bs.commons.objects.access.test.FindParentTest.Parent.Child;
import bs.commons.objects.manipulation.XMLParser;

public class FindParentTest
{

	public static abstract class ContainsChild
	{

		public ContainsChild()
		{

		}

		public boolean containsAChild(Object test)
		{
			for (Field field : this.getClass().getDeclaredFields())
			{
				try
				{
					System.out.println(field.getName());
					field.setAccessible(true);
					if (field.get(this).equals(test))

					{
						return true;
					}
				} catch (IllegalArgumentException | IllegalAccessException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return false;
		}
	}

	public static class Parent extends ContainsChild
	{

		public Child child;
		public static Integer counter = 0;
		public Integer i = counter;

		public Parent()
		{
			super();
			child = new Child();
			i = counter++;
		}

		public boolean containsChild(Child test)
		{
			return child.equals(test);
		}

		public static class Child
		{

			public String co = "Child:" + Parent.counter;
		}
	}

	public static void main(String args[])
	{
		//matchingTest();
		largeMatchingTest();
	}

	public static void largeMatchingTest()
	{
		ArrayList<Parent> parents = new ArrayList<Parent>();
		ArrayList<Child> childs = new ArrayList<Child>();
		for (Integer i = 0; i < 10; i++)
		{
			Parent np = new Parent();
			parents.add(np);
			childs.add(np.child);
		}
		HashMap<Parent, Child> matches = new HashMap<Parent, Child>();
		for (Parent p : parents)
		{
			for (Child c : childs)
			{
				if (p.containsAChild(c))
				{
					matches.put(p, c);
				}
			}

		}
		System.out.println(XMLParser.serializeObject(matches));
	}

	public static void matchingTest()
	{

		Parent parent = new Parent();
		Parent parent2 = new Parent();
		System.out.println(parent.child.getClass().getDeclaringClass().getName());
		System.out.println(parent2.child.getClass().getDeclaringClass());
		System.out.println(parent.containsChild(parent2.child));
		System.out.println(parent.containsAChild(parent.child));
	}
}
