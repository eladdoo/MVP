package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import algorithms.search.ActuallState;

/**
 * the maze class with all the method that helping creating the maze 
 * @author אלעד
 *
 */
@SuppressWarnings("serial")
public class Maze implements Serializable
{
	private Cell[][] maze;
	private int rows;
	private int colls;
	private ActuallState start; //starting cell
	private ActuallState goal; //goal cell
	
	//C'TOR
	public Maze(int rows, int colls,int startRow,int startColl) 
	{
		super();
		this.rows = rows;
		this.colls = colls;
		maze=new Cell[rows][colls];
		for (int i=0;i<rows;i++)
		{
			for (int j=0;j<colls;j++)
			{
				maze[i][j]=new Cell(i,j);
			}
		}
		this.start = new ActuallState(startRow,startColl);
		this.goal = new ActuallState(rows-1,colls-1);
	}
	
	public Maze(){}

	public Cell getCell(int row,int coll) //getting the cell 
	{
		return maze[row][coll];
	}
	
	public boolean HaveVisit() //checking if there are unvisited cell if not return false else return true
	{
		for (int i=0;i<rows;i++)
		{
			for (int j=0;j<colls;j++)
			{
				if (maze[i][j].isVisit()==false)
					return false;
			}
		}
		return true;
	}

	public List<Cell> GetUnvisitedCell() //returning all the unvisited cells in the maze
	{
		int i,j;
		List<Cell> list = new ArrayList<Cell>();
		for (i=0;i<rows;i++)
		{
			for (j=0;j<colls;j++)
			{
				if (maze[i][j].isVisit()==false)
				{
					list.add(maze[i][j]);
				}
			}
		}
		return list;
	}
	
	/**
	 * this method help in getting all the cell neighbours
	 * the method is looking for the cell neighbours (cells from up,down,left,right) which have yet to be visited
	 * if the cell wasnt visited the cell is added to the list
	 * @param c
	 * @return
	 */
	public List<Cell> GetUnvisitedNeighbours(Cell c) //getting all the cell neighbours
	{
		List<Cell> list = new ArrayList<Cell>();
		if (c.getRow()==0 && c.getColl()==0) //the cell is the begining of the maze
		{
			if (maze[c.getRow()+1][c.getColl()].isVisit()==false)
			{
				list.add(maze[c.getRow()+1][c.getColl()]);
			}
			if (maze[c.getRow()][c.getColl()+1].isVisit()==false)
			{
				list.add(maze[c.getRow()][c.getColl()+1]);
			}
		}
		else if (c.getRow()==rows-1 && c.getColl()==colls-1) //the cell is the finish cell of the maze
		{
			if (maze[c.getRow()-1][c.getColl()].isVisit()==false)
			{
				list.add(maze[c.getRow()-1][c.getColl()]);
			}
			if (maze[c.getRow()][c.getColl()-1].isVisit()==false)
			{
				list.add(maze[c.getRow()][c.getColl()-1]);
			}
		}
		else if (c.getRow()==0 && c.getColl()==colls-1) //the top right corner of the maze
		{
			if (maze[c.getRow()][c.getColl()-1].isVisit()==false)
			{
				list.add(maze[c.getRow()][c.getColl()-1]);
			}
			if (maze[c.getRow()+1][c.getColl()].isVisit()==false)
			{
				list.add(maze[c.getRow()+1][c.getColl()]);
			}
		}
		else if (c.getRow()==rows-1 && c.getColl()==0) //the down left corner of the maze
		{
			if (maze[c.getRow()][c.getColl()+1].isVisit()==false)
			{
				list.add(maze[c.getRow()][c.getColl()+1]);
			}
			if (maze[c.getRow()-1][c.getColl()].isVisit()==false)
			{
				list.add(maze[c.getRow()-1][c.getColl()]);
			}
		}
		else if (c.getRow()==0) //the cell is in the starting row
		{
			if (maze[c.getRow()][c.getColl()-1].isVisit()==false)
			{
				list.add(maze[c.getRow()][c.getColl()-1]);
			}
			if (maze[c.getRow()][c.getColl()+1].isVisit()==false)
			{
				list.add(maze[c.getRow()][c.getColl()+1]);
			}
			if (maze[c.getRow()+1][c.getColl()].isVisit()==false)
			{
				list.add(maze[c.getRow()+1][c.getColl()]);
			}
		}
		else if (c.getRow()==rows-1) //the cell is in the most down row of the maze
		{
			if (maze[c.getRow()][c.getColl()-1].isVisit()==false)
			{
				list.add(maze[c.getRow()][c.getColl()-1]);
			}
			if (maze[c.getRow()][c.getColl()+1].isVisit()==false)
			{
				list.add(maze[c.getRow()][c.getColl()+1]);
			}
			if (maze[c.getRow()-1][c.getColl()].isVisit()==false)
			{
				list.add(maze[c.getRow()-1][c.getColl()]);
			}
		}
		else if (c.getColl()==0) //the cell is in the far left side of the maze
		{
			if (maze[c.getRow()-1][c.getColl()].isVisit()==false)
			{
				list.add(maze[c.getRow()-1][c.getColl()]);
			}
			if (maze[c.getRow()+1][c.getColl()].isVisit()==false)
			{
				list.add(maze[c.getRow()+1][c.getColl()]);
			}
			if (maze[c.getRow()][c.getColl()+1].isVisit()==false)
			{
				list.add(maze[c.getRow()][c.getColl()+1]);
			}
		}
		else if (c.getColl()==colls-1) //the cell is in the far right side of the maze
		{
			if (maze[c.getRow()-1][c.getColl()].isVisit()==false)
			{
				list.add(maze[c.getRow()-1][c.getColl()]);
			}
			if (maze[c.getRow()+1][c.getColl()].isVisit()==false)
			{
				list.add(maze[c.getRow()+1][c.getColl()]);
			}
			if (maze[c.getRow()][c.getColl()-1].isVisit()==false)
			{
				list.add(maze[c.getRow()][c.getColl()-1]);
			}
		}
		else //its not cell in the frame of the maze
		{
			if (maze[c.getRow()-1][c.getColl()].isVisit()==false)
			{
				list.add(maze[c.getRow()-1][c.getColl()]);
			}
			if (maze[c.getRow()+1][c.getColl()].isVisit()==false)
			{
				list.add(maze[c.getRow()+1][c.getColl()]);
			}
			if (maze[c.getRow()][c.getColl()-1].isVisit()==false)
			{
				list.add(maze[c.getRow()][c.getColl()-1]);
			}
			if (maze[c.getRow()][c.getColl()+1].isVisit()==false)
			{
				list.add(maze[c.getRow()][c.getColl()+1]);
			}
		}
		return list;
	}
	
	public void print() //printing the maze
	{
		int i,j;
		for (i=0;i<rows;i++)
		{
			System.out.print("|");
			for (j=0;j<colls;j++)
			{
				if (maze[i][j].isHasDownWall()==true)
					System.out.print("_");
				else
					System.out.print(" ");
				if (maze[i][j].isHasRightWall()==true)
					System.out.print("|");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
	}
	/**
	 * this method remove the wall between cell and his neighbour
	 * the method is checking where is the neighbour cell and through that removing the wall
	 * @param c1
	 * @param c2
	 */
	public void RemovWall(Cell c1,Cell c2) //remove wall between neighbours
	{
		if (c1.getRow()==c2.getRow() && c1.getColl()+1==c2.getColl())
		{
			c1.setHasRightWall(false);
		}
		else if (c1.getRow()==c2.getRow() && c1.getColl()-1==c2.getColl())
		{
			c2.setHasRightWall(false);
		}
		else if (c1.getRow()+1==c2.getRow() && c1.getColl()==c2.getColl())
		{
			c1.setHasDownWall(false);
		}
		else
		{
			c2.setHasDownWall(false);
		}
	}

	public ActuallState getStart() //getting the starting of the maze
	{
		return start;
	}

	public void setStart(ActuallState start) //set the starting of the maze
	{
		this.start = start;
	}

	public ActuallState getGoal() //getting the goal state of the maze
	{
		return goal;
	}

	public void setGoal(ActuallState goal) //setting the goal state of the maze
	{
		this.goal = goal;
	}

	public int getRows() //getting the maze rows
	{
		return rows;
	}

	public void setRows(int rows) //setting the maze rows
	{
		this.rows = rows;
	}

	public int getColls() //getting the maze colls
	{
		return colls;
	}

	public void setColls(int colls) //setting the maze colls
	{
		this.colls = colls;
	}

	public Cell[][] getMaze() 
	{
		return maze;
	}

	public void setMaze(Cell[][] maze) 
	{
		this.maze = maze;
	}
	
	@Override
	public boolean equals(Object obj) //implement equal for maze use
	{
		if (this.rows!=((Maze)obj).getRows() || this.colls!=((Maze)obj).getColls())
		{
			return false;
		}
		for (int i=0;i<this.rows;i++)
		{
			for (int j=0;j<this.colls;j++)
			{
				if (this.getCell(i,j).equals(((Maze)obj).getCell(i,j))==false)
				{
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public int hashCode() //getting maze code
	{
		int code=0;
		for (int i=0;i<this.rows;i++)
		{
			for (int j=0;j<this.colls;j++)
			{
				code = code + this.getCell(i,j).hashCode();
			}
		}
		return code;
	}
	
	@Override
	public String toString()
	{
		String str = "";
		str = str + Integer.toString(this.rows) + " ";
		str = str + Integer.toString(this.colls) + " ";
		str = str + this.start.toString();
		str = str + this.goal.toString();
		for (int i=0;i<this.rows;i++)
		{
			for (int j=0;j<this.colls;j++)
			{
				str = str + this.maze[i][j].toString();
			}
		}
		str = str + ",";
		return str;
	}
}
