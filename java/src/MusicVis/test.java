package MusicVis;

import processing.core.*;
import java.util.ArrayList;

public class test {
    MyVisual mv;
    float x, y = 0;
    float speed = 2; // Speed of the square    
    float jumpStrength = -10; // Strength of the jump
    float gravity = (float) (0.5); // Strength of gravity
    boolean jumping = false;

    public test(MyVisual mv) {
        this.mv = mv;
        x = mv.width / 2 - 500;
        y = mv.height / 2;
    }

    public void render() {
        mv.fill(255, 0, 0); // Red color
        mv.rectMode(mv.CENTER); // Set rectangle mode to center
        mv.rect(x, y, 50, 50); // Draw square at current position
        
        // Update the position of the square for movement
        x += speed; // Move horizontally

        // Wrap around when the square reaches the canvas edge
        if (x > mv.width) {
            x = 0;
        }

        // Check if the cube is on a platform and handle jumping
        handleJump();
    }

    void handleJump() {
        if (!jumping && isOnPlatform()) { // If cube is not jumping and is on platform
            y -= 1; // Prevent sticking to the platform
        }

        if (jumping) { // If cube is jumping
            y += speed; // Apply jump velocity
            speed += gravity; // Apply gravity
            if (y >= mv.height / 2) { // If cube reaches original height
                jumping = false; // Stop jumping
                y = mv.height / 2; // Reset y position
                speed = 2; // Reset jump speed
            }
        }
    }

    boolean isOnPlatform() {
        // Check if cube is on platform
        return (y >= mv.height / 2 - 25 && y <= mv.height / 2 + 25 && x >= mv.width / 3 - 25 && x <= mv.width / 3 + 25)
                || (y >= mv.height / 2 - 25 && y <= mv.height / 2 + 25 && x >= mv.width / 3 * 2 - 25
                        && x <= mv.width / 3 * 2 + 25);
    }

    public void jump() {
        if (!jumping) { // Only jump if not already jumping
            jumping = true;
            speed = jumpStrength; // Set jump velocity
        }
    }
}
