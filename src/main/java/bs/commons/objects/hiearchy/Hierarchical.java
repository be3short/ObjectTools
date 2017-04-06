package bs.commons.objects.hiearchy;

import java.util.ArrayList;

public interface Hierarchical
{

	public Hierarchical getParent();

	public ArrayList<Hierarchical> getChildren();

	public Object getSelf();

}
