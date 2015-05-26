package algorithms.demo;

import java.util.ArrayList;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MazeGenerator;
import algorithms.mazeGenerators.Searchable;
import algorithms.search.Action;
import algorithms.search.ActuallState;
import algorithms.search.State;

/**
 * creat an adapter from maze to search problem
 * in this class there are method for the algorithems to use in their goal to find a solution to the maze
 * @author אלעד
 *
 */
public class MazeDomain implements Searchable
{
	private Maze m;
	private boolean diagonal=false;
	
	//C'TOR
	public MazeDomain(MazeGenerator x,int rows,int colls,boolean diagonal) 
	{
		super();
		this.m = x.generateMaze(rows, colls);
		this.diagonal = diagonal;
	}
	
	@Override
	public ActuallState getStartState() //get start state method
	{
		return m.getStart();
	}
	
	@Override
	public ActuallState getGoalState() //get goal state method
	{
		return m.getGoal();
	}
	
	/**
	 * this method getting all possibol moves from a current state
	 * the method creat an ArrayList with all the new state according to the if conditions
	 * each new state is getting its price when: diagonal moves = 15 price and regular moves (left,right,down,up) = 10 price
	 * each new state is also getting an order where to go and its location
	 */
	@Override
	public ArrayList<State> getAllPossibleStates(State s) //from the current State
	{
		ArrayList<State> states = new ArrayList<State>();
		ActuallState t = (ActuallState) s;
		if (t.getRow()==0 && t.getColl()==0) //check if it is the first cell in the maze = start cell
		{
			if (m.getCell(t.getRow(),t.getColl()).isHasRightWall()==false) //check if it has right wall
			{
				ActuallState newS = new ActuallState(t.getRow(),t.getColl()+1); //entering the location of the new state
				Action move = new Action("go right"); //giving order where to go
				move.setPrice(10); //action cost
				newS.setMove(move);
				states.add(newS); //add the new state to the ArrayList
			}
			if (m.getCell(t.getRow(),t.getColl()).isHasDownWall()==false) //check if it has down wall
			{
				ActuallState newS = new ActuallState(t.getRow()+1,t.getColl());
				Action move = new Action("go down");
				move.setPrice(10);
				newS.setMove(move);
				states.add(newS);
			}
		}
		else if (t.getRow()==0 && t.getColl()==m.getColls()-1)
		{
			if (m.getCell(t.getRow(),t.getColl()).isHasDownWall()==false) //check if it has down wall
			{
				ActuallState newS = new ActuallState(t.getRow()+1,t.getColl());
				Action move = new Action("go down");
				move.setPrice(10);
				newS.setMove(move);
				states.add(newS);
			}
			if (m.getCell(t.getRow(),t.getColl()-1).isHasRightWall()==false) //check if it has left wall
			{
				ActuallState newS = new ActuallState(t.getRow(),t.getColl()-1);
				Action move = new Action("go left");
				move.setPrice(10);
				newS.setMove(move);
				states.add(newS);
			}
		}
		else if (t.getRow()==m.getRows()-1 && t.getColl()==0)
		{
			if (m.getCell(t.getRow(),t.getColl()).isHasRightWall()==false) //check if it has right wall
			{
				ActuallState newS = new ActuallState(t.getRow(),t.getColl()+1);
				Action move = new Action("go right");
				move.setPrice(10);
				newS.setMove(move);
				states.add(newS);
			}
			if (m.getCell(t.getRow()-1,t.getColl()).isHasDownWall()==false) //check if it has upper wall
			{
				ActuallState newS = new ActuallState(t.getRow()-1,t.getColl());
				Action move = new Action("go up");
				move.setPrice(10);
				newS.setMove(move);
				states.add(newS);			
			}
		}
		else if (t.getRow()==0)
		{
			if (m.getCell(t.getRow(),t.getColl()).isHasRightWall()==false) //check if it has right wall
			{
				ActuallState newS = new ActuallState(t.getRow(),t.getColl()+1);
				Action move = new Action("go right");
				move.setPrice(10);
				newS.setMove(move);
				states.add(newS);
			}
			if (m.getCell(t.getRow(),t.getColl()).isHasDownWall()==false) //check if it has down wall
			{
				ActuallState newS = new ActuallState(t.getRow()+1,t.getColl());
				Action move = new Action("go down");
				move.setPrice(10);
				newS.setMove(move);
				states.add(newS);
			}
			if (m.getCell(t.getRow(),t.getColl()-1).isHasRightWall()==false) //check if it has left wall
			{
				ActuallState newS = new ActuallState(t.getRow(),t.getColl()-1);
				Action move = new Action("go left");
				move.setPrice(10);
				newS.setMove(move);
				states.add(newS);
			}
		}
		else if (t.getRow()==m.getRows()-1)
		{
			if (m.getCell(t.getRow(),t.getColl()).isHasRightWall()==false) //check if it has right wall
			{
				ActuallState newS = new ActuallState(t.getRow(),t.getColl()+1);
				Action move = new Action("go right");
				move.setPrice(10);
				newS.setMove(move);
				states.add(newS);
			}
			if (m.getCell(t.getRow(),t.getColl()-1).isHasRightWall()==false) //check if it has left wall
			{
				ActuallState newS = new ActuallState(t.getRow(),t.getColl()-1);
				Action move = new Action("go left");
				move.setPrice(10);
				newS.setMove(move);
				states.add(newS);
			}
			if (m.getCell(t.getRow()-1,t.getColl()).isHasDownWall()==false) //check if it has upper wall
			{
				ActuallState newS = new ActuallState(t.getRow()-1,t.getColl());
				Action move = new Action("go up");
				move.setPrice(10);
				newS.setMove(move);
				states.add(newS);			
			}
		}
		else if (t.getColl()==0)
		{
			if (m.getCell(t.getRow(),t.getColl()).isHasRightWall()==false) //check if it has right wall
			{
				ActuallState newS = new ActuallState(t.getRow(),t.getColl()+1);
				Action move = new Action("go right");
				move.setPrice(10);
				newS.setMove(move);
				states.add(newS);
			}
			if (m.getCell(t.getRow(),t.getColl()).isHasDownWall()==false) //check if it has down wall
			{
				ActuallState newS = new ActuallState(t.getRow()+1,t.getColl());
				Action move = new Action("go down");
				move.setPrice(10);
				newS.setMove(move);
				states.add(newS);
			}
			if (m.getCell(t.getRow()-1,t.getColl()).isHasDownWall()==false) //check if it has upper wall
			{
				ActuallState newS = new ActuallState(t.getRow()-1,t.getColl());
				Action move = new Action("go up");
				move.setPrice(10);
				newS.setMove(move);
				states.add(newS);			
			}
		}
		else if (t.getColl()==m.getColls()-1)
		{
			if (m.getCell(t.getRow(),t.getColl()).isHasDownWall()==false) //check if it has down wall
			{
				ActuallState newS = new ActuallState(t.getRow()+1,t.getColl());
				Action move = new Action("go down");
				move.setPrice(10);
				newS.setMove(move);
				states.add(newS);
			}
			if (m.getCell(t.getRow(),t.getColl()-1).isHasRightWall()==false) //check if it has left wall
			{
				ActuallState newS = new ActuallState(t.getRow(),t.getColl()-1);
				Action move = new Action("go left");
				move.setPrice(10);
				newS.setMove(move);
				states.add(newS);
			}
			if (m.getCell(t.getRow()-1,t.getColl()).isHasDownWall()==false) //check if it has upper wall
			{
				ActuallState newS = new ActuallState(t.getRow()-1,t.getColl());
				Action move = new Action("go up");
				move.setPrice(10);
				newS.setMove(move);
				states.add(newS);			
			}
		}
		else
		{
			if (m.getCell(t.getRow(),t.getColl()).isHasRightWall()==false) //check if it has right wall
			{
				ActuallState newS = new ActuallState(t.getRow(),t.getColl()+1);
				Action move = new Action("go right");
				move.setPrice(10);
				newS.setMove(move);
				states.add(newS);
			}
			if (m.getCell(t.getRow(),t.getColl()).isHasDownWall()==false) //check if it has down wall
			{
				ActuallState newS = new ActuallState(t.getRow()+1,t.getColl());
				Action move = new Action("go down");
				move.setPrice(10);
				newS.setMove(move);
				states.add(newS);
			}
			if (m.getCell(t.getRow(),t.getColl()-1).isHasRightWall()==false) //check if it has left wall
			{
				ActuallState newS = new ActuallState(t.getRow(),t.getColl()-1);
				Action move = new Action("go left");
				move.setPrice(10);
				newS.setMove(move);
				states.add(newS);
			}
			if (m.getCell(t.getRow()-1,t.getColl()).isHasDownWall()==false) //check if it has upper wall
			{
				ActuallState newS = new ActuallState(t.getRow()-1,t.getColl());
				Action move = new Action("go up");
				move.setPrice(10);
				newS.setMove(move);
				states.add(newS);			
			}
		}
		if (this.diagonal==true) //if the maze ruls are allowing to go diagonal
		{
			if (t.getRow()==0 && t.getColl()==0) //if its the first cell = start
			{
				if ((m.getCell(t.getRow(),t.getColl()).isHasDownWall()==false) && (m.getCell(t.getRow()+1,t.getColl()).isHasRightWall()==false)) 
				{
					ActuallState newS = new ActuallState(t.getRow()+1,t.getColl()+1);
					Action move = new Action("go down and right");
					move.setPrice(15);
					newS.setMove(move);
					states.add(newS);
				}
			}
			else if (t.getRow()==0 && t.getColl()==m.getColls()-1) //if the cell is on the top right corner
			{
				if ((m.getCell(t.getRow(),t.getColl()).isHasDownWall()==false) && (m.getCell(t.getRow()+1,t.getColl()-1).isHasRightWall()==false))
				{
					ActuallState newS = new ActuallState(t.getRow()+1,t.getColl()-1);
					Action move = new Action("go down and left");
					move.setPrice(15);
					newS.setMove(move);
					states.add(newS);
				}
			}
			else if (t.getRow()==m.getRows()-1 && t.getColl()==0) //if the cell is on the down left corner
			{
				if ((m.getCell(t.getRow()-1,t.getColl()).isHasDownWall()==false) && (m.getCell(t.getRow()-1,t.getColl()).isHasRightWall()==false))
				{
					ActuallState newS = new ActuallState(t.getRow()-1,t.getColl()+1);
					Action move = new Action("go up and right");
					move.setPrice(15);
					newS.setMove(move);
					states.add(newS);
				}
			}
			else if (t.getRow()==0)
			{
				if ((m.getCell(t.getRow(),t.getColl()).isHasDownWall()==false) && (m.getCell(t.getRow()+1,t.getColl()).isHasRightWall()==false))
				{
					ActuallState newS = new ActuallState(t.getRow()+1,t.getColl()+1);
					Action move = new Action("go down and right");
					move.setPrice(15);
					newS.setMove(move);
					states.add(newS);
				}
				if ((m.getCell(t.getRow(),t.getColl()).isHasDownWall()==false) && (m.getCell(t.getRow()+1,t.getColl()-1).isHasRightWall()==false))
				{
					ActuallState newS = new ActuallState(t.getRow()+1,t.getColl()-1);
					Action move = new Action("go down and left");
					move.setPrice(15);
					newS.setMove(move);
					states.add(newS);
				}
			}
			else if (t.getRow()==m.getRows()-1)
			{
				if ((m.getCell(t.getRow()-1,t.getColl()).isHasDownWall()==false) && (m.getCell(t.getRow()-1,t.getColl()).isHasRightWall()==false))
				{
					ActuallState newS = new ActuallState(t.getRow()-1,t.getColl()+1);
					Action move = new Action("go up and right");
					move.setPrice(15);
					newS.setMove(move);
					states.add(newS);
				}
				if ((m.getCell(t.getRow()-1,t.getColl()).isHasDownWall()==false) && (m.getCell(t.getRow()-1,t.getColl()-1).isHasRightWall()==false))
				{
					ActuallState newS = new ActuallState(t.getRow()-1,t.getColl()-1);
					Action move = new Action("go up and left");
					move.setPrice(15);
					newS.setMove(move);
					states.add(newS);
				}
			}
			else if (t.getColl()==0)
			{
				if ((m.getCell(t.getRow(),t.getColl()).isHasDownWall()==false) && (m.getCell(t.getRow()+1,t.getColl()).isHasRightWall()==false))
				{
					ActuallState newS = new ActuallState(t.getRow()+1,t.getColl()+1);
					Action move = new Action("go down and right");
					move.setPrice(15);
					newS.setMove(move);
					states.add(newS);
				}
				if ((m.getCell(t.getRow()-1,t.getColl()).isHasDownWall()==false) && (m.getCell(t.getRow()-1,t.getColl()).isHasRightWall()==false))
				{
					ActuallState newS = new ActuallState(t.getRow()-1,t.getColl()+1);
					Action move = new Action("go up and right");
					move.setPrice(15);
					newS.setMove(move);
					states.add(newS);
				}	
			}
			else if (t.getColl()==m.getColls()-1)
			{
				if ((m.getCell(t.getRow(),t.getColl()).isHasDownWall()==false) && (m.getCell(t.getRow()+1,t.getColl()-1).isHasRightWall()==false))
				{
					ActuallState newS = new ActuallState(t.getRow()+1,t.getColl()-1);
					Action move = new Action("go down and left");
					move.setPrice(15);
					newS.setMove(move);
					states.add(newS);
				}
				if ((m.getCell(t.getRow()-1,t.getColl()).isHasDownWall()==false) && (m.getCell(t.getRow()-1,t.getColl()-1).isHasRightWall()==false))
				{
					ActuallState newS = new ActuallState(t.getRow()-1,t.getColl()-1);
					Action move = new Action("go up and left");
					move.setPrice(15);
					newS.setMove(move);
					states.add(newS);
				}
			}
			else
			{
				if ((m.getCell(t.getRow(),t.getColl()).isHasDownWall()==false) && (m.getCell(t.getRow()+1,t.getColl()).isHasRightWall()==false))
				{
					ActuallState newS = new ActuallState(t.getRow()+1,t.getColl()+1);
					Action move = new Action("go down and right");
					move.setPrice(15);
					newS.setMove(move);
					states.add(newS);
				}
				if ((m.getCell(t.getRow()-1,t.getColl()).isHasDownWall()==false) && (m.getCell(t.getRow()-1,t.getColl()).isHasRightWall()==false))
				{
					ActuallState newS = new ActuallState(t.getRow()-1,t.getColl()+1);
					Action move = new Action("go up and right");
					move.setPrice(15);
					newS.setMove(move);
					states.add(newS);
				}
				if ((m.getCell(t.getRow(),t.getColl()).isHasDownWall()==false) && (m.getCell(t.getRow()+1,t.getColl()-1).isHasRightWall()==false))
				{
					ActuallState newS = new ActuallState(t.getRow()+1,t.getColl()-1);
					Action move = new Action("go down and left");
					move.setPrice(15);
					newS.setMove(move);
					states.add(newS);
				}
				if ((m.getCell(t.getRow()-1,t.getColl()).isHasDownWall()==false) && (m.getCell(t.getRow()-1,t.getColl()-1).isHasRightWall()==false))
				{
					ActuallState newS = new ActuallState(t.getRow()-1,t.getColl()-1);
					Action move = new Action("go up and left");
					move.setPrice(15);
					newS.setMove(move);
					states.add(newS);
				}
			}
		}
		return states;
	}

	public Maze getM() //get the maze 
	{
		return m;
	}

	public void setM(Maze m) //seting the maze
	{
		this.m = m;
	}

	public boolean isDiagonal() //getting diagonal or not
	{
		return diagonal;
	}

	public void setDiagonal(boolean diagonal) //seting diagonal or not
	{
		this.diagonal = diagonal;
	}
	
	
}
