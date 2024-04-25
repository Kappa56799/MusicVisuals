package c22376553;
import MusicVis.MyVisual;

public class FlowerBloom {
    MyVisual mv;
    bigwave wave; // Using the bigwave class for wave motion
    int petals = 40; // Number of petals
    float[] angles; // Angles for each petal

    public FlowerBloom(MyVisual mv) {
        this.mv = mv;
        wave = new bigwave(mv); // Initialize the wave object

        // Initialize angles for each petal
        angles = new float[petals];
        for (int i = 0; i < petals; i++) {
            angles[i] = mv.random(mv.TWO_PI); // Random initial angle for each petal
        }
    }

    public void render() {

        // Render the flower petals
        renderDynamicBackground();
        renderPetals();
    }


    void renderPetals() {
        mv.noStroke();
        float hue = mv.random(360); // Random hue for petals

        // Define parameters for pulsating effect
        float petalSize = 50; // Base size of petals
        float pulseMagnitude = 10; // Magnitude of pulsation
        float pulseSpeed = 0.05f; // Speed of pulsation
        float petalOffset = mv.random(50, 150); // Random petal offset range

        // Calculate pulsation factor based on sine function
        float pulseFactor = mv.sin(mv.frameCount * pulseSpeed);

        for (int i = 0; i < petals; i++) {
            float x = mv.width / 2 + mv.cos(angles[i]) * petalOffset;
            float y = mv.height / 2 + mv.sin(angles[i]) * petalOffset;

            // Calculate pulsating petal size
            float pulsatingSize = petalSize + pulseFactor * pulseMagnitude;

            mv.fill(hue, 360, 360, 360); // Set petal color
            mv.ellipse(x, y, pulsatingSize, pulsatingSize); // Draw petals

            // Update angle for next frame
            angles[i] += 0.02; // You can adjust the speed of petal movement here
        }
    }
void renderDynamicBackground() {
    // Define background parameters
    int numShapes = 50; // Number of shapes
    float maxRadius = mv.max(mv.width, mv.height); // Maximum radius for shapes
    float hueOffset = mv.random(360); // Random hue offset to vary colors
    float rotationSpeed = 0.01f; // Speed of rotation

    // Render animated shapes
    mv.noStroke();
    for (int i = 0; i < numShapes; i++) {
        float angle = mv.radians(i * (360 / numShapes) + mv.frameCount * rotationSpeed); // Calculate angle
        float radius = maxRadius * mv.abs(mv.sin(mv.frameCount * 0.02f + angle)); // Calculate pulsating radius
        float x = mv.width / 2 + mv.cos(angle) * radius;
        float y = mv.height / 2 + mv.sin(angle) * radius;
        float shapeSize = mv.map(radius, 0, maxRadius, 5, 50); // Map size based on radius

        // Set color based on angle and frame count
        float hue = (hueOffset + angle * mv.degrees(rotationSpeed) + mv.frameCount) % 360;
        mv.fill(hue, 360, 360);
        
        // Draw shape
        mv.ellipse(x, y, shapeSize, shapeSize);
    }
}
}
