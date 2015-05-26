package algorithms.search;

import static org.junit.Assert.*;

import org.junit.Test;

import algorithms.demo.MazeDomain;
import algorithms.mazeGenerators.DFSMazeGenerator;

public class AStarTest {

	@Test
	public void test()
	{
		AStar check = new AStar(new AirDistance());
		MazeDomain maze = new MazeDomain(new DFSMazeGenerator(),4,4,true);
		BFS check2 = new BFS();
		Solution sol = check2.search(maze);
		Solution s = check.search(maze);
		assertEquals(sol,s);
	}

}
