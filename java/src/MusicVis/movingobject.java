package MusicVis;

public class movingobject {
    MyVisual mv;
    long lastChangeTime = 0; // Variable to store the last time color or size was changed
    int interval = 400; // Interval in milliseconds for color and size change
    int currentColor; // Variable to store the current color
    int nextColor; // Variable to store the next color
    float currentSize; // Variable to store the current size
    float nextSize; // Variable to store the next size

    public movingobject(MyVisual mv) {
        this.mv = mv;
        currentColor = mv.color(mv.random(70, 500), 100, 100);
        nextColor = mv.color(mv.random(70,500), mv.random(70,500), mv.random(70,500));
        currentSize = mv.random(80, 150);
        nextSize = mv.random(80, 150);
    }

    public void render() {

        int audioIndex = (int)(mv.random(30, 512));
        float audioValue = mv.getAudioBuffer().get(audioIndex);
        
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

        mv.pushMatrix();
        mv.lights();
        mv.fill(mv.lerpColor(currentColor, nextColor, amt));
        
        // Calculate the lerped size
        float lerpedSize = mv.lerp(currentSize - 50, nextSize - 50, amt);
        
        // Change height of the camera with mouseY
        mv.camera(30f, lerpedSize, 220.0f, // eyeX, eyeY, eyeZ
              0.0f, 0.0f, 0.0f, // centerX, centerY, centerZ
              0.0f, 1.0f, 0.0f); // upX, upY, upZ
        
        mv.noStroke();
        
        // Render the sphere and lines with camera effect
        mv.translate(0, -50, 0); // Position of the sphere
        mv.sphere(lerpedSize); // Draw a sphere with the calculated size
        mv.line(-100, 0, 0, 100, 0, 0);
        mv.line(0, -100, 0, 0, 100, 0);
        mv.line(0, 0, -100, 0, 0, 100);
        
        mv.popMatrix();
    }
}
