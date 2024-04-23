package MusicVis;

public class cube {
    MyVisual mv;
    float x, y = 0;
    public cube(MyVisual mv) {
        this.mv = mv;
        x = mv.width / 2;
        y = mv.height / 2 + 235;
    }

    public void render() {
        mv.hint(mv.DISABLE_DEPTH_TEST);
        mv.fill(50); // Red color
        mv.stroke(0, 0, 100);
        mv.rectMode(mv.CENTER); // Set rectangle mode to center
        mv.rect(x, y, 50, 50); // Draw square at current position
        
        // Draw a face on one side of the cube
        mv.fill(0, 0, 100); // Green color for the face
        mv.circle(x , y, (float) 20); // Draw a rectangle to represent the face
        mv.hint(mv.ENABLE_DEPTH_TEST);
    }
}
