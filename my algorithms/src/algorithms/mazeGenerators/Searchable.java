package algorithms.mazeGenerators;

import java.util.ArrayList;

import algorithms.search.ActuallState;
import algorithms.search.State;

/**
 * an interface for all the search problems that need to be solve
 * @author אלעד
 *
 */
public interface Searchable 
{
	ActuallState getStartState();
	ActuallState getGoalState();
	ArrayList<State> getAllPossibleStates(State s);
}
