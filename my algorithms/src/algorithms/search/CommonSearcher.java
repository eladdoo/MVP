package algorithms.search;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.PriorityQueue;

import algorithms.mazeGenerators.Searchable;

/**
 * this class is a kind of base class for all the algorithems that solve search problems
 * the class contains all that is common to the seach probles solveing algorithems
 * @author אלעד
 *
 */
public abstract class CommonSearcher implements Searcher
{
	protected PriorityQueue<State> openList;
	private int evaluatedNodes; //how much nodes was evaluated durning the algorithem work
	protected double pathPrice;
	private Hashtable<State, Double> gScore= new Hashtable<State, Double>(); //keeping track of states and there gscore

	//C'TOR
	 public CommonSearcher() 
	 {
	  openList=new PriorityQueue<State>();
	  evaluatedNodes=0;
	 }

	 protected State popOpenList() //getting the first state in the queue and updateing the evaluated node number
	 {
	  evaluatedNodes++;
	  return openList.poll();
	 }
	 
	 @Override
	 public abstract Solution search(Searchable s); //method to implement by the algorithems

	 @Override
	 public int getNumberOfNodesEvaluated() //returning the number of nodes that had been evaluated
	 {
	  return evaluatedNodes;
	 }
	 
	 /**
	  * this method returning the path to the solution
	  * @param goal
	  * @param start
	  * @return
	  */
	 public Solution backTrace(State goal,State start) 
	 {
		 State s = goal;
		 List<Action> action = new ArrayList<Action>();
		 while (s.getCameFrom()!=null) //while its not the start state
		 {
			action.add(s.getMove()); //adding the actions that need to be done
			s=s.getCameFrom(); //getting the previos state parent
		 }
		  int size=action.size();
		  for(int i=0;i<size/2;i++) //a loop to make the list in the right order
		  {
			  Action act=action.get(i);
			  action.set(i, action.get(size-i-1));
			  action.set(size-i-1, act);
		  }
		 Solution sol = new Solution(action);
		 return sol;
	 }

	public double getPathPrice() //getting the path price
	{
		return pathPrice;
	}

	public void setPathPrice(double pathPrice) //setting the path price
	{
		this.pathPrice = pathPrice;
	}

	public Hashtable<State, Double> getgScore() //getting the g score
	{
		return gScore;
	}

	public void setgScore(Hashtable<State, Double> gScore) //setting the g score
	{
		this.gScore = gScore;
	}
	 
	 

}
