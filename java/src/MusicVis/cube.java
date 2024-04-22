package MusicVis;

import processing.core.*;
import java.util.ArrayList;

public class cube {
    MyVisual mv;
    float x, y = 0;
    float jumpStrength = -10; // Strength of the jump
    float gravity = (float) (0.5); // Strength of gravity
    boolean jumping = false;

    public cube(MyVisual mv) {
        this.mv = mv;
        x = mv.width / 2;
        y = mv.height / 2;
    }

    public void render() {
        mv.fill(255, 0, 0); // Red color
        mv.rectMode(mv.CENTER); // Set rectangle mode to center
        mv.rect(x, y, 50, 50); // Draw square at current position
        
    }
}
