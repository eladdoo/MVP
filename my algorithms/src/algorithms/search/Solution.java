package algorithms.search;

import java.io.Serializable;
import java.util.List;

/**
 * the solution class which will finally give the path to the solution
 * @author אלעד
 *
 */
@SuppressWarnings("serial")
public class Solution implements Serializable
{
	private List<Action> moves; //list of moves that need to be done in order to get to the solution
	
	//C'TOR
	public Solution(List<Action> moves) 
	{
		super();
		this.moves = moves;
	}
	
	public Solution(){}

	public List<Action> getMoves() //get the moves
	{
		return moves;
	}

	public void setMoves(List<Action> moves) //set the moves
	{
		this.moves = moves;
	}
	
	public void PrintSoul() //print the solution method
	{
		for (int i=0;i<moves.size();i++)
		{
			System.out.println(moves.get(i).getMove());
		}
	}
	
	@Override
	public int hashCode() //getting solution code
	{
		int code=0;
		for(Action a:moves)
		{
			code = code + a.getMove().hashCode();
		}
		return code;
	}
	
	@Override
	public String toString()
	{
		String str = "";
		for (Action a:this.moves)
		{
			str = str + a.toString();
		}
		str = str + ",";
		return str;
	}
	
	@Override
	public boolean equals(Object o)
	{
		Solution s = (Solution)o;
		if (this.getMoves().size()!=s.getMoves().size())
		{
			return false;
		}
		else
		{
			for (int i=0;i<this.getMoves().size();i++)
			{
				if (this.getMoves().get(i).getMove().equals(s.getMoves().get(i).getMove())==false)
				{
					return false;
				}
				else if (this.getMoves().get(i).getPrice()!=s.getMoves().get(i).getPrice())
				{
					return false;
				}
			}
		}
		return true;
	}
}
