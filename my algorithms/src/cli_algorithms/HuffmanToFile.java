package cli_algorithms;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import compression_algorithms.Bits;
import compression_algorithms.HuffmanAlg;

public class HuffmanToFile implements DoCommand
{
	/* 
	 * compress a file using huffman algorithem
	 */
	@SuppressWarnings("unchecked")
	public boolean doCommand(String path) 
	{
		HuffmanAlg h = new HuffmanAlg();
		BufferedReader o;
		ObjectInputStream f;
		ObjectOutputStream c;
		String text="",s;
		HashMap<Bits, Character> dict;
		try 
		{
			o = new BufferedReader(new FileReader(path)); //read from file
			do //read the text
			{
				s = o.readLine();
				text = text + s;
			}while (s!=null);
			o.close();
			byte[] b = h.compress(text.getBytes()); //compress the text
			ByteArrayInputStream by = new ByteArrayInputStream(b);
			f = new ObjectInputStream(by);
			try {
				dict = (HashMap<Bits, Character>) f.readObject();
				Bits bf = (Bits) f.readObject();
				f.close();
				c = new ObjectOutputStream(new FileOutputStream("CompText.hff")); //writing the bits to a file
				c.writeObject(dict);
				c.writeObject(bf);
				c.close();
				return true;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
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
