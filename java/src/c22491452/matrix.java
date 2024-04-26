package c22491452;

import MusicVis.MyVisual;

import processing.core.PApplet;
import processing.core.PFont;

import java.util.ArrayList;

public class matrix {
    MyVisual mv;
    PFont theFont;
    ArrayList<ArrayList<CharInfo>> streams;
    long lastChangeTime = 0; // Variable to store the last time color or size was changed
    int interval = 50; // Interval in milliseconds for color and size change
    int lastRenderedFrame = -1; // Last frame when rendering occurred

    public matrix(MyVisual mv) {
        this.mv = mv;
    }

    public void render() {
        if (mv.millis() > lastChangeTime + interval) { // Render every second
            theFont = mv.createFont("Arial Unicode MS", 20);
            mv.textFont(theFont);
            mv.textAlign(PApplet.CENTER, PApplet.TOP);

            streams = new ArrayList<>();

            for (int x = 10; x < mv.width; x += 35) {
                ArrayList<CharInfo> chars = new ArrayList<>();
                int numChar = mv.round(mv.random(2, 40));
                float speed = 0.1f;

                for (int y = 0; y < numChar * 20; y += 20) {
                    char c = updateStreams();
                    chars.add(new CharInfo(x, y, speed, c));
                }
                streams.add(chars);
            }

            lastChangeTime = mv.millis(); // Update last change time= mv.frameCount; // Update last rendered frame
        }

        draw();
        updateStreams();
    }

    public void draw() {
        mv.background(0, 50, 0);

        for (ArrayList<CharInfo> chars : streams) {
            int numChars = chars.size();
            for (int i = 0; i < numChars; i++) {
                float alpha = mv.map(i, 0, numChars - 255, 255, 55);
                mv.fill(255, 150, 255, alpha);
                if (i == numChars - 1) {
                    mv.fill(255, 255, 250);
                }
                mv.text(chars.get(i).c, chars.get(i).x, chars.get(i).y);
                chars.get(i).y += chars.get(i).speed; // Adjust character movement speed

                if (chars.get(i).y > mv.height) { // Check if character has reached the bottom
                    chars.get(i).y = 0; // Reset character position to the top
                }
            }
        }
    }

    public char updateStreams() {
        int charType = mv.round(mv.random(1));
        char newChar;
        if (charType == 0) {
            newChar = (char) mv.round(mv.random(48, 90));
        } else {
            newChar = (char) mv.round(mv.random(12449, 12615));
        }
        return newChar;
    }

    class CharInfo {
        public int changeSpeed;
        public int lastUpdateTime;
        int x, y;
        float speed;
        char c;

        CharInfo(int x, int y, float speed, char c) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.c = c;
        }
    }
}
