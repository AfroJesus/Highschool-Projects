import java.util.*;
import java.io.*;
/**
 * Write a description of class ReadWrite here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ReadWrite
{
    // instance variables - replace the example below with your own
    private static int limit = 50;

    /**
     * Constructor for objects of class ReadWrite
     */
    public ReadWrite()
    {
        // initialise instance variables
    }

    public static void main (String[]args) throws FileNotFoundException {
        Random rand = new Random();
        PrintWriter outFile = new PrintWriter("Stuff.txt");
        File inputFile = new File("test.txt");
        Scanner input = new Scanner(inputFile);
        String[] ary = new String[1000];
        int i=0;
        while(input.hasNext()) {
            ary[i] = input.nextLine();
            i++;         
        }
        for ( int j = 0;j<limit;j++) {
            outFile.println(ary[j]);
        }
        outFile.close();
    }
}
