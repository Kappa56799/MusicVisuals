package ie.tudublin;

import MusicVis.*;

public class Main {

    public void startUI() {
        String[] a = { "MAIN" };
        processing.core.PApplet.runSketch(a, new MyVisual());
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.startUI();
    }
}
