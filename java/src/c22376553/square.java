package c22376553;
import MusicVis.MyVisual;
public class square {
    MyVisual mv;
    float x, y = 0;
    float velocity = 0; // Initial velocity
    float gravity = 0.6f; // Strength of gravity
    boolean isJumping = false; // Flag to track whether the cube is currently jumping
    float jumpStrength = -15; // Strength of the jump

    public square(MyVisual mv) {
        this.mv = mv;
        x = mv.width / 2 - 300;
        y = mv.height / 2 + 244;
    }


    public void render() {
        mv.hint(mv.DISABLE_DEPTH_TEST);
        mv.pushMatrix(); // Save the current transformation matrix

        // Translate to the position of the cube
        mv.translate(x, y);

        // Rotate the cube if it's jumping
        if (isJumping) {
            // Calculate rotation angle based on velocity
            float rotationAngle = mv.map(velocity, jumpStrength, 0, 0, mv.HALF_PI);
            mv.rotate(rotationAngle);
        }

        // Draw the cube
        mv.fill(50); // Gray color for the cube
        mv.stroke(0, 0, 100);
        mv.strokeWeight(2);
        mv.rectMode(mv.CENTER); // Set rectangle mode to center
        mv.rect(0, 0, 50, 50); // Draw square at current position

        // Draw a face on one side of the cube
        mv.fill(0, 0, 100); // Green color for the face
        mv.circle(0, 0, 20); // Draw a rectangle to represent the face

        mv.popMatrix(); // Restore the previous transformation matrix
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
