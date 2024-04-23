package MusicVis;
import processing.core.*;

public class rainbow {
    MyVisual mv;
    float cy = 0;
    float angle = 1;
    float colorOffset = 0;

    public rainbow(MyVisual mv) {
        this.mv = mv;
    }

    public void render() {
        int gridSize = 50;
        int width = mv.width;
        int height = mv.height;

        mv.colorMode(PApplet.HSB, 360, 100, 100); // Set color mode to HSB

        for (int x = gridSize; x <= width - gridSize; x += gridSize) {
            for (int y = gridSize; y <= height - gridSize; y += gridSize) {
                float r = mv.dist(x, y, width/1, height/3); // Distance from center
                float hue = (colorOffset + r) % 360; // Hue based on distance
                mv.fill(hue, 100, 200);
                
                mv.pushMatrix(); // Save current transformation matrix
                mv.translate(x, y); // Move origin to current dot position
                mv.rotate(angle); // Rotate based on angle
                mv.noStroke();
                mv.rect(x-1, y-1, 30, 5); // Draw rotated rectangle
                mv.stroke(hue, 125, 100, 55);
                mv.line(x-1, y-1, gridSize, 9); // Draw rotated line
                mv.popMatrix(); // Restore original transformation matrix
            } 
        }

        angle -= 0.01; // Increment rotation angle
        colorOffset += 0.1; // Increment color offset
    }
}