import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

/**
 * Class BoxBall - a graphical ball that moves at a constant rate in
 * the x and y axes and bounces off of 'walls' as defined by values passed in
 * to the BoxBall Constructor
 * 
 * This class  is a precursor to implementing Pong
 *
 * This movement can be initiated by repeated calls to the "move" method.
 * 
 * @author Willen O. Leal
 * @version 2018.10.19
 * 
 * @author Bill Crosbie
 * @version 2011.07.31
 */

public class BoxBall
{
    private static final int GRAVITY = 3;  // effect of gravity

    // private int ballDegradation = 2;
    private Ellipse2D.Double circle;
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private final int bottomWall;          // y position of bottom Wall
    private final int topWall;             // y position of top Wall
    private final int leftWall;            // x position of left Wall
    private final int rightWall;           // x position of right Wall
    private Canvas canvas;
    private Random randy = new Random();
    private int ySpeed = randy.nextInt((13-7) + 1) + 7;                // initial downward speed
    private int xSpeed = randy.nextInt((13-7) + 1) + 7;


    /**
     * Constructor for objects of class BoxBall
     *
     * @param xPos  the horizontal coordinate of the ball
     * @param yPos  the vertical coordinate of the ball
     * @param ballDiameter  the diameter (in pixels) of the ball
     * @param ballColor  the color of the ball
     * @param drawingCanvas  the canvas to draw this ball on the screen
     * @param height the height of the canvas
     * @param width the width of the canvas
     */
    public BoxBall(int xPos, int yPos, int ballDiameter, Color ballColor,
                        Canvas drawingCanvas, int bW,int tW, int lW, int rW)
    {
        xPosition = xPos;
        yPosition = yPos;
        color = ballColor;
        diameter = ballDiameter;
        canvas = drawingCanvas;
        leftWall = lW;
        rightWall = rW;
        topWall = tW;
        bottomWall = bW;
    }

    /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }

    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }    

    /**
     * Move this ball according to its position and speed and redraw.
     **/
    public void move()
    {
        // remove from canvas at the current position
        erase();
            
        // compute new position
        //ySpeed += GRAVITY;
        yPosition += ySpeed;
        xPosition += xSpeed;

        // check if it has hit the ground
//         if(yPosition >= (botoomWall - diameter) && ySpeed > 0) {
//             yPosition = (int)(groundPosition - diameter);
//             ySpeed = -ySpeed + ballDegradation; 
//         }

        // WALL CHECKS
        if (xPosition < leftWall ) {
            xPosition = leftWall;
            xSpeed = -xSpeed;
        }
        
        if(xPosition >= (rightWall - diameter)){
            xPosition = (int)(rightWall - diameter);
            xSpeed = -xSpeed;
        }
        
        if (yPosition < topWall ) {
            yPosition = topWall;
            ySpeed = -ySpeed;
        }
        
        if(yPosition >= (bottomWall - diameter)){
            yPosition = (int)(bottomWall - diameter);
            ySpeed = -ySpeed;
        }
        

        // draw again at new position
        draw();
    }    

    /**
     * return the horizontal position of this ball
     */
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * return the vertical position of this ball
     */
    public int getYPosition()
    {
        return yPosition;
    }
}
