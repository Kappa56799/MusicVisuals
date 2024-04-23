package MusicVis;

import ie.tudublin.*;

public class MyVisual extends Visual {
    //Tree tree;
    cube cube;
    Raindrops raindrops;
    Platforms platforms;
    wave wave;
    Tree tree;
    grids grids;


    public void settings() {
        //size(1024, 500);

        // Use this to make fullscreen
        fullScreen();

        // Use this to make fullscreen and use P3D for 3D graphics
        //fullScreen(P3D, SPAN);
    }

    public void setup() {
        startMinim();

        // Call loadAudio to load an audio file to process
        loadAudio("music/Snake_Eyes_Faint.mp3");
        colorMode(HSB, 360, 100, 100);
        //Call this instead to read audio from the microphone
        //startListening();

        //tree = new Tree(this);
        cube = new cube(this);
        raindrops = new Raindrops(this);
        platforms = new Platforms(this);
        wave = new wave(this);
        tree = new Tree(this);
        grids = new grids(this);

    }

    public void keyPressed() {
        if (key == ' ') {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        }
    }

    public void draw() {
        background(0);

        if (key == '2') {
            tree.render();
            
        }

        if (key == '1') {
            raindrops.render();
        }

        if (key == '3'){
            
        }


        if (key == '3') {
            grids.render();
        }
        wave.render();
        cube.render();
        platforms.render();


            
    }
}
