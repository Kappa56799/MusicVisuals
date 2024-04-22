package MusicVis;
import processing.core.*;

public class Platforms {
    MyVisual mv;
    float platformHeight; // Height of the platform

    public Platforms(MyVisual mv) {
        this.mv = mv;
        platformHeight = mv.height / 2; // Set platform height to the middle of the screen
    }

    public void render() {
        mv.stroke(255); // Set stroke color to white
        
        // Draw platforms
        drawPlatform(0, platformHeight, mv.width / 3, 20); // First platform
        drawPlatform(mv.width / 3 * 2, platformHeight, mv.width / 3, 20); // Second platform
    }

    void drawPlatform(float x, float y, float width, float height) {
        mv.fill(100); // Set platform color to gray
        mv.rectMode(mv.CENTER); // Set rectangle mode to center
        mv.rect(x, y, width, height); // Draw platform
    }
}

