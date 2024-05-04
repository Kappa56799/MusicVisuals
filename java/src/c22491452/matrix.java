package c22491452;

import MusicVis.MyVisual;
import processing.core.PApplet;
import processing.core.PFont;

import java.util.ArrayList;

public class matrix {
    MyVisual mv; // references main visualization class
    PFont theFont;
    ArrayList<ArrayList<CharInfo>> streams; // list to store character streams
    long lastChangeTime = 0; // Variable to store the last time color or size was changed
    int interval = 200; // Interval in milliseconds for color and size change
    int lastRenderedFrame = -1; // Last frame when rendering occurred

    public matrix(MyVisual mv) {
        this.mv = mv;
    }

    public void render() {
        if (mv.millis() > lastChangeTime + interval) { // Render every second
            theFont = mv.createFont("Arial Unicode MS", 20);
            mv.textFont(theFont);
            mv.textAlign(PApplet.CENTER, PApplet.TOP); // Alligns the text to the center and so that the text comes from
                                                       // the top of the screen

            streams = new ArrayList<>();

            for (int x = 10; x < mv.width; x += 35) {
                ArrayList<CharInfo> chars = new ArrayList<>();
                int numChar = mv.round(mv.random(2, 40)); // randomly generates the length of the character streams
                                                          // between the 2 numbers shown
                float speed = 0.1f;

                for (int y = 0; y < numChar * 20; y += 20) {
                    char c = updateStreams(); // generates new character
                    chars.add(new CharInfo(x, y, speed, c)); // adding the character to the stream
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

        for (ArrayList<CharInfo> chars : streams) { // renders each character stream
            int numChars = chars.size();
            for (int i = 0; i < numChars; i++) { // this for loop is used
                float alpha = mv.map(i, 0, numChars - 1, 150, 55); // this fades the characters in the stream from top
                                                                   // to bottom
                mv.fill(100, 255, 100, alpha); // changes character colour to green with alpha
                if (i == numChars - 1) {
                    mv.fill(1, 1, 255); // changes the colour of the last character in the stream
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
        int charType = mv.round(mv.random(1)); // randomly selects the character type
        char newChar;
        if (charType == 0) {
            newChar = (char) mv.round(mv.random(48, 90)); // this generates ASCII characters 0-Z using decimal numbers
        } else {
            newChar = (char) mv.round(mv.random(12449, 12615)); // this generates kanji characters using decimal numbers
        }
        return newChar;
    }

    class CharInfo { // inner class to store character information
        public int changeSpeed;
        public int lastUpdateTime;
        int x, y; // character positions
        float speed; // character speed
        char c; // character value

        CharInfo(int x, int y, float speed, char c) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.c = c;
        }
    }
}
