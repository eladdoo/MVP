package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import presenter.Presenter.Command;
import Messages.MessageType;
import algorithms.mazeGenerators.Maze;
import algorithms.search.Solution;

public class MyMazeWindow extends BasicWindow implements View
{
	MessageBox messageBox = new MessageBox(shell,SWT.ICON_INFORMATION | SWT.OK);
	String MazeName;
	String fileName;
	String TheCommand;
	int hintCounter=0;
	PlayTheMaze pl;
	/** The h. */
	private HashMap<String,Command> h = new HashMap<String,Command>();
	
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
		h.put(coName,c);
	}

	@Override
	public Command getUserCommand() 
	{
		return h.get(TheCommand);
	}

	@Override
	public void displayMaze(Maze m,String pMaze) 
	{
		pl = new PlayTheMaze(this);
		this.MazeName = pMaze;
		pl.setM(m);
		pl.Open();
	}

	@Override
	public void displaySolution(Solution s)
	{
		pl.setSol(s);
	}

	@Override
	public String getTheName() 
	{
		return this.MazeName;
	}

	@Override
	public void exitProg() 
	{
		this.UpdateUser("Thank You For Playing");
		shell.close();
	}

	@Override
	public void UpdateUser(String update) 
	{
		messageBox.setText("Information");
	    messageBox.setMessage(update);
	    messageBox.open();
	}

	@Override
	void initWidgets()
	{
		shell.setLayout(new GridLayout(2,false));
		final Image menu = new Image(display,"images/menu1.jpeg");
		shell.setBackgroundImage(menu);
		shell.setBounds(menu.getBounds().x,menu.getBounds().y, menu.getBounds().width,menu.getBounds().height);
		Button nm = new Button(shell,SWT.PUSH);
		nm.setText("New Maze");
		nm.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,2,1));
		nm.addSelectionListener(new SelectionListener()
		{

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}

			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				InputDialog dlg = new InputDialog(shell,SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL,"Maze Defenition","Enter Name(Space)num rows(Space)num colls(Space)diagonal Y/N");
				MazeName = dlg.open();
				if (MazeName==null)
				{
					UpdateUser("You Didnt Entered The Properties Please Try Again");
				}
				else
				{
					TheCommand = "generateMaze";
					setChanged();
					notifyObservers(MessageType.GotCommand);
				}
			}
			
		});
		Button pm = new Button(shell,SWT.PUSH);
		pm.setText("Play Maze");
		pm.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,2,1));
		pm.addSelectionListener(new SelectionListener()
		{

			@Override
			public void widgetDefaultSelected(SelectionEvent paramSelectionEvent) {}

			@Override
			public void widgetSelected(SelectionEvent paramSelectionEvent) 
			{
				InputDialog dlg = new InputDialog(shell,SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL,"Choose Maze","Enter The Name Of The Maze");
				MazeName = dlg.open();
				if (MazeName==null)
				{
					UpdateUser("You Didnt Entered The Name Of The Maze You Want To Play");
				}
				else
				{
					TheCommand = "displayMaze";
					setChanged();
					notifyObservers(MessageType.GotCommand);
				}
			}
			
		});
		Button pr = new Button(shell,SWT.PUSH);
		pr.setText("Open Properties");
		pr.setLayoutData(new GridData(SWT.FILL,SWT.NONE,true,false,2,1));
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
				if (fileName!=null)
				{
					setChanged();
					notifyObservers(MessageType.OpenFile);
				}
			}
		});
		Button ex = new Button(shell,SWT.PUSH);
		ex.setText("EXIT");
		ex.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,2,1));
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
	
	public void SetChange()
	{
		this.setChanged();
	}
	
	public void setTheCommand(String theCommand) 
	{
		TheCommand = theCommand;
	}

	public int getHintCounter() 
	{
		return hintCounter;
	}

	public void setHintCounter(int hintCounter) 
	{
		this.hintCounter = hintCounter;
	}
	
}
