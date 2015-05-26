package cli_algorithms;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import compression_algorithms.WordDictionary;

public class DicToFile implements DoCommand
{
	/*
	 * compress a text using the naiv dictionary algorithem
	 */
	public boolean doCommand(String path) 
	{
		WordDictionary w = new WordDictionary();
		BufferedReader o;
		ObjectInputStream read;
		ObjectOutputStream out;
		String send,text="";
		try 
		{
			o = new BufferedReader(new FileReader(path));
			do  //read the text
			{
				send = o.readLine();
				text = text + send;
			}while (send!=null);
			o.close();
			byte[] by = w.compress(text.getBytes()); //compress the text
			ByteArrayInputStream in = new ByteArrayInputStream(by);
			read = new ObjectInputStream(in);
			try 
			{
				@SuppressWarnings("unchecked")
				HashMap<ArrayList<Integer>,String> temp = (HashMap<ArrayList<Integer>, String>) read.readObject(); //reading the dictionary
				String code = read.readUTF(); //reading the code
				read.close();
				out = new ObjectOutputStream(new FileOutputStream("CompDic.txt")); //writing to file
				out.writeObject(temp);
				out.writeUTF(code);
				out.close();
				return true;
			} 
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			}			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return false;
	} 
}
