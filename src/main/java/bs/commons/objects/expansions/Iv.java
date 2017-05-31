package bs.commons.objects.expansions;

import bs.commons.objects.access.FieldFinder;
import bs.commons.objects.manipulation.ObjectCloner;
import bs.commons.unitvars.core.UnitData.Unit;
import bs.commons.unitvars.core.UnitValue;

public class Iv<T>
{

	private T value;
	private Range<Double> range;

	@SuppressWarnings("unchecked")
	public Iv(T value)
	{
		// if (value == null)
		// {
		// range = null;
		// } else
		{
			Double initialRange = null;
			if (value.getClass().equals(Double.class))
			{
				initialRange = (Double) value;
				range = new Range<Double>(initialRange, null, Double.class);
			} else
			{
				try
				{
					if (FieldFinder.containsSuper(value, UnitValue.class))
					{
						Unit defaultUnit = (Unit) ObjectCloner.xmlClone(((UnitValue) value).getUnit());
						Double unitVal = (Double) ((UnitValue) value).get(defaultUnit);
						range = new Range<Double>(unitVal, null, Double.class);
						// try
						// {
						// initialVal = new InitialValue<T>(get());
						// } catch (UnitException e)
						// {
						// // TODO Auto-generated catch block
						// // initialVal = new InitialValue<T>(get());
						// e.printStackTrace();
						// }
					}
				} catch (Exception badClass)
				{
					badClass.printStackTrace();
				}
			}
		}
		this.value = value;
	}

	@SuppressWarnings("unchecked")
	public Iv(T value, T max)
	{
		Double initialRange = null;
		if (value.getClass().equals(Double.class))
		{
			initialRange = (Double) value;
			range = new Range<Double>(initialRange, (Double) max, Double.class);
		} else
		{
			range = null;
		}
		this.value = value;
	}

	@SuppressWarnings("unchecked")
	public void setRandomValues(Double min, Double max)
	{
		if (value.getClass().equals(Double.class))
		{
			range.setValues(min, max);
		} else
		{
			// IO.warn("attempted to set double min and max values when value
			// type is " + value.getClass());
		}

	}

	@SuppressWarnings("unchecked")
	public T getValue()
	{
		if (value.getClass().equals(Double.class))
		{
			if (range.getUpper() != null && range.getLower() != null)
			{

				return (T) Double.class.cast(
				(((Double) range.getUpper() - (Double) range.getLower()) * Math.random()) + (Double) range.getLower());
			} else
			{
				return (T) range.getLower();
			}
		} else
		{
			return value;
		}
	}

	@SuppressWarnings("unchecked")
	public Double getNumberValue()
	{

		if (range.getUpper() != null && range.getLower() != null)
		{

			return Double.class.cast((Double) range.getUpper() * Math.random() + (Double) range.getLower());
		} else
		{
			return range.getLower();
		}

	}

	public void setValue(T val)
	{
		value = val;
	}

	public Range<Double> getRange()
	{
		return range;
	}
}
