package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import algorithms.search.ActuallState;

/**
 * a naive algorithem to creating maze
 * the algorithem uses random choosing to remove walls between neighbours and creating the maze
 * @author אלעד
 *
 */
public class RandomMazeGenerator implements MazeGenerator
{
	@Override
	public Maze generateMaze(int rows, int colls) 
	{
		int i,j=0,choose;
		Random rand=new Random();
		i = rand.nextInt(rows); //random which row to beggin
		Maze m=new Maze(rows,colls,i,j);
		m.getCell(i,j).setVisit(true); //setting the current cell visit to true
		List<Cell> list = new ArrayList<Cell>();
		Stack<Cell> s = new Stack<Cell>();
		while (j!=colls) //while the maze didnt get to the last coll+1
		{
			if (j==colls-1) //if it is the final coll of the maze
			{
				m.getCell(i,j).setHasRightWall(false); //remove the right wall so we will have exit in the maze
				m.getCell(i,j).setVisit(true); //set the exit cell visit to true
				ActuallState ending = new ActuallState(i,j); //creating the ending state of the maze
				m.setGoal(ending); //setting the ending state of the maze
				j++; 
			}
			else
			{
				list=m.GetUnvisitedNeighbours(m.getCell(i,j)); //getting the cell neighbours
				if (list.size()>0)
				{
					choose = rand.nextInt(list.size());
					m.RemovWall(m.getCell(i,j),list.get(choose)); //remove the wall between the current cell and random neighbour
					list.get(choose).setVisit(true); //set the neighbour visit to true
					s.push(m.getCell(i,j)); //push the current cell to the stack
					i=list.get(choose).getRow(); //i becoming the neighbour row number
					j=list.get(choose).getColl(); //j becoming the neighbour coll number
				}
				else //if the list size<0
				{
					Cell c = s.pop(); //getting the last cell that was entered from the stack
					c.setVisit(true); //setting his visit to true
					i=c.getRow(); //i is getting the cell row
					j=c.getColl(); //j is getting the cell coll
				}
			}
		}
		return m;
	}
}


