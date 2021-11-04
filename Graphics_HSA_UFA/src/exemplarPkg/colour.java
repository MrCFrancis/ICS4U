package exemplarPkg;

import java.awt.Color;

public class colour {
	Color colour;	//Holds the actual colour
	int red;	//Red RGB value
	int green;	//Green RGB value
	int blue;	//Blue RGB value
	int change = 0;	//Holds a random number used to determine 
	boolean redUp;	//Holds whether the red value should go up or down
	boolean greenUp;	//Holds whether the green value should go up or down
	boolean blueUp;	//Holds whether the blue value should go up or down
	
	//Constructor that sets all variables
	colour(int x, int y, int z){
		red = x;
		green = y;
		blue = z;
		newColour();
		
		//Sets the starting direction of colour change for the three colours (to go from 0 to 255 or the opposite)
		if(x == 255){
			redUp = false;
		}
		else{
			redUp = true;
		}
		
		if(y == 255){
			greenUp = false;
		}
		else{
			greenUp = true;
		}
		
		if(z == 255){
			blueUp = false;
		}
		else{
			blueUp = true;
		}
	}

	//Gradualy changes a colour all three RGB values at once
	public void gradualChange(int delta){
		red += delta*2;
		blue += delta;
		green -= delta;
		newColour();
	}
	
	/* 
	 Name of method: changeColour
	 Parameters: none
	 Returns: None, it will randomly choose a RGB value of a colour and either increase or decrease it by 1
	 Dependencies: java.awt 
	 Last modified: 03/04/2016
	 Throws: None
	 */
	public void changeColour(){		
		change = randomNumber(3);
		switch(change){
		case 1:
			if (redUp == true ){
				red ++;
			}else{
				red --;
			}
			if (red == 255 || red == 0){
				redUp = !redUp;
			}
			break;
		case 2:
			if (greenUp == true ){
				green ++;
			}else{
				green --;
			}
			
			if (green == 255 || green == 0){
				greenUp = !greenUp;
			}
			break;
		case 3:
			if (blueUp == true ){
				blue ++;
			}else{
				blue --;
			}
			
			if (blue == 255 || blue == 0){
				blueUp = !blueUp;
			}
			break;
		}
		newColour();
	}
	
	//Setters and getters
	public void newColour(){
		this.colour = new Color (red, green, blue);
	}
	public static int randomNumber(int max){
		return (int) (Math.random() * max) +1;
	}
	
	public Color getColour() {
		return colour;
	}
	public void setColour(Color colour) {
		this.colour = colour;
	}
	public void setColours(int x, int y, int z){
		red = x;
		green = y;
		blue = z;
	}
}