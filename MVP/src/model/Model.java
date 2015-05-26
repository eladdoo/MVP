package model;

import java.util.Queue;

import algorithms.demo.MazeDomain;
import algorithms.mazeGenerators.Maze;
import algorithms.search.Solution;

/**
 * The Interface Model.
 */
public interface Model 
{
	
	/**
	 * Generate maze.
	 *
	 * @param name the name
	 * @param rows the rows
	 * @param colls the colls
	 * @param diag the diag
	 */
	void generateMaze(String name,int rows,int colls,boolean diag);
	
	/**
	 * Gets the maze.
	 *
	 * @return the maze
	 */
	MazeDomain getMaze();
	
	/**
	 * Solve maze.
	 *
	 * @param name the name
	 * @param m the m
	 */
	void solveMaze(String name,MazeDomain m);
	
	/**
	 * Gets the solution.
	 *
	 * @param min the min
	 * @return the solution
	 */
	Solution getSolution(Maze min);
	
	/**
	 * Stop.
	 */
	void stop();
	
	/**
	 * Gets the ting queue names.
	 *
	 * @return the ting queue names
	 */
	Queue<String> gettingQueueNames();
	
	/**
	 * Gets the ting queue solution.
	 *
	 * @return the ting queue solution
	 */
	Queue<String> gettingQueueSolution();
	
	/**
	 * Gets the ting queue mazes.
	 *
	 * @return the ting queue mazes
	 */
	Queue<MazeDomain> gettingQueueMazes();
}
