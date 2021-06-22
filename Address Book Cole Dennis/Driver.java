import java.util.*;
/**
 * The Driver Class displays a menu allowing one to Add, Remove, or View Entry objects in an arrayList
 * Cole Dennis
 * 2/1/2019
 */
public class Driver
{   
    public static void main (String[]args)
    {
        Scanner scan = new Scanner(System.in);
        ArrayList<Entry> addressBook = new ArrayList<Entry>();
        int x = 0;
         while (x != 4) {  //4: Quit Addressbook
            System.out.print('\u000C'); //clear screen; 
            System.out.println("1. Add \n2. Remove \n3. View \n4. Exit Addressbook" );
          
            x = scan.nextInt();
        
            switch(x) {            
                case 1: //add
                    System.out.print('\u000C');
                    System.out.println("Please fill the Entry\n");
                    addressBook.add(new Entry());
                    printAndPause("\nEntry successfully saved",3000);
                    break;
                    
                case 2: //remove
                    if (addressBook.size() > 0 ) {  //checks if empty
                        System.out.print('\u000C');
                        System.out.print("Enter the index of the Entry you want to remove: ");
                        int num = scan.nextInt();
                        if ( addressBook.size() > num  ) {   //checks if index is in bounds
                            addressBook.remove(num);      
                            printAndPause("Entry successfully deleted",3000);
                        }
                        else 
                            printAndPause("The Entry you tried to remove does not exist",3000);  //out of bounds
                    }
                    else
                        printAndPause("The address book is currently empty",3000);
                    break;
                    
                case 3: //view
                    System.out.print('\u000C');
                    printAndPause(addressBook,10000);
                    break;
                         
            }
        }
    }
    
    public static void printAndPause(String text,int timeMs) { //outputs given string for timeMs milliseconds
        System.out.print(text);
        try{
            Thread.sleep(timeMs); } catch(InterruptedException e){};
    }
    
    public static void printAndPause(ArrayList aryList,int timeMs) { //outputs given ArrayList for timeMs milliseconds
        for (int i = 0; i < aryList.size();i++) 
            System.out.print(aryList.get(i));            
        try{
            Thread.sleep(timeMs); } catch(InterruptedException e){};
    }
}
