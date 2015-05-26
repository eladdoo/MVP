package algorithms.search;

import java.util.HashSet;
import java.util.List;

import algorithms.mazeGenerators.Searchable;

/**
 * this is an algorithem wich is solving a searchable problem
 * @author אלעד
 *
 */
public class AStar extends CommonSearcher
{
	Heuristic h; //getting the heuristic for the algorithem use

	//C'TOR
	public AStar(Heuristic h) 
	{
		super();
		this.h = h;
	}
	
	/**
	 * this is the method that the algorithem should implement so he could solve a search problem
	 */
	@Override
	public Solution search(Searchable s) 
	{
		ActuallState start = s.getStartState(); //getting the start state of the maze
		ActuallState goal = s.getGoalState(); //getting the goal state of the maze
		HashSet<State> closedSet=new HashSet<State>();
		this.getgScore().put(start,0.0); //following the g score
		start.setCost(0+h.CalculateH(start,goal)); //setting the start cost using the heuristic we got in the C'TOR
		openList.add(start);
		while (openList.isEmpty()==false) 
		{
			State current = popOpenList(); //remove the state with the lowest F
			if (current.equals(goal)) //if the current state is the goal state
			{
				return backTrace(current,start); //return the way
			}
			closedSet.add(current); 
			List<State> successors=s.getAllPossibleStates(current); //getting all possible moves from the current move
			for (State state : successors) //for each move that we got
			{
				if (closedSet.contains(state)) 
				{
					continue;
				}
				double totalGscore = this.getgScore().get(current) + state.getMove().getPrice(); //calculating the G score 
				if (openList.contains(state)==false || totalGscore<this.getgScore().get(state))
				{
					state.setCameFrom(current); //setting the parent of the new state has the current state
					this.getgScore().put(state,totalGscore); //entering the new state to the hash table
					state.setCost(this.getgScore().get(state)+h.CalculateH((ActuallState)state,goal)); //setting the new state cost which will be the heuristic calculate+his g score
					if (openList.contains(state)==false)
					{
						openList.add(state);
					}
				}
			}
		}
		return null; //if the algorithem doesnt find solution
	}
	
}
