package algorithms.search;

/**
 * this is the heuristic interface for the h calculating classes that will implement this interface
 * @author אלעד
 *
 */
public interface Heuristic 
{
	public double CalculateH(ActuallState current,ActuallState Goal);
}
