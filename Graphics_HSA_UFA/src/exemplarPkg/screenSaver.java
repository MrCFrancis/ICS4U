package exemplarPkg;

// Run this
import exemplarPkg.hsa2.GraphicsConsole;   
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;


/**
***********************************************
 @Author : Brandon Macdonald
 @Last Modified:04/04/2016
 @Description: Creates a graphical screensaver that will do various graphics in order to change the pixels of the screen and look interesting
***********************************************
*/


public class screenSaver {
	//Code taken from https://stackoverflow.com/questions/5689676/how-can-i-get-the-screen-resolution-in-java
	//Used to make the program fullScreen
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int width = (int) screenSize.getWidth();
	static int height = (int) screenSize.getHeight();
	//Holds the number of objects going around the screen
	static int numObjects = 4;
	
	
	/* 
	 Name of method: startStars
	 Parameters: star array, colour array, containing the stars and the colours of those stars
	 Returns: None, it creates 3 stars in starting format with colours
	 Dependencies: none 
	 Last modified: 03/04/2016
	 Throws: None
	 */
	public static void startStars(star [] stars, colour [] colours){
		//Goes through and initializes the star objects and their colours
		for (int i = 0; i < numObjects - 1; i++){
			stars[i] = new star(100, 100, i);
			colours[i] = new colour(colour.randomNumber(255), colour.randomNumber(255), colour.randomNumber(255));
		}
	}
	
	
	
	public static void main(String[] args) throws InterruptedException {
		int counter = 0;	//Used as a loop counter
		int point1X = 0;	//Holds the x value of a point
		int point1Y = 0;	//Holds the y value of a point
		int stroke = 0;		//Holds the size of the stroke for a drawline
		
		GraphicsConsole c = new GraphicsConsole(width, height);		//Graphics console everything is done on
		Color backgroundColour = Color.black;	//Sets the background to black
		colour[] pinkFloyd = new colour[7]; 	//Holds all the colours for the final pink floyd drawing
		star [] stars = new star [numObjects];	//Holds the stars that move around
		colour[] colours = new colour [numObjects];		//Holds the colours for the stars and the rectangle
		startStars(stars, colours);		//Initializes the stars
		
		//Creates the 7 colours used for the prism
		pinkFloyd[0] = new colour(255,0,0);
		pinkFloyd[1] = new colour(255,165,0);
		pinkFloyd[2] = new colour(255,255,0);
		pinkFloyd[3] = new colour(0,255,0);
		pinkFloyd[4] = new colour(0,0,255);
		pinkFloyd[5] = new colour(75,0,130);
		pinkFloyd[6] = new colour(208, 32, 144);
		colours[numObjects - 1] = new colour (0,0,0);
				
		c.setBackgroundColor(backgroundColour);
		c.clear();
		
		//This infinite loop will keep on running the screensaver
		while (true){
			Thread.sleep(7);	//So the program is visible
			switch (stars[0].getStage()){
			
			//Calls the starMove method to do all the star movement across the screen
			case 0:
				for (int i = 0; i < numObjects - 1; i++){
					stars[i].starMove();
					c.setColor(colours[i].getColour());
					c.drawStar(stars[i].getX(), stars[i].getY(), stars[i].getWidth(), stars[i].getHeight());
					colours[i].changeColour();
				}
				break;
				
			//Starts a rectangle in the middle of the screen, expands it while changing its colour
			case 1:
				colours[numObjects - 1].changeColour();
				c.setColor(colours[numObjects - 1].getColour());
				counter++;
				c.drawRect(width/2 - counter/2, height/2 - counter/2, counter, counter);
				if(counter == width + 100){
					counter = 0;
					stars[0].stageChange(1);
				}
				break;
				
			//Takes a white rectangle around the outside and slowly brings it inwards changing its colour
			case 2:
				colours[numObjects - 1].changeColour();
				c.setColor(colours[numObjects - 1].getColour());
				counter++;
				c.drawRect(counter/2, counter/2, width - counter, height - counter);
				if(counter == width){
					counter = 0;
					stars[0].stageChange(1);
					colours[numObjects - 1].setColour(Color.WHITE);
				}
				break;
			
			//Creates another rectangle in the middle of the screen and expands it
			case 3:
				colours[numObjects - 1].changeColour();
				c.setColor(colours[numObjects - 1].getColour());
				counter++;
				c.drawRect(width/2 - counter/2, height/2 - counter/2, counter, counter);
				if(counter == width + 100){
					counter = 0;
					stars[0].stageChange(1);
					colours[numObjects - 1].setColour(Color.BLACK);
					c.setColor(colours[numObjects - 1].getColour());
				}
				break;
				
			//Covers the screen in black
			case 4:
				c.fillRect(width/2 - counter/2, height/2 - counter/2, counter, counter);
				counter++;
				if (counter == width + 300){
					counter = 0;
					c.clear();
					c.setBackgroundColor(backgroundColour);
					colours[numObjects - 1].setColours(0,0,0);
					colours[numObjects - 1].setColour(new Color (0,0,0));
					c.setStroke(5);
					stars[0].stageChange(1);
				}
				break;
				
			//Draws a white triangle in the middle of the screen
			case 5:
				c.setColor(colours[numObjects - 1].getColour());
				c.drawLine(width/2, height/2 - 100, width/2 + 100, height / 2 + 100);
				c.drawLine(width/2, height/2 - 100, width/2 - 100, height / 2 + 100);
				c.drawLine(width/2 - 100, height/2 + 100, width/2 + 100, height / 2 + 100);
				colours[numObjects - 1].gradualChange(1);
				counter++;
				if (counter == 255){
					stars[0].stageChange(1);
					counter = 0;
					c.setStroke(7);
					point1X = (width/2 - 50) - 900;
					point1Y = (height/2 - 50) + 350;
				}
				break;
			
			//Draws a white line leading into the triangle
			case 6:
				c.drawLine(point1X, point1Y, point1X + (3 * counter), point1Y - counter);
				counter++;
				if (counter == 300){
					stars[0].stageChange(1);
					point1X += 900;
					point1Y -= 300;
					counter = 0;
				}
				break;
				
			//Goes from one side of the triangle to the other in a spread out pattern
			case 7:
				c.setColor(colours[numObjects - 1].getColour());
				c.drawLine(point1X, point1Y, point1X + 1, point1Y);
				point1X ++;
				counter ++;
				if (counter < 85){
					colours[numObjects - 1].gradualChange(-3);	
				}
				c.setStroke(7 + counter/5);
				if (counter == 100){
					stars[0].stageChange(1);
					c.setColor(Color.WHITE);
					stroke = 5;
					c.setStroke(stroke);
					c.drawLine(width/2, height/2 - 100, width/2 + 100, height / 2 + 100);
					System.out.println(point1X + "ppointx" + height/2 + "ppointY");
					counter = 0;
				}
				break;
				
			//Draws the rainbow spread from the triangle to the other edge of the screen
			case 8:
				c.setStroke(stroke);
				for(int i = 0; i < 3; i++){
					c.setColor(pinkFloyd[i].getColour());
					c.drawLine(point1X, height/2, width/2 + 50 + counter, height/2 + ((-1*(counter / 45)) + i*(2*(counter/45))));
					c.setColor(pinkFloyd[6 - i].getColour());
					c.drawLine(point1X, height/2, width/2 + 50 + counter, height/2 + (11 * (counter / 45) - i*(2*(counter/45))));
				}
				c.setColor(pinkFloyd[3].getColour());
				c.drawLine(point1X, height/2, width/2 + 50 + counter, height/2 + (counter / 9));
				counter ++;
				if (counter % 30 == 0){
					stroke++;
				}
				if (counter == width/2){
					Thread.sleep(2000);
					counter = 0;
					stars[0].stageChange(1);
					colours[numObjects - 1].setColour(Color.BLACK);
					c.setColor(colours[numObjects - 1].getColour());
				}
				break;
				
			//Goes back to the start
			case 9:
				c.fillRect(width/2 - counter/2, height/2 - counter/2, counter, counter);
				counter++;
				if (counter == width + 300){
					counter = 0;
					c.clear();
					c.setBackgroundColor(backgroundColour);
					startStars(stars, colours);
				}
				break;
			}
		}
	}
}
