import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.*;

/**
 * Write a description of class PlayerPaddle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerPaddle implements Paddle
{
    // instance variables - replace the example below with your own
    private int x, player;
    private final int PAD_WIDTH, PAD_HEIGHT;
    private final double PAD_SPEED;
    
    private double y, yVel;
    private boolean acelUp,acelDown;
    Rectangle p1Pad;
    /**
     * Constructor for objects of class PlayerPaddle
     */
    public PlayerPaddle( int SCREEN_HEIGHT, int SCREEN_WIDTH, int player)
    {
        acelUp = false; acelDown = false;       
        
        PAD_WIDTH = SCREEN_WIDTH/70;
        PAD_HEIGHT = SCREEN_HEIGHT/6;
        
        y = (SCREEN_HEIGHT/2)-PAD_HEIGHT/2;
        if (player == 1)
            x = 0;
        else
            x = SCREEN_WIDTH - PAD_WIDTH;
        p1Pad = new Rectangle ( x, (int)y, PAD_WIDTH, PAD_HEIGHT);
        PAD_SPEED = 2;
    }
    
    public Rectangle getP1Pad() {
        return p1Pad;
    }
    
    public void draw( Graphics g) {
        g.setColor(Color.white);
        g.fillRect ( x, (int)y , PAD_WIDTH, PAD_HEIGHT );
    }
    
    public void move(double delta, int SCREEN_HEIGHT) {
         if ( acelUp ) yVel -= PAD_SPEED * delta;
         else if ( acelDown ) yVel += PAD_SPEED * delta;
         else if ( !acelDown && !acelUp ) yVel *= 0.80;
         
         if (yVel > 15 )
            yVel = 15;
         else if (yVel < -15)
            yVel = -15;
            
         
         y += (int)yVel;
         
         if ( y > SCREEN_HEIGHT-PAD_HEIGHT)
            y=SCREEN_HEIGHT-PAD_HEIGHT;
         else if (y < 0)
            y = 0;
         
    }
    
    public int getY() {
        return (int)y;
    } 
    public int getX() {
        return (int)x;
    }
    
    public int getPaddleHeight() {
        return PAD_HEIGHT;
    }
    public int getPaddleWidth() {
        return PAD_WIDTH;
    }
    
    public void setAcelUp(boolean input) {
        acelUp = input;
    }
    public void setAcelDown(boolean input) {
        acelDown = input;
    }
}
