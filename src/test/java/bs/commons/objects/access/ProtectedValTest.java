package bs.commons.objects.access;

import bs.commons.objects.expansions.Iv;
import bs.commons.objects.manipulation.ObjectCloner;

public class ProtectedValTest
{

	public static class Pro2
	{

		public Pro pro;
		public Protected<Iv<Double>> proVal2;
		public Iv<Double> nonVal2;

		public Pro2(Pro pro)
		{
			//this.pro = pro;
			proVal2 = new Protected<Iv<Double>>(new Iv<Double>(2.0), true);
			nonVal2 = new Iv<Double>(1.0);
		}

	}

	public static class Pro
	{

		public Protected<Iv<Double>> proVal;
		public Iv<Double> nonVal;

		public Pro()
		{
			proVal = new Protected<Iv<Double>>(new Iv<Double>(2.0), true);
			nonVal = new Iv<Double>(1.0);
		}

	}

	public static void main(String args[])
	{
		Pro pro = new Pro();
		Pro2 pro2 = new Pro2(null);//pro);
		pro2.pro = pro;
		Pro proc = (Pro) ObjectCloner.xmlClone(pro);
		Pro2 pro2c = (Pro2) ObjectCloner.xmlClone(pro2);

		//		System.out.println(XMLParser.serializeObject(pro) + "\n\n");
		//		System.out.println(XMLParser.serializeObject(pro2));
		System.out.println(pro + " " + pro.nonVal + " " + pro.proVal + "\n\n");
		System.out.println(pro2.nonVal2 + " " + pro2.proVal2.get() + pro2.pro);
		//		System.out.println(XMLParser.serializeObject(pro2));
		System.out.println("\n\n" + proc + " " + proc.nonVal + " " + proc.proVal + "\n\n");
		System.out.println(pro2c.nonVal2 + " " + pro2c.proVal2.get() + pro2c.pro);
	}
}
