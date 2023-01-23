import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

// Create abstract class for all binary structures with common methods

public abstract class BrStruct {
    
    // Create interface for reading and writing
    public void read(InputStream in, BinRead br) throws IOException {
        // pass
    }

    // Create write method
    public void write(OutputStream out, BinRead br) throws IOException {
        //pass
    }


    // Method to return the size of the object taking into account the size of the object's fields
    
    public int sizeOf(Object obj) {
        int size = 0;
        for (java.lang.reflect.Field field : obj.getClass().getDeclaredFields()) {
            if (field.getType().equals(short.class)) {
                size += 2;
            } else if (field.getType().equals(int.class)) {
                size += 4;
            } else if (field.getType().equals(long.class)) {
                size += 8;
            } else if (field.getType().equals(String.class)) {
                size += 4;
            }
        }
        return size;
    }

    // Method to written a JSON representation of the object
    public String serialize() { 
        String json = "{";
        for (java.lang.reflect.Field field : this.getClass().getDeclaredFields()) {
            try {


                if (field.getType().equals(short.class)) {
                    json += "\"" + field.getName() + "\":" + field.getShort(this) + ",";
                } else if (field.getType().equals(int.class)) {
                    json += "\"" + field.getName() + "\":" + field.getInt(this) + ",";
                } else if (field.getType().equals(long.class)) {
                    json += "\"" + field.getName() + "\":" + field.getLong(this) + ",";
                } else if (field.getType().equals(char.class)) {
                    json += "\"" + field.getName() + "\":\"" + field.getChar(this) + "\",";
                
                } else if (field.getType().equals(String.class)) {
                    json += "\"" + field.getName() + "\":\"" + field.get(this) + "\",";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        json = json.substring(0, json.length() - 1);
        json += "}";
        return json;
        
    }


}
        
