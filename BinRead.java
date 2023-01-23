import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class BinRead {
    private int endian;


    public BinRead(int endian) {
        this.endian = endian;
    }

    
    public byte readByte(InputStream in) throws IOException {
        int b = in.read();
        if (b == -1) {
            throw new EOFException();
        }

        if (endian == Endian.LITTLE) {
            return (byte) b;
        } else {
            return (byte) b;
        }
    }

    public byte[] readByte(InputStream in, int count) throws IOException {
        byte[] bytes = new byte[count];
        for (int i = 0; i < count; i++) {
            bytes[i] = readByte(in);
        }
        return bytes;
    }

    public byte readInt8(InputStream in) throws IOException {
        return readByte(in);
    }

    public byte readUInt8(InputStream in) throws IOException {
        return readByte(in);
    }

    
    public short readInt16(InputStream in) throws IOException {
        int b1 = readByte(in);
        int b2 = readByte(in);

        if (endian == Endian.LITTLE) {
            return (short) ((b2 << 8) | b1);
        } else {
            return (short) ((b1 << 8) | b2);
        }

    }

    public short readUInt16(InputStream in) throws IOException {
        if (endian == Endian.LITTLE) {
            return (short) (readByte(in) | (readByte(in) << 8));
        } else {
            return (short) ((readByte(in) << 8) | readByte(in));
        }
    }

    public int readInt32(InputStream in) throws IOException {
        int b1 = readByte(in);
        int b2 = readByte(in);
        int b3 = readByte(in);
        int b4 = readByte(in);

        if (endian == Endian.LITTLE) {
            return (b4 << 24) | (b3 << 16) | (b2 << 8) | b1;
        } else {
            return (b1 << 24) | (b2 << 16) | (b3 << 8) | b4;
        }
    }

    public int readUInt32(InputStream in) throws IOException {
        if (endian == Endian.LITTLE) {
            return readByte(in) | (readByte(in) << 8) | (readByte(in) << 16) | (readByte(in) << 24);
        } else {
            return (readByte(in) << 24) | (readByte(in) << 16) | (readByte(in) << 8) | readByte(in);
        }
    }

    public int[] readInt32(InputStream in, int count) throws IOException {
       // Will read an array of 32 bit integers
         int[] array = new int[count];
            for (int i = 0; i < count; i++) {
                array[i] = readInt32(in);
            }
            return array;
    }

    public long readInt64(InputStream in) throws IOException {
        long b1 = readByte(in);
        long b2 = readByte(in);
        long b3 = readByte(in);
        long b4 = readByte(in);
        long b5 = readByte(in);
        long b6 = readByte(in);
        long b7 = readByte(in);
        long b8 = readByte(in);

        if (endian == Endian.LITTLE) {
            return (b8 << 56) | (b7 << 48) | (b6 << 40) | (b5 << 32) | (b4 << 24) | (b3 << 16) | (b2 << 8) | b1;
        } else {
            return (b1 << 56) | (b2 << 48) | (b3 << 40) | (b4 << 32) | (b5 << 24) | (b6 << 16) | (b7 << 8) | b8;
        }
    }

    public long readUInt64(InputStream in) throws IOException {
        if (endian == Endian.LITTLE) {
            return readByte(in) | (readByte(in) << 8) | (readByte(in) << 16) | (readByte(in) << 24) | (readByte(in) << 32) | (readByte(in) << 40) | (readByte(in) << 48) | (readByte(in) << 56);
        } else {
            return (readByte(in) << 56) | (readByte(in) << 48) | (readByte(in) << 40) | (readByte(in) << 32) | (readByte(in) << 24) | (readByte(in) << 16) | (readByte(in) << 8) | readByte(in);
        }

    }

    public float readFloat(InputStream in) throws IOException {
        if (endian == Endian.LITTLE) {
            return Float.intBitsToFloat(readInt32(in));
        } else {
            return Float.intBitsToFloat(readInt32(in));
            
        }
    }


    public char readChar(InputStream in) throws IOException {
        // Will read a single char
        return (char) readByte(in);
    }

    public char[] readChar(InputStream in, int count) throws IOException {
        // Will read an array of chars
        char[] array = new char[count];
        for (int i = 0; i < count; i++) {
            array[i] = readChar(in);
        }
        return array;
    }


    public String readString(InputStream in) throws IOException {
        // Will read chars until it finds a null byte
        StringBuilder sb = new StringBuilder();
        while (true) {
            byte b = readByte(in);
            if (b == 0) {
                break;
            }
            sb.append((char) b);
        }
        return sb.toString();
        
    }

    public void Padding(InputStream in, int count) throws IOException {
        // Will skip a number of bytes
        for (int i = 0; i < count; i++) {
            readByte(in);
        }
    }

    


    




}