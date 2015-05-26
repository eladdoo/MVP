package cli_algorithms;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import compression_algorithms.WordDictionary;

public class UnDicToFile implements DoCommand
{
	/* 
	 * Decompressing a code using the naiv dictionary algorithem
	 */
	@SuppressWarnings("unchecked")
	public boolean doCommand(String path) 
	{
		WordDictionary w = new WordDictionary();
		ObjectInputStream o,read;
		ObjectOutputStream out;
		try 
		{
			o = new ObjectInputStream(new FileInputStream(path));
			HashMap<ArrayList<Integer>, String> newH;
			try
			{
				newH = (HashMap<ArrayList<Integer>, String>) o.readObject(); //reading the dictionary
				String s = o.readUTF(); //reading the code
				o.close();
				ByteArrayOutputStream bs = new ByteArrayOutputStream();
				out = new ObjectOutputStream(bs);
				out.writeObject(newH); //writing the dictionary to a bytearray to send to the DEcompress algorithem
				out.writeUTF(s); //writing the code to a bytearray to send to the DEcompress algorithem
				out.close();
				byte[] by = w.decompress(bs.toByteArray()); //decompress the code
				ByteArrayInputStream bit = new ByteArrayInputStream(by);
				read = new ObjectInputStream(bit);
				String text = read.readUTF();
				out = new ObjectOutputStream(new FileOutputStream("DeCompDic.txt")); //writing the decompress code to a new file
				out.writeUTF(text);
				out.close();
				return true;
			} 
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			}
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
