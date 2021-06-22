import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.Ellipse2D;
/**
 * Write a description of class Ball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ball
{
    // instance variables - replace the example below with your own
    private final int BALL_DIAM;
    private boolean p1Hit,p2Hit;
    private double x,y, yVel,xVel,speed, minBallSpeed, maxBallSpeed,ySpeed,multiplier;
    //Ellipse2D.Double[] hitbox;
    Ellipse2D.Double ball;
    Point pos;
    Point oldPos;
    Point ballCenter;
    /**
     * Constructor for objects of class Ball
     */
    public Ball(int SCREEN_HEIGHT, int SCREEN_WIDTH)
    {
        // initialise instance variables
        BALL_DIAM = SCREEN_HEIGHT/25;
        y = SCREEN_HEIGHT/2-BALL_DIAM/2;
        x = SCREEN_WIDTH/2-BALL_DIAM/2;
        minBallSpeed = SCREEN_HEIGHT/140;
        maxBallSpeed = SCREEN_HEIGHT/85;
        //Ellipse2D.Double[] hitbox = new Ellipse2D.Double[3];
        ySpeed = SCREEN_HEIGHT/70;
        yVel = getRandSpeed() * getRandDirection();
        xVel = getRandSpeed() * getRandDirection();
        multiplier = 1;
    }
    
    public double getY() {
        return y;        
    }
    
    public void setSpeed(int min, int max,int yspee) {
        minBallSpeed = min;
        maxBallSpeed = max;
        ySpeed = yspee;
        xVel *= 2;
        yVel *= 2;
        
    }
    public  double getX() {
        return x;        
    }
    
    public int getBallDiam() {
        return BALL_DIAM;
    }
    
    public void setPos(int tempX, int tempY) {
        x = tempX;
        y = tempY;
    }
    public void setPos(Point old) {
        x = old.x;
        y = old.y;
    }
    
    public void resetBall(int direction,int SCREEN_HEIGHT,int SCREEN_WIDTH) {
        x = SCREEN_WIDTH/2-BALL_DIAM/2;
        y = SCREEN_HEIGHT/2-BALL_DIAM/2;
        yVel = getRandSpeed() * getRandDirection();
        xVel = xVel * direction;
    }
    
    public void draw( Graphics g,int i) {
        g.setColor(Color.red);
        g.fillOval((int)x, (int)y, BALL_DIAM, BALL_DIAM);
        g.setColor(Color.white);
        g.drawString(Double.toString(xVel),150+(i*300),150);
        g.drawString(Double.toString(yVel),150+(i*300),300);
    }   
    
    public void move(double delta,int SCREEN_HEIGHT,int SCREEN_WIDTH,int i ) {     
        oldPos = new Point((int)x,(int) y);  
        y += yVel;
        x += xVel;
        // = new Ellipse2D.Double(x, y, BALL_DIAM, BALL_DIAM);
        pos = new Point((int)x,(int) y);
        ballCenter = new Point((int)x+BALL_DIAM/2,(int)y+BALL_DIAM/2);
        if (y+BALL_DIAM > SCREEN_HEIGHT) yVel = -yVel;
        if (y < 0) yVel = -yVel;
        
            
        
       
        
        
        
    }
    
    public void checkCollison(Paddle p1,Paddle p2,int SCREEN_HEIGHT, int SCREEN_WIDTH, double delta, PowerUps[] powerup, int puNum, Ball[] balls,int ballCt) {
        for ( int i = 1;i <= ballCt; i++) {
            if ( ball.intersects( (double)p1.getX(), (double)p1.getY(), (double)p1.getPaddleWidth(), (double)p1.getPaddleHeight() )) {
                if ( ballCenter.x < p1.getX() + p1.getPaddleWidth()) {
                    setPos(oldPos);
                    yVel = -yVel;
                }
                else if (ballCenter.x > p1.getX() + p1.getPaddleWidth()) {
                    double curSpeed = Math.sqrt((xVel*xVel) + (yVel*yVel));
                    //double angle = ((y + BALL_DIAM/2) - (p1.getY() + p1.getPaddleHeight()/2))/(p1.getPaddleHeight() * 0.5236 + Math.PI/2);
                    double offset = (((y + BALL_DIAM/2) - (p1.getY() + p1.getPaddleHeight()/2))/(p1.getPaddleWidth() + BALL_DIAM/2));
                    double angle = offset * 0.7236 + Math.PI/2;
                    setPos(oldPos);
                    xVel = -Math.signum(xVel) * curSpeed * Math.sin(angle);
                    yVel = -(curSpeed * Math.cos(angle));
                }
            }
            if ( ball.intersects( (double)p2.getX(), (double)p2.getY(), (double)p2.getPaddleWidth(), (double)p2.getPaddleHeight() )) {        //right side paddle hitbox
                double curSpeed = Math.sqrt((xVel*xVel) + (yVel*yVel));
                double offset = (((y + BALL_DIAM/2) - (p2.getY() + p2.getPaddleHeight()/2))/(p2.getPaddleWidth() + BALL_DIAM/2));
                double angle = offset * 0.7236 + Math.PI/2;
                setPos(oldPos);
                xVel = -Math.signum(xVel) * curSpeed * Math.sin(angle);
                yVel = -(curSpeed * Math.cos(angle));            
            }
            if (xVel >= 0 && xVel < minBallSpeed ) xVel = minBallSpeed;
            else if ( xVel <= 0 && xVel > -minBallSpeed) xVel = -minBallSpeed;   //Min speed
        
            for (int j = 1; j <= puNum; j++ ) {
                if (ball.intersects(powerup[j].getBounds()) )    
                    powerup[j].activate(this, balls,SCREEN_WIDTH,SCREEN_HEIGHT);        
            }
        
            if (xVel > maxBallSpeed) xVel = maxBallSpeed;
            else if ( xVel < -maxBallSpeed) xVel = -maxBallSpeed;
        
            if (yVel > ySpeed) yVel = ySpeed;
            else if (yVel < -ySpeed) yVel = -ySpeed;
        }
    } 
    
    
    public void checkCollison(Paddle p1,CPUPaddle p2,int SCREEN_HEIGHT, int SCREEN_WIDTH, double delta,PowerUps[] powerup, int puNum, Ball[] balls,int ballCt) {                    
            //for ( int i = 1;i <= ballCt; i++) {
            ball = new Ellipse2D.Double(x, y, BALL_DIAM, BALL_DIAM);
            if ( this.ball.intersects( (double)p1.getX(), (double)p1.getY(), (double)p1.getPaddleWidth(), (double)p1.getPaddleHeight() )) {
                if ( ballCenter.x < p1.getX() + p1.getPaddleWidth()) {
                    setPos(oldPos);
                    yVel = -yVel;
                }
                else if (ballCenter.x > p1.getX() + p1.getPaddleWidth()) {
                    double curSpeed = Math.sqrt((xVel*xVel) + (yVel*yVel));
                    //double angle = ((y + BALL_DIAM/2) - (p1.getY() + p1.getPaddleHeight()/2))/(p1.getPaddleHeight() * 0.5236 + Math.PI/2);
                    double offset = (((y + BALL_DIAM/2) - (p1.getY() + p1.getPaddleHeight()/2))/(p1.getPaddleWidth() + BALL_DIAM/2));
                    double angle = offset * 0.7236 + Math.PI/2;
                    setPos(oldPos);
                    xVel = -Math.signum(xVel) * curSpeed * Math.sin(angle);
                    yVel = -(curSpeed * Math.cos(angle));
                }
            }
            if ( this.ball.intersects( (double)p2.getX(), (double)p2.getY(), (double)p2.getPaddleWidth(), (double)p2.getPaddleHeight() )) {        //right side paddle hitbox
                double curSpeed = Math.sqrt((xVel*xVel) + (yVel*yVel));
                double offset = (((y + BALL_DIAM/2) - (p2.getY() + p2.getPaddleHeight()/2))/(p2.getPaddleWidth() + BALL_DIAM/2));
                double angle = offset * 0.7236 + Math.PI/2;
                setPos(oldPos);
                xVel = -Math.signum(xVel) * curSpeed * Math.sin(angle);
                yVel = -(curSpeed * Math.cos(angle));            
            }
            if (xVel >= 0 && xVel < minBallSpeed ) xVel = minBallSpeed;
            else if ( xVel <= 0 && xVel > -minBallSpeed) xVel = -minBallSpeed;   //Min speed
        
            for (int j = 1; j <= puNum; j++ ) {
                if ( ball.intersects(powerup[j-1].getBounds()) )    {
                    powerup[j-1].activate(this, balls,SCREEN_WIDTH,SCREEN_HEIGHT);        
                }
            }
        
        
            if (xVel >= 0 && xVel < minBallSpeed ) xVel = minBallSpeed;
            else if ( xVel <= 0 && xVel > -minBallSpeed) xVel = -minBallSpeed;   //Min speed
        
            if (xVel > maxBallSpeed) xVel = maxBallSpeed;
            else if ( xVel < -maxBallSpeed) xVel = -maxBallSpeed;
        
            if (yVel > ySpeed) yVel = ySpeed;
            else if (yVel < -ySpeed) yVel = -ySpeed;
        
        }
    //}
    
    public int getRandDirection() {
        Random rand = new Random();
        int num = rand.nextInt(2);
        if ( num == 0)
            return 1;
        else if ( num == 1)
            return -1;  
        else
            return 0;
    }
    
    public int getRandSpeed() {
        Random rand = new Random();
        return rand.nextInt(5) + 10;
    }
    
    public void setMultiplier(double num) {
        multiplier = num;
    }

    
    
}
