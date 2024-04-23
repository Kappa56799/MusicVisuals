package MusicVis;

import processing.core.PApplet;
import processing.core.PFont;

import java.util.ArrayList;

public class matrix extends PApplet {

    PFont theFont;
    ArrayList<Stream> streams;
    MyVisual mv;

    public static void main(String[] args) {
        PApplet.main("Matrix");
    }


    public void render() {
        theFont = createFont("Arial Unicode MS", 20);
        textFont(theFont);

        streams = new ArrayList<>();

        for (int x = 10; x < width; x += 20) {
            streams.add(new Stream(x));
        }

        draw();
    }

    public void draw() {
        background(0, 50, 0);

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
            fill(0, 250, 75, 255);
            text(String.valueOf(theChar), x, y);

            if (y >= height - 20) {
                fill(255);
                text(String.valueOf(theChar), x, y);
            }
        }

        void getRandomChar() {
            int charType = (int) Math.round(random(1));
            if (charType == 0) {
                int rndChar = (int) Math.round(random(48, 90));
                theChar = (char) rndChar;
            } else if (charType == 1) {
                int rndChar = (int) Math.round(random(12449, 12615));
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
            numChar = height / 20; // Adjusted to fill the height of the screen
            speed = (int) Math.round(random(10, 60));

            for (int y = 0; y < height; y += 20) { // Adjusted to fill the height of the screen
                chars.add(new Char(tempX, y));
            }
        }

        void update() {
            for (int i = 0; i < chars.size(); i++) {
                float alpha = map(i, 0, chars.size() - 1, 60, 255);
                fill(0, 250, 75, (int) alpha);
                if (i == chars.size() - 1) {
                    fill(255);
                }
                chars.get(i).show();

                if (i == chars.size() - 1 && (millis() / speed) % 2 == 0) {
                    chars.get(i).getRandomChar();
                } else if (i < chars.size() - 1) {
                    chars.get(i).theChar = chars.get(i + 1).theChar;
                }
            }

            if (chars.get(0).y > height) {
                for (int i = 0; i < chars.size(); i++) {
                    chars.get(i).y = ((chars.size() - 1) - i) * -20;
                }
            }
        }
    }
}
