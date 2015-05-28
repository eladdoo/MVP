package view;

import presenter.Presenter.Command;
import algorithms.mazeGenerators.Maze;
import algorithms.search.Solution;

/**
 * The Interface View.
 */
public interface View 
{
	
	/**
	 * Start.
	 */
	void start();
	
	/**
	 * Sets the commands.
	 *
	 * @param coName the co name
	 * @param c the c
	 */
	void setCommands(String coName,Command c);
	
	/**
	 * Gets the user command.
	 *
	 * @return the user command
	 */
	Command getUserCommand();
	
	/**
	 * Display maze.
	 *
	 * @param m the m
	 */
	void displayMaze(Maze m,String pMaze);
	
	/**
	 * Display solution.
	 *
	 * @param s the s
	 */
	void displaySolution(Solution s);
	
	/**
	 * Gets the the name.
	 *
	 * @return the the name
	 */
	String getTheName();
	
	/**
	 * Exit prog.
	 */
	void exitProg();
	
	/**
	 * Update user.
	 *
	 * @param update the update
	 */
	void UpdateUser(String update);
	
	String gettingFileName();
}
