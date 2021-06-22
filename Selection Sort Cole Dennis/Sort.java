import java.util.*;
/**
 * Uses 3 separate sorting methods to sort arrays and outputs them on screen.
 * The count compares the efficiency of each
 * 
 * Cole Dennis 
 * 2/11/19
 */
public class Sort
{
    static int countBub, countSelect, countInsert;
    static final int SIZE = 150;  //size of array
    public static void main(String[] args) {
        Random rand = new Random();
        int[] array1 = new int[SIZE];
        for (int i = 0; i < array1.length;i++)
            array1[i] = rand.nextInt(99)+1;
        int[] array2 = array1.clone();
        int[] array3 = array1.clone();
        int[] array4 = array1.clone();
        countBub = 0;
        countSelect = 0;
        countInsert = 0;
        array2 = bubbleSort(array2);
        array3 = selectionSort(array3);
        array4 = insertionSort(array4);
        System.out.println("UnSorted");
        printArrays(array1);
        System.out.println("");
        System.out.println("Bubble Sort");
        printArrays(array2,countBub);
        System.out.println("");
        System.out.println("Selection Sort");
        printArrays(array3,countSelect);
        System.out.println("");
        System.out.println("Insertion Sort");
        printArrays(array4,countInsert);     
    }
    
    public static int[] bubbleSort(int[] array) {
        for ( int i = 0; i < array.length-1;i++)
            for ( int j = i+1; j < array.length;j++)
                if (array[i] > array[j]) {
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                    countBub += 1;
                }
        return array;
    }
    
    public static int[] selectionSort(int[] array) {
        int min;
        for ( int i = 0; i < array.length-1;i++) {
            min = i;
            for ( int j = i+1; j < array.length;j++)
                if (array[min] > array[j]) {
                    min = j;            
                }
            
            if (min != i) {    
                int temp = array[min];
                array[min] = array[i];
                array[i] = temp;
                countSelect += 1;
            }
        }
        return array;
    }
    
    public static int[] insertionSort(int[] array) {
        for ( int i = 1; i < array.length; i++) {
            int key = array[i];
            int pos = i;
            while ( pos > 0 && array[pos-1] > key) {
                array[pos] = array[pos-1];
                pos--;                
            }
            array[pos] = key;
            countInsert += 1;
        }
        return array;
    }
    
    public static void printArrays(int[] array, int count) {
        for (int i = 1; i <= array.length;i++) {
            System.out.print(String.format("%2s",Integer.toString(array[i-1]))+ " "); //%2s gives each number 2 spaces to print
            if ( i !=0 && i % 10 == 0 )
                System.out.print("\n");          
        }
        System.out.println("Changes: " + count);
    }  
    public static void printArrays(int[] array) {
        for (int i = 1; i <= array.length;i++) {
            System.out.print(String.format("%2s",Integer.toString(array[i-1]))+ " "); //%2s gives each number 2 spaces to print
            if ( i !=0 && i % 10 == 0 )
                System.out.print("\n");
            
        }
    }
}