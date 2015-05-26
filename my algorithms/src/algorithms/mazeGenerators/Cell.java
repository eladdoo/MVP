package algorithms.mazeGenerators;

/**
 * each cell of the maze is an object wich has its own data members
 * @author אלעד
 *
 */
public class Cell
{
	private int row;
	private int coll;
	private boolean visit=false;//check if the cell had been visited
	private boolean hasRightWall=true;//Test
	private boolean hasDownWall=true;//Test
	
	//C'TOR
	public Cell(int row, int coll) 
	{
		super();
		this.row = row;
		this.coll = coll;
	}
	
	public boolean isVisit() //the cell had been visited or not
	{
		return visit;
	}
	public void setVisit(boolean visit) //set visit true or false
	{
		this.visit = visit;
	}
	public int getRow() //getting the cell row
	{
		return row;
	}
	public void setRow(int row) //setting the cell row
	{
		this.row = row;
	}
	public int getColl() //getting the cell coll
	{
		return coll;
	}
	public void setColl(int coll) //setting the cell coll
	{
		this.coll = coll;
	}

	public boolean isHasRightWall() //checking if the cell has right wall
	{
		return hasRightWall;
	}

	public void setHasRightWall(boolean hasRightWall) //set right wall true or false
	{
		this.hasRightWall = hasRightWall;
	}

	public boolean isHasDownWall() //checking if the cell has down wall
	{
		return hasDownWall;
	}

	public void setHasDownWall(boolean hasDownWall) //setting down wall true or false
	{
		this.hasDownWall = hasDownWall;
	}
	
	@Override
	public boolean equals(Object obj) //implement equal for Cell use
	{ 
        //return state.equals(((State)obj).state);
		if ((this.isHasDownWall() == true && ((Cell) obj).isHasDownWall() == true) || (this.isHasRightWall() == true && ((Cell) obj).isHasRightWall() == true))
		{
			return true;
		}
		else if ((this.isHasDownWall() == false && ((Cell) obj).isHasDownWall() == false) || (this.isHasRightWall() == true && ((Cell) obj).isHasRightWall() == true))
		{
			return true;
		}
		else if ((this.isHasDownWall() == true && ((Cell) obj).isHasDownWall() == true) || (this.isHasRightWall() == false && ((Cell) obj).isHasRightWall() == false))
		{
			return true;
		}
		else if ((this.isHasDownWall() == false && ((Cell) obj).isHasDownWall() == false) || (this.isHasRightWall() == false && ((Cell) obj).isHasRightWall() == false))
		{
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() //getting cell code
	{
		String code="";
		if (this.hasDownWall==true)
		{
			code = code + "true";
		}
		else
		{
			code = code + "false";
		}
		if (this.hasRightWall==true)
		{
			code = code + "true";
		}
		else
		{
			code = code + "false";
		}
		return code.hashCode();
	}
	
	@Override
	public String toString()
	{
		String str = "";
		if (this.hasDownWall == true)
		{
			str = str + "true" + " ";
		}
		else
		{
			str = str + "false" + " ";
		}
		if (this.hasRightWall == true)
		{
			str = str + "true" + " ";
		}
		else
		{
			str = str + "false" + " ";
		}
		return str;
	}
}
