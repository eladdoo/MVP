package algorithms.search;

import algorithms.mazeGenerators.Searchable;

/**
 * an interface for the searcher algorithems which solving search problems
 * all the search problems solving algorithems will have to implement those method
 * @author אלעד
 *
 */
public interface Searcher 
{
	// the search method
    public Solution search(Searchable s);
    // get how many nodes were evaluated by the algorithm
    public int getNumberOfNodesEvaluated();
}
