package MusicVis;

public class bigwave {
    MyVisual mv;
    int xspacing = 8;   // How far apart should each horizontal location be spaced
    int w;              // Width of entire wave
    int maxwaves = 4;   // total # of waves to add together

    float theta = 0.0f;
    float[] amplitude = new float[maxwaves];   // Height of wave
    float[] dx = new float[maxwaves];          // Value for incrementing X, to be calculated as a function of period and xspacing
    float[] yvalues;                           // Using an array to store height values for the wave (not entirely necessary)

    public bigwave(MyVisual mv) {
        this.mv = mv;
        w = mv.width + 16; // Moved initialization here
        
        for (int i = 0; i < maxwaves; i++) {
            amplitude[i] = mv.random(100,120);
            float period = mv.random(300,1000); // How many pixels before the wave repeats
            dx[i] = (mv.TWO_PI / period) * xspacing;
        }

        yvalues = new float[w/xspacing];
    }

    void render() {
        mv.hint(mv.DISABLE_DEPTH_TEST);
        calcWave();
        renderWave();
        mv.hint(mv.ENABLE_DEPTH_TEST);
    }

    void calcWave() {
        // Increment theta (try different values for 'angular velocity' here
        theta += 0.02;

        // Set all height values to zero
        for (int i = 0; i < yvalues.length; i++) {
            yvalues[i] = 0;
        }
        
        // Accumulate wave height values
        for (int j = 0; j < maxwaves; j++) {
            float x = theta;
            for (int i = 0; i < yvalues.length; i++) {
                // Every other wave is cosine instead of sine
                if (j % 2 == 0)  yvalues[i] += mv.sin(x)*amplitude[j];
                else yvalues[i] += mv.cos(x)*amplitude[j];
                x+=dx[j];
            }
        }
    }

    void renderWave() {
        // A simple way to draw the wave with an ellipse at each location
        mv.stroke(0);
        // Smoothly change colors
        float hue = 0;
        float hueIncrement = 255.0f / yvalues.length;
        for (int x = 0; x < yvalues.length; x++) {
            mv.fill(hue, 255, 255);
            mv.ellipse(x * xspacing, mv.height / 2 + yvalues[x], 100, 50);
            hue += hueIncrement;
        }

        // Render the second wave
        for (int x = 0; x < yvalues.length; x++) {
            mv.fill((hue %255)-50, 255, 255);
            mv.ellipse(x * xspacing, mv.height / 2 - yvalues[x], 65, 50); // Draw ellipse for second wave
            hue += hueIncrement;
        }
    }
}
