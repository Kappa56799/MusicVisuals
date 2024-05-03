package c22376553;

import MusicVis.MyVisual;
import java.util.ArrayList;

public class Raindrops {
    MyVisual mv;
    ArrayList<Raindrop> raindrops;
    float angle = 0.349066f; // Angle in radians (20 degrees)

    public Raindrops(MyVisual mv) {
        this.mv = mv;
        raindrops = new ArrayList<>();
    }

    public void render() {
        mv.pushMatrix(); // Save the current transformation matrix
        // Draw raindrops
        drawRaindrops();
        mv.popMatrix(); // Restore the transformation matrix
    }

    void drawRaindrops() {

        for (int i = 0; i < 8; i++) { // Adjust the number of raindrops as needed
            float rx = mv.random(-500, mv.width + 100); // Random x position (extended beyond the screen)
            float ry = mv.random(-500, -20); // Random y position above the screen
            float rspeed = mv.random(2, 5); // Random falling speed
            float rlength = mv.random(6, 14); // Random length of raindrop

            raindrops.add(new Raindrop(rx, ry, rspeed, rlength)); // Add raindrop to the list
        }

        // Update and draw raindrops
        for (int i = raindrops.size() - 1; i >= 0; i--) {
            Raindrop drop = raindrops.get(i);
            drop.update();
            drop.display();
            if (drop.offScreen()) {
                raindrops.remove(i); // Remove raindrop if off the screen
            }
        }

    }

    // Inner class for raindrop
    class Raindrop {
        float x, y; // Position
        float speed; // Falling speed
        float length; // Length

        Raindrop(float x, float y, float speed, float length) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.length = length;
        }

        void update() {
            y += speed; // Move the raindrop down
            x += speed * mv.tan(angle); // Move the raindrop horizontally at an angle
        }

        void display() {
            mv.stroke(0, 0, 100); // Set random raindrop color
            mv.line(x, y, x, y + length); // Draw raindrop
        }

        boolean offScreen() {
            // Check if raindrop is off the screen
            return x < -500 || x > mv.width + 100 || y > mv.height + 100;
        }
    }
}
