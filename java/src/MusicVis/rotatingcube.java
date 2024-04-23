package MusicVis;

public class rotatingcube {
    int lastColorChange = 0;
    int colorChangeInterval = 1000; // Change color every 1 second
    int[] cubeColors; // Array to store the hue of each cube
    MyVisual mv;
    float a;                 // Angle of rotation
    float offset;            // Angle offset between boxes
    int num = 12;            // Number of boxes

    public rotatingcube(MyVisual mv) {
        this.mv = mv;
        offset = (float)(mv.PI / 24.0);
        cubeColors = new int[num];
        initializeCubeColors(); // Initialize cube colors
    }

    void initializeCubeColors() {
        for (int i = 0; i < num; i++) {
            cubeColors[i] = (int) mv.random(360); // Initialize each cube with a random hue
        }
    }

    void render() {
        mv.pushMatrix();
        mv.colorMode(mv.HSB, 360, 100, 100);
        mv.translate(mv.width/2, mv.height/2 - 150); 

        if (mv.millis() - lastColorChange > colorChangeInterval) {
            for (int i = 0; i < num; i++) {
                cubeColors[i] = (int) mv.random(360); // Change hue for each cube
            }
            lastColorChange = mv.millis(); // Update the last color change time
        }

        for(int i = 0; i < num; i++) {
            mv.fill(cubeColors[i], 100, 100); // Set fill color using HSB color mode
            mv.pushMatrix();
            mv.rotateY(a + offset*i);
            mv.rotateX(a/2 + offset*i);
            mv.box(325);
            mv.popMatrix();
        }

        a += 0.01;
        mv.popMatrix();
    } 
}
