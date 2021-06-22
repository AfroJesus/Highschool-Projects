import java.util.*;
/**
 * This program takes a user's string and outputs the 5 middle charcacters
 * 
 * Cole Dennis
 * 9/18/18
 */
public class Mid5
{
    public static void main (String[]args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print ("Enter any String: ");
        String ans = scan.nextLine();
        
        int len = ans.length();
        int hLen = len/2;
        
        System.out.println(ans.substring(hLen-2, hLen+3));
    }
}

