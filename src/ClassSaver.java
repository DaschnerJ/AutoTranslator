import java.io.*;

public class ClassSaver {

    public void save(Object o, String name)
    {
        String filename = name+".ez";

        // Serialization
        try
        {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(o);

            out.close();
            file.close();

            System.out.println("Ez Object has been serialized.");

        }

        catch(IOException ex)
        {
            System.out.println("Save file for object does not exist!");
        }
    }

    public Object load(String name)
    {
        Object o = null;
        // Deserialization
        try
        {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(name+".ez");
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            o = in.readObject();

            in.close();
            file.close();

            System.out.println("Ez Object has been deserialized ");
        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }

        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }
        return o;
    }
}
