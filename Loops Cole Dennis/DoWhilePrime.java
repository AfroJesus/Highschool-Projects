/**
 * Finds and displays all prime numbers between 1 and 1000 using a do while loop
 * 
 * Cole Dennis
 * 10/24/18
 */
public class DoWhilePrime
{
    public static void main(String[]args) {
        int i = 1;
        int j = i-1;
        int counter = 0;
        do {
            boolean prime = true;
            do {
                if ( j != 0 && i % j == 0 ) {
                    prime = false;
                    break;
                    }            
                j--;
            }while ( j < i && j >= 2 );
                        
        if (prime){
            System.out.printf("%4d",i);
            counter++;
            if (counter == 10) {
                System.out.println(""); 
                counter = 0;
            }
        }
        i++;
        j=i-1;
       }while ( i <= 1000);
    } 
}



