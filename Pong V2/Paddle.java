import java.awt.*;
/**
 * Write a description of interface Paddle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface Paddle
{
    public void draw( Graphics g);   
    public void move(double delta,int SCREEN_HEIGHT);        
    public int getY();
    public int getX();
    public int getPaddleHeight();
    public int getPaddleWidth();
}
