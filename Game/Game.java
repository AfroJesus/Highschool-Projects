
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Game extends Frame implements KeyListener 
{
    // Instance variables
    private boolean quitGame, leftKey, rightKey, upKey, downKey;
    private int screenWidth, screenHeight, playerSize, playerX, playerY;

    /**
     * Constructor for objects of class Game
     */
    public Game()
    {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment ( );
        GraphicsDevice gd = ge.getDefaultScreenDevice ( );
        Toolkit tk = Toolkit.getDefaultToolkit ( );
        
        // Initialize boolean instance variables
        quitGame = leftKey = rightKey = upKey = downKey = false;

        // Hide mouse cursor
        BufferedImage blankImg = new BufferedImage ( 1, 1, BufferedImage.TRANSLUCENT );
        setCursor ( tk.createCustomCursor ( blankImg, new Point ( 0, 0 ), "" ) );

        // Configure game window
        setUndecorated ( true );
        gd.setFullScreenWindow ( this );
        
        // Detect screen size and calculate player size
        screenWidth = getWidth ( );
        screenHeight = getHeight ( );
        playerSize = screenHeight / 10;
        
        // Start with player in the middle
        playerX = screenWidth / 2;
        playerY = screenHeight / 2;

        // Activate keyboard input
        addKeyListener ( this );
    }
    
    public void dispose ( )
    {
        // Destroy game window
        super.dispose ( );
    }

    public void paint ( Graphics g )
    {
        int i;
        
        // Draw head
        for ( i = 0; i < 12; i++ ) {
            g.drawLine (
                playerX + (int) ( playerSize * Math.sin ( Math.PI * i / 6 ) / 4 ),
                playerY + (int) ( playerSize * Math.cos ( Math.PI * i / 6 ) / 4 ) - playerSize / 2,
                playerX + (int) ( playerSize * Math.sin ( Math.PI * ( i + 1 ) / 6 ) / 4 ),
                playerY + (int) ( playerSize * Math.cos ( Math.PI * ( i + 1 ) / 6 ) / 4 ) - playerSize / 2
            );
        }

        // Draw body
        g.drawLine ( playerX, playerY - playerSize / 4, playerX, playerY + playerSize / 4 );
        g.drawLine ( playerX, playerY - playerSize / 8, playerX - playerSize / 2, playerY );
        g.drawLine ( playerX, playerY - playerSize / 8, playerX + playerSize / 2, playerY );
        g.drawLine ( playerX, playerY + playerSize / 4, playerX - playerSize / 3, playerY + 3 * playerSize / 4 );
        g.drawLine ( playerX, playerY + playerSize / 4, playerX + playerSize / 3, playerY + 3 * playerSize / 4 );
    }
    
    public boolean update ( )
    {
        // Quit the game if quit variable was set
        if ( quitGame ) return false;
        
        // Adjust number of lines based on keys pressed
        if ( leftKey ) playerX -= playerSize / 4;
        if ( rightKey ) playerX += playerSize / 4;
        if ( upKey ) playerY -= playerSize / 4;
        if ( downKey ) playerY += playerSize / 4;
        
        // Limit player position to the screen
        if ( playerX < playerSize / 2 ) playerX = playerSize / 2;
        if ( playerX > screenWidth - playerSize / 2 ) playerX = screenWidth - playerSize / 2;
        if ( playerY < playerSize / 2 ) playerY = playerSize / 2;
        if ( playerY > screenHeight - playerSize / 2 ) playerY = screenHeight - playerSize / 2;
        
        // Instruct graphic system to repaint game window
        repaint ( );
        
        // Return true to continue game
        return true;
    }
    
    public void keyPressed ( KeyEvent e )
    {
        int keyCode = e.getKeyCode ( );
        
        // Update key state variables
        if ( keyCode == KeyEvent.VK_ESCAPE ) quitGame = true;
        if ( keyCode == KeyEvent.VK_LEFT ) leftKey = true;
        if ( keyCode == KeyEvent.VK_RIGHT ) rightKey = true;
        if ( keyCode == KeyEvent.VK_UP ) upKey = true;
        if ( keyCode == KeyEvent.VK_DOWN ) downKey = true;
    }
    
    public void keyReleased ( KeyEvent e )
    {
        int keyCode = e.getKeyCode ( );

        // Update key state variables
        if ( keyCode == KeyEvent.VK_LEFT ) leftKey = false;
        if ( keyCode == KeyEvent.VK_RIGHT ) rightKey = false;
        if ( keyCode == KeyEvent.VK_UP ) upKey = false;
        if ( keyCode == KeyEvent.VK_DOWN ) downKey = false;
    }
    
    public void keyTyped ( KeyEvent e )
    {
    }
    
    public static void main ( String[] args )
    {
        // Create game object
        Game game = new Game ( );
        
        // Update game state and redraw game window
        while ( game.update ( ) ) {
            // Sleep after each update to control animation speed
            try { Thread.sleep ( 50 ); } catch ( Exception e ) { }
        }

        // Destroy game object
        game.dispose ( );
    }
}
