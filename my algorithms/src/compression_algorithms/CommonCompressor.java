package compression_algorithms;

import java.io.IOException;

/**
 * the abstract class for compression algorithems wich contain data member that is common to all algorithems
 * @author אלעד
 *
 */
public abstract class CommonCompressor implements Compressor
{
	public abstract byte[] compress(byte[] data)throws IOException;
	public abstract byte[] decompress(byte[] data)throws IOException;
}
