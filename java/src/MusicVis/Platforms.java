package MusicVis;
import processing.core.*;

public class Platforms {
    MyVisual mv;
    float platformX; // X position of the platform
    float platformHeight; // Height of the platform
    float gapWidth = 100; // Width of the gap in the platform
    boolean hasGap = false; // Flag to track whether a gap is present
    float speed = 20; // Speed of the platform movement

    public Platforms(MyVisual mv) {
        this.mv = mv;
        platformHeight = mv.height / 2 + 280; // Set platform height to the middle of the screen
        platformX = mv.width / 2; // Set initial X position to the middle of the screen
    }


    public void render() {
        mv.stroke(255); // Set stroke color to white
        mv.hint(mv.DISABLE_DEPTH_TEST);
            
        // Draw platforms
        drawPlatform(platformX, platformHeight, mv.width * 2, 20); // First platform

        // Move the platform to the left
        platformX -= speed; // Adjust the speed as needed
        
        // Check if the platform has moved completely off the screen to the left
        if (platformX + (mv.width * 2) / 2 < 0) {
            // Reset the platform to the right side of the screen
            platformX = mv.width + (mv.width * 2) / 2;
        }
        mv.hint(mv.ENABLE_DEPTH_TEST);

    }


    void drawPlatform(float x, float y, float width, float height) {
        mv.fill(100); // Set platform color to gray
        mv.rectMode(mv.CENTER); // Set rectangle mode to center
        mv.stroke(0, 0, 100);

        mv.rect(x, y, width, height); // Draw platform
    }
}
