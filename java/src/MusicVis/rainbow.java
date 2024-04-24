package MusicVis;

public class rainbow {
    MyVisual mv;
    float cy = 0;
    float angle = 0;
    float colorOffset = 0;

    public rainbow(MyVisual mv) {
        this.mv = mv;
    }

    public void render() {
        int gridSize = 20;
        int width = mv.width;
        int height = mv.height;


        for (int x = gridSize; x <= width - gridSize; x += gridSize) {
            for (int y = gridSize; y <= height - gridSize; y += gridSize) {
                float r = mv.dist(x, y, width/1, height/1); // Distance from center
                float hue = (colorOffset + r) % 360; // Hue based on distance
                mv.fill(hue, 100, 200);
                
                mv.pushMatrix(); // Save current transformation matrix
                mv.translate(x, y); // Move origin to current dot position
                mv.rotate(angle); // Rotate based on angle
                mv.noStroke();
                mv.rect(x-10, y-10, 0, 0); // Draw rotated rectangle
                mv.stroke(hue, 125, 100, 55);
                mv.line(x-1, y-1, gridSize, 100); // Draw rotated line
                mv.popMatrix(); // Restore original transformation matrix
            } 
        }

        angle += 0.01; // Increment rotation angle
        colorOffset += 0.9; // Increment color offset
    }
}
