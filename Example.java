import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import DDS.BrDDS;


public class Example {
    
    public static void main(String[] args) throws IOException {


        // Open the file
        InputStream file = new FileInputStream("example.dds");

        // Create a new BinRead object
        BinRead br = new BinRead(Endian.LITTLE);

        // Create a new binary DDS object which will represent file 
        BrDDS dds = new BrDDS(file, br);

        // Print the serialized data of the DDS object
        System.out.println(dds.serialize());

        

    }

    
    
    
}
