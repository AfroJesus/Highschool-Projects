import java.util.*;
/**
 * The Entry Class collects data from the user to fill the entry
 * 
 * Cole Dennis
 * 2/1/19
 */
public class Entry
{
    String fName,lName,address,city,state,zip,phone;
    Scanner scan = new Scanner(System.in);
    public Entry()
    {
        System.out.print("Enter first name: ");
        fName = scan.nextLine();
        System.out.print("Enter last name: ");
        lName = scan.nextLine();
        System.out.print("Enter the street address: ");
        address = scan.nextLine();
        System.out.print("Enter the city name: ");
        city = scan.nextLine();
        System.out.print("Enter the state initials: ");
        state = scan.nextLine();
        System.out.print("Enter the zip code: ");
        zip = scan.nextLine();
        System.out.print("Enter phone number: ");
        phone = scan.nextLine();
    }
    
    public String toString() //allows object to be printed
    {
        String result = fName + " " + lName + "\n" + address + "\n" + city + ", " +
                        state + " " + zip + "\nContact: " + phone + "\n\n";                        
        return result;                        
    }
}
