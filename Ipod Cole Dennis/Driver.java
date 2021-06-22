 import java.util.*;
/**
 * Displays menu and directs the program
 * 
 * @author Cole Dennis
 * @version 12/20/18
 */
public class Driver
{
    public static void main(String[]args) 
    {
        Ipod my = new Ipod();
        boolean power = true;
        
        while (power == true)
        {
            System.out.println("[1] Play");
            System.out.println("[2] Pause");
            System.out.println("[3] Skip");
            System.out.println("[4] Replay");
            System.out.println("[5] Change Song");
            System.out.println("[6] Volume");
            System.out.println("[7] Turn off \n");
            
            my.info();
            System.out.print("Enter menu option : ");
        
            Scanner scan = new Scanner(System.in);
            int x = 1;//scan.nextInt();
            switch(x)
            {
                case 1:
                    //System.out.print('\u000C');
                    System.out.println("1");
                    //my.play();
                    //break;
                case 2:
                    //System.out.print('\u000C');
                    //System.out.println("2");
                    //my.pause();
                    //break;
                case 3:
                   // System.out.print('\u000C');
                    System.out.println("2");
                    System.out.println("it skips");
                    scan.nextInt();
                    //my.skip();
                    break;
                case 4:
                    //System.out.print('\u000C');
                    //my.replay();
                    System.out.println("4");
                    break;
                case 5:
                    //System.out.print('\u000C');
                   // my.changeSong();
                   System.out.println("5");
                    break;
                case 6:
                    //System.out.print('\u000C');
                    //my.volume();
                    System.out.println("6");
                    break;
                case 7:
                    //System.out.print('\u000C');
                    //power = my.power();     
                    System.out.println("7");
                    break;                
            }
        }        
    }   
}
