package ie.tudublin;

import MusicVis.MyVisual;
import c22376553.*;
import c22491452.*;
import c22486382.*;

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
