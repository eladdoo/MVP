package compression_algorithms;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Class WordDictionary.
 */
public class WordDictionary extends CommonCompressor 
{
	
	/** The dict. */
	private WordDoctionaryHelp dict = new WordDoctionaryHelp();
	
	/** The conv dict. */
	private HashMap<ArrayList<Integer>,String> convDict = new HashMap<ArrayList<Integer>,String>();
	
	/*
	 * @param byte[] of a text to compress
	 * @return byte[] of decompress 
	 */
	@Override
	public byte[] compress(byte[] data) throws IOException
	{
		String input = new String(data);
		String[] sp = input.split(" ");	
		String comp = "";
		for (int i = 0; i < sp.length; i++) //creating a hash map if indices for each word
		{
			String word = sp[i];
			ArrayList<Integer> indices;				
			if (dict.containsKey(word))
			{
				indices = dict.get(word);
			}
			else
			{
				indices = new ArrayList<Integer>();		
			}
			indices.add(i);
			dict.put(word,indices);
		}
		for (String f:dict.keySet()) //creating the dictionary
		{
			convDict.put(dict.get(f),f);
		}
		ByteArrayOutputStream Bite = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(Bite);
		out.writeObject(convDict); //writing the dictionary
		for (String w:dict.keySet())
		{
			ArrayList<Integer> inc = dict.get(w);
			for (int i : inc)
			{
				comp = comp + i;
			}
		}
		out.writeUTF(comp); //writing the decompress string 
		out.close();
		return Bite.toByteArray();
	}
	
	/*
	 * @param byte[] to decompress
	 * @return byte[] of the decompressed text
	 */
	@SuppressWarnings({"unchecked" })
	@Override
	public byte[] decompress(byte[] data) throws IOException
	{
		ByteArrayInputStream Bite = new ByteArrayInputStream(data);
		ObjectInputStream in = new ObjectInputStream(Bite);
		try
		{
			convDict = (HashMap<ArrayList<Integer>, String>) in.readObject(); //reading the dictionary
			String input = in.readUTF(); //reading the string to decompress
			String decom = "";
			String res[] = new String[this.CountValues(data)]; //result array for the text
			int i=0;
			ArrayList<Integer> check = new ArrayList<Integer>(); //check for the indicies
			ArrayList<Integer> check2 = new ArrayList<Integer>(); //locationg the the text
			for (char c:input.toCharArray()) //decompress the byte[]
			{
					check.add(c-'0'); //adding the code to array list 
					check2.add(c-'0'); //adding the code to array list 
					if (convDict.containsKey(check)==true) //if the dictionary contain the array list
					{
						while (check2.isEmpty()==false)
						{
							res[check2.get(i)] = convDict.get(check); //putting the word in the right place in the text
							check2.remove(i); //cleaning the first array list for new codes to come
							i=0;
						}
						while (check.isEmpty()==false)
						{
							check.remove(i); //cleaning the second array list for new codes to come
							i=0;
						}
					}
			}
			ByteArrayOutputStream bits = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bits);
			for (String str:res) //making the text
			{
				decom = decom + str + " ";
			}
			out.writeUTF(decom); //writing the text
			out.close();
			in.close();
			return bits.toByteArray();
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Count values.
	 *
	 * @param s the s
	 * @return the int
	 * counting how much value the are in the dictionary
	 */
	@SuppressWarnings({"unchecked" })
	public int CountValues(byte[] s)
	{
		int count=0;
		ByteArrayInputStream Bite = new ByteArrayInputStream(s);
		ObjectInputStream in;
		HashMap<ArrayList<Integer>,String> temp = new HashMap<ArrayList<Integer>,String>();
		try 
		{
			in = new ObjectInputStream(Bite);
			try 
			{
				temp = (HashMap<ArrayList<Integer>, String>) in.readObject();
				for (ArrayList<Integer> t:temp.keySet())
				{
					count=count+t.size();
				}
				return count;
			} 
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			}
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		} 
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		return -1;
	}

	/**
	 * Gets the dict.
	 *
	 * @return the dict
	 */
	public WordDoctionaryHelp getDict() 
	{
		return dict;
	}

	/**
	 * Sets the dict.
	 *
	 * @param dict the new dict
	 */
	public void setDict(WordDoctionaryHelp dict) 
	{
		this.dict = dict;
	}

	/**
	 * Gets the conv dict.
	 *
	 * @return the conv dict
	 */
	public HashMap<ArrayList<Integer>, String> getConvDict() 
	{
		return convDict;
	}

	/**
	 * Sets the conv dict.
	 *
	 * @param convDict the conv dict
	 */
	public void setConvDict(HashMap<ArrayList<Integer>, String> convDict) 
	{
		this.convDict = convDict;
	}

}
