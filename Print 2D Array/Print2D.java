
/**
 * Write a description of class Print2D here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Print2D
{
    // instance variables - replace the example below with your own
    public static void main (String[]args) {
        int [][] twoDimArray = new int [10][10];
        for (int i = 0; i<twoDimArray.length;i++)
            for (int j = 0; j<twoDimArray[0].length;j++)
                twoDimArray[i][j] = i * j;
        printTwoD(twoDimArray);
    }
    
    public static void printTwoD ( int[][] array ) {
        for (int i = 0; i < array.length;i++) {  
            if (i != 0)
                System.out.println();
            for (int j = 0; j < array[i].length;j++) {
                System.out.print(String.format("%4s",Integer.toString(array[i][j])));
            }
        }
    }
}
