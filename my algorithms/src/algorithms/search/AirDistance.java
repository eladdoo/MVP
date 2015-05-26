package algorithms.search;

/**
 * a huristic way for Astar algorithem 
 * this way is for mazes that allowing diagonal movement
 * @author אלעד
 *
 */
public class AirDistance implements Heuristic
{
	@Override
	public double CalculateH(ActuallState current,ActuallState Goal)
	{
		double countRowC = current.getRow(); //getting the row of the current cell
		double countCollC = current.getColl(); //getting the coll of the current cell
		double countRowG = Goal.getRow(); //getting the row of the goal cell
		double countCollG = Goal.getColl(); //getting the coll of the goal cell
		return (Math.sqrt(Math.pow(Math.abs(countRowG-countRowC),2) + Math.pow(Math.abs(countCollG-countCollC),2))*10); //the h calculation
	}
}
