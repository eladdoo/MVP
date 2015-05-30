package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Maze;

public class MazeDisplay extends Canvas
{
	Maze m;

	public MazeDisplay(Composite parent, int style,Maze me) 
	{
		super(parent, style);
		this.m = me;
		setBackground(new Color(null, 255, 255, 255));
		this.addPaintListener(new PaintListener()
		{

			@Override
			public void paintControl(PaintEvent e) 
			{
				e.gc.setForeground(new Color(null,0,0,0));
				e.gc.setBackground(new Color(null,0,0,0));
				int width=getSize().x;
				int height=getSize().y;
				int w = width/m.getMaze()[0].length;
				int h = height/m.getMaze().length;
				for(int i=0;i<m.getMaze().length;i++)
				{
					for(int j=0;j<m.getMaze()[i].length;j++)
					{
						int x=j*w;
				        int y=i*h;
				        if (m.getCell(i,j).isHasDownWall()==true)
				        {
				        	//e.gc.drawLine(x,y,w,h);
				        	e.gc.fillRectangle(x,y,w,h);
				        }
				        if (m.getCell(i,j).isHasRightWall()==true)
				        {
				        	//e.gc.drawLine(x,y,w,h);
				        	e.gc.fillRectangle(x,y,w,h);
				        }
					}
				}
			}
			
		});
	}
	


}
