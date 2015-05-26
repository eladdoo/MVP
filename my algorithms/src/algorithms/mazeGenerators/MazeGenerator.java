package algorithms.mazeGenerators;

/**
 * an interface for the maze creating algorithems
 * each algorithem that need to build maze should implement the interface
 * @author אלעד
 *
 */
public interface MazeGenerator 
{
	Maze generateMaze(int rows,int colls);
}
