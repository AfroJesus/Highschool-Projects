
/**
 * Write a description of class problemTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class problemTwo
{
    // instance variables - replace the example below with your own
    public static void main(String[]args) {
        int[] array = {1,2,3,4,5,6,7,8,9,10};
        System.out.println(range(array,array.length));
    }
    
    public static int range(int[] array,int size) {
        int max = array[0];
        int min = array[0];
        for(int i=0;i<size;i++) {
            if (min > array[i])
                min = array[i];
            if (max < array[i])
                max = array[i];
            }
        return max-min;
        }
}
