package algorithms.search;

/**
 * a class that extends state and add to her the row and the coll of the cell
 * @author אלעד
 *
 */
public class ActuallState extends State
{
	private int row;
	private int coll;
	
	//C'TOR
	public ActuallState(int row, int coll) 
	{
		super("you are in a row:"+row+"and coll:"+coll);
		this.row = row;
		this.coll = coll;
	}

	public int getRow() //getting the state row
	{
		return row;
	}

	public void setRow(int row)  //setting the state row
	{
		this.row = row;
	}

	public int getColl() //getting the state coll
	{
		return coll;
	}

	public void setColl(int coll) //setting the state coll
	{
		this.coll = coll;
	}
	
	@Override
	public String toString()
	{
		String str = "";
		str = str + Integer.toString(this.row) + " ";
		str = str + Integer.toString(this.coll) + " ";
		return str;
	}
}
