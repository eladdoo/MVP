package presenter;

import java.io.Serializable;

/**
 * The Class Properties.
 */
@SuppressWarnings("serial")
public class Properties implements Serializable
{
	
	/** The tnum. */
	private int TNUM=2;
	
	/** The algo solution. */
	private String algoSolution="BFS";
	
	/** The algo cr maze. */
	private String algoCrMaze="DFS";
	
	/** The Huristic. */
	private String Huristic="AirDistance";
	
	/**
	 * Instantiates a new properties.
	 */
	public Properties() 
	{
		super();
	}
	
	/**
	 * Instantiates a new properties.
	 *
	 * @param tNUM the t num
	 * @param algoSolution the algo solution
	 * @param algoCrMaze the algo cr maze
	 * @param huristic the huristic
	 */
	public Properties(int tNUM, String algoSolution, String algoCrMaze,
			String huristic) 
	{
		super();
		TNUM = tNUM;
		this.algoSolution = algoSolution;
		this.algoCrMaze = algoCrMaze;
		Huristic = huristic;
	}



	/**
	 * Gets the tnum.
	 *
	 * @return the tnum
	 */
	public int getTNUM() 
	{
		return TNUM;
	}
	
	/**
	 * Sets the tnum.
	 *
	 * @param tNUM the new tnum
	 */
	public void setTNUM(int tNUM) 
	{
		TNUM = tNUM;
	}
	
	/**
	 * Gets the algo solution.
	 *
	 * @return the algo solution
	 */
	public String getAlgoSolution()
	{
		return algoSolution;
	}
	
	/**
	 * Sets the algo solution.
	 *
	 * @param algoSolution the new algo solution
	 */
	public void setAlgoSolution(String algoSolution) 
	{
		this.algoSolution = algoSolution;
	}
	
	/**
	 * Gets the algo cr maze.
	 *
	 * @return the algo cr maze
	 */
	public String getAlgoCrMaze()
	{
		return algoCrMaze;
	}
	
	/**
	 * Sets the algo cr maze.
	 *
	 * @param algoCrMaze the new algo cr maze
	 */
	public void setAlgoCrMaze(String algoCrMaze) 
	{
		this.algoCrMaze = algoCrMaze;
	}

	/**
	 * Gets the huristic.
	 *
	 * @return the huristic
	 */
	public String getHuristic() 
	{
		return Huristic;
	}

	/**
	 * Sets the huristic.
	 *
	 * @param huristic the new huristic
	 */
	public void setHuristic(String huristic) 
	{
		Huristic = huristic;
	}


}
