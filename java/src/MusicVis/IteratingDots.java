package MusicVis;
import processing.core.*;

public class IteratingDots {
    MyVisual mv;
    float cy = 0;
    float angle = 0;
    float colorOffset = 0;

    public IteratingDots(MyVisual mv) {
        this.mv = mv;
    }

    public void render() {
        int gridSize = 80;
        int width = mv.width;
        int height = mv.height;

        mv.colorMode(PApplet.HSB, 360, 100, 100); // Set color mode to HSB

        for (int x = gridSize; x <= width - gridSize; x += gridSize) {
            for (int y = gridSize; y <= height - gridSize; y += gridSize) {
                float r = mv.dist(x, y, width/2, height/2); // Distance from center
                float hue = (colorOffset + r) % 360; // Hue based on distance
                mv.fill(hue, 100, 100);
                
                mv.pushMatrix(); // Save current transformation matrix
                mv.translate(x, y); // Move origin to current dot position
                mv.rotate(angle); // Rotate based on angle
                mv.noStroke();
                mv.rect(x-1, y-1, 3, 3); // Draw rotated rectangle
                mv.stroke(hue, 100, 100, 100);
                mv.line(x-1, y-1, gridSize, 0); // Draw rotated line
                mv.popMatrix(); // Restore original transformation matrix
            } 
        }

        angle += 0.1; // Increment rotation angle
        colorOffset += 0.5; // Increment color offset
    }
}
