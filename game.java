// The "game" class.
import java.applet.*;
import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.util.*;
import java.awt.Image;

public class game extends Applet implements Runnable
{
    // Place instance variables here
    // Initialization of variables
    int appletsize_x = 600;
    int appletsize_y = 400;

    int x_pos = appletsize_x / 2;         // x - Position of ball
    int y_pos = appletsize_y / 2;        // y - Position of ball

    int radius = 10;        // Radius of ball

    int x_speed = 4;
    
    int y_speed = 4;

    // declare two instance variables at the head of the program
    private Image dbImage;
    private Graphics dbg;
    
    int a,b,da;
    int c,d,dc;
    int p1 = 0;
    int p2 = 0;
    
    int paddleWidth = 100;
    int paddleHeight = 10;

    public game ()
    {

enableEvents(java.awt.AWTEvent.KEY_EVENT_MASK);
//requestFocus();

a = (appletsize_x + paddleWidth)/2; //bottom paddle location horizontally
b = appletsize_y - paddleHeight; //bottom paddle location vertically 
da = 10;

c = (appletsize_x + paddleWidth)/2; //top paddle horizontal location
d = 0; //top paddle verticle location
dc = 10;

Timer t = new Timer(true);
t.schedule(new java.util.TimerTask()
{
	public void run()
		{
		//doSomething();
		repaint();
		}
},0,30);
}
                
  
    public void processKeyEvent(KeyEvent e)
    {
        if ( e.getID() == KeyEvent.KEY_PRESSED )
        {
            if ( e.getKeyCode() == KeyEvent.VK_S && a < (appletsize_x - paddleWidth) )
            {
            a = a + da; 
            }
    
            if ( e.getKeyCode() == KeyEvent.VK_A && a>0 )
            {
            a = a - da;
            }
    
            if ( e.getKeyCode() == KeyEvent.VK_L && c < (appletsize_x - paddleWidth) )
            {
            c = c + dc; 
            }
    
            if ( e.getKeyCode() == KeyEvent.VK_K && c>0 )
            {
            c = c - dc;
            }
        }
    }
    
    public void init ()
    {
    // Place the body of the initialization method here
    setBackground (Color.blue);

    } // init method


    public void start ()
    {

    // define a new thread
    Thread th = new Thread (this);
    // start this thread
    th.start ();

    }

    public void run ()
    {

    // lower ThreadPriority
    Thread.currentThread ().setPriority (Thread.MIN_PRIORITY);

    // run a long while (true) this means in our case "always"
    while (true)
    {

        // Ball is bounced if its x - position reaches the right border of the applet
        if (x_pos > appletsize_x - radius)
        {

        // Change direction of ball movement
        x_speed = -4;

        }
        // Ball is bounced if its x - position reaches the left border of the applet
        else if (x_pos < radius)
        {

        // Change direction of ball movement
        x_speed = +4;

        }

        x_pos += x_speed;

        // repaint the applet
        repaint ();

        try
        {
        // Stop thread for 20 milliseconds
        Thread.sleep (20);
        }
        catch (InterruptedException ex)
        {
        // do nothing
        }
        
        if (y_pos > appletsize_y - radius)
        {

        // Change direction of ball movement
        y_speed = -4;

        }
        // Ball is bounced if its x - position reaches the left border of the applet
        else if (y_pos < radius)
        {

        // Change direction of ball movement
        y_speed = +4;

        }

        y_pos += y_speed;

        // repaint the applet
        repaint ();

        try
        {
        // Stop thread for 20 milliseconds
        Thread.sleep (20);
        }
        catch (InterruptedException ex)
        {
        // do nothing
        }

        // set ThreadPriority to maximum value
        Thread.currentThread ().setPriority (Thread.MAX_PRIORITY);
    }
    
    }
  

    /** Update - Method, implements double buffering */
    public void update (Graphics g)
    {

	if (dbImage == null)
	{
	    dbImage = createImage (this.getSize ().width, this.getSize ().height);
	    dbg = dbImage.getGraphics ();
	}

	// clear screen in background
	dbg.setColor (getBackground ());
	dbg.fillRect (0, 0, this.getSize ().width, this.getSize ().height);

	// draw elements in background
	dbg.setColor (getForeground ());
	paint (dbg);

	// draw image on the screen
	g.drawImage (dbImage, 0, 0, this);

    }



    public void paint (Graphics g)
    {
	// Place the body of the drawing method here
    Image myImage= getImage (getCodeBase(), "WdJLMuJ.jpg");
    g.drawImage(myImage,0,0, 1100, 600, this); 
	
    // set color
	g.setColor (Color.red);

	// paint a filled colored circle
	g.fillOval (x_pos - radius, y_pos - radius, 2 * radius, 2 * radius);
    

    g.setColor(Color.red);
    g.fillRect(a,b, paddleWidth, paddleHeight);

    g.setColor(Color.red);
    g.fillRect(c,d, paddleWidth,paddleHeight);
    

    } // paint method
} // MovingBallApplet4 class