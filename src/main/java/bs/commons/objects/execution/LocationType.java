package bs.commons.objects.execution;

public enum LocationType
{
	MENU(
		"Menu"),
	MENU_BAR(
		"Menu Bar"),
	MENU_ITEM(
		"Menu Item");

	public final String locationLabel;

	private LocationType(String location_label)
	{
		locationLabel = location_label;
	}

}
