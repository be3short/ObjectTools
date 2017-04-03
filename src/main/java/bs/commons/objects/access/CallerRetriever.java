package bs.commons.objects.access;

public class CallerRetriever
{

	public static void main(String args[])
	{
		TestCaller caller = new TestCaller();
		TestCaller caller2 = new TestCaller();
		caller.checkSelf();
		caller2.checkSelf();
		//CallingClass caller = new CallingClass();
		//System.out.println(caller.getCallingClasses()[2].getSimpleName());
		//System.out.println(caller.getListOfCallingClasses());

	}

	public static class TestCaller
	{

		public void checkSelf()
		{
			CallingClass caller = new CallingClass();
			//System.out.println(caller.getCallingClasses()[2].getSimpleName());
			System.out.println(caller.getListOfCallingClasses());
		}
	}

	public static class CallingClass extends SecurityManager
	{

		public static final CallingClass INSTANCE = new CallingClass();

		public Class[] getCallingClasses()
		{
			return getClassContext();
		}

		public String getListOfCallingClasses()
		{
			Class[] classes = getClassContext();
			String callStack = "Call stack\n";
			for (int i = 0; i < classes.length; i++)
			{
				callStack += i + " " + classes[i].getName() + " " + classes[i].toString() + " "
				+ classes[i].getEnclosingClass() + " " + classes[i].getSuperclass() + "\n";
			}
			return callStack;
		}
	}
}
