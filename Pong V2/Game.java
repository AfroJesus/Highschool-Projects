import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;
import javax.swing.*;
import java.lang.*;
/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Game extends JFrame implements KeyListener
{
    // instance variables - replace the example below with your own
    private boolean gameIsRunning, menu, running, AI , quitGame, coop, p1Win, p2Win, check;
    public final int SCREEN_HEIGHT,SCREEN_WIDTH,MAX_POWERUPS, MAX_BALLS;
    private double delta;
    private int p1Score, p2Score, puNum; 
    public static int ballCt;
    PlayerPaddle p1;
    public static Game game;
    Ball[] balls;
    CPUPaddle cpu;
    Graphics gfx;
    BufferedImage screen;
    PowerUps[] powerup;
    Timer timer;
    TimerTask task;
    Random rand;
    /**
     * Constructor for objects of class Game
     */
    public Game()
    {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment( );
        GraphicsDevice gd = ge.getDefaultScreenDevice ( );
        Toolkit tk = Toolkit.getDefaultToolkit( );
        
        BufferedImage blankImg = new BufferedImage ( 1, 1, BufferedImage.TRANSLUCENT );
        setCursor ( tk.createCustomCursor ( blankImg, new Point ( 0, 0 ), "" ) );
  
        
        setUndecorated(true);
        gd.setFullScreenWindow(this);
        
        MAX_POWERUPS = 10;
        MAX_BALLS = 3;
        puNum = 0;
        ballCt = 1;
     
        gameIsRunning = coop = p1Win = p2Win = false;
        menu = running = check = true;
        SCREEN_HEIGHT=getHeight();
        SCREEN_WIDTH=getWidth();
        screen = new BufferedImage ( SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
        gfx = screen.getGraphics();
        
        //powerup = new PowerUps();
        powerup = new PowerUps[MAX_POWERUPS];
        rand = new Random();
        balls = new Ball[MAX_BALLS];
        balls[0] = new Ball(SCREEN_HEIGHT,SCREEN_WIDTH);
        task = new TimerTask() { 
            @Override
            public void run() {
                   if ( puNum < 10 ) {
                        powerup[puNum] = new PowerUps(balls,SCREEN_WIDTH,SCREEN_HEIGHT);
                        puNum++;
                        }
            }                    
        };
        timer = new Timer();
        p1 = new PlayerPaddle(SCREEN_HEIGHT,SCREEN_WIDTH,1);
        //ball = new Ball(SCREEN_HEIGHT,SCREEN_WIDTH);
        cpu = new CPUPaddle(SCREEN_HEIGHT,SCREEN_WIDTH);
        AI=true;
       
        addKeyListener (this);
    }
    
    public void gameLoop()
    {
        long lastLoopTime = System.nanoTime();
        final int FPS = 60;
        final long OPTIMAL_TIME = 1000000000/ FPS;
        while(running) {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            delta = updateLength / ((double)OPTIMAL_TIME);
            if (menu) {
                repaint();
            }
            if (gameIsRunning) {
                p1.move(delta,SCREEN_HEIGHT);
                if (AI = true)
                    cpu.move(balls,delta,SCREEN_HEIGHT,ballCt);
                else if (coop = true)
                    p1.move(delta,SCREEN_HEIGHT);
                
                for (int i = 1; i <= ballCt; i++) 
                    balls[i-1].move(delta,SCREEN_HEIGHT,SCREEN_WIDTH,i);                   
                
                
                if (AI = true)
                    for (int i = 1; i<= ballCt; i++) {
                        balls[i-1].checkCollison(p1,cpu,SCREEN_HEIGHT,SCREEN_WIDTH,delta,powerup,puNum,balls,ballCt);  
                    }
                else if (coop = true)
                    for (int i = 1; i<= ballCt; i++)
                        balls[i-1].checkCollison(p1,p1,SCREEN_HEIGHT,SCREEN_WIDTH,delta,powerup,puNum,balls,ballCt);
                
                for (int i = 1; i<= ballCt; i++)  {      
                    if (balls[i-1].getX() < 0-5 ) {
                        p2Score += 1;
                        balls[i-1].resetBall(-1,SCREEN_HEIGHT,SCREEN_WIDTH);
                    }
                    else if ( balls[i-1].getX() > SCREEN_WIDTH+5 ) {
                        p1Score += 1;
                        balls[i-1].resetBall(-1,SCREEN_HEIGHT,SCREEN_WIDTH);           
                    }        
                }
                
       
                
                if (p1Score == 15) {
                    p1Win = true;
                    gameIsRunning = false;
                }               
                else if (p2Score == 15) {
                    p2Win = true;
                    gameIsRunning = false;
                }
                    
                    
                repaint();
                
                
                
            }
            try{Thread.sleep( (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000); } catch( Exception e ) { }
        }
    }
    
    public void paint ( Graphics g )
    {
        gfx.setColor(Color.black);
        gfx.fillRect(0,0, SCREEN_WIDTH+1,SCREEN_HEIGHT+1);
        gfx.setColor(Color.white);
        for (int i = 0;i<=SCREEN_HEIGHT;i+=200)          
            gfx.drawLine(SCREEN_WIDTH/2,i,SCREEN_WIDTH/2,i+100);
        
        gfx.drawString(Integer.toString(p1Score), SCREEN_WIDTH/15, SCREEN_HEIGHT/20);
        gfx.drawString(Integer.toString(p2Score), SCREEN_WIDTH-SCREEN_WIDTH/15, SCREEN_HEIGHT/20);
        p1.draw(gfx);
        cpu.draw(gfx);
        
        for (int i = 1; i<= ballCt; i++)
            balls[i-1].draw(gfx,i);
            
        gfx.setColor(Color.white);
        
        if ( menu ) 
            gfx.drawString("Press \"enter\" to start match",SCREEN_WIDTH/2-SCREEN_WIDTH/20,SCREEN_HEIGHT/4);
        
        if (gameIsRunning)
            for (int i = 0; i < puNum; i++ )
                powerup[i].draw(gfx,SCREEN_HEIGHT/25);
            
        if ( AI ) {
            if ( p1Win )
                gfx.drawString("Congrats! You Win! Press ESC to reset",SCREEN_WIDTH/2-SCREEN_WIDTH/17,SCREEN_HEIGHT/4);
            else if ( p2Win )
                gfx.drawString("OOf! You Lose! Press ESC to reset",SCREEN_WIDTH/2-SCREEN_WIDTH/17,SCREEN_HEIGHT/4);
        }
        
        if ( coop ) {
            if ( p1Win )
                gfx.drawString("Player 1 Wins!",SCREEN_WIDTH/2-15,SCREEN_HEIGHT/4);
            else if ( p2Win )
                gfx.drawString("Player 2 Wins!",SCREEN_WIDTH/2-15,SCREEN_HEIGHT/4);
        }
         
        
        g.drawImage(screen, 0, 0, this);
            
          
    }
    
    public void keyReleased ( KeyEvent e )
    {
        int keyCode = e.getKeyCode ( );
        
        //update key state vairables
        if ( keyCode == KeyEvent.VK_UP )
            p1.setAcelUp(false);
        if ( keyCode == KeyEvent.VK_DOWN )
            p1.setAcelDown(false);
    }
    
    public void keyPressed (KeyEvent e ) 
    {
        int keyCode = e.getKeyCode ( );
        
        if ( keyCode == KeyEvent.VK_ENTER ) {
            if (menu) {
                //p1 = new PlayerPaddle(SCREEN_HEIGHT,SCREEN_WIDTH,1);
                //balls[0] = new Ball(SCREEN_HEIGHT,SCREEN_WIDTH);
                //cpu = new CPUPaddle(SCREEN_HEIGHT,SCREEN_WIDTH);
                menu = false;
                gameIsRunning = true;
                timer = new Timer();
                task = new TimerTask() { 
                    @Override
                    public void run() {
                        if ( puNum < 10 ) {
                            powerup[puNum] = new PowerUps(balls,SCREEN_WIDTH,SCREEN_HEIGHT);
                            puNum++;
                            }
                        }                    
                };
                timer.scheduleAtFixedRate(task, 1, 15000);
            }
        }
            
        if ( keyCode == KeyEvent.VK_ESCAPE )
        {
            menu = true;
            gameIsRunning = false;
            p1Win = false;
            p2Win = false;
            p1Score = 0;
            p2Score = 0;
            p1 = new PlayerPaddle(SCREEN_HEIGHT,SCREEN_WIDTH,1); 
            for (int i = 1; i<= ballCt; i++)
                balls[i-1] = null;
            balls[0] = new Ball(SCREEN_HEIGHT,SCREEN_WIDTH);
            for (int i = 1; i<=puNum;i++)
                powerup[i-1] = null;
            puNum = 0;
            ballCt = 1;
            cpu = new CPUPaddle(SCREEN_HEIGHT,SCREEN_WIDTH);
            Runtime.getRuntime().gc();
            timer.cancel();
            timer.purge();
        }
        if ( keyCode == KeyEvent.VK_UP )
            p1.setAcelUp(true);
        if ( keyCode == KeyEvent.VK_DOWN )
            p1.setAcelDown(true);
    }
    
    public void keyTyped ( KeyEvent e )
    {
    }
    
    public static void setBallCt (int count) {        
        ballCt = count;
    }
    public static int getBallCt() {        
        return ballCt;
    }
    
    
    public static void main(String[]args)
    {
        game = new Game();
        game.gameLoop();
        
        
    }
}
