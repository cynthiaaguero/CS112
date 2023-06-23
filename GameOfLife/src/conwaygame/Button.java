package conwaygame;
/*
 * Class used to create Buttons to be used on each page of the driver.
 */
import java.awt.Color;

public class Button extends Rectangle {
    public String name;  // Name of the button

    public Button(int x, int y, int halfWidth, int halfHeight, String name, boolean filled) {
        super(x, y, halfWidth, halfHeight, filled);  // Call the constructor of the parent class
        this.name = name;  // Set the name of the button
    }

    public void draw() {
        super.draw();  // Call the draw method of the parent class to draw the button rectangle
        Color tmp = StdDraw.getPenColor();  // Store the current pen color
        StdDraw.setPenColor(StdDraw.BLACK);  // Set the pen color to black
        StdDraw.text(x, y, name);  // Draw the text of the button at the center
        StdDraw.setPenColor(tmp);  // Restore the previous pen color
    }
}
