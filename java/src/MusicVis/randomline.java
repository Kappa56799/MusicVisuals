package MusicVis;

public class randomline {
    int num = 500;
    MyVisual mv;
    int range = 60; // Increase the range to draw points further apart
    float[] ax = new float[num];
    float[] ay = new float[num];

    public randomline(MyVisual mv) {
        this.mv = mv;

        // Initialize arrays
        for(int i = 0; i < num; i++) {
            ax[i] = mv.width/2;
            ay[i] = mv.height/2;
        }
    }

    public void render() {
        // Shift all elements 1 place to the left
        for(int i = 1; i < num; i++) {
            ax[i-1] = ax[i];
            ay[i-1] = ay[i];
        }

        // Put a new value at the end of the array
        ax[num-1] += mv.random(-range, range);
        ay[num-1] += mv.random(-range, range);
        
        // Constrain all points to the screen
        ax[num-1] = mv.constrain(ax[num-1], 0, mv.width);
        ay[num-1] = mv.constrain(ay[num-1], 0, mv.height);
        
        // Draw the line
        for(int i=1; i<num; i++) {    
            float val = (float)((i)/num * 204.0 + 51);
            mv.strokeWeight(5);
            mv.stroke(mv.random(70, 500), 100, 100);
            mv.line(ax[i-1], ay[i-1], ax[i], ay[i]);
        }
    }
}