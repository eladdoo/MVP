package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import Messages.MessageType;
import algorithms.mazeGenerators.Maze;
import algorithms.search.Solution;

public class PlayTheMaze
{
	private Shell shell;
	private Solution sol;
	private Maze m;
	private MyMazeWindow my;

	public PlayTheMaze(MyMazeWindow mz)
	{
		shell = new Shell();
		my = mz;
	}

	void Open() 
	{
		shell.setText("Play Game");
		shell.setLayout(new GridLayout(2,false));
		Button sm = new Button(shell,SWT.PUSH);
		sm.setText("Solve Maze");
		sm.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,false,1,1));
		sm.addSelectionListener(new SelectionListener()
		{
			@Override
			public void widgetSelected(SelectionEvent paramSelectionEvent) 
			{
				my.setTheCommand("solveMaze");
				my.SetChange();
				my.notifyObservers(MessageType.GotCommand);
				my.setTheCommand("displaySolution");
				my.SetChange();
				my.notifyObservers(MessageType.GotCommand);
				my.UpdateUser("maze solved");
				my.setHintCounter(0);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent paramSelectionEvent) {}	
		});
		final MazeDisplay maze=new MazeDisplay(shell, SWT.BORDER,m);
		maze.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,3));
		Button hint = new Button(shell,SWT.PUSH);
		hint.setText("Get Hint");
		hint.setLayoutData(new GridData(SWT.FILL,SWT.NONE,false,false,1,1));
		hint.addSelectionListener(new SelectionListener()
		{
			@Override
			public void widgetSelected(SelectionEvent paramSelectionEvent) 
			{
				int count = my.getHintCounter();
				if (sol==null)
				{
					my.setTheCommand("solveMaze");
					my.SetChange();
					my.notifyObservers(MessageType.GotCommand);
					my.setTheCommand("displaySolution");
					my.SetChange();
					my.notifyObservers(MessageType.GotCommand);
					String move = sol.getMoves().get(count).getMove();
					count++;
					my.setHintCounter(count);
					my.UpdateUser("You Need To:" + move);
				}
				else
				{
					if (count==sol.getMoves().size())
					{
						my.UpdateUser("You won already");
					}
					else
					{
					String move = sol.getMoves().get(count).getMove();
					count++;
					my.setHintCounter(count);
					my.UpdateUser("You Need To Go:" + move);
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent paramSelectionEvent) {}
		});
		Button exit = new Button(shell,SWT.PUSH);
		exit.setText("Exit");
		exit.setLayoutData(new GridData(SWT.FILL,SWT.NONE,false,false,1,1));
		exit.addSelectionListener(new SelectionListener()
		{

			@Override
			public void widgetSelected(SelectionEvent paramSelectionEvent) 
			{
				my.setHintCounter(0);
				shell.close();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent paramSelectionEvent) {}
			
		});
		shell.open();
	}

	public Solution getSol() 
	{
		return sol;
	}

	public void setSol(Solution sol)
	{
		this.sol = sol;
	}

	public Maze getM() 
	{
		return m;
	}

	public void setM(Maze m) 
	{
		this.m = m;
	}
	
	
	

}
