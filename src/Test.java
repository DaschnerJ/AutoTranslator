import java.util.Scanner;

public class Test {

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        String line = "";
        Translate tr = new Translate();
        while(!line.equalsIgnoreCase("/quit"))
        {
            line = in.nextLine();
            if(line.equalsIgnoreCase("/help"))
            {
                say("Commands:");
                say("/help -> List of commands.");
                say("/setup -> Allows to set up the language conversions.");
                say("/set infrom -> Sets in from.");
                say("/set into -> Sets in to.");
                say("/set outfrom -> Sets out from.");
                say("/set outto -> Sets out to.");
                say("/save -> Saves the current translate configuration to be loaded next time.");
                say("/info -> Shows current translate configuration.");
                say("/quit -> Quits the program.");
            }
            else if(line.equalsIgnoreCase("/setup"))
            {
                tr.requestLanguages();
            }
            else if(line.equalsIgnoreCase("/set infrom"))
            {
                tr.setInFrom();
            }
            else if(line.equalsIgnoreCase("/set into"))
            {
                tr.setInTo();
            }
            else if(line.equalsIgnoreCase("/set outfrom"))
            {
                tr.setOutFrom();
            }
            else if(line.equalsIgnoreCase("/set outto"))
            {
                tr.setOutTo();
            }
            else if(line.equalsIgnoreCase("/save"))
            {
                tr.save();
            }
            else if(line.equalsIgnoreCase("/info"))
            {
                tr.info();
            }
            else
            {
                say("In: " + tr.translateIn(line));
                say("Out: " + tr.translateOut(line));
            }
        }
    }

    public static void say(String msg)
    {
        System.out.println(msg);
    }
}
