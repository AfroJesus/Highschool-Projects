import java.util.*;
/**
 * Includes methods that perform expected Ipod functions
 * 
 * @author Cole Dennis 
 * @version 12/20/18
 */
public class Ipod
{
    private int song;
    private int volume;
    Scanner scan = new Scanner(System.in);
    /**
     * Constructor for objects of class Ipod
     */
    public Ipod()
    {
        volume = 5;
        song = 1;
    }
    
    public void info()
    {
        System.out.println("Selected Song: " + song);
        System.out.println("Volume: " + volume);
    }
    
    public void play()
    {
        System.out.println("Playing Song " + song);
    }
    
    public boolean pause()
    {
        System.out.println("Song " + song + " paused");
        return true;
    }
    
    public void skip()
    {
        song += 2;
        
        System.out.println("Song " + (song-1) + " skipped");
    }
    
    public void replay()
    {
        System.out.println("Replaying Song " + song);
    }
    
    public void changeSong()
    {
        System.out.print("Enter song number: ");
        song = scan.nextInt();            
        System.out.print('\u000C');
    }
  
    public void volume()
    {
        System.out.print("Enter Volume (10 MAX): ");
        volume = scan.nextInt();
        
        if (volume > 10)
            volume = 10;
        if (volume < 0)
            volume = 0;
            
        System.out.print('\u000C');
    }
    
    public boolean power()
    {
        System.out.println('\u000C');
        return false;
    }
}
