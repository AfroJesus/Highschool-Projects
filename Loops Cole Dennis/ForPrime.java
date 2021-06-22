
/**
 * Finds and displays all prime numbers between 1 and 1000 using a for loop
 * 
 * Cole Dennis
 * 10/24/18
 */
public class ForPrime
{
    public static void main(String[]args) {  
    int counter = 0;    
    for ( int i = 1; i <= 1000; i++) {           
        boolean prime = true;
        for ( int j = i-1; j>=2; j-- ) {                         
            if ( i%j == 0 ) {
                prime = false;
                break;
            }
        }
        if (prime){
            System.out.printf("%4d",i);
            counter++;
            if (counter == 10) {
                System.out.println(""); 
                counter = 0;
            }
        }
  }
 }
}
