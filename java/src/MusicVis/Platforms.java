package MusicVis;
import processing.core.*;

public class Platforms {
    MyVisual mv;
    float platformHeight; // Height of the platform

    public Platforms(MyVisual mv) {
        this.mv = mv;
        platformHeight = mv.height / 2 + 280; // Set platform height to the middle of the screen
    }

    public void render() {
        mv.stroke(255); // Set stroke color to white
        
        // Draw platforms
        drawPlatform(0, platformHeight, mv.width * 2, 20); // First platform
    }

    void drawPlatform(float x, float y, float width, float height) {
        mv.fill(100); // Set platform color to gray
        mv.rectMode(mv.CENTER); // Set rectangle mode to center
        mv.stroke(0, 0, 100);
        mv.rect(x, y, width, height); // Draw platform
    }
}

