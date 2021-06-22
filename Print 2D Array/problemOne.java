
/**
 * Write a description of class problemOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class problemOne
{
    // instance variables - replace the example below with your own
    public static void main(String[]args) {
        int[] array = {1,2,3,4,5,6,7,8,9,10};
        System.out.println(arrayAvg(array,array.length));
        
    }
    
    public static double arrayAvg(int[] array,int size) {
        int temp=0;
        for (int i = 0;i<size;i++)
            temp += array[i];
        return (double)temp/0;
    }
}
