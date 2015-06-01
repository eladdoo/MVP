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
						int x=(j)*w;
				        int y=(i)*h;
				        
				        int x2 = (j +1)*w;
				        int y2 = (i + 1) * h;
				        
				        if (m.getCell(i,j).isHasDownWall()==true)
				        {
				        	e.gc.drawLine(x,y2,x2,y2);
				        	//e.gc.fillRectangle(x,y,w,h);
				        	//e.gc.drawLine(x1, y1, x2, y2);
				        }
				        if (m.getCell(i,j).isHasRightWall()==true)
				        {
				        	e.gc.drawLine(x2,y,x2, y2);
				        	//e.gc.fillRectangle(0,y,x,y);
				        }
					}
				}
				for (int i=0;i<m.getMaze().length;i++)
				{
					int y=(i)*h;
					int y2 = (i + 1) * h;
					e.gc.drawLine(0,y,0, y2);
					int x = width-3;
					e.gc.drawLine(x,y,x, y2);
				}
				for (int j=0;j<m.getMaze()[0].length;j++)
				{
					int x=(j + 1)*w;
					int x2 = (j + 2)*w;
					e.gc.drawLine(x,0,x2,0);
					int y = height-3;
					e.gc.drawLine(x,y,x2,y);
				}
				m.print();
			}
			
		});
	}
	


}
