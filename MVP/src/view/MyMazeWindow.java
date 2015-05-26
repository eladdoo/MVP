package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

import presenter.Presenter.Command;
import Messages.MessageType;
import algorithms.mazeGenerators.Maze;
import algorithms.search.Solution;

public class MyMazeWindow extends BasicWindow implements View
{
	Text t;
	
	public MyMazeWindow(String title, int width, int height)
	{
		super(title, width, height);
	}

	@Override
	public void start() 
	{
		this.run();
	}

	@Override
	public void setCommands(String coName, Command c)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Command getUserCommand() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void displayMaze(Maze m) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displaySolution(Solution s)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTheName() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exitProg() 
	{
		t.setText("Thank You For Playing");
		shell.close();
	}

	@Override
	public void UpdateUser(String update) 
	{
		t.setText(update);
	}

	@Override
	void initWidgets()
	{
		shell.setLayout(new GridLayout(2,false));
		Button b = new Button(shell,SWT.PUSH);
		b.setText("EXIT");
		b.setLayoutData(new GridData(SWT.FILL,SWT.NONE,false,false,1,1));
		b.addSelectionListener(new SelectionListener()
		{
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) 
			{
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				setChanged();
				notifyObservers(MessageType.EXIT);
			}
		});
		t=new Text(shell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		t.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,1,2));
	}

}
