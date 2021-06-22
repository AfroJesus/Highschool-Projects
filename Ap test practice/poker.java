import java.util.*;
/**
 * Write a description of class poker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class poker
{
    // instance variables - replace the example below with your own
    private int x;
    int[][] deck = new int[14][4];
    ArrayList<Integer> playerHand = new ArrayList();
    ArrayList<Integer> dealerHand = new ArrayList();
    Random rand = new Random();
    boolean win = false;
    Scanner scan = new Scanner(System.in);
    /**
     * Constructor for objects of class poker
     */
    public poker()
    {
        boolean playing = true;
        for (int i = 0; i<2; i++) {
            getCard(playerHand);
            getCard(dealerHand);
        }
        printHand(playerHand);
        printHand(dealerHand);
        if (addHand(playerHand) == 21 ) {
           win = true;
        }
        else 
            while (playing) {
                System.out.println("Do you want to hit (press 1) or stay(press 2): ");
                if (scan.nextInt() == 1)
                    while(scan.nextInt() == 1) {
                        getCard(playerHand);
                        System.out.print('\u000C');
                        printHand(playerHand);
                        printHand(dealerHand);
                        if(addHand(playerHand) > 21) {
                            win = false;
                            playing = false;
                        }
                    }
                while (addHand(dealerHand) < 16) {
                    getCard(dealerHand);
                    System.out.print('\u000C');
                    printHand(playerHand);
                    printHand(dealerHand);
                    if(addHand(dealerHand) > 21) {
                            win = true;
                            playing = false;
                        }
                }
                if ( addHand(playerHand) > addHand(dealerHand)) {
                    win = true;
                    playing = false;
                }
                else {
                    win = false;
                    playing = false;
                }
            }
        if (win)
            System.out.print("you win");
        else
            System.out.print("you suck");
        }
   
    
    

    public void getCard(ArrayList hand) {
        int value = rand.nextInt(14);
        int suit = rand.nextInt(4);
        if (deck[value][suit] == 0) {
            deck[value][suit] = 1;
            hand.add(new Integer(value));
        }
        else   
            getCard(hand);
            
    }
    
    public void printHand(ArrayList hand) {
        for (int i = 0; i < hand.size(); i++) {
                System.out.print(hand.get(i) + "\t");
            }
        System.out.println("\t" + addHand(hand));
       
    }
    
    public int addHand(ArrayList hand) {
        int temp = 0;
        for (int i = 0; i < hand.size();i++){
           temp += (int)hand.get(i);
        }
        return temp;
    }
    
    public static void main(String[]args) {
        poker game = new poker();
    }
}
