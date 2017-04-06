package bs.commons.objects.hiearchy;

import java.util.ArrayList;

public interface GlobalHierarchy
{

	public Hierarchical findParent();

	public ArrayList<Hierarchical> findChildren();
}
