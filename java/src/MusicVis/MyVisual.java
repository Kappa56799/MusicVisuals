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
    matrix matrix;
    rotatingcube rotatingcube;
    randomline randomline;


    public void settings() {
        //size(1024, 500);

        // Use this to make fullscreen
        //fullScreen();

        // Use this to make fullscreen and use P3D for 3D graphics
        fullScreen(P3D, SPAN);
    }

    public void setup() {
        startMinim();

        // Call loadAudio to load an audio file to process
        loadAudio("music/Snake_Eyes_Faint.mp3");
        colorMode(HSB, 360, 100, 100);
        //Call this instead to read audio from the microphone
        //startListening();

        getAudioPlayer().cue(0);
        getAudioPlayer().play();
        //tree = new Tree(this);
        cube = new cube(this);
        raindrops = new Raindrops(this);
        platforms = new Platforms(this);
        wave = new wave(this);
        tree = new Tree(this);
        grids = new grids(this);
        matrix = new matrix(this);
        rotatingcube = new rotatingcube(this);
        randomline = new randomline(this);

    }




int current = 0;
boolean spacePressed = false;
int jumpDuration = 60; // Adjust this value to change the duration of the jump
int jumpTimer = 0;

public void keyPressed() {
    if (key == '1') {
        raindrops.render();
        current = 1;
    } else if (key == '2') {
        tree.render();
        current = 2;
    } else if (key == '3') {
        grids.render();
        rotatingcube.render();
        current = 3;
    } else if (key == '4') {
        matrix.render();
        current = 4;
    } else if (key == '7') {
        randomline.render();
        current = 7;
    } else if (key == ' ') {
        spacePressed = true;
        cube.update();
    }
}

public void keyReleased() {
    if (key == ' ') {
        spacePressed = false;
        jumpTimer = jumpDuration; // Set the jump timer to the duration
    }
}

public void draw() {
    background(0);
    if (spacePressed) {
        cube.update();
    } else if (jumpTimer > 0) {
        cube.update(); // Update cube position even if space bar isn't pressed
        jumpTimer--; // Decrease the jump timer
    }
    
    if (current == 1) {
        raindrops.render();
    } else if (current == 2) {
        tree.render();
    } else if (current == 3) {
        grids.render();
        rotatingcube.render();
    } else if (current == 4) {
        matrix.render();
    } else if (current == 7) {
        randomline.render();
    }
    
    // Render common elements
    wave.render();
    cube.render();
    platforms.render();
}




}
