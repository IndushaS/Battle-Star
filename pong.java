// The "MovingBallApplet5" class.
import java.applet.*;
import java.awt.*;

public class MovingBallApplet5DownUp extends Applet implements Runnable
{
    // Place instance variables here
    // Initialization of variables
    int appletsize_x = 300;
    int appletsize_y = 200;

    int x_pos = appletsize_x / 2;         // x - Position of ball
    int y_pos = appletsize_y / 2;        // y - Position of ball

    int radius = 20;        // Radius of ball

    int x_speed = 1;
    int y_speed = 1;

    // declare two instance variables at the head of the program
    private Image dbImage;
    private Graphics dbg;

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


    public void stop ()
    {
	//nothing right now
    }


    public void destroy ()
    {
	//nothing right now
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
		x_speed = -1;

	    }
	    // Ball is bounced if its x - position reaches the left border of the applet
	    else if (x_pos < radius)
	    {

		// Change direction of ball movement
		x_speed = +1;

	    }

	    else if (x_pos < radius)
	    {

		// Change direction of ball movement
		x_speed = +1;

	    }
	    //if the ball is g
	    else if (y_pos > appletsize_y - radius)
	    {

		// Change direction of ball movement
		y_speed = -1;

	    }
	    else if (y_pos < radius)
	    {

		// Change direction of ball movement
		y_speed = +1;

	    }

	    x_pos = x_pos + x_speed; //update the current location of the x on the ball
	    y_pos = y_pos + y_speed; //update the current location of the y on the ball

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

	// initialize buffer
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


    // method to handle key - down events
    public boolean keyDown (Event e, int key)
    {

	// user presses left cursor key
	if (key == Event.LEFT)
	{
	    // changing x - speed so that ball moves to the left side (x_speed negative)
	    x_speed = -1;
	    y_speed = 0;
	}
	// user presses right cursor key
	else if (key == Event.RIGHT)
	{
	    // changing x - speed so that ball moves to the right side (x_speed positive)
	    x_speed = 1;
	    y_speed = 0;
	}
	// user presses space bar (value = 32!)
	else if (key == 32)
	{
	    // Stop ball (x_speed = 0)
	    x_speed = 0;
	    y_speed = 0;
	}

	else if (key == Event.DOWN)
	{
	    // Stop ball (x_speed = 0)
	    y_speed = 1;
	    x_speed = 0;

	}
	else if (key == Event.UP)
	{
	    // Stop ball (x_speed = 0)
	    y_speed = -1;
	    x_speed = 0;

	}
	else
	{
	    /* Additionally the method prints out the ASCII - value if an other key is pressed. This is not necessary but a possibility for you to test which value a key has.*/
	    System.out.println ("Character: " + (char) key + " Integer Value: " + key);
	}

	// DON'T FORGET (although it has no meaning here)
	return true;

    }


    public void paint (Graphics g)
    {
	// Place the body of the drawing method here

	// set color
	g.setColor (Color.red);

	// paint a filled colored circle
	g.fillOval (x_pos - radius, y_pos - radius, 2 * radius, 2 * radius);

    } // paint method
} // MovingBallApplet5 class
