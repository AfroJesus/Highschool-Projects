
/**
 * Finds and displays all prime numbers between 1 and 1000 using a while loop
 * 
 * Cole Dennis
 * 10/24/18
 */
public class WhilePrime
{
    public static void main(String[]args) {
        int i = 1;
        int j = i-1;
        int counter = 0;
        while ( i <= 1000) {
            boolean prime = true;
            while ( j < i && j >= 2 ) {
                if ( i % j == 0 ) {
                prime = false;
                break;
            }
            j--;
        }
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
    } 
  }
}
