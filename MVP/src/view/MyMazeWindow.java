package view;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import presenter.Presenter.Command;
import Messages.MessageType;
import algorithms.mazeGenerators.Maze;
import algorithms.search.Solution;

public class MyMazeWindow extends BasicWindow implements View
{
	Text t;
	MessageBox messageBox = new MessageBox(shell, SWT.ICON_INFORMATION |SWT.OK | SWT.CANCEL);
	String MazeName;
	String fileName;
	
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
		return this.MazeName;
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
	    messageBox.setMessage(update);
	}

	@Override
	void initWidgets()
	{
		shell.setLayout(new GridLayout(2,false));
		Button pr = new Button(shell,SWT.PUSH);
		pr.setText("Open properties");
		pr.setLayoutData(new GridData(SWT.FILL,SWT.NONE,false,false,1,1));
		pr.addSelectionListener(new SelectionListener()
		{

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}

			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				FileDialog fd = new FileDialog(shell,SWT.OPEN);
				fd.setText("open");
				fd.setFilterPath("");
				String[] filterExt = {"*.xml"};
				fd.setFilterExtensions(filterExt);
				fileName = fd.open();
				setChanged();
				notifyObservers(MessageType.OpenFile);
			}
			
		});
		
		t=new Text(shell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		t.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,1,2));
		
		Button ex = new Button(shell,SWT.PUSH);
		ex.setText("EXIT");
		ex.setLayoutData(new GridData(SWT.FILL,SWT.NONE,false,false,1,1));
		ex.addSelectionListener(new SelectionListener()
		{
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}

			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				setChanged();
				notifyObservers(MessageType.EXIT);
			}
		});
	}
	
	@Override
	public String gettingFileName()
	{
		return this.fileName;
	}

}
