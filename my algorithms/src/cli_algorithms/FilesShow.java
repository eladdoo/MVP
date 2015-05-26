package cli_algorithms;

import java.io.File;

public class FilesShow implements DoCommand
{
	/* 
	 * showing files in folders or just file that chosen
	 */
	public boolean doCommand(String path) 
	{
		File file = new File(path);
		if (file.isFile())
		{
			System.out.println(file);
		}
		else
		{
			for (File f : file.listFiles())
			{
				System.out.println(f);
			}
		}
		return true;
	}
}
