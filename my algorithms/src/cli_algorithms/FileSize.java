package cli_algorithms;

import java.io.File;

public class FileSize implements DoCommand
{
	/*
	 * getting the size of the file
	 */
	public boolean doCommand(String path)
	{
		File file = new File(path);
		System.out.println("file size:" + file.length());
		return true;
	}
}
