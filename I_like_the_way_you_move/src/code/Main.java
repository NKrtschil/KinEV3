package code;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
import edu.ufl.digitalworlds.j4k.*;

public class Main {

	public static void main(String[] args) {
		LCD.drawString("heyho", 0, 4);
		Delay.msDelay(5000);
		
	}

}
