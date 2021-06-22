import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
/**
 * Finds the average Mpg of all the cars in the array. Each car is them compared to the average and labeled
 * average, not so average, and not average
 * 
 * Cole Dennis
 * 4/30/19
 */
public class MpgAvg
{
    static int avgCount = 0;
    static double tempLow = 100;
    static double tempHigh = 0;
    public static void main(String[]args) throws FileNotFoundException {
        int size = 99; //The text file only contains 99 cars
        File inputFile = new File("C:\\Users\\V115\\Desktop\\vehicles.txt");
        Scanner input = new Scanner(inputFile);
        double[] mpg = new double[size];    //stores mpg for the vehicles
        String[] names = new String[size];  //stores names and models of the vehicles
        input.nextLine();                   //Used to skip the heading of the data in the file
        int i = 0;
        while (input.hasNext()) { 
            mpg[i] = Double.parseDouble(input.next());
            names[i] = input.nextLine();
            i++;
        }
        double avg = findAverage(mpg);
        for (int j = 0; j<mpg.length;j++) { 
           System.out.print(getMessageMPG(mpg[j],avg)+ "\t");
           System.out.println(names[j]);
        }
        System.out.println("\n\nHighest MPG: " + tempHigh + "\nLowestMPG: " + tempLow);
        System.out.printf("%-3dvehicles were labeled as having average MPG or %-2.2f%% of the data ",avgCount,(((double)avgCount)/size)*100);//multiplies by 100 for formatting 
    }  
    public static double findAverage(double[] array) {  //takes any size double array and returns the mean of the values
        double temp = 0;
        for (int i = 0; i < array.length;i++)
             temp += array[i];        
        return temp/array.length; //the mean
    }   
    public static String getMessageMPG(double mpg, double average) { //compares a car's MPG with the average and returns the correct statement
        if (mpg > tempHigh)  //finds highest MPG
            tempHigh = mpg;
        if (mpg < tempLow)   //finds lowest MPG
            tempLow = mpg;
        if (mpg > average -1 && mpg < average +1) {  
            avgCount += 1;  //keeps count on how nany cars have average MPG
            return "average mpg";
        }
        else if ((mpg < average -1 && mpg > average +2 ) || (mpg > average +1 && mpg < average +2))
            return "not so average mpg";
        else //all remaining cars must be 2 gallons above or below the average
            return "not average mpg";           
    }
}
