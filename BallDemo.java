import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;


/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class BallDemo   
{
    private Canvas myCanvas;
    ArrayList<BoxBall> bouncingBalls = new ArrayList<>();

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    public void boxBounce(){
        Random rand = new Random();
        
        int ballsNum = rand.nextInt(10) + 1;
     
        myCanvas.setVisible(true);
        
        for(int i = 0; i < ballsNum; i++)
        {
        
            
            int red = rand.nextInt(128);
            int green =  rand.nextInt(128);
            int blue = rand.nextInt(128);
            int diameter = rand.nextInt(26);
            int xPos = rand.nextInt(600);
            int yPos = rand.nextInt(500);
            
            Color newColor = new Color(red, green, blue);
            
            bouncingBalls.add(new BoxBall(xPos, yPos, diameter, newColor, myCanvas));
            bouncingBalls.get(i).draw();    
            
        }
        
        while(true)
        {
            for(int j = 0; j < bouncingBalls.size(); j++)
            {
                if(bouncingBalls.size() < 5)
                {
                    myCanvas.wait(3);
                    bouncingBalls.get(j).move();
                }
                
                else if(bouncingBalls.size() > 5)
                {
                    myCanvas.wait(10);
                    bouncingBalls.get(j).move();
                
                }
            }
        
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
