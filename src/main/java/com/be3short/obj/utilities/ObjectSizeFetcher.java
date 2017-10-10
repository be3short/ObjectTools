package com.be3short.obj.utilities;

import java.lang.instrument.Instrumentation;
import com.be3short.obj.expansions.Range;
import com.carrotsearch.sizeof.RamUsageEstimator;

public class ObjectSizeFetcher
{

	private static Instrumentation instrumentation;

	public static void premain(String args, Instrumentation inst)
	{
		instrumentation = inst;
	}

	public static long getObjectSize(Object o)
	{
		return instrumentation.getObjectSize(o);
	}

	public static void main(String args[])
	{
		Double d = 214748364.0;
		//Long d = (long) 99999999.99999;
		Range<Double> range = new Range<Double>(2.0, 3.0, Double.class);
		System.out.println(RamUsageEstimator.sizeOf(d) + " " + RamUsageEstimator.sizeOf(range));
	}
}