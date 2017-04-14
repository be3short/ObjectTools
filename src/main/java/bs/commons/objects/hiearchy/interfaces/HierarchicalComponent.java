package bs.commons.objects.hiearchy.interfaces;

import java.util.ArrayList;

import bs.commons.objects.access.Protected;
import bs.commons.objects.heiarchy.structures.HierarchicalLayer;

public interface HierarchicalComponent
{

	public Protected<HierarchicalLayer> getParent();

	public ArrayList<Protected<HierarchicalLayer>> getChildren();

	public <T> ArrayList<T> getChildren(Class<T> type);

	public Object getSelf();

}
