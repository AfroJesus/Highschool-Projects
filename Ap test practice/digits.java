import java.util.*;
/**
 * Write a description of class digits here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class digits
{
    // instance variables - replace the example below with your own
    ArrayList<Integer> digitList = new ArrayList();

    /**
     * Constructor for objects of class digits
     */
    public digits(int num)
    {      
        Integer tempNum = new Integer(num);
        String stringNum = tempNum.toString();
        
        for (int i = 0; i<stringNum.length();i++){
            digitList.add(new Integer(Integer.parseInt(stringNum.charAt(i) + "")));
        }
           
        for (int i = 0; i < digitList.size(); i++) {
            System.out.println(digitList.get(i));            
        }
    }
    
    public boolean isStrictlyIncreasing() {
        for (int i=0; i<digitList.size()-1;i++) {
            if ((digitList.get(i)) >= (digitList.get(i+1)))
                return false;             
        }
        return true;
    }
    
    public static void main (String[]args) {
        digits boi = new digits(1234);
        boolean temp = boi.isStrictlyIncreasing();
        System.out.println(temp);
        digits boi2 = new digits(631);
        boolean temp2 = boi2.isStrictlyIncreasing();
        System.out.println(temp2);
    }
}
