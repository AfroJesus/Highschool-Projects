import java.applet.Applet;
import java.awt.*;
/**
 * This program displays a drawing of a city street on the screen
 * 
 * Cole Dennis 
 * 10/5/18
 */
public class Pic extends Applet
{
    int i,h = 0;
    public void paint (Graphics page)
    {
       setBackground(new Color(0, 0, 50));
        
       page.setColor(Color.green);
       page.fillRect (-1, 550, 1400, 300);     //grass
        
       page.setColor(Color.gray);
       page.fillRect (25, 100, 200, 450);            //buildings
       page.fillRect (300, 200, 200, 350);
       page.setColor(new Color(150 ,30 ,40));        
       page.fillRect (600, 350, 400, 200);
        
       page.setColor(Color.black);
       page.fillRect (-1, 570, 1400, 200);     //road
        
       page.setColor( new Color(180, 180, 255));
       int x = -50;
       for (h=0; h < 2; h++)
       {
            x = x + 100;
            int y = 125;
            for (i=0; i < 4; i++)
            {
                page.fillRect (x, y, 50, 50);        //   windows
                y = y + 100;
            }
       }
       
       x = 225;
       for (h=0; h < 2; h++)
       {
            x = x + 100;
            int y = 225;
            for (i=0; i < 3; i++)
            {
                page.fillRect (x, y, 50, 50);         //windows
                y = y + 100;
            }
       }
       page.fillOval ( 650, 400, 80, 80 );
       page.fillOval ( 875, 400, 80, 80 );
       
       page.setColor(Color.yellow);
       x = 0;
       for (i = 0; i < 8;i++)
       {
           page.fillRect (x, 625 , 100, 10);    //road markings
           x = x + 200;
       }
       
       page.setColor(new Color( 0, 100, 0 ));
       int arrayX[] = {600, 600, 1000 };               //roof
       int arrayY[] = {350, 200, 350 };
       page.fillPolygon( arrayX, arrayY, 3 );
             
       page.setColor(new Color( 150, 150, 0 ));
       page.fillRect ( 775, 470 , 50, 80 );
       page.fillRect ( 105, 480 , 40, 70 );             //doors
       page.fillRect ( 380, 480 , 40, 70 );
                                     
       page.setColor(Color.white);
       int arrayX2[] = { 1150, 1200, 1235, 1250, 1250, 1235, 1200, 1150, 1175, 1195, 1210, 1210, 1195, 1175 };
       int arrayY2[] = { 65, 95, 130, 170, 210, 250, 290, 310, 275, 250, 210, 170, 130, 105 };                       //moon
       page.fillPolygon( arrayX2, arrayY2, 14 );        
       
       
   }
}
        
