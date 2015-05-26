package compression_algorithms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class WordDoctionaryHelp.
 */
public class WordDoctionaryHelp extends HashMap<String, ArrayList<Integer>> implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * @return string for keys
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		for (String word: this.keySet())
		{
			sb.append(word + ": ");
			ArrayList<Integer> indices = this.get(word);
			for (int index : indices)
			{
				sb.append(index + ",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
}
