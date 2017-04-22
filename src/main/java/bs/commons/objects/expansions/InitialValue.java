package bs.commons.objects.expansions;

public class InitialValue<T>
{

	private T value;
	private Range<Double> range;

	@SuppressWarnings("unchecked")
	public InitialValue(T value)
	{
		if (value == null)
		{
			range = null;
		} else
		{
			Double initialRange = null;
			if (value.getClass().equals(Double.class))
			{
				initialRange = (Double) value;
				range = new Range<Double>(initialRange, null, Double.class);
			} else
			{
				range = null;
			}
		}
		this.value = value;
	}

	@SuppressWarnings("unchecked")
	public InitialValue(T value, T max)
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
			//IO.warn("attempted to set double min and max values when value type is " + value.getClass());
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
				((Double) range.getUpper() - (Double) range.getLower()) * Math.random() + (Double) range.getLower());
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
