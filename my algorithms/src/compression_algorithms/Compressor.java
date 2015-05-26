package compression_algorithms;

import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * The Interface Compressor.
 */
public interface Compressor 
{
	
	/**
	 * Compress.
	 *
	 * @param data the data
	 * @return the byte[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public byte[] compress(byte[] data)throws IOException;
	
	/**
	 * Decompress.
	 *
	 * @param data the data
	 * @return the byte[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public byte[] decompress(byte[] data)throws IOException;
}	
