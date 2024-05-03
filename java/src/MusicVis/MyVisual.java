package MusicVis;

import ie.tudublin.*;
import c22376553.*; // Kacper Palka
import c22491452.*; //Pavels Strelnikovs
import c22486382.*;

public class MyVisual extends rft {
    // Tree tree;
    square square;
    Raindrops raindrops;
    Platforms platforms;
    wave wave;
    Tree tree;
    grids grids;
    matrix matrix;
    rotatingcube rotatingcube;
    randomline randomline;
    rainbow rainbow;
    movingobject movingobject;
    bigwave bigwave;
    Snowflake snowflake;
    birds birds;
    balls balls;
    FlowerBloom flowerBloom;
    spiral spiral;
    IteratingDots iteratingDots;

    public float smothedAmplitude = 0;

    public void settings() {
        // size(1024, 500);

        // Use this to make fullscreen
        // fullScreen();

        // Use this to make fullscreen and use P3D for 3D graphics
        fullScreen(P3D, SPAN);
    }

    public void setup() {
        startMinim();

        // Call loadAudio to load an audio file to process
        loadAudio("music/Snake_Eyes_Faint.mp3");
        colorMode(HSB, 360, 100, 100);
        // Call this instead to read audio from the microphone
        // startListening();

        getAudioPlayer().cue(0);
        getAudioPlayer().play();

        square = new square(this);
        raindrops = new Raindrops(this);
        platforms = new Platforms(this);
        wave = new wave(this);
        tree = new Tree(this);
        grids = new grids(this);
        matrix = new matrix(this);
        rotatingcube = new rotatingcube(this);
        randomline = new randomline(this);
        rainbow = new rainbow(this);
        movingobject = new movingobject(this);
        bigwave = new bigwave(this);
        snowflake = new Snowflake(this);
        birds = new birds(this);
        balls = new balls(this);
        flowerBloom = new FlowerBloom(this);
        spiral = new spiral(this);
        iteratingDots = new IteratingDots(this);

    }

    int current = 0;
    boolean spacePressed = false;
    int jumpDuration = 60; // Adjust this value to change the duration of the jump
    int jumpTimer = 0;

    public void keyPressed() {
        if (key == '1') {
            snowflake.render();
            movingobject.render();
            raindrops.render();
            current = 1;
        } else if (key == '2') {
            tree.render();
            birds.render();
            current = 2;
        } else if (key == '3') {
            grids.render();
            rotatingcube.render();
            current = 3;
        } else if (key == '4') {
            matrix.render();
            flowerBloom.render();
            current = 4;
        } else if (key == '5') {
            rainbow.render();
            spiral.render();
            current = 5;
        } else if (key == '6') {
            iteratingDots.render();
            current = 6;
        } else if (key == '7') {
            randomline.render();
            balls.render();
            bigwave.render();
            current = 7;
        } else if (key == ' ') {
            spacePressed = true;
            square.update();
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
            square.update();
        } else if (jumpTimer > 0) {
            square.update(); // Update cube position even if space bar isn't pressed
            jumpTimer--; // Decrease the jump timer
        }

        if (current == 1) {
            snowflake.render();
            movingobject.render();
            raindrops.render();
        } else if (current == 2) {
            tree.render();
            birds.render();
        } else if (current == 3) {
            grids.render();
            rotatingcube.render();
        } else if (current == 4) {
            matrix.render();
            flowerBloom.render();
        } else if (current == 5) {
            rainbow.render();
            spiral.render();
        } else if (current == 6) {
            iteratingDots.render();
        } else if (current == 7) {
            randomline.render();
            balls.render();
            bigwave.render();
        }

        // Render common elements

        wave.render();
        square.render();
        platforms.render();
    }

}
