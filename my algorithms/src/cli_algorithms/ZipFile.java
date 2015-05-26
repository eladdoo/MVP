package cli_algorithms;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class ZipFile implements DoCommand
{
	/* 
	 * zip file
	 */
	public boolean doCommand(String path) 
	{
		BufferedInputStream in;
		GZIPOutputStream out;
		try
		{
			in=new BufferedInputStream(new FileInputStream(path));
			byte[] data=new byte[1000];		
			try 
			{
				out=new GZIPOutputStream(new FileOutputStream(path+".zip"));
				while((in.read(data))!=-1)
				{
					out.write(data);
				}
				out.flush();
				out.close();
				in.close();
				return true;
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}

		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		return false;
	}
}
