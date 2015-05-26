package algorithms.search;

/**
 * a class for the heuristic implementation of the non-diagonal mazes
 * using it to calculate h
 * @author אלעד
 *
 */
public class ManhattanDistance implements Heuristic
{
	@Override
	public double CalculateH(ActuallState current,ActuallState Goal)
	{
		double countRowC = current.getRow(); //getting the current state row
		double countCollC = current.getColl(); //getting the current state coll
		double countRowG = Goal.getRow(); //getting the goal row
		double countCollG = Goal.getColl(); //getting the goal coll
		return (Math.abs(countRowG-countRowC) + Math.abs(countCollG-countCollC))*10; //calculating h
	}
}
