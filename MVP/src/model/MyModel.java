package model;

import java.beans.XMLDecoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import presenter.Properties;
import compression_algorithms.Bits;
import compression_algorithms.HuffmanAlg;
import Messages.MessageType;
import algorithms.demo.MazeDomain;
import algorithms.mazeGenerators.Cell;
import algorithms.mazeGenerators.DFSMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.RandomMazeGenerator;
import algorithms.search.AStar;
import algorithms.search.Action;
import algorithms.search.ActuallState;
import algorithms.search.AirDistance;
import algorithms.search.BFS;
import algorithms.search.ManhattanDistance;
import algorithms.search.Solution;

/**
 * The Class MyModel.
 */
public class MyModel extends Observable implements Model
{
	
	/** The Tnum. */
	private int Tnum;
	
	/** The sol. */
	private Solution sol;
	
	/** The map. */
	private HashMap<Maze,Solution> map = new HashMap<Maze,Solution>();
	
	/** The executor. */
	private ExecutorService executor;
	
	/** The Names. */
	private Queue<String> Names = new LinkedList<String>();
	
	/** The new m. */
	private Queue<MazeDomain> newM = new LinkedList<MazeDomain>();
	
	/** The solu. */
	private Queue<String> solu = new LinkedList<String>();
	
	/** The algo solution. */
	private String algoSolution;
	
	/** The algo maze. */
	private String algoMaze;
	
	/** The huris. */
	private String huris;
	
	/** The Error. */
	private String Error;
	

	/**
	 * Instantiates a new my model.
	 */
	public MyModel()
	{
		super();
		XMLDecoder decoder;
		try 
		{
			decoder = new XMLDecoder(new FileInputStream("properties.xml"));
			Properties p = (Properties)decoder.readObject();
			this.Tnum = p.getTNUM();
			this.algoMaze = p.getAlgoCrMaze();
			this.algoSolution = p.getAlgoSolution();
			this.huris = p.getHuristic();
			executor = Executors. newFixedThreadPool(Tnum);
			this.LoadHuff();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			this.Error = "file not found";
		}
	}

	/*
	 * @see model.Model#generateMaze(java.lang.String, int, int, boolean)
	 * generate maze 
	 */
	@Override
	public void generateMaze(String name,int rows,int colls,boolean diag) 
	{
		Future<MazeDomain> f;
		if (this.algoMaze == "DFS")
		{
			f = executor.submit (new MazeCallable(rows,colls,new DFSMazeGenerator(),diag));
		}
		else
		{
			f = executor.submit (new MazeCallable(rows,colls,new RandomMazeGenerator(),diag));
		}
		
		try 
		{
			this.Names.add(name);
			this.newM.add(f.get());
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
			this.Error = "Interrupt found";
		} 
		catch (ExecutionException e) 
		{
			e.printStackTrace();
			this.Error = "Execution found";
		}
		this.setChanged();
		this.notifyObservers(MessageType.GenreatedMaze);
	}

	/* getting the ready maze
	 */
	@Override
	public MazeDomain getMaze() 
	{
		this.setChanged();
		return this.newM.poll();
	}
	
	/* solving the maze who name's is what the user entered
	 */
	@Override
	public void solveMaze(String name,MazeDomain m) 
	{
		for (Maze maz:map.keySet())
		{
			if (maz.equals(m.getM()))
			{
				System.out.println("solving maze is ready already...");
				solu.add(name);
				this.setChanged();
				this.notifyObservers(MessageType.MazeReady);
				return;
			}
		}
		Future<Solution> f;
		if (this.algoSolution == "BFS")
		{
			f = executor.submit (new SolutionCallable(m,new BFS()));
		}
		else
		{
			if (this.huris == "AirDistance")
			{
				f = executor.submit(new SolutionCallable(m,new AStar(new AirDistance())));
			}
			else
			{
				f = executor.submit(new SolutionCallable(m,new AStar(new ManhattanDistance())));
			}
		}
		try
		{
			sol = f.get();
			solu.add(name);
			map.put(m.getM(),sol);
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		catch (ExecutionException e) 
		{
			e.printStackTrace();
		} 
		this.setChanged();
		this.notifyObservers(MessageType.MazeSolved);
	}

	/* getting the solution of the maze that entered
	 */
	@Override
	public Solution getSolution(Maze min) 
	{
		this.setChanged();
		return this.map.get(min);
	}

	/* exit program
	 */
	@Override
	public void stop() 
	{
		this.SaveHuff();
		this.executor.shutdown();
		this.setChanged();
	}
	
	/**
	 * Save huff.
	 * didnt had time to do it
	 */
	@SuppressWarnings("unchecked")
	public void SaveHuff()
	{
		String str = "";
		for (Maze temp:map.keySet()) //converting the map into string
		{
			str = str + temp.toString();
			str = str + map.get(temp).toString();
		}
		HuffmanAlg hff = new HuffmanAlg();
		try 
		{
			byte[] b = hff.compress(str.getBytes());
			ByteArrayInputStream by = new ByteArrayInputStream(b);
			ObjectInputStream f = new ObjectInputStream(by);
			HashMap<Bits, Character> dict;
			Bits bf;
			try 
			{
				dict = (HashMap<Bits, Character>) f.readObject();
				bf = (Bits) f.readObject();
				f.close();
				ObjectOutputStream c = new ObjectOutputStream(new FileOutputStream("Save.hff")); //writing the bits to a file
				c.writeObject(dict);
				c.writeObject(bf);
				c.close();
			} 
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			}
			
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Load huff.
	 * load from huff file 
	 * didnt had time to do it
	 */
	@SuppressWarnings("unchecked")
	public void LoadHuff()
	{
		File check = new File("Save.hff");
		if (check.length() == 0)
		{
			return;
		}
		else
		{
			HuffmanAlg hff = new HuffmanAlg();
			try 
			{
				ObjectInputStream o = new ObjectInputStream(new FileInputStream("Save.hff"));
				ByteArrayOutputStream by = new ByteArrayOutputStream();
				HashMap<Bits, Character> dict;
				try 
				{
					dict = (HashMap<Bits, Character>) o.readObject(); //reading from the compressed file the dictionary
					Bits bf = (Bits) o.readObject(); //reading from the compressed file the code
					o.close(); 
					ObjectOutputStream out = new ObjectOutputStream(by);
					out.writeObject(dict); //writing the dictionary to bytearray to send to the decompress algorithem
					out.writeObject(bf); //writing the code to bytearray to send to the decompress algorithem
					out.close();
					byte[] b = hff.decompress(by.toByteArray());
					ByteArrayInputStream fg = new ByteArrayInputStream(b);
					ObjectInputStream hg = new ObjectInputStream(fg);
					String text = hg.readUTF();
					hg.close();
					String []res = text.split(","); //split the string into mazes and solutions
					for (int i=0;i<res.length;i+=2)
					{
						Maze temp = new Maze();
						Solution temp2 = new Solution();
						String []ForM = res[i].split(" "); //split in the maze itself to know its variables
						String []ForS = res[i+1].split("/"); //split in the solution itself to know its variables
						temp.setRows(Integer.parseInt(ForM[0])); 
						temp.setColls(Integer.parseInt(ForM[1]));
						temp.setStart(new ActuallState(Integer.parseInt(ForM[2]),Integer.parseInt(ForM[3])));
						temp.setGoal(new ActuallState(Integer.parseInt(ForM[4]),Integer.parseInt(ForM[5])));
						temp.setMaze(new Cell[temp.getRows()][temp.getColls()]);
						for (int x=0;x<temp.getRows();x++) //initilize matrix
						{
							for (int y=0;y<temp.getColls();y++)
							{
								temp.getMaze()[x][y] = new Cell(x,y);
							}
						}
						int r=0,c=0;
						for (int j=6;j<ForM.length;j+=2) //setting the walls
						{
							if (ForM[j].equals("true"))
							{
								temp.getMaze()[r][c].setHasDownWall(true);
							}
							else
							{
								temp.getMaze()[r][c].setHasDownWall(false);
							}
							if (ForM[j+1].equals("true"))
							{
								temp.getMaze()[r][c].setHasRightWall(true);
							}
							else
							{
								temp.getMaze()[r][c].setHasRightWall(false);
							}
							c++;
							if (c==temp.getColls())
							{
								r++;
								c=0;
							}
						}
						List<Action> moves = new ArrayList<Action>();
						for (int j=0;j<ForS.length;j++) //creating the actions
						{
							String []ForA = ForS[j].split("_");
							double P = Double.parseDouble(ForA[0]);
							String TheMove = ForA[1];
							Action a = new Action(TheMove);
							a.setPrice(P);
							moves.add(a);
						}
						temp2.setMoves(moves);
						map.put(temp,temp2); //setting into the map
					}
				} 
				catch (ClassNotFoundException e) 
				{
					e.printStackTrace();
				} 
			}	 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		return;
	}
	
	/* 
	 * getting the queue of the strings (by who made first)
	 */
	@Override
	public Queue<String> gettingQueueNames()
	{
		return this.Names;
	}
	
	/* 
	 * getting the mazeDomain from queue (by the order it asked to creat it)
	 */
	@Override
	public Queue<MazeDomain> gettingQueueMazes()
	{
		return this.newM;
	}

	/* 
	 * getting the solution from queue by the order it asked
	 */
	@Override
	public Queue<String> gettingQueueSolution()
	{
		return this.solu;
	}
	
	@Override
	public String gettingError()
	{
		return this.Error;
	}
	
	@Override
	public void loadXmlFill(String file)
	{
		XMLDecoder decoder;
		try 
		{
			decoder = new XMLDecoder(new FileInputStream(file));
			Properties p = (Properties)decoder.readObject();
			this.Tnum = p.getTNUM();
			this.algoMaze = p.getAlgoCrMaze();
			this.algoSolution = p.getAlgoSolution();
			this.huris = p.getHuristic();
			executor = Executors. newFixedThreadPool(Tnum);
			this.LoadHuff();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			this.Error = "file not found";
		}
	}

}
