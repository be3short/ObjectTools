package bs.commons.objects.hiearchy.interfaces;

import java.util.ArrayList;

import bs.commons.objects.heiarchy.structures.HierarchicalRoot;

public interface GlobalHierarchy
{

	public HierarchicalRoot getRoot();

	public HierarchicalComponent findParent();

	public ArrayList<HierarchicalComponent> findChildren();
}
