package boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import presenter.Presenter;
import view.MyMazeWindow;
import view.MyView;
import view.PlayTheMaze;
import view.ViewRunnable;
import model.MyModel;

/**
 * The Class Run.
 * ELAD DOOCKER 304813280
 * VLAD OGRANOVIC 317208288
 111*/
public class Run {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) 
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter writer = new PrintWriter(System.out);
		MyModel m = new MyModel();
		MyView v = new MyView(writer,reader);
		MyMazeWindow win = new MyMazeWindow("my maze",500,500);
		Presenter p = new Presenter(m,win);
		m.addObserver(p);
		v.addObserver(p);
		win.addObserver(p);
		win.start();
		//Thread t=new Thread(new ViewRunnable(v));
		//t.start();
	}
}
