import java.awt.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Bill Crosbie
 * @version 2015-March-BB
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class BallDemo   
{
    public Canvas myCanvas;
    private Rectangle2D theRectangle;
    private Graphics2D graphic;
    private ArrayList<BallBox> balls = new ArrayList<BallBox>();
    private Random randomLocation = new Random(300);
    private Random randomColor = new Random();
    private Random randomSize = new Random();
    
    /**
     * This method intitializes the canvas and also draws a rectangle with all of its walls five 
     * integers less then the dimension of the canvas.
     */
    public void setCanvas(int width, int height)
    {
        myCanvas = new Canvas("Box Bounce",width,height,Color.white);
        drawRECTANGLE();
    }
   
    /**
     * boxBounce randomly assigns 20 different balls color within a 
     * certain range to avoid transparent and white balls, it randomly assigns the diameter of the balls and their
     * location within the size of the rectangle that is drawn on the canvas. 
     */
    public void boxBounce(){
        if(myCanvas==null)
        {
            System.out.println("Please initialize setCanvas()");
        }
        else
            {
            /*
             * The code within the for loop does everything from randomly assigning color within a 
             * range to initializing the balls adding them to the array and setting their location
             * within the rectangle.
             */
            
            int colorIndex = 1;
            for(int i= 1; i<20; i++)
            {
                //Randomly assigns red, green, blue colors in the range of 0 to 255. 
                int r = randomColor.nextInt(255)+1;
                int g = randomColor.nextInt(255)+1;
                int b = randomColor.nextInt(255)+1;
                Random randomRgb = new Random();
                
                /*
                 * This conditional statement checks to see if the combined color color values for red
                 * green and blue exceeds 425 because as this value gets higher the balls become more 
                 * transparent.
                 */
                
                if((r+g+b)>425)
                {
                   /*
                    * The random number generate chooses a random combination of two of the rgb colors
                    * and divides their value by 5 so that the value is decreased minmizing the transparency.
                    */
                    int n = randomRgb.nextInt(3);
                    if(n==0)
                    {
                        r = r/5;
                        g = g/5;
                    }
                    
                    if(n==1)
                    {
                        g = g/5;
                        b = b/5;
                    }
                        
                    if(n==2)
                    {
                        b = b/5;
                        r = r/5;
                            
                    }
                }
                    
                Color colorSet = new Color(r,g,b);
                //Gets the size of the rectangle to draw the rectangle within.
                Dimension size = myCanvas.getSize();
                int width = size.width;
                int height = size.height; 
                
                width -= 10;
                height -= 10;
                
                int locationX;
                int locationY;
                
                int randomXLocation =  size.width;
                int randomYLocation = size.height;
                
                //creates balls with a random diameter from 12 to 35.
                int ballSize = randomSize.nextInt(24)+12;
                
                randomXLocation-=(10+ballSize);
                randomYLocation-=(10+ballSize);
                //The next line randomly assigns an x location to the ball starting at a minimum x
                //positin of 5 so the ball does not get trapped outside the leftwall of the rectangle.
                locationX = randomLocation.nextInt(randomXLocation)+5;
                //The next line randomly assigns a y location to the ball starting at a minimum y
                //position of 5 so the ball does not get trapped outside the topwall of the rectangle.
                locationY = randomLocation.nextInt(randomYLocation)+5;
                //Adds the balls to the balls array one at a time.
                balls.add(new BallBox(locationX, locationY, ballSize, width, height, colorSet, myCanvas));
            }
            //Draws the balls initial placement.
             for(int indexx = 0; indexx<balls.size(); indexx++)
             {
                BallBox ball=null;
                ball = balls.get(indexx);
                ball.draw();
            }
            /*
             * This whil loop controls the movement of the BallBoxs by redrawing them from the array
             * calling the move method from BallBox and redrawing the rectangle. Creates the illusion of
             * movement.
             */
             while(true){
                 myCanvas.wait(25);
                 for(int index = 0; index<balls.size(); index++)
                 {
                    BallBox ball;
                    ball = balls.get(index);
                    ball.move();
                }
            }
        }
    }
    
    /**
     * This method draws a rectangle that receives the dimensions of the canvas and then sets the walls
     * of the rectangle to be five less than the dimensions of the canvas.
     */
    private void drawRECTANGLE()
    {
        Dimension size = myCanvas.getSize();
        double x = 5;
        double y = 5;
        double widthToBeFixed = size.width;
        double heightToBeFixed = size.height;
        double width = widthToBeFixed-10.00;
        double height = heightToBeFixed-10.00;
        Shape rectangle = (new Rectangle2D.Double(x, y, width, height));
        myCanvas.draw(rectangle);
    }
    
  
}