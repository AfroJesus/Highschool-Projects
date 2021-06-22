import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.*;
/**
 * Write a description of class CPUPaddle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CPUPaddle
{
    // instance variables - replace the example below with your own
    private int x;
    private final int PAD_WIDTH, PAD_HEIGHT,  BALL_DIAM;
    private final double PAD_SPEED;
    
    private double y, yVel;
    Rectangle cpuPaddle;

    /**
     * Constructor for objects of class CPUPaddle
     */
    public CPUPaddle(int SCREEN_HEIGHT, int SCREEN_WIDTH)
    {
        PAD_WIDTH = SCREEN_WIDTH/70;
        PAD_HEIGHT = SCREEN_HEIGHT/6;
        BALL_DIAM = SCREEN_HEIGHT/25;
        y = (SCREEN_HEIGHT/2)-PAD_HEIGHT/2;
        x = SCREEN_WIDTH - PAD_WIDTH;
        PAD_SPEED = 25;
        cpuPaddle = new Rectangle(x, (int)y, PAD_WIDTH, PAD_HEIGHT);
    }

    public void draw( Graphics g) {
        g.setColor(Color.white);
        g.fillRect ( x, (int)y , PAD_WIDTH, PAD_HEIGHT );
    }
    
    public void move(Ball[] balls, double delta,int SCREEN_HEIGHT,int ballCt) { 
        boolean findBall = true;
        int whichBall = 0;
        while((findBall) && (ballCt > 1)  ) {               
                if (whichBall == 2) {
                    findBall=false;}           
                else if ((x - balls[whichBall].getX()) > (x - balls[whichBall+1].getX())) {
                    whichBall +=1; }                      
                else { 
                findBall = false;
            }      
        }
        
        if ( y + PAD_HEIGHT/2 < balls[whichBall].getY() + BALL_DIAM/2 ) 
            y += PAD_SPEED * delta;
        
        else if ( y + PAD_HEIGHT/2 > balls[whichBall].getY() + BALL_DIAM/2 ) 
            y -= PAD_SPEED * delta;
            
    
            
                    
                
        
    }
    
    public Rectangle getCpuPad() {
        return cpuPaddle;
    }
    
    public double getSpeed() {
        return PAD_SPEED;
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
}
