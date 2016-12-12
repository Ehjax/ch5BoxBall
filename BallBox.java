import java.awt.*;
import java.awt.geom.*;
import java.util.Random;
import java.awt.geom.Rectangle2D;

/**
 * Write a description of class BallBox here.
 * 
 * @author Ben Fasinski 
 * @version 1.0
 */
public class BallBox
{
    // instance variables - replace the example below with your own
    private Ellipse2D.Double circle;
    private Color color;
    private int xPosition;
    private int yPosition;
    private int diameter;
    private final int bottomWall;
    private final int topWall; 
    private final int leftWall;
    private final int rightWall;
    private Canvas canvas;
    private int ySpeed;
    private int xSpeed;
    private Random ballSpeed = new Random();
    private Random speedNegOrPos = new Random();
    private boolean negative;
    private boolean positive;
   

    /**
     * Constuctor for BallBox
     * @param topwall must match y value from BallDemo class.
     * @param leftwall must match x value from BallDemo class.
     * After the constructor sets the necessary parameters the constructor calls the randomSpeed() method to assign both a random speed and a positive or negative value.
     */
    public BallBox(int xPos, int yPos, int ballDiameter, int width, int height, Color ballColor, Canvas drawingCanvas)
    {
       diameter = ballDiameter;
       xPosition = xPos;
       yPosition = yPos;
       leftWall = 5;
       rightWall = width;
       bottomWall = height;
       topWall = 5;
       color = ballColor;
       canvas = drawingCanvas;
       negative = false;
       positive = false;
       randomSpeed();
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
     * randomSpeed Method randomly generates a speed from -6 to 6 not including 0 and also randomly assigns the speed as positive or negative. 
     * This way both variables don't have to have the same speed or direction.
     */
    private void randomSpeed()
    {
        int speed;
        int posNeg;
        int finished=0;
        while (finished<2){
            //Assign positive or negative to x.
             if (finished==0){
                posNeg = speedNegOrPos.nextInt(2);
                speed = ballSpeed.nextInt(6)+1;
                if (posNeg == 1)
                {
                    negative=true;
                    xSpeed += (-speed);
                    finished++;
                }
                else
                {
                    positive = true;
                    xSpeed += speed;
                    finished++;
                }   
            }  
            //Assign positive or negative to y.
             if (finished==1){
                posNeg = speedNegOrPos.nextInt(2);
                speed = ballSpeed.nextInt(6)+1;
                if (posNeg == 1)
                {
                    negative = true;
                    ySpeed += (-speed);
                    finished++;
                }
                else
                {
                    positive = true;
                    ySpeed += speed;
                    finished++;
                }   
            }
        }
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
            yPosition += ySpeed;
            xPosition += xSpeed;
            
            
             if (xPosition < leftWall) {
                xSpeed = -xSpeed;
            }
            
            if (xPosition > rightWall-diameter) {
                xSpeed = -xSpeed;
            }
            
            if (yPosition < topWall) {
                ySpeed = -ySpeed;
            }
            
            if (yPosition > bottomWall-diameter ) {
                ySpeed = -ySpeed;
            }
            
            // draw again at new position
            draw();
            drawRECTANGLE();
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
    
    /**
     * Method to redraw a rectangle after ball movements are drawn and the rectangle gets chipped away at. This method is called from the move method.
     */
     private void drawRECTANGLE()
    {
        Shape rectangle = (new Rectangle2D.Double(leftWall, topWall, rightWall, bottomWall));
        canvas.draw(rectangle);
    }
}
