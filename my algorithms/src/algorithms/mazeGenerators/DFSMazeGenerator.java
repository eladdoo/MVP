package algorithms.mazeGenerators;

import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * an implementetion to algorithem for creating a maze
 * @author אלעד
 *
 */
public class DFSMazeGenerator implements MazeGenerator 
{
	@Override
	public Maze generateMaze(int rows,int colls)
	{
		int chosen;
		Maze m = new Maze(rows,colls,0,0); //creating new maze
		m.getCell(rows-1,colls-1).setHasRightWall(false); //last cell shouldnt have Right wall
		Random rand = new Random(); 
		Stack<Cell> s=new Stack<Cell>();
		Cell beginning = new Cell(m.getStart().getRow(),m.getStart().getColl()); //get starting Cell
		Cell CurrentCell = beginning; //current cell initialize to the beginning
		CurrentCell.setVisit(true); //set current cell visit to true
		while (m.HaveVisit()==false) //while all the cells wasnt visited
		{
			List<Cell> list = m.GetUnvisitedNeighbours(CurrentCell); //method for getting the cell neighbours
			if (list.isEmpty()==false)
			{
				chosen=rand.nextInt(list.size()); //getting randomize cell from the neighbours list
				s.push(CurrentCell); //pushing current cell to the stack
				m.RemovWall(CurrentCell,list.get(chosen)); //remove the wall between the current cell and his neighbour
				CurrentCell=list.get(chosen); //making the chosen cell from the ArrayList to the current cell
				CurrentCell.setVisit(true); //setting the new current cell visit to true
			}
			else if (s.isEmpty()==false) //if the stack isnt empty
			{
				CurrentCell=s.pop(); //getting a current cell (the latest cell that entered the stack
			}
			else
			{
				List<Cell> l = m.GetUnvisitedCell(); //getting all the unvisited cells from the maze
				chosen = rand.nextInt(l.size()); //randomize one of the unvisited cells
				CurrentCell = l.get(chosen); //making the current cell the chosen cell from the list
				CurrentCell.setVisit(true); //setting the new current cell visit to true
			}
		}
		return m;
	}
}
