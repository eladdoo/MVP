package cli_algorithms;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import compression_algorithms.Bits;
import compression_algorithms.HuffmanAlg;

public class UnHuffmanToFile implements DoCommand
{
	/* 
	 * Decompress a file using huffman algorithem
	 */
	@SuppressWarnings("unchecked")
	public boolean doCommand(String path) 
	{
		HuffmanAlg h = new HuffmanAlg();
		ObjectInputStream o,hg;
		ObjectOutputStream outer;
		try 
		{
			o = new ObjectInputStream(new FileInputStream(path));
			ByteArrayOutputStream ps = new ByteArrayOutputStream();
			HashMap<Bits, Character> dict;
			try 
			{
				dict = (HashMap<Bits, Character>) o.readObject(); //reading from the compressed file the dictionary
				Bits bf = (Bits) o.readObject(); //reading from the compressed file the code
				o.close(); 
				ObjectOutputStream out = new ObjectOutputStream(ps);
				out.writeObject(dict); //writing the dictionary to bytearray to send to the decompress algorithem
				out.writeObject(bf); //writing the code to bytearray to send to the decompress algorithem
				out.close();
				byte[] t = h.decompress(ps.toByteArray()); //decompress the file
				ByteArrayInputStream fg = new ByteArrayInputStream(t);
				hg = new ObjectInputStream(fg);
				String text = hg.readUTF();
				outer = new ObjectOutputStream(new FileOutputStream("DeCompHuff.hff")); //writing the decompress code into a new file
				outer.writeUTF(text);
				outer.close();
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
