package algorithms.search;

import java.util.HashSet;
import java.util.List;

import algorithms.mazeGenerators.Searchable;

/**
 * the naive algorithem for solving search problems
 * @author אלעד
 *
 */
public class BFS extends CommonSearcher
{
	@Override
	public Solution search(Searchable s) 
	{
		openList.add(s.getStartState());
		HashSet<State> closedSet=new HashSet<State>();

		while(openList.size()>0)
		{
			State n=popOpenList();// dequeue
			closedSet.add(n);
			if(n.equals(s.getGoalState())) //if the current state is the goal state
				return backTrace(n, s.getStartState()); // private method, back traces through the parents
			List<State> successors=s.getAllPossibleStates(n); //getting all the possible moves from the current state
					for(State state : successors)
					{
						State newState = state;
						setPathPrice(newState.getMove().getPrice()+n.getCost()); //pathPrice to the new state
						if((closedSet.contains(newState)==false) && (openList.contains(newState)==false))
						{
							newState.setCameFrom(n); //setting the new state parent has the current state
							newState.setCost(pathPrice); //setting path price of the new state
							openList.add(newState); 
						} 
						else
						{
							if (pathPrice<state.getCost()) 
							{
								if (openList.contains(newState)==false)
								{
									openList.add(newState);
								}
								else //adjust the new state priority
								{
									openList.remove(newState);
									newState.setCost(pathPrice);
									newState.setCameFrom(n);
									openList.add(newState);
								}
							}
							
						}
					}
		}
		return null; //if the algorithem doesnt find solution
	}

}
