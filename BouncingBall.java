import java.awt.*;
import java.awt.geom.*;

/**
 * Class BouncingBall - a graphical ball that observes the effect of gravity. The ball
 * has the ability to move. Details of movement are determined by the ball itself. It
 * will fall downwards, accelerating with time due to the effect of gravity, and bounce
 * upward again when hitting the ground.
 *
 * This movement can be initiated by repeated calls to the "move" method.
 * 
 * @author Michael Kölling (mik)
 * @author David J. Barnes
 * @author Bruce Quig
 *
 * @version 2011.07.31
 */

public class BouncingBall
{
    private static final int GRAVITY = 3;  // effect of gravity

    private int ballDegradation = 2;
    private Ellipse2D.Double circle;
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private final int bottomPosition; // y position of ground
    private final int leftPosition; 
    private final int rightPosition; 
    private final int topPosition; 
    private Canvas canvas;
    private int ySpeed = 3;                // initial downward speed
    private int xSpeed = 3;

    /**
     * Constructor for objects of class BouncingBall
     *
     * @param xPos  the horizontal coordinate of the ball
     * @param yPos  the vertical coordinate of the ball
     * @param ballDiameter  the diameter (in pixels) of the ball
     * @param ballColor  the color of the ball
     * @param groundPos  the position of the ground (where the wall will bounce)
     * @param drawingCanvas  the canvas to draw this ball on
     */
    public BouncingBall(int xPos, int yPos, int ballDiameter, Color ballColor,
                        int bottomPos, int leftPos, int rightPos, int topPos,
                        Canvas drawingCanvas)
    {
        xPosition = xPos;
        yPosition = yPos;
        color = ballColor;
        diameter = ballDiameter;
        bottomPosition = bottomPos;
        leftPosition = leftPos;
        rightPosition = rightPos;
        topPosition = topPos;
        canvas = drawingCanvas;
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
        
        yPosition += ySpeed;
        xPosition += xSpeed;

        // check if it has hit the ground
        if(yPosition >= (bottomPosition - diameter) && ySpeed > 0) {
            yPosition = (int)(bottomPosition - diameter);
            ySpeed = -ySpeed ; 
        }
        if(yPosition <= (topPosition) && ySpeed < 0) {
            yPosition = (int)(topPosition);
            ySpeed = -ySpeed ; 
        }
        if(xPosition <= (leftPosition) && xSpeed < 0) {
            xPosition = (int)(leftPosition);
            xSpeed = -xSpeed ; 
        }
        if(xPosition >= (rightPosition - diameter) && xSpeed > 0) {
            xPosition = (int)(rightPosition - diameter);
            xSpeed = -xSpeed ; 
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
