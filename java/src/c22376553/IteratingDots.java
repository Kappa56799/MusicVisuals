package c22376553;

import MusicVis.MyVisual;

public class IteratingDots {
    MyVisual mv;
    float cy = 0;
    float angle = 0;
    float colorOffset = 0;

    public IteratingDots(MyVisual mv) {
        this.mv = mv;
    }

    public void render() {
        int gridSize = 50;
        int width = mv.width;
        int height = mv.height;

        mv.colorMode(mv.HSB, 360, 100, 100); // Set color mode to HSB

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
                
                // Add some cool animations
                float sizeOffset = mv.map(mv.sin(angle), -1, 1, -10, 10); // Size offset based on sine wave
                mv.ellipse(x, y, 10 + sizeOffset, 10 + sizeOffset); // Changing size with sine wave
                
                // Apply bouncing effect to specific elements
                if (x % (gridSize * 3) == 0 && y % (gridSize * 3) == 0) {
                    float bounceOffsetX = mv.sin(angle * 2) * 20; // Horizontal bounce offset
                    float bounceOffsetY = mv.cos(angle * 2) * 20; // Vertical bounce offset
                    mv.translate(bounceOffsetX, bounceOffsetY); // Apply bounce offset
                    mv.fill(0, 0, 100, 20); // Semi-transparent fill
                    mv.rect(x, y, 50 + mv.random(10), 50 + mv.random(10)); // Randomly changing size and shape
                }
                
                mv.popMatrix(); // Restore original transformation matrix
            } 
        }

        angle += 0.1; // Increment rotation angle
        colorOffset += 0.5; // Increment color offset
    }
}
