package MusicVis;

public class cube {
    MyVisual mv;
    float x, y = 0;
    float velocity = 0; // Initial velocity
    float gravity = (float) 0.6; // Strength of gravity
    boolean isJumping = false; // Flag to track whether the cube is currently jumping
    float jumpStrength = -15; // Strength of the jump

    public cube(MyVisual mv) {
        this.mv = mv;
        x = mv.width / 2 - 300;
        y = mv.height / 2 + 244;
    }

    public void render() {
        mv.hint(mv.DISABLE_DEPTH_TEST);
        mv.fill(50); // Gray color for the cube
        mv.stroke(0, 0, 100);
        mv.rectMode(mv.CENTER); // Set rectangle mode to center
        mv.rect(x, y, 50, 50); // Draw square at current position

        // Draw a face on one side of the cube
        mv.fill(0, 0, 100); // Green color for the face
        mv.circle(x , y, (float) 20); // Draw a rectangle to represent the face
        mv.hint(mv.ENABLE_DEPTH_TEST);
    }

    public void jump() {
        if (!isJumping) { // Check if the cube is not already jumping
            velocity = jumpStrength; // Set the velocity to the jump strength
            isJumping = true; // Set the jumping flag to true
        }
    }

    public void update() {
        velocity += gravity; // Apply gravity to the velocity
        y += velocity; // Update the position based on velocity

        if (y >= mv.height / 2 + 244) { // Check if the cube has landed on the ground
            y = mv.height / 2 + 244; // Snap the cube to the ground
            velocity = 0; // Reset the velocity
            isJumping = false; // Reset the jumping flag
        }

        // Jump if spacebar is pressed and the cube is not already jumping
        if (mv.keyPressed && mv.key == ' ' && !isJumping) {
            jump();
        }
    }
}
