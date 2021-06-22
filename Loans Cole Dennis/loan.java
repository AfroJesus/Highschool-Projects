import java.util.*;
import java.io.*;
import java.text.NumberFormat;
/**
 * Write a description of class loan here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class loan
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class loan
     */
    public loan()
    {
        // initialise instance variables
        x = 0;
    }

    public static void main(String[]args) throws FileNotFoundException {
        final int SIZE = 7;
        File inFile = new File("loans.txt");
        Scanner scan = new Scanner(inFile);
        double[] initLoan = new double[SIZE];
        double[] rate = new double[SIZE];
        int[] period = new int[SIZE];
        double[] time = new double[SIZE];
        System.out.printf("%-16s%-8s%-8s%-8s%-8s\n","Principal","Rate","Period","Time","Payment");
        int i = 0; 
        while (scan.hasNext()) {
            initLoan[i] = scan.nextDouble();
            rate[i] = scan.nextDouble();
            period[i] = scan.nextInt();
            time[i] = scan.nextDouble();
            i+=1;
        }
        i = 0;
         for (i = 0; i<initLoan.length;i++) {
            System.out.printf("$%-8.2f\t",initLoan[i]);
            System.out.printf("%-4.2f%%\t",rate[i]*100);
            System.out.printf("%-4d\t",period[i]);
            System.out.printf("%-5.2f\t",time[i]);
            double adjustedRate = rate[i]/period[i];
            System.out.printf("%-6.2f\n",initLoan[i]*(adjustedRate/(1-Math.pow((1+adjustedRate),-(period[i]*time[i])))));
        }
        
        
       
    }
}
