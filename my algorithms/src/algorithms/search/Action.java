package algorithms.search;

/**
 * a class for the action that need to be done between the diffrent states in the maze
 * @author אלעד
 *
 */
public class Action 
{
	private String move; //what move should we do now
	double price; //the cost of that move
	
	//C'TOR
	public Action(String move) 
	{
		super();
		this.move = move;
		this.price = 0;
	}

	public String getMove() //getting the move
	{
		return move;
	}

	public void setMove(String move) //setting the move
	{
		this.move = move;
	}

	public double getPrice() //getting the cost of the move
	{
		return price;
	}

	public void setPrice(double price) //setting the cost of the move
	{
		this.price = price;
	}
	
	@Override
	public String toString()
	{
		String str = "";
		str = str + Double.toString(this.price) + "_";
		str = str + this.move + "/";
		return str;
	}
}
