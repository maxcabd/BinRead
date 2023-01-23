import java.io.IOException;
import java.io.InputStream;

public class BrDDS extends BrStruct {
    // Define the fields
    public char[] magic; // 4 bytes
    public int size;
    public int flags;
    public int height;
    public int width;
    public int pitchOrLinearSize;
    public int depth;
    public int mipmapCount;
    public int[] reserved1; // 11 * 4 bytes

    public int pixelFormatSize;
    public int pixelFormatFlags;
    public int fourCC;
    public int rgbBitCount;
    public int rBitMask;
    public int gBitMask;
    public int bBitMask;
    public int aBitMask;

    public int caps;
    public int caps2;
    public int caps3;
    public int caps4;
    public int reserved2;

    public byte[] data;


    public BrDDS(InputStream in, BinRead br) throws IOException {
        // If the endian is not specified, default to little endian
        if (br == null) {
            br = new BinRead(Endian.LITTLE);
        }
        
        this.magic = br.readChar(in, 4);

        // Check if the magic number is valid (DDS )
        if (this.magic == "DDS ".toCharArray()) {
            throw new IOException("Invalid magic number!");
        }
        

        /*if (this.magic != "DDS ".toCharArray()) {
            throw new IOException("Invalid magic number!");
        }*/

        this.size = br.readInt32(in);
        this.flags = br.readInt32(in);
        this.height = br.readInt32(in);
        this.width = br.readInt32(in);
        this.pitchOrLinearSize = br.readInt32(in);
        this.depth = br.readInt32(in);
        this.mipmapCount = br.readInt32(in);
        this.reserved1 = br.readInt32(in, 11);

        this.pixelFormatSize = br.readInt32(in);
        this.pixelFormatFlags = br.readInt32(in);
        this.fourCC = br.readInt32(in);
        this.rgbBitCount = br.readInt32(in);
        this.rBitMask = br.readInt32(in);
        this.gBitMask = br.readInt32(in);
        this.bBitMask = br.readInt32(in);
        this.aBitMask = br.readInt32(in);

        this.caps = br.readInt32(in);
        this.caps2 = br.readInt32(in);
        this.caps3 = br.readInt32(in);
        this.caps4 = br.readInt32(in);
        this.reserved2 = br.readInt32(in);

        this.data = br.readByte(in, this.size);
    }
        
     
        
}
