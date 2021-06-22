import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
/**
 * Write a description of class Driver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Driver
{
    static class DataSet{   
        ArrayList<String> stringAry = new ArrayList<String>();
        ArrayList<Double> doubleAry = new ArrayList<Double>();
        ArrayList<Double> doubleAry2 = new ArrayList<Double>();
        ArrayList<Integer> intAry = new ArrayList<Integer>();
        //public String[] stringAry;
        //public double[] doubleAry;
        //public double[] doubleAry2;
        //public int[] intAry;
        }
    public static void main (String[]args) throws FileNotFoundException {
        int size = 50;
        PrintWriter outputFile = new PrintWriter("Sorted States");
        File inputFile = new File("Book2.txt");
        Scanner input = new Scanner(inputFile);
        Scanner scan = new Scanner(System.in);
        String[] states = new String[size];
        Double[] mean = new Double[size];
        Double[] low = new Double[size];
        Integer[] place = new Integer[size];
        int i = 0;
        while (input.hasNext()) {
            states[i] = input.next() ;
            if (input.hasNextDouble()==false && input.hasNextInt()==false)
                states[i] = states[i] + " " + input.next() ;
   
            mean[i] = Double.parseDouble(input.next());
            
            low[i] = input.nextDouble();
            place[i] = input.nextInt();
      
            i += 1;
        }
        
        System.out.print("1: View Unsorted\n2: View Sorted\n3: 1 SD away");
        int x = 0;
        while (x != 4) {
            x = scan.nextInt();
            switch(x) {
                case 1: //view
                    printData(states,mean,low,place);           
                    break;
                    case 2:
                    printData(sortData(states,mean,low,place));
                    break;
                    case 3:                
                    filterStates(states,mean,low,place,outputFile);
                    break;
            }
        }
        outputFile.close();
    }
        
    public static void printData(String[] names,Double[] mean, Double[] low, Integer[] place) {
        for (int i = 0;i<names.length;i++) {
            System.out.printf("%-15s\t%-3.1f\t%-3.1f\t%-5d\n",names[i],mean[i],low[i],place[i]);
        }
    }
    
    public static void printData(DataSet dataSet) {
        for (int i = 0;i<dataSet.stringAry.size();i++) {
            System.out.printf("%-15s\t%-3.1f\t%-3.1f\t%-5d\n",dataSet.stringAry.get(i),dataSet.doubleAry.get(i),dataSet.doubleAry2.get(i),dataSet.intAry.get(i));
        }
    }
    
    
    public static DataSet sortData(String[] names,Double[] mean, Double[] low, Integer[] place) {
        DataSet dataSet = new DataSet();
        String[] tempNames = names.clone();
        Double[] tempMean = mean.clone();
        Double[] tempLow = low.clone();
        Integer[] tempPlace = place.clone();
          for ( int i = 0; i < place.length-1;i++) {
            int min = i;
            for ( int j = i+1; j < place.length;j++)
                if (place[min] > place[j]) {
                    min = j;            
                }
            
            if (min != i) {    
                int Place = tempPlace[min];
                double Low = tempLow[min];
                double Mean = tempMean[min];
                String Names = tempNames[min];
                tempPlace[min] = tempPlace[i];
                tempLow[min] = tempLow[i];
                tempMean[min] = tempMean[i];
                tempNames[min] = tempNames[i];
                tempPlace[i] = Place;
                tempLow[i] = Low;
                tempLow[i] = Mean;
                tempNames[i] = Names;
   
            }        
        }
        dataSet.stringAry = new ArrayList<String>(Arrays.asList(tempNames));
        dataSet.doubleAry = new ArrayList<Double>(Arrays.asList(tempMean));
        dataSet.doubleAry2 = new ArrayList<Double>(Arrays.asList(tempLow));;
        dataSet.intAry = new ArrayList<Integer>(Arrays.asList(tempPlace));
        return dataSet;
    }
    
    public static double calculateSD(Double[] mean) {
        double avg = calculateAvg(mean);
        double sumOfData = 0;
        for (int i = 0; i<mean.length;i++) {
             sumOfData += Math.pow(mean[i] - avg,2);
        }
        double standardDevi = Math.sqrt(sumOfData/(mean.length-1));
        return standardDevi;
    }
    
    public static double calculateAvg(Double[] mean) {
        double temp = 0;
        for (int i = 0; i < mean.length;i++) {
             temp += mean[i];        
        }
        return temp/mean.length;
    }
    
    public static void filterStates(String[] names,Double[] mean, Double[] low, Integer[] place,PrintWriter outputFile) {
        DataSet dataSet = new DataSet();
        //dataSet.stringAry = new ArrayList<String>();
        //dataSet.doubleAry = new ArrayList<Double>();
        //dataSet.doubleAry2 = new ArrayList<Double>();
        //dataSet.intAry = new ArrayList<Integer>();
        for (int i = 0;i<mean.length;i++) 
            if (mean[i] > calculateAvg(mean) - calculateSD(mean) && mean[i] < calculateAvg(mean) + calculateSD(mean)) { //checks if mean[i] is within 1 standard deviation away from mean
                dataSet.stringAry.add(names[i]);
                dataSet.doubleAry.add(mean[i]);
                dataSet.doubleAry2.add(low[i]);
                dataSet.intAry.add(place[i]);
                
                
                //System.out.printf("%-15s\t%-3.1f\t%-3.1f\t%-5d\n",names[i],mean[i],low[i],place[i]);       
                //outputFile.printf("%-15s\t%-3.1f\t%-3.1f\t%-5d",names[i],mean[i],low[i],place[i]);
                //outputFile.println();
                 
            }
        printData(dataSet);
        }
}


