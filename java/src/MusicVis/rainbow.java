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
        int gridSize = 10;
        int width = mv.width;
        int height = mv.height;
        float centerX = width / 5.0f; // Calculate center X coordinate
        float centerY = height / 7.0f; // Calculate center Y coordinate
    
        for (int x = 0; x <= width; x += gridSize) {
            for (int y = 0; y <= height; y += gridSize) {
                float r = mv.dist(x, y, centerX, centerY); // Distance from center
                float hue = (colorOffset + r) % 360; // Hue based on distance
                mv.fill(hue, 20, 200);
                
                mv.pushMatrix(); // Save current transformation matrix
                mv.translate(x, y); // Move origin to current dot position
                mv.rotate(angle); // Rotate based on angle
                mv.noStroke();
                mv.rect(-gridSize / 5, -gridSize / 2, gridSize, gridSize); // Draw rotated rectangle
                mv.stroke(hue, 255, 100, 55);
                mv.line(-gridSize / 3, gridSize / 20, gridSize / 10, -gridSize / 2); // Draw rotated line
                mv.popMatrix(); // Restore original transformation matrix
            } 
        }
    
        angle += 0.5; // Increment rotation angle
        colorOffset += 0.6; // Increment color offset
    }
}
