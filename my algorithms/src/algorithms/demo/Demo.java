package algorithms.demo;

import algorithms.mazeGenerators.DFSMazeGenerator;
import algorithms.search.AStar;
import algorithms.search.AirDistance;
import algorithms.search.BFS;
import algorithms.search.ManhattanDistance;
import algorithms.search.Solution;

public class Demo 
{
	/**
	 * run a demo test on the algorithems and on the maze
	 */
	public void run()
	{
		MazeDomain m = new MazeDomain(new DFSMazeGenerator(),10,10,false);//without diagonal
		m.getM().print();
		BFS soul = new BFS();
		Solution solution = soul.search(m); //send the maze to BFS
		AStar soul2 = new AStar(new ManhattanDistance()); 
		Solution solution2 = soul2.search(m); //send the maze to Astar
		if (solution!=null)
		{
			solution.PrintSoul(); //BFS
			System.out.println(soul.getNumberOfNodesEvaluated()); //BFS
			System.out.println("**************************************************************************************");
			solution2.PrintSoul(); //Astar
			System.out.println(soul2.getNumberOfNodesEvaluated()); //Astar
		}
		else
		{
			System.out.println("No Solution");
		}
		System.out.println("********************************************************************************************");
		System.out.println("********************************************************************************************");
		MazeDomain m2 = new MazeDomain(new DFSMazeGenerator(),10,10,true);//with diagonal
		m.getM().print();
		BFS soul3 = new BFS();
		Solution solution3 = soul3.search(m2); //send the maze to BFS
		AStar soul4 = new AStar(new AirDistance());
		Solution solution4 = soul4.search(m2); //send the maze to Astar
		if (solution!=null)
		{
			solution3.PrintSoul(); //BFS
			System.out.println(soul3.getNumberOfNodesEvaluated()); //BFS
			System.out.println("**************************************************************************************");
			solution4.PrintSoul(); //Astar
			System.out.println(soul4.getNumberOfNodesEvaluated()); //Astar
		}
		else
		{
			System.out.println("No Solution");
		}
	}
}
