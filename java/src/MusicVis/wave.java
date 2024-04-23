package MusicVis;

public class wave {
    MyVisual mv;
    float yoff = (float) 0.0;  
    long lastColorChangeTime = 0; // Variable to store the last time color was changed
    int interval = 600; // Interval in milliseconds for color change
    int currentColor; // Variable to store the current color

    public wave(MyVisual mv) {
        this.mv = mv;
        currentColor = mv.color(mv.random(150, 255), mv.random(150, 255), mv.random(150, 255)); // Initial random color

    }
    public void render() {
        mv.hint(mv.DISABLE_DEPTH_TEST);
        mv.strokeWeight(1);
        // Check if 2 seconds have elapsed since the last color change
        if (mv.millis() - lastColorChangeTime >= interval) {
            // Change color
            currentColor = mv.color(mv.random(150, 255), mv.random(150, 255), mv.random(150, 255));
            // Update last color change time
            lastColorChangeTime = mv.millis();
        }

        mv.fill(currentColor); // Set the current color
        // We are going to draw a polygon out of the wave points
        mv.beginShape(); 
        
        float xoff = 0;       // Option #1: 2D Noise
        int i = 0;
        // float xoff = yoff; // Option #2: 1D Noise

        
        // Iterate over horizontal pixels
        for (float x = 0; x <= mv.width; x += 10) {
            // Calculate a y value according to noise, map to 
            float y = mv.map((float) (mv.noise(xoff,yoff)), 0, 1, 960,800); // Option #1: 2D Noise
            // float y = map(noise(xoff), 0, 1, 200,300);    // Option #2: 1D Noise
            
            // Set the vertex
            mv.vertex(x, y); 
            // Increment x dimension for noise
            xoff += 0.1;
            i += 1;
        }
        // increment y dimension for noise
        yoff += mv.getAudioBuffer().get(i) + 0.1;
        mv.vertex(mv.width, mv.height);
        mv.vertex(0, mv.height);
        mv.endShape(mv.CLOSE);
        mv.hint(mv.ENABLE_DEPTH_TEST);
    }
    
}
