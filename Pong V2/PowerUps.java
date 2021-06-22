import java.util.*;
import java.awt.geom.Ellipse2D;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.*;
/**
 * Write a description of class PowerUps here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PowerUps
{
    // instance variables - replace the example below with your own
    private double x,y;
    private int powerId, height, width, minSpeed, maxSpeed;
    Rectangle bounds;
    Random rand;
    Timer timer;
    TimerTask task;
    boolean active = false;
    class myTask extends TimerTask {
            private final int ID;
            Ball[] balls;
            boolean active;
            myTask(int ID,Ball[] balls,boolean active) {
                this.ID = ID;
                this.balls = balls;
                this.active = active;
            }
            public void run() {
                deactivate(ID,balls);
            }
        };
    /**
     * Constructor for objects of class PowerUps
     */
    public PowerUps(Ball[] ball,int SCREEN_WIDTH,int SCREEN_HEIGHT)
    {
        // initialise instance varia
        rand = new Random();
        powerId = rand.nextInt(3) +1;
        int size = ball[0].getBallDiam();
        height = SCREEN_HEIGHT;
        width = SCREEN_WIDTH;
        x = rand.nextInt(SCREEN_WIDTH-SCREEN_WIDTH/6)+SCREEN_WIDTH/12 ;
        y = rand.nextInt(SCREEN_HEIGHT- size) ;
        bounds = new Rectangle((int)x,(int)y,size,size);
        timer = new Timer();
        
        
        //Default Values
        minSpeed = SCREEN_HEIGHT/140;
        maxSpeed = SCREEN_HEIGHT/85;
    }
    
    public Rectangle getBounds() {
        return bounds;
    }
    public void spawnPowerUp(int SCREEN_WIDTH, int SCREEN_HEIGHT, Ball ball) {
        rand = new Random();
        powerId = rand.nextInt(3) +1;
        int size = ball.getBallDiam();
        x = rand.nextInt(SCREEN_WIDTH-SCREEN_WIDTH/6)+SCREEN_WIDTH/12 ;
        y = rand.nextInt(SCREEN_HEIGHT- size) ;
        bounds = new Rectangle((int)x,(int)y,size,size);
    }
    
    public void activate(Ball ball, Ball[] balls,int SCREEN_WIDTH,int SCREEN_HEIGHT) {
        if (powerId == 1 && active == false)
            this.speedBall(balls,SCREEN_WIDTH,SCREEN_HEIGHT);
        else if (powerId == 2)
            this.multiBall(balls);
        else if ( powerId == 3)
            this.slowBall();
    }
    
    public void deactivate(int ID,Ball[] balls) {
        if (ID == 1) {
            for (int i = 1;i<=Game.getBallCt();i++)
                balls[i-1].setSpeed((int)minSpeed,(int)maxSpeed,maxSpeed);
            for (int i = 1;i<=Game.getBallCt();i++)
                balls[i-1].setMultiplier(1.0);
            active = false;
        }
        if (ID == 2) {
            balls[1] = null;
            balls[2] = null;
            Game.setBallCt(1);
        }
    }
    
        public void speedBall(Ball[] balls,int SCREEN_WIDTH,int SCREEN_HEIGHT) {    
                for (int i = 1;i<=Game.getBallCt();i++)
                    balls[i-1].setSpeed((int)(minSpeed*2),(int)(maxSpeed*1.5),(int)(maxSpeed*1.4));
                for (int i = 1;i<=Game.getBallCt();i++)
                    balls[i-1].setMultiplier(2.0);
                active = true;
                System.out.print("I was ran");
                timer.schedule(new myTask(1,balls,active),20000);
    }
    
    public void multiBall(Ball[] balls) {
        if (Game.getBallCt() == 1) {
            balls[1] = new Ball(height,width);
            balls[2] = new Ball(height,width);
            Game.setBallCt(3);
            timer.schedule(new myTask(2,balls,false),20000);
        }
    }
    
    public void slowBall() {
        
    }
    
    
 
    public void draw( Graphics g,int size) {
        if (powerId == 1)
            g.setColor(Color.blue);
        else if (powerId == 2)
            g.setColor(Color.red); 
        else if (powerId == 3)
            g.setColor(Color.green); 
        g.fillRect((int)x,(int)y,size,size);
        
        if (active) {
            g.setColor(Color.orange); 
            g.fillRect((int)50,(int)50,size,size);
        }
            
    }
    
}
