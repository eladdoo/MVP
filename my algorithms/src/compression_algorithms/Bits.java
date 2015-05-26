package compression_algorithms;

import java.io.Serializable;
import java.util.BitSet;

// TODO: Auto-generated Javadoc
/**
 * The Class Bits.
 * helping with the problem of BitSet size
 */
public class Bits implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The bit. */
	private BitSet bit;
	
	/** The size. */
	private int size;
	
	/**
	 * Instantiates a new bits.
	 */
	public Bits(){}
	
	/**
	 * Instantiates a new bits.
	 *
	 * @param bit the bit
	 * @param size the size
	 */
	public Bits(BitSet bit, int size)
	{
		super();
		this.bit = bit;
		this.size = size;
	}
	
	/**
	 * Gets the bit.
	 *
	 * @return the bit
	 */
	public BitSet getBit()
	{
		return bit;
	}
	
	/**
	 * Sets the bit.
	 *
	 * @param bit the new bit
	 */
	public void setBit(BitSet bit) 
	{
		this.bit = bit;
	}
	
	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize()
	{
		return size;
	}
	
	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(int size)
	{
		this.size = size;
	}
	
	
}
