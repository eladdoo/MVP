package compression_algorithms;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Hchar.
 */
public class Hchar implements Serializable
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The count. */
	int count;
	
	/** The character. */
	String character;
	
	/** The right/left son in the tree. */
	Hchar left=null,right=null;
	/** The bin rep. */
	byte[] binRep;
	
	/** The bin length. */
	int binLength;
	
	/* 
	 * return hash code for char
	 */
	@Override
	public int hashCode()
	{
		return character.hashCode(); 
	}

	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public int getCount() 
	{
		return count;
	}

	/**
	 * Sets the count.
	 *
	 * @param count the new count
	 */
	public void setCount(int count) 
	{
		this.count = count;
	}

	/**
	 * Gets the character.
	 *
	 * @return the character
	 */
	public String getCharacter()
	{
		return character;
	}

	/**
	 * Sets the character.
	 *
	 * @param character the new character
	 */
	public void setCharacter(String character) 
	{
		this.character = character;
	}

	/**
	 * Gets the left.
	 *
	 * @return the left
	 */
	public Hchar getLeft() 
	{
		return left;
	}

	/**
	 * Sets the left.
	 *
	 * @param left the new left
	 */
	public void setLeft(Hchar left) 
	{
		this.left = left;
	}

	/**
	 * Gets the right.
	 *
	 * @return the right
	 */
	public Hchar getRight()
	{
		return right;
	}

	/**
	 * Sets the right.
	 *
	 * @param right the new right
	 */
	public void setRight(Hchar right) 
	{
		this.right = right;
	}

	/**
	 * Gets the bin rep.
	 *
	 * @return the bin rep
	 */
	public byte[] getBinRep()
	{
		return binRep;
	}

	/**
	 * Sets the bin rep.
	 *
	 * @param binRep the new bin rep
	 */
	public void setBinRep(byte[] binRep)
	{
		this.binRep = binRep;
	}

	/**
	 * Gets the bin length.
	 *
	 * @return the bin length
	 */
	public int getBinLength()
	{
		return binLength;
	}

	/**
	 * Sets the bin length.
	 *
	 * @param binLength the new bin length
	 */
	public void setBinLength(int binLength) 
	{
		this.binLength = binLength;
	}
	
	
}
