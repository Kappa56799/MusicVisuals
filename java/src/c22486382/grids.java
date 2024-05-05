package c22486382;

import MusicVis.MyVisual;

public class grids {
    int lastColorChange = 0;
    int colorChangeInterval = 1500;
    int currentColor, nextColor;
    MyVisual mv;

    public grids(MyVisual mv) {
        this.mv = mv;
    }

    public void render() {
        mv.pushStyle();
        mv.hint(mv.DISABLE_DEPTH_TEST);

        mv.stroke(0);
        defineLights();

        float amt = (float) (mv.millis() - lastColorChange) / colorChangeInterval;
        if (amt >= 1.0) {
            currentColor = nextColor;
            nextColor = mv.color(mv.random(70, 500), mv.random(70, 500), mv.random(70, 500));
            lastColorChange = mv.millis();
            amt = 0.0f;
        }

        for (int x = 0; x <= mv.width; x += 60) {
            for (int y = 0; y <= mv.height; y += 60) {
                mv.pushMatrix();
                mv.translate(x, y);
                mv.rotateY(mv.map(mv.mouseX, 0, mv.width, 0, mv.PI));
                mv.rotateX(mv.map(mv.mouseY, 0, mv.height, 0, mv.PI));
                mv.box(90);
                mv.fill(mv.lerpColor(currentColor, nextColor, amt));
                mv.popMatrix();
            }
        }
        mv.hint(mv.ENABLE_DEPTH_TEST);
        mv.popStyle();
    }

    void defineLights() {
        mv.ambientLight(0, 0, 100);

        mv.pointLight(150, 100, 0, // Color
                200, -150, 0); // Position

        mv.directionalLight(0, 102, 255, // Color
                1, 0, 0); // The x-, y-, z-axis direction

        mv.spotLight(255, 255, 109, // Color
                0f, 40f, 200f, // Position
                0f, -0.5f, -0.5f, // Direction
                (float) (mv.PI / 2), 2f); // Angle, concentration
    }
}
