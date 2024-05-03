package c22376553;
import MusicVis.MyVisual;


public class FlowerBloom {
    MyVisual mv;
    int petals = 40; // Number of petals
    float[] angles; // Angles for each petal
    long lastChangeTime = 0; // Variable to store the last time color or size was changed
    int interval = 1000; // Interval in milliseconds for color and size change
    int currentColor; // Variable to store the current color
    int nextColor; // Variable to store the next color
    float currentSize; // Variable to store the current size
    float nextSize; // Variable to store the next size

    public FlowerBloom(MyVisual mv) {
        this.mv = mv;
        currentColor = mv.color(mv.random(70, 500), 100, 100);
        nextColor = mv.color(mv.random(70,500), mv.random(70,500), mv.random(70,500));
        currentSize = mv.random(80, 150);
        nextSize = mv.random(80, 150);

        // Initialize angles for each petal
        angles = new float[petals];
        for (int i = 0; i < petals; i++) {
            angles[i] = mv.random(mv.TWO_PI); // Random initial angle for each petal
        }
    }

    public void render() {
        // Render the flower petals
        renderDynamicBackground();
    }

void renderDynamicBackground() {
    // Define background parameters
    int numShapes = 50; // Number of shapes
    float maxRadius = mv.max(mv.width, mv.height); // Maximum radius for shapes
    float hueOffset = mv.random(360); // Random hue offset to vary colors
    float rotationSpeed = 0.1f; // Speed of rotation

    float amt = (float)(mv.millis() - lastChangeTime) / interval;

      if (amt >= 1.0) {

          // Change color
          currentColor = nextColor;
          nextColor = mv.color(mv.random(70,500), mv.random(70,500), mv.random(70,500));
          // Change size
          currentSize = nextSize;
          nextSize = mv.random(80, 150);
          // Update last change time
          lastChangeTime = mv.millis();
          amt = 0.0f;
      }
    // Render animated shapes
    mv.noStroke();
    for (int i = 0; i < numShapes; i++) {
        float angle = mv.radians(i * (360 / numShapes) + mv.frameCount * rotationSpeed); // Calculate angle
        float radius = maxRadius * mv.abs(mv.sin(5 * 0.02f + angle)); // Calculate pulsating radius
        float x = mv.width / 2 + mv.cos(angle) * radius;
        float y = mv.height / 2 + mv.sin(angle) * radius;
        float shapeSize = mv.map(radius, 0, maxRadius, 5, 50); // Map size based on radius

        // Set color based on angle and frame count
        float hue = (hueOffset + angle * mv.degrees(rotationSpeed) + mv.frameCount) % 360;
        mv.fill(mv.lerpColor(currentColor, nextColor, amt));
        
        // Draw shape
        mv.ellipse(x, y, shapeSize, shapeSize);
    }
}
}
