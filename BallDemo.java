import java.awt.Color;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Random;


/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 * 
 * @author Willen O. Leal
 * @version 2018.10.19
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class BallDemo   
{
    private Canvas myCanvas;
    ArrayList<BoxBall> bouncingBalls = new ArrayList<>();
    private int height = 500;
    private int width = 600;
            

    /**
     * @Constructor
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", width, height);
    }

    /**
     * Generates an array of bouncing balls with a number of balls between 5 and 25. Then in the loop
     * creates a new color for each ball for each iteration. Finally sets each ball in motion by iteracting
     * through the list infinitely
     */
    public void boxBounce(){
        Random rand = new Random();
        
        int ballsNum = rand.nextInt((25-5) + 1) + 5;;
     
        myCanvas.setVisible(true);
        
        int leftWall = 50;
        int topWall = 50;
        int rightWall = 550;
        int bottomWall = 450;
        
        myCanvas.drawLine(leftWall, bottomWall, rightWall, bottomWall);
        myCanvas.drawLine(leftWall, topWall, rightWall, topWall);
        myCanvas.drawLine(leftWall, topWall, leftWall, bottomWall);
        myCanvas.drawLine(rightWall, topWall, rightWall, bottomWall);
        
        for(int i = 0; i < ballsNum; i++)
        {
        
            
            int red = rand.nextInt(128);
            int green =  rand.nextInt(128);
            int blue = rand.nextInt(128);
            int diameter = rand.nextInt((25-10) + 1) + 10;
            int xPos = rand.nextInt(width);
            int yPos = rand.nextInt(height);
            
            Color newColor = new Color(red, green, blue);
            
            bouncingBalls.add(new BoxBall(xPos, yPos, diameter, newColor, myCanvas, bottomWall, topWall , leftWall, rightWall));
            bouncingBalls.get(i).draw();    
            
        }
        
        int index = 0;
        while(true)
        { 
          
          myCanvas.drawLine(leftWall, bottomWall, rightWall, bottomWall);
          myCanvas.drawLine(leftWall, topWall, rightWall, topWall);
          myCanvas.drawLine(leftWall, topWall, leftWall, bottomWall);
          myCanvas.drawLine(rightWall, topWall, rightWall, bottomWall);
          
          if(index < bouncingBalls.size())
          { 
            bouncingBalls.get(index).move();
            index++;
          }
          
          else
          {
            index = 0;
          }
          
          myCanvas.wait(1);
        }
        
        
        
    }
    
    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.drawLine(50, ground, 550, ground);

        // crate and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
}
