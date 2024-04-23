package MusicVis;

public class Tree {
    MyVisual mv;
    float theta;  // Angle of branch
    float spacing = 700; // Spacing between trees
    int lastColorChange = 0;
    int colorChangeInterval = 500; // Change color every 2 seconds
    int currentColor; // Variable to store the current color
    int i = 0;
    boolean increasing = true; // Variable to track if i is increasing or decreasing

    public Tree(MyVisual mv) {
        this.mv = mv;
    }

    public void render() {
        mv.stroke(255);
        mv.pushStyle();

        mv.hint(mv.DISABLE_DEPTH_TEST);

        // Check if it's time to change color
        if (mv.millis() - lastColorChange > colorChangeInterval) {
            currentColor = mv.color(mv.random(50,255), mv.random(50,255), mv.random(50,255));
            lastColorChange = mv.millis(); // Update the last color change time
        }

        // Adjust i based on whether it's increasing or decreasing
        if (increasing) {
            i += 30;
            if (i >= mv.width) {
                increasing = false;
            }
        } else {
            i -= 30;
            if (i <= 0) {
                increasing = true;
            }
        }

        // Calculate theta based on the current value of i
        float a = (i / (float) mv.width) * 90f;
        // Convert it to radians
        theta = mv.radians(a);

        // Start the tree drawing
        drawTree(mv.width / 2, mv.height - 250); // Draw the middle tree
        drawTree(mv.width / 2 - spacing, mv.height - 250); // Draw the left tree
        drawTree(mv.width / 2 + spacing, mv.height - 250); // Draw the right tree
        
        // Draw three upside-down trees from the top of the screen
        drawUpsideDownTree(mv.width / 2, 50);
        drawUpsideDownTree(mv.width / 2 - spacing, 50);
        drawUpsideDownTree(mv.width / 2 + spacing, 50);
    }

    void drawTree(float x, float y) {
        // Start the tree from the bottom of the screen
        mv.pushMatrix(); // Save the current transformation state
        mv.translate(x, y);
        // Set random color
        mv.stroke(currentColor);
        // Draw a line 120 pixels
        mv.line(0, 0, 0, -120);
        // Move to the end of that line
        mv.translate(0, -120);
        // Start the recursive branching!
        branch(300);
        mv.popMatrix(); // Restore the previous transformation state
    }
    
    void drawUpsideDownTree(float x, float y) {
        // Start the tree from the top of the screen
        mv.pushMatrix(); // Save the current transformation state
        mv.translate(x, y);
        // Set random color
        mv.stroke(currentColor);
        // Draw a line 120 pixels
        mv.line(0, 0, 0, 120);
        // Move to the end of that line
        mv.translate(0, 120);
        // Start the recursive branching!
        branch(300);
        mv.popMatrix(); // Restore the previous transformation state
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
