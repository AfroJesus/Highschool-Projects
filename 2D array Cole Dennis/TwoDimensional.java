import java.util.*;
/**
 * This class fills up a 2D array and calculates the values for each row and column. Each value is stored in another array where
 * it is added again to recieve the total value of the array. Finally all arrays are outputted to the screen.
 * 
 * Cole Dennis
 * 3/1/19
 */
public class TwoDimensional
{
    static int[][] array = new int [10][10];
    static int [] rowTotal = new int[10];
    static int [] columnTotal = new int[10];
    static int temp = 0;
    public static void main(String[]args) {
        Random rand = new Random();
        for (int i = 0; i < array.length;i++)
            for (int j = 0; j < array[0].length;j++) {
                array[i][j] = rand.nextInt(49)+1;              //fills array    
            }
                                   
        for (int i = 0; i < array.length;i++) {  
            if (i != 0)
                System.out.println();
            for (int j = 0; j < array[i].length;j++) {
                System.out.print(String.format("%4s",Integer.toString(array[i][j])));
                temp += array[i][j];
                if ( j == array[i].length-1) {
                    rowTotal[i] = temp;
                    System.out.print("   " + rowTotal[i]);
                    temp = 0;
                }
            }
        }
        System.out.println("\n");
        for (int i = 0; i < array[0].length;i++) {
            for (int j = 0; j < array.length;j++) {
                temp += array[j][i];
                if ( j == array.length-1) {
                    columnTotal[i] = temp;
                    System.out.print(String.format("%4s",Integer.toString(columnTotal[i])));
                    temp = 0;
                }
            }
        }
        
        if ( addArray(rowTotal) == addArray(columnTotal))  
            System.out.print("   " + addArray(rowTotal));
    }
    
    //takes a 1D array and returns the added value
    public static int addArray(int[] array) {
        temp = 0;
        for (int i = 0; i < array.length;i++) {
            temp += array[i];
        }
        return temp;
    }
}
