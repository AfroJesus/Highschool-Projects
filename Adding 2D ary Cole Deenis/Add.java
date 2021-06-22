import java.util.*;
/**
 * Write a description of class Add here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Add
{
    // instance variables - replace the example below with your own
    
    public static void main(String[]args) {
        Random rand = new Random();
        int[][] array = new int[10][10];
        for (int i = 0; i < array.length;i++)
            for(int j = 0;j < array[i].length;j++)
                array[i][j] = rand.nextInt(49);
                
                
    }
}
