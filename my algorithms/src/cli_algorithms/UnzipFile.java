package cli_algorithms;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class UnzipFile implements DoCommand
{
	/* 
	 * unzip a file
	 */
	public boolean doCommand(String path)  
	{

		GZIPInputStream in;
		BufferedOutputStream out;
		try 
		{
			in = new GZIPInputStream(new FileInputStream(path+".zip"));
			out = new BufferedOutputStream(new FileOutputStream("Unzip.txt"));
			byte[] data = new byte[1000];
			while ((in.read(data))!=-1)
			{
				out.write(data);
			}
			out.flush();
			out.close();
			in.close();
			return true;
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
