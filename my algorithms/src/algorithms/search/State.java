package algorithms.search;

/**
 * state class that represnt a state in the maze 
 * @author אלעד
 *
 */
public class State implements Comparable<State>
{
	private String state;    // the state represented by a string
    private double cost;     // cost to reach this state
    private State cameFrom;  //the state we came from to this state
    private Action move; //the action that led from parent to son
    
    //C'TOR
	public State(String state) 
	{
		super();
		this.state = state;
		this.cost = 0;
		this.cameFrom = null;
		this.move = null;
	}
	
	@Override
	public boolean equals(Object obj) //implement equal for state use
	{ 
        return state.equals(((State)obj).state);
	}

	public String getState() //getting the state
	{
		return state;
	}

	public void setState(String state) //setting the state
	{
		this.state = state;
	}

	public double getCost() //getting the cost to get to this state
	{
		return cost;
	}

	public void setCost(double cost) //setting the cost to get to this state
	{
		this.cost = this.cost+cost;
	}

	public State getCameFrom() //get the state parent
	{
		return cameFrom;
	}

	public void setCameFrom(State cameFrom) //setting the state parent
	{
		this.cameFrom = cameFrom;
	}

	public Action getMove() //get the move that led to this state
	{
		return move;
	}

	public void setMove(Action move) //setting the move that led to this state
	{
		this.move = move;
	}
	
	@Override
	public int compareTo(State state) //compare between states
	{
		if (this.getCost() > state.getCost())
			return 1;
	    if (this.getCost() < state.getCost())
			return -1;
		return 0;
	}
	@Override
	public int hashCode() //getting state code
	{
		return state.hashCode();
	}
	@Override
	public String toString()
	{
		return state;
	}
}
