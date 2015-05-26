package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import presenter.Presenter.Command;
import Messages.MessageType;
import View.CLI;
import algorithms.mazeGenerators.Maze;
import algorithms.search.Solution;

/**
 * The Class MyView.
 */
public class MyView extends CLI implements View
{
	
	/** The h. */
	private HashMap<String,Command> h = new HashMap<String,Command>();
	
	/** The The command. */
	private String TheCommand;
	
	/** The The name. */
	private String TheName="";

	/**
	 * Instantiates a new my view.
	 *
	 * @param out the out
	 * @param in the in
	 */
	public MyView(PrintWriter out, BufferedReader in) 
	{
		super(out, in);
	}
	
	/*
	 * start method override from class CLI
	 */
	@Override
	public void start()
	{
		System.out.println("starting...");
		System.out.println("Enter command: ");
		System.out.println("1) To generate maze enter The command: generateMaze(space)Name of the maze(space)rows Num(space)colls Num(space)diagonal Y/N");
		System.out.println("2) To display the maze enter The command: displayMaze(space)Name of the maze you want to display");
		System.out.println("3) To solve the maze enter The command: solveMaze(space)the Name of the maze your want to solve");
		System.out.println("4) To display the solution enter The command: displaySolution(space)the Name of the maze you want to see it solution");
		System.out.println("5) To exit enter The command: exit(space)Your Name");
		try 
		{
			String line = in.readLine(); //read the command
			while (!line.equals("exit"))
			{
				String[] sp = line.split(" ");	//split path and command				
				TheCommand = sp[0];
				if (h.containsKey(TheCommand)==false)
				{
					System.out.println("No Such Command");
				}
				else
				{
					TheName = "";
					if (sp.length > 1) 
					{
						if (sp.length==2)
						{
							TheName = sp[1];
						}
						else
						{
							for (int i=1;i<sp.length;i++)
							{
								TheName = TheName + sp[i] + " "; //putting the name into string
							}
						}
						this.setChanged();
						this.notifyObservers(MessageType.GotCommand);
					}
					else
					{
						System.out.println("need param");
					}
				}
				System.out.print("Enter command: ");
				System.out.println("1) To generate maze enter The command: generateMaze(space)Name of the maze(space)rows Num(space)colls Num(space)diagonal Y/N");
				System.out.println("2) To display the maze enter The command: displayMaze(space)Name of the maze you want to display");
				System.out.println("3) To solve the maze enter The command: solveMaze(space)the Name of the maze your want to solve");
				System.out.println("4) To display the solution enter The command: displaySolution(space)the Name of the maze you want to see it solution");
				System.out.println("5) To exit enter The command: exit(space)Your Name");
				line = in.readLine(); //read a new command or exit
			}	
			this.setChanged();
			this.notifyObservers(MessageType.EXIT);
		} 
		catch (IOException e) 
		{			
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				in.close();
				out.close();
			} 
			catch (IOException e)
			{				
				e.printStackTrace();
			}			
		}
	}

	/* 
	 * set commands for VIEW
	 */
	@Override
	public void setCommands(String coName,Command c)
	{
		h.put(coName,c);
		this.setChanged();
	}

	/* 
	 * getting the command the user entered
	 */
	@Override
	public Command getUserCommand() 
	{
		this.setChanged();
		return h.get(TheCommand);
	}

	/* 
	 * display the maze that was created in model
	 */
	@Override
	public void displayMaze(Maze m) 
	{
		m.print();
		this.setChanged();
	}

	/* 
	 * display the solution from the model
	 */
	@Override
	public void displaySolution(Solution s) 
	{
		s.PrintSoul();
		this.setChanged();
	}
	
	/* 
	 * getting the name of the maze
	 */
	@Override
	public String getTheName()
	{
		return this.TheName;
	}
	
	/* 
	 * message to user after exiting from program
	 */
	@Override
	public void exitProg()
	{
		System.out.println("Thank You For Playing");
	}
	
	/* 
	 * update user if needed
	 */
	@Override
	public void UpdateUser(String update)
	{
		System.out.println(update);
	}
}
