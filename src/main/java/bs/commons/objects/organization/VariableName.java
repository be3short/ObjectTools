package bs.commons.objects.organization;

import java.util.HashMap;

import bs.commons.objects.access.Protected;

public class VariableName
{

	public final Protected<String> title; // description of the object -ie "Storage Device" or "UAV"
	public final Protected<String> description; // name of the object - ie "Generic HD Video Camera" "Randomized Temperature Sensor"
	public final Protected<String> prefix; // optional prefix to the objects name - ie a specific part number
	public final Protected<String> suffix; // optional suffix to the objects name - ie a relavant specification
	private HashMap<NameComponent, Protected<String>> componentMap; // map to all name components, which allows name to be extracted in any format

	public VariableName(String title)
	{
		this.title = new Protected<String>(title, true);
		this.description = new Protected<String>("", true);
		this.prefix = new Protected<String>("", true);
		this.suffix = new Protected<String>("", true);
		componentMap = initializeComponentMap();
	}

	public VariableName(String title, String description)
	{
		this.title = new Protected<String>(title, true);
		this.description = new Protected<String>(description, true);
		this.prefix = new Protected<String>("", true);
		this.suffix = new Protected<String>("", true);
		componentMap = initializeComponentMap();
	}

	public VariableName(String title, String description, String prefix)
	{
		this.title = new Protected<String>(title, true);
		this.description = new Protected<String>(description, true);
		this.prefix = new Protected<String>(prefix, true);
		this.suffix = new Protected<String>("", true);
		componentMap = initializeComponentMap();
	}

	public VariableName(String title, String description, String prefix, String suffix)
	{
		this.title = new Protected<String>(title, true);
		this.description = new Protected<String>(description, true);
		this.prefix = new Protected<String>(prefix, true);
		this.suffix = new Protected<String>(suffix, true);
		componentMap = initializeComponentMap();
	}

	private HashMap<NameComponent, Protected<String>> initializeComponentMap()
	{
		HashMap<NameComponent, Protected<String>> map = new HashMap<NameComponent, Protected<String>>();
		map.put(NameComponent.TITLE, title);
		map.put(NameComponent.DESCRIPTION, description);
		map.put(NameComponent.PREFIX, prefix);
		map.put(NameComponent.SUFFIX, suffix);
		return map;
	}

	public String getName(DescriptiveComponent... format)
	{
		String nameStructure = "";
		for (DescriptiveComponent component : format)
		{
			if (nameStructure.length() > 0)
			{
				nameStructure += " ";
			}
			nameStructure += componentMap.get(component).get();
		}
		return nameStructure;
	}

	public static enum NameComponent implements DescriptiveComponent
	{
		TITLE,
		DESCRIPTION,
		PREFIX,
		SUFFIX;

		@Override
		public InformationCategory getCategory()
		{
			// TODO Auto-generated method stub
			return InformationCategory.DESCRIPTION;
		}
	}
}
