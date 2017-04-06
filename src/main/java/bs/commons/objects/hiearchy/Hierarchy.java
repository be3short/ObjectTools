package bs.commons.objects.hiearchy;

import java.lang.reflect.Field;
import java.util.ArrayList;

import bs.commons.objects.access.test.FindParentTest.Parent;

public class Hierarchy implements GlobalHierarchy
{

	public ArrayList<Layer> allObjects;

	public Hierarchy()
	{
		allObjects = new ArrayList<Layer>();
	}

	public void addLayer(Object obj)
	{
		allObjects.add(new Layer(obj, this));
	}

	public void findAllParents()
	{
		for (Layer child : allObjects)
		{
			for (Layer parent : allObjects)
			{
				if (parent.containsAChild(child.self))
				{
					child.parent = parent;
					parent.children.add(child);
				}
			}
		}
	}

	@Override
	public Hierarchical findParent()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Hierarchical> findChildren()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public static class Layer implements Hierarchical
	{

		public GlobalHierarchy globe;
		public Hierarchical parent;
		public Object self;
		public ArrayList<Hierarchical> children;

		public Layer(Object self, GlobalHierarchy globe)
		{
			this.globe = globe;
			this.self = self;
			this.parent = null;// new Layer("Unknown", globe);
			children = new ArrayList<Hierarchical>();
		}

		private void searchForParent()
		{

		}

		@Override
		public Hierarchical getParent()
		{
			// TODO Auto-generated method stub
			return parent;
		}

		@Override
		public ArrayList<Hierarchical> getChildren()
		{
			// TODO Auto-generated method stub
			return children;
		}

		@Override
		public Object getSelf()
		{
			// TODO Auto-generated method stub
			return self;
		}

		public boolean containsAChild(Object test)
		{
			for (Field field : self.getClass().getDeclaredFields())
			{
				try
				{
					//System.out.println(field.getName());
					field.setAccessible(true);
					try
					{
						if (field.get(self).equals(test))
						{

							return true;
						}
					} catch (Exception p)
					{

					}
				} catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return false;
		}
	}

	public static void main(String args[])
	{
		//matchingTest();
		largeMatchingTest();
	}

	public static void largeMatchingTest()
	{
		Hierarchy hei = new Hierarchy();
		for (Integer i = 0; i < 10; i++)
		{
			Parent np = new Parent();
			hei.addLayer(np);
			hei.addLayer(np.child);
		}
		hei.findAllParents();
		for (Layer l : hei.allObjects)
		{
			System.out.println(l.self.toString() + "\n" + "Parent:" + "\n" + "\n");
			for (Hierarchical h : l.children)
			{
				System.out.println("Child " + h.getSelf().getClass() + "\n");
			}
		}
		//System.out.println(XMLParser.serializeObject(hei));
	}

	public String printLevels()
	{
		findAllParents();
		String print = "Hierarchy\n";
		for (Layer l : allObjects)
		{
			if (l.self != null)
			{
				print += "Self: " + l.getSelf().toString() + "\n";

				if (l.parent != null)
				{
					print += "Parent: " + l.parent.getSelf().toString() + "\n";
				}
				for (Hierarchical h : l.children)
				{
					print += ("Child " + h.getSelf().toString() + "\n");
				}
			}
		}
		return print;
	}
}
