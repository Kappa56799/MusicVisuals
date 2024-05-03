package c22486382;

import MusicVis.MyVisual;

public class randomline {
    int num = 350;
    MyVisual mv;
    int range = 60; // Increase the range to draw points further apart
    float[] ax = new float[num];
    float[] ay = new float[num];
    float[] bx = new float[num]; // New array for second line
    float[] by = new float[num]; // New array for second line
    float[] cx = new float[num]; // New array for third line
    float[] cy = new float[num]; // New array for third line
    float[] dx = new float[num]; // New array for fourth line
    float[] dy = new float[num]; // New array for fourth line
    int colorA, colorB, colorC, colorD; // Variables to store the colors of each line

    public randomline(MyVisual mv) {
        this.mv = mv;

        // Initialize arrays
        for (int i = 0; i < num; i++) {
            ax[i] = mv.width / 2;
            ay[i] = mv.height / 2;
            bx[i] = mv.width / 2; // Initialize second line with same starting point
            by[i] = mv.height / 2; // Initialize second line with same starting point
            cx[i] = mv.width / 2; // Initialize third line with same starting point
            cy[i] = mv.height / 2; // Initialize third line with same starting point
            dx[i] = mv.width / 2; // Initialize fourth line with same starting point
            dy[i] = mv.height / 2; // Initialize fourth line with same starting point
        }

        // Initialize colors
        colorA = mv.color(mv.random(70, 500), 100, 100);
        colorB = mv.color(mv.random(70, 500), 100, 100);
        colorC = mv.color(mv.random(70, 500), 100, 100);
        colorD = mv.color(mv.random(70, 500), 100, 100);
    }

    public void render() {
        // Shift all elements 1 place to the left
        for (int i = 1; i < num; i++) {
            ax[i - 1] = ax[i];
            ay[i - 1] = ay[i];
            bx[i - 1] = bx[i];
            by[i - 1] = by[i];
            cx[i - 1] = cx[i];
            cy[i - 1] = cy[i];
            dx[i - 1] = dx[i];
            dy[i - 1] = dy[i];
        }

        // Put a new value at the end of the array
        ax[num - 1] += mv.random(-range, range);
        ay[num - 1] += mv.random(-range, range);
        bx[num - 1] += mv.random(-range, range); // Generate new value for second line
        by[num - 1] += mv.random(-range, range); // Generate new value for second line
        cx[num - 1] += mv.random(-range, range); // Generate new value for third line
        cy[num - 1] += mv.random(-range, range); // Generate new value for third line
        dx[num - 1] += mv.random(-range, range); // Generate new value for fourth line
        dy[num - 1] += mv.random(-range, range); // Generate new value for fourth line

        // Constrain all points to the screen
        ax[num - 1] = mv.constrain(ax[num - 1], 0, mv.width);
        ay[num - 1] = mv.constrain(ay[num - 1], 0, mv.height);
        bx[num - 1] = mv.constrain(bx[num - 1], 0, mv.width); // Constrain second line point to the screen
        by[num - 1] = mv.constrain(by[num - 1], 0, mv.height); // Constrain second line point to the screen
        cx[num - 1] = mv.constrain(cx[num - 1], 0, mv.width); // Constrain third line point to the screen
        cy[num - 1] = mv.constrain(cy[num - 1], 0, mv.height); // Constrain third line point to the screen
        dx[num - 1] = mv.constrain(dx[num - 1], 0, mv.width); // Constrain fourth line point to the screen
        dy[num - 1] = mv.constrain(dy[num - 1], 0, mv.height); // Constrain fourth line point to the screen

        // Draw the lines
        mv.strokeWeight(5);
        mv.stroke(colorA);
        for (int i = 1; i < num; i++) {
            mv.line(ax[i - 1], ay[i - 1], ax[i], ay[i]);
        }
        mv.stroke(colorB);
        for (int i = 1; i < num; i++) {
            mv.line(bx[i - 1], by[i - 1], bx[i], by[i]);
        }
        mv.stroke(colorC);
        for (int i = 1; i < num; i++) {
            mv.line(cx[i - 1], cy[i - 1], cx[i], cy[i]);
        }
        mv.stroke(colorD);
        for (int i = 1; i < num; i++) {
            mv.line(dx[i - 1], dy[i - 1], dx[i], dy[i]);
        }

        // Change colors
        if (mv.frameCount % 30 == 0) { // Change colors every 60 frames
            colorA = mv.color(mv.random(70, 500), 100, 100);
            colorB = mv.color(mv.random(70, 500), 100, 100);
            colorC = mv.color(mv.random(70, 500), 100, 100);
            colorD = mv.color(mv.random(70, 500), 100, 100);
        }
    }
}
