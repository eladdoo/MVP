package presenter;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import algorithms.demo.MazeDomain;
import algorithms.mazeGenerators.Maze;
import Messages.MessageType;
import view.View;
import model.Model;

/**
 * The Class Presenter.
 */
public class Presenter implements Observer
{
	
	/** The model. */
	private Model m;
	
	/** The view. */
	private View v;
	
	/** The saved m. */
	private HashMap<String,MazeDomain> savedM = new HashMap<String,MazeDomain>();
	
	/**
	 * Instantiates a new presenter.
	 *
	 * @param m the m
	 * @param v the v
	 */
	public Presenter(Model m, View v) 
	{
		super();
		this.m = m;
		this.v = v;
		v.setCommands("generateMaze",new GenerateMaze());
		v.setCommands("displayMaze",new DisplayMaze());
		v.setCommands("solveMaze",new SolveMaze());
		v.setCommands("displaySolution",new DisplaySolution());
		v.setCommands("exit",new Exit());
	}

	/* 
	 * updates for observer
	 */
	@SuppressWarnings("incomplete-switch")
	@Override
	public void update(Observable o, Object arg) 
	{
		if (o==v) //if its view
		{
			MessageType n = (MessageType)arg;
			switch (n)
			{
			case GotCommand:
				Command c = v.getUserCommand();
				String Name = v.getTheName();
				c.doCommand(Name);
				break;
			case EXIT:
				m.stop();
				v.exitProg();
				break;
			case OpenFile:
				String fname = v.gettingFileName();
				m.loadXmlFill(fname);
				break;
			}
		}
		else //its model
		{
			MessageType t = (MessageType)arg;
			switch (t)
			{
			case GenreatedMaze:
				String NewName = m.gettingQueueNames().poll();
				this.savedM.put(NewName,m.getMaze());
				v.UpdateUser("The Maze"+ " " + NewName + " " + "is ready");
				break;
			case MazeSolved:
				String solo = m.gettingQueueSolution().poll();
				v.UpdateUser("The Solution for:" + " " + solo + " " + "is ready" );
				break;
			case MazeReady:
				String solu = m.gettingQueueSolution().poll();
				v.UpdateUser("The Solution for:" + " " + solu + " " + "is ready" );
				break;
			case ERROR:
				String er = m.gettingError();
				v.UpdateUser(er);
				break;
			}
		}
	}
	
	/**
	 * The Interface Command.
	 */
	public interface Command 
	{
		
		/**
		 * Do command.
		 *
		 * @param name the name
		 */
		void doCommand(String name);
	}
	
	/**
	 * The Class GenerateMaze.
	 */
	public class GenerateMaze implements Command
	{

		/* 
		 * do the generate maze command
		 */
		@Override
		public void doCommand(String name) 
		{
			String[] res = name.split(" ");
			String n = res[0];
			int rows = Integer.parseInt(res[1]);
			int colls = Integer.parseInt(res[2]);
			boolean dig;
			if (res[3].equals("Y"))
			{
				dig = true;
			}
			else
			{
				dig = false;
			}
			m.generateMaze(n,rows,colls,dig);
		}

	}
	
	/**
	 * The Class DisplayMaze.
	 */
	public class DisplayMaze implements Command
	{

		/*
		 * do the display maze command
		 */
		@Override
		public void doCommand(String name) 
		{
			v.displayMaze(savedM.get(name).getM());
		}

	}
	
	/**
	 * The Class SolveMaze.
	 */
	public class SolveMaze implements Command
	{

		/* 
		 * do the solve maze command
		 */
		@Override
		public void doCommand(String name) 
		{
			m.solveMaze(name,savedM.get(name));
		}
	}
	
	/**
	 * The Class DisplaySolution.
	 */
	public class DisplaySolution implements Command
	{

		/* 
		 * do the display solution command
		 */
		@Override
		public void doCommand(String name) 
		{
			Maze miz = savedM.get(name).getM();
			v.displaySolution(m.getSolution(miz));
		}

	}
	
	/**
	 * The Class Exit.
	 */
	public class Exit implements Command
	{

		/* 
		 * do the exit command
		 */
		@Override
		public void doCommand(String name) 
		{
			m.stop();
		}

	}

}
