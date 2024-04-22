package MusicVis;
import processing.core.*;
import java.util.concurrent.TimeUnit;

public class Tree {
    MyVisual mv;
    float theta;  // Angle of branch
    long lastUpdateTime; // Time when the angle was last updated
    long updateInterval = TimeUnit.SECONDS.toMillis(1); // Update interval in milliseconds

    public Tree(MyVisual mv) {
        this.mv = mv;
        this.lastUpdateTime = System.currentTimeMillis(); // Initialize lastUpdateTime
    }

    public void render() {
        int width = mv.width;
        int height = mv.height;

        mv.stroke(255);
        // Check if it's time to update the angle
        if (System.currentTimeMillis() - lastUpdateTime >= updateInterval) {
            // Let's pick an angle 0 to 90 degrees based on the mouse position
            float a = (mv.random(100, 1920) / (float) mv.width) * 90f;
            // Convert it to radians
            theta = mv.radians(a);
            // Update lastUpdateTime
            lastUpdateTime = System.currentTimeMillis();
        }
        
        // Start the tree drawing
        drawTree();
    }

    void drawTree() {
        int width = mv.width;
        int height = mv.height;
        // Start the tree from the bottom of the screen
        mv.translate(width/2,height);
        // Draw a line 120 pixels
        mv.line(0,0,0,-120);
        // Move to the end of that line
        mv.translate(0,-120);
        // Start the recursive branching!
        branch(300);
    }

    void branch(float h) {
        // Each branch will be 2/3rds the size of the previous one
        h *= 0.66;
        // All recursive functions must have an exit condition!!!!
        // Here, ours is when the length of the branch is 2 pixels or less
        if (h > 2) {
            mv.pushMatrix();    // Save the current state of transformation (i.e. where are we now)
            mv.rotate(theta);   // Rotate by theta
            mv.line(0, 0, 0, -h);  // Draw the branch
            mv.translate(0, -h); // Move to the end of the branch
            branch(h);       // Ok, now call myself to draw two new branches!!
            mv.popMatrix();     // Whenever we get back here, we "pop" in order to restore the previous matrix state

            // Repeat the same thing, only branch off to the "left" this time!
            mv.pushMatrix();
            mv.rotate(-theta);
            mv.line(0, 0, 0, -h);
            mv.translate(0, -h);
            branch(h);
            mv.popMatrix();       
        }
    }
}
