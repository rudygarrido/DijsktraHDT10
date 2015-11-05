// A program to use Dijkstra's algorithm to find the
// shortest road from here to thereName.
// (c) 1996 duane a. bailey

import structure.*;
import java.io.*;

public class Dijkstra {

    //+code
    public static Dictionary dijkstra(Graph g, Object start)
    // pre: g is a graph, start is source vertex
    // post: returns a dictionary of vertex-based results
    //       value is association (total-distance,prior-edge)
    {
	// keep a priority queue of distances from source
	PriorityQueue q = new SkewHeap();
	Dictionary result = new Table(); // results, sorted by vertex
	Object v = start;	// last vertex added
	// result is a (total-distance,previous-edge) pair
	ComparableAssociation possible =
	    new ComparableAssociation(new Integer(0),null);
	// as long as we add a new vertex...
	while (v != null)
	{
	    if (!result.containsKey(v))
	    {
		// visit node v -- record incoming edge
		result.put(v,possible);
		// vDist is shortest distance to v
		int vDist = ((Integer)possible.key()).intValue();

		// compute and consider distance to each neighbor
		Iterator ai = g.neighbors(v);
		while (ai.hasMoreElements())
		{
		    // get edge to neighbor
		    Edge e = g.getEdge(v,ai.nextElement());
		    // construct (distance,edge) pair for possible result
		    possible = new ComparableAssociation(
			 new Integer(vDist+((Integer)e.label()).intValue()),
			 e);
		    q.add(possible);	// add to priority queue
		}
	    }
	    // now, get closest (possibly unvisited) vertex
	    if (!q.isEmpty()){
		possible = (ComparableAssociation)q.remove();
		// get destination vertex (take care w/undirected graphs)
		v = ((Edge)possible.value()).there();
			if (result.containsKey(v))
				v = ((Edge)possible.value()).here();
			} else {
			// no new vertex (algorithm stops)
			v = null;
			}
		}
	return result;
    }
    //-code

    static public void main(String[] args) {
	Graph g = new GraphMatrixDirected(300);

	// towns and their names
	String hereName, thereName;// two generic towns
	String startName, finishName; // the specific query

	// a road to be added or manipulated
	Integer length;
	ReadStream input = new ReadStream();

	// read in all roads, add two copies <-> bidirectional
	for (hereName = input.readString();
	     !hereName.equals("end");
	     hereName = input.readString()) {
	
	    thereName = input.readString();
	    input.readString();
	    length = new Integer(input.readInt());
	
	    g.add(hereName);
	    g.add(thereName);
	    // for getting from hereTown to thereTown
	    g.addEdge(hereName, thereName, length);
	    // for getting from thereTown to hereTown
	    g.addEdge(thereName, hereName, length);
	}

	// read in the source town
	startName = input.readString();

	Dictionary results = dijkstra(g,startName);
	Iterator ki = results.keys();
	Iterator vi = results.elements();
	while (ki.hasMoreElements())
	{
	    String dest = (String)ki.nextElement();
	    Association a = (Association)vi.nextElement();
	    if (dest.equals(startName)) continue;
	    String source = (String)((Edge)a.value()).here();
	    if (source.equals(dest))
		source = (String)((Edge)a.value()).there();
	    int len = ((Integer)a.key()).intValue();
	    System.out.println(dest+" is "+len+" miles from "+startName+" (via "+source+")");
	}
    }
}
/*
//+input
albany_ny binghamton_ny I88 142

//+output
albany_ny is 48 miles from williamstown_ma (via troy_ny)
*/