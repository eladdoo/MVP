package algorithms.demo;

import algorithms.mazeGenerators.DFSMazeGenerator;
import algorithms.search.AStar;
import algorithms.search.ManhattanDistance;
import algorithms.search.Solution;

public class Test2 {

	public static void main(String[] args) 
	{
		MazeDomain m = new MazeDomain(new DFSMazeGenerator(),15,15,false);
		m.getM().print();
		AStar soul = new AStar(new ManhattanDistance()); 
		Solution solution = soul.search(m); //send the maze to Astar
		if (solution!=null)
		{
			solution.PrintSoul(); //Astar
			System.out.println(soul.getNumberOfNodesEvaluated()); //Astar
		}
		else
		{
			System.out.println("No Solution");
		}
	}

}
