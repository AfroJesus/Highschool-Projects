import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.*;
/**
 * Write a description of class Pong here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 *
 */
public class Pong extends Frame implements KeyListener
{
    // instance variables - replace the example below with your own
    private boolean rKey,lKey,uKey,dKey,gameOver,quitGame,gameIsRunning,p1Win,p2Win,playing;
    
    private final int SCREEN_HEIGHT,SCREEN_WIDTH,PAD_SPEED,BALL_SPEED,P1_GOAL,P2_GOAL,PAD_WIDTH,PAD_HEIGHT,BALL_MID;
    private int i,y,ballX,ballY,p1PadY,p2PadY,p1Score,p2Score,ballSize,xVelocity,yVelocity;
    BufferStrategy  bs;
    
    Timer gameLoop;
    
    public void gameLoop() {
        long lastLoopTime = System.nanoTime();
        final int FPS = 60;
        final long OPTIMAL_TIME = 1000000000/ FPS;
        
        while(gameIsRunning) {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / ((double)OPTIMAL_TIME);
            
            
            update(delta);
            repaint();
            try{Thread.sleep( (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000); } catch( Exception e ) { }
            
        }
    }
    
    /**
     * Constructor for objects of class Pong
     */
    public Pong()
    {
        // initialise booleans
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment( );
        GraphicsDevice gd = ge.getDefaultScreenDevice ( );
        Toolkit tk = Toolkit.getDefaultToolkit( );
        
        
        //initializes booleans
        rKey = lKey = uKey = dKey = quitGame = p1Win = p2Win = false;
        gameIsRunning = true;
        
        //hide cursor
        BufferedImage blankImg = new BufferedImage ( 1, 1, BufferedImage.TRANSLUCENT );
        setCursor ( tk.createCustomCursor ( blankImg, new Point ( 0, 0 ), "" ) );
        //createBufferStrategy(2);
        //bs = this.getBufferstrategy();
        
        //gamewindow config
        setUndecorated(true);
        gd.setFullScreenWindow(this);
        

        
        //gets screen size
        SCREEN_WIDTH = getWidth();
        SCREEN_HEIGHT = getHeight();
        P1_GOAL = 0;
        P2_GOAL = SCREEN_WIDTH;
        
        // initializes ball
        ballX = SCREEN_WIDTH/2;
        ballY = SCREEN_HEIGHT/2;
        ballSize = SCREEN_HEIGHT / 25;
        BALL_MID = ballY + ballSize/2;
        BALL_SPEED = 10;
        xVelocity = BALL_SPEED;
        yVelocity = 0;
        y=0;
        
        //paddle config
        PAD_SPEED = 7;
        PAD_WIDTH = SCREEN_WIDTH/30;
        PAD_HEIGHT = SCREEN_HEIGHT/6;
        
        p1PadY = SCREEN_HEIGHT/2 - (SCREEN_HEIGHT/6)/2;
        p2PadY = SCREEN_HEIGHT/2 - (SCREEN_HEIGHT/6)/2;
       
        
        //key listener
        addKeyListener (this);
    }
    
    
    public void dispose ()
    {
        // Destroy game window
        super.dispose ( );
    }
    
    public void paint ( Graphics g )
    {
        
        setBackground ( Color.black );
        //while ( playing = true ) {
            g.fillRect ( -1, -1, 2000, 2000 );
            g.setColor ( Color.white );
            g.fillOval ( ballX, ballY, ballSize, ballSize );
            g.fillRect ( P1_GOAL, p1PadY , PAD_WIDTH, PAD_HEIGHT );
            g.fillRect ( SCREEN_WIDTH - PAD_WIDTH, p2PadY , PAD_WIDTH, PAD_HEIGHT );
            g.drawString ( Integer.toString(p1Score), 50, 50 );
            g.drawString ( Integer.toString(p2Score), SCREEN_WIDTH-50, 50 );
            for (i=0; i < 6;i++) {            
                g.drawLine ( SCREEN_WIDTH/2, y, SCREEN_WIDTH/2, y+25);
                y = y + 50;
            }
        //}
        //while (p1Win = true) {
        //    g.drawString ("Congrats you win!!!",SCREEN_WIDTH/2,SCREEN_HEIGHT/2);
        //}
        //while (p2Win = true) {
        //    g.drawString ("You Lost",SCREEN_WIDTH/2,SCREEN_HEIGHT/2);
        //}
    }
  
    public boolean update(double delta)
    
    {   
        
       if (quitGame) return false;
        
       if ( uKey ) p1PadY = p1PadY +PAD_SPEED;
       if ( dKey ) p1PadY = p1PadY -PAD_SPEED;
        
       if ( ballY < 0 ) yVelocity = BALL_SPEED;
       if ( ballY > SCREEN_HEIGHT ) yVelocity = -BALL_SPEED;
       if ( p1PadY + PAD_HEIGHT > SCREEN_HEIGHT ) p1PadY = SCREEN_HEIGHT - PAD_HEIGHT;
       if ( p1PadY < 0 ) p1PadY = 0;
       
      // if ( p1Score == 5 ) {
       //    playing = false;
         //  p1Win = true;
        //}
       //if ( p2Score == 5 ) {
       //    playing = false;
       //    p2Win = true;
       // }
           
       
       ballX += xVelocity;
       ballY += yVelocity; 
       
       if ( ballX > P1_GOAL && ballX < P1_GOAL + PAD_WIDTH )
       {
           if ( ballY > p1PadY && ballY < p1PadY + PAD_HEIGHT )
           {
               if ( ballY > p1PadY + PAD_HEIGHT/2 )
               {
                   yVelocity = BALL_SPEED;
                   xVelocity = -xVelocity;
                }
               else
               {
                   yVelocity = -BALL_SPEED;
                   xVelocity = -xVelocity;
                }
           }
       }
    
       
       if (ballX < P2_GOAL && ballX + ballSize > P2_GOAL - PAD_WIDTH )  
       {
           if ( ballY > p2PadY && ballY  < p2PadY + PAD_HEIGHT )
           {
               if ( ballY > p1PadY + PAD_HEIGHT/2 )
               {
                   yVelocity = BALL_SPEED;
                   xVelocity = -xVelocity;
                }
               else
               {
                   yVelocity = -BALL_SPEED;
                   xVelocity = -xVelocity;
                }
                
           }
       }
        
       if ( ballX < P1_GOAL ) 
       {
           p2Score = p2Score + 1;         
           xVelocity = -xVelocity;
           ballX = SCREEN_WIDTH/2;
           ballY = SCREEN_HEIGHT/2;
        }
           
           
       if ( ballX + ballSize > P2_GOAL )
       {
           p1Score = p1Score + 1;
           xVelocity = -xVelocity;
           ballX = SCREEN_WIDTH/2;
           ballY = SCREEN_HEIGHT/2;
        }
       
        
       
     
      return true;
        
        
    }
     
    
    public void keyReleased ( KeyEvent e )
    {
        int keyCode = e.getKeyCode ( );
        
        //update key state vairables
        if ( keyCode == KeyEvent.VK_UP ) dKey = false;
        if ( keyCode == KeyEvent.VK_DOWN ) uKey = false;
    }
    
    public void keyPressed (KeyEvent e ) 
    {
        int keyCode = e.getKeyCode ( );
        
        if ( keyCode == KeyEvent.VK_ESCAPE )
        {
            quitGame = true;
            //pong.dispose();
        }
        if ( keyCode == KeyEvent.VK_UP ) dKey = true;
        if ( keyCode == KeyEvent.VK_DOWN ) uKey = true;
    }
    
    public void keyTyped ( KeyEvent e )
    {
    }
    
    
    
    public static void main (String[]args)
    {
        Pong pong = new Pong( );
        pong.gameLoop();
            

        // Destroy game object
        pong.dispose();
    } 
   }

