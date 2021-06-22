import java.applet.Applet;
import java.awt.*;
import javax.swing.JOptionPane;
//import java.util.*;
/**
 * Calculates and graphs a user defined parabola. 
 * 
 * Cole Dennis 
 * 11/13/18
 */
public class Graph extends Applet
{
    String A,B,C;
    int a,b,c;
    public void init() {
        A = JOptionPane.showInputDialog("Enter the value of A: ");
        B = JOptionPane.showInputDialog("Enter the value of B: ");
        C = JOptionPane.showInputDialog("Enter the value of C: ");
        a = Integer.valueOf(A);
        b = Integer.valueOf(B);
        c = Integer.valueOf(C);
    }
    
   
    public void paint (Graphics page)
    {
        setBackground(Color.white);
        int xMax = getWidth();       //screen width
        int yMax = getHeight();      //screen height
        double unitSize = (int)Math.round((getWidth()/50));
        
        page.setColor(Color.black);
        double x = 0;
        do {
            page.drawLine(xMax/2+(int)x, -1, xMax/2+(int)x, yMax);
            page.drawLine(xMax/2-(int)x, -1, xMax/2-(int)x, yMax);        
            x  +=  unitSize;        
        }while ( x < xMax);     //less than right of screen
    
        double y = 0;
        do {
            page.drawLine(-1, yMax/2+(int)y, xMax,yMax/2+(int)y);
            page.drawLine(-1, yMax/2-(int)y, xMax,yMax/2-(int)y);
            y += unitSize;     
        }while ( y < yMax);    //less than bottom of screen
        
        page.setColor(Color.red);
        page.drawLine(-1, yMax/2, xMax, yMax/2);        //X axis
        page.drawLine(xMax/2, -1, xMax/2, yMax+1);      //y axis
       
        for (x = -10; x <= 10; x+=0.01) {
            y =((a*(x*x))+(b*x)+c);
            int h = (int)Math.round((-y * unitSize) + yMax/2);
            int w = (int)Math.round((x * unitSize) + xMax/2);
            page.fillOval (w-3, h-3, 6, 6);
        }
    }
}
