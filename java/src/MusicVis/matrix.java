package MusicVis;

import processing.core.PFont;
import java.util.ArrayList;

public class matrix {
    PFont theFont;
    ArrayList<Stream> streams;
    MyVisual mv;
    int lastCharTime = 0;

    public matrix(MyVisual mv) {
        this.mv = mv;
    }


    public void render() {
        theFont = mv.createFont("Arial Unicode MS", 20);
        mv.textFont(theFont);

        streams = new ArrayList<>();

        for (int x = 10; x < mv.width; x += 20) {
            streams.add(new Stream(x));
        }
        int currentTime = mv.millis(); // Get the current time in milliseconds

        // Check if one second has passed since the last character appeared
        if (currentTime - lastCharTime >= 1000) {
            draw();
        }

    }

    public void draw() {

        for (Stream s : streams) {
            s.update();
        }
    }

    class Char {
        char theChar;
        int x, y;

        Char(int tempX, int tempY) {
            x = tempX;
            y = tempY;
            getRandomChar();
        }

        void show() {
            mv.fill(80, 250, 255, 255);
            mv.text(String.valueOf(theChar), x, y);

            if (y >= mv.height - 20) {
                mv.fill(0,255,0);
                mv.text(String.valueOf(theChar), x, y);
            }
        }

        void getRandomChar() {
            int charType = (int) Math.round(mv.random(1));
            if (charType == 0) {
                int rndChar = (int) Math.round(mv.random(48, 90));
                theChar = (char) rndChar;
            } else if (charType == 1) {
                int rndChar = (int) Math.round(mv.random(12449, 12615));
                theChar = (char) rndChar;
            }
        }
    }

    class Stream {
        ArrayList<Char> chars;
        int numChar, speed, tempX;

        Stream(int tempX) {
            this.tempX = tempX;
            chars = new ArrayList<>();
            numChar = mv.height / 20; // Adjusted to fill the height of the screen
            speed = (int) Math.round(mv.random(80, 100));

            for (int y = 0; y < mv.height; y += 20) { // Adjusted to fill the height of the screen
                chars.add(new Char(tempX, y));
            }
        }


    void update() {

        // Iterate through characters and update their positions
        for (int i = 0; i < chars.size(); i++) {
            float alpha = mv.map(i, 0, chars.size() - 1, 60, 255);
            mv.fill(0, 250, 75, (int) alpha);
            if (i == chars.size() - 1) {
                mv.fill(255);
            }
            chars.get(i).show();

            // Make characters fall down gradually
            chars.get(i).y += 1; // Adjust this value to control the falling speed
            if (i == chars.size() - 1 && (mv.millis() / speed) % 2 == 0) {
                chars.get(i).getRandomChar();
            } else if (i < chars.size() - 1) {
                chars.get(i).theChar = chars.get(i + 1).theChar;
            }
        }

        // Reset characters' positions when they reach the bottom
        if (chars.get(0).y > mv.height) {
            for (int i = 0; i < chars.size(); i++) {
                chars.get(i).y = ((chars.size() - 1) - i) * -20;
            }
        }
    }



    }
}
