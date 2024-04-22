package MusicVis;

import ie.tudublin.*;

public class MyVisual extends Visual {
    //Tree tree;
    cube cube;
    Raindrops raindrops;
    Platforms platforms;

    public void settings() {
        //size(1024, 500);

        // Use this to make fullscreen
        fullScreen();

        // Use this to make fullscreen and use P3D for 3D graphics
        // fullScreen(P3D, SPAN);
    }

    public void setup() {
        startMinim();

        // Call loadAudio to load an audio file to process
        loadAudio("music/Snake_Eyes_Faint.mp3");

        //Call this instead to read audio from the microphone
        //startListening();

        //tree = new Tree(this);
        cube = new cube(this);
        raindrops = new Raindrops(this);
        platforms = new Platforms(this);
    }

    public void keyPressed() {
        if (key == ' ') {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        }
    }

    public void draw() {
        raindrops.render();
        cube.render();
        platforms.render();
            
    }
}
