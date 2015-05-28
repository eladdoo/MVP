package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import algorithms.mazeGenerators.Maze;
import algorithms.search.Solution;

public class PlayTheMaze
{
	private Shell shell;
	private Solution sol;
	private Maze m;
	private MyMazeWindow my;

	public PlayTheMaze(Shell s,MyMazeWindow mz)
	{
		shell = new Shell(s);
		my = mz;
	}

	void Open() 
	{
		shell.setText("Play Game");
		shell.setLayout(new GridLayout(2,false));
		Button sm = new Button(shell,SWT.PUSH);
		sm.setText("Solve Maze");
		sm.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,false,1,1));
		Text t = new Text(shell,SWT.BORDER);
		t.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,1,3));
		Button hint = new Button(shell,SWT.PUSH);
		hint.setText("Get Hint");
		hint.setLayoutData(new GridData(SWT.FILL,SWT.NONE,false,false,1,1));
		Button exit = new Button(shell,SWT.PUSH);
		exit.setText("Exit");
		exit.setLayoutData(new GridData(SWT.FILL,SWT.NONE,false,false,1,1));
		shell.open();
	}
	
	

}
