package compression_algorithms;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.BitSet;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;


// TODO: Auto-generated Javadoc
/**
 * The Class HuffmanAlg.
 */
public class HuffmanAlg extends CommonCompressor
{
	
	/** The dict. */
	private HashMap<Bits,Character> dict = new HashMap<Bits,Character>();
	
	/* 
	 * the compression implemention for huffman code
	 * @param byte[] data to compress
	 * @return compressed byte[] of data
	 */
	@Override
	public byte[] compress(byte[] data) throws IOException
	{
		String input = new String(data);
		int j=0;
		HashMap<Character, Hchar> countAppearance=new HashMap<>();
			for(char c: input.toCharArray()) //counting how many times each letters is in the string
			{
				Hchar hc=countAppearance.get(c);
				if(hc==null)
				{
					hc=new Hchar();
					hc.character=""+c;
					hc.count=0;
					countAppearance.put(c, hc);
				}
				hc.count++;
			}
		PriorityQueue<Hchar> pq=new PriorityQueue<Hchar>( //creating the priority queue
				new Comparator<Hchar>() 
				{
				 @Override
				 public int compare(Hchar o1, Hchar o2) 
				 {
				 return o1.count-o2.count;
				 }
				});
		pq.addAll(countAppearance.values()); //add all counting times of each letter to the pq
		while(pq.size()>1) //creating the tree
		{
			 Hchar hc0=pq.poll();
			 Hchar hc1=pq.poll();
			 Hchar hc2=new Hchar();
			 hc2.count=hc0.count+hc1.count;
			 hc2.character=hc0.character+hc1.character;
			 hc2.left=hc0;
			 hc2.right=hc1;
			 pq.add(hc2);
		}
		if (pq.peek().left!=null) //if there is left son to the root
		{
			Bits b1 = new Bits(new BitSet(),0);
			DFSbinRep(pq.peek().left,b1,false,0); //creating left tree side with code 0 = left and 1 = right
		}
		if (pq.peek().right!=null) //if the is right son to the root
		{
			Bits b2 = new Bits(new BitSet(),0);
			DFSbinRep(pq.peek().right,b2,true,0); // creating right tree side with code 0 = left and 1 = right
		}
		Bits TheBit = new Bits(new BitSet(),0);
		for (char c:input.toCharArray()) //loop to creat the data comressed bits
		{
			Bits bs = new Bits(BitSet.valueOf(countAppearance.get(c).binRep),countAppearance.get(c).binLength);
			for (int i=0;i<bs.getSize();i++,j++) //loop to check if the bit is on or off
			{
				if (bs.getBit().get(i)==true)
				{
					TheBit.getBit().set(j);
				}
			}
		}
		for (char c:countAppearance.keySet()) //creating the dictionary
		{
			Bits br = new Bits(BitSet.valueOf(countAppearance.get(c).binRep),countAppearance.get(c).binLength);
			dict.put(br,c);
		}
		TheBit.setSize(j);
		TheBit.getBit().set(j);
		ByteArrayOutputStream by = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(by);
		out.writeObject(dict); //writing the dictionary
		out.writeObject(TheBit); //writing the compressed byte[] with the size of it
		out.close();
		return by.toByteArray();
	}
	
	/*
	 * @param byte[] to decompress
	 * @return byte[] of the text
	 */
	@SuppressWarnings({ "unchecked"})
	@Override
	public byte[] decompress(byte[] data) throws IOException
	{
		ByteArrayInputStream by = new ByteArrayInputStream(data);
		String word="";
		int j,i;
		ObjectInputStream in;
		Bits bt;
		in = new ObjectInputStream(by);
		HashMap<String,Character> find = new HashMap<String,Character>();
		try 
		{
			dict = (HashMap<Bits, Character>) in.readObject(); //reading the dictionary
			bt = (Bits) in.readObject(); //reading the bits to decompress
			String sprint = "",check="",check2="";
			for (i=0;i<bt.getSize();i++) //creating a string of the byte[] to decompress
			{
				if (bt.getBit().get(i))
				{
					sprint = sprint + "1";
				}
				else
				{
					sprint = sprint + "0";
				}
			}
			for (Bits bis:dict.keySet()) //creating a dictionary containing string of representetion for each char
			{
				for (j=0;j<bis.getSize();j++)
				{
					if (bis.getBit().get(j))
					{
						check2 = check2 + "1";
					}
					else
					{
						check2 = check2 + "0";
					}
				}
				find.put(check2,dict.get(bis));
				check2="";
			}
			for (i=0;i<sprint.length();i++) //decompress the byte[] using the string and the hash map
			{
				check = check + sprint.charAt(i);
				for (String set:find.keySet())
				{
					if (check.equals(set))
					{
						word = word + find.get(set);
						check="";
					}
				}
			}
			ByteArrayOutputStream Bite = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(Bite);
			out.writeUTF(word); //wrting the text 
			out.close();
			return Bite.toByteArray();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * DF sbin rep.
	 *
	 * @param node the node
	 * @param b the b
	 * @param bin the bin
	 * @param i the i
	 * function to creat the tree with 0/1 when right son = 1 and left son = 0
	 */
	private void DFSbinRep(Hchar node,Bits b,boolean bin,int i) 
	{
		Bits by = new Bits(b.getBit().get(0,i),i);
		if (bin==true)
		{
			by.getBit().set(i);
		}
		node.binRep = by.getBit().toByteArray();
		node.binLength = (short) (i+1);
		if(node.left!=null)
		{
			DFSbinRep(node.left,by,false,i+1);
		}
		if(node.right!=null)
		{
			DFSbinRep(node.right,by,true,i+1);
		}
	} 
}
