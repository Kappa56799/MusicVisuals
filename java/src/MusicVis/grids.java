package MusicVis;

public class grids {
    MyVisual mv;
    public grids(MyVisual mv) {
        this.mv = mv;
    }

    void render() {
        mv.pushStyle();
        mv.hint(mv.DISABLE_DEPTH_TEST);

        defineLights();
        for (int x = 0; x <= mv.width; x += 60) {
            for (int y = 0; y <= mv.height; y += 60) {
                mv.pushMatrix();
                mv.translate(x, y);
                mv.rotateY(mv.map(mv.mouseX, 0, mv.width, 0, mv.PI));
                mv.rotateX(mv.map(mv.mouseY, 0, mv.height, 0, mv.PI));
                mv.box(90);
                mv.popMatrix();
            }
        }
        mv.hint(mv.ENABLE_DEPTH_TEST);
        mv.popStyle();
    }
    
        void defineLights() {
        mv.ambientLight(0, 0, 100);
        // Orange point light on the right
        mv.pointLight(150, 100, 0,   // Color
                   200, -150, 0); // Position
    
        // Blue directional light from the left
        mv.directionalLight(0, 102, 255, // Color
                         1, 0, 0);    // The x-, y-, z-axis direction
    
        // Yellow spotlight from the front
        mv.spotLight(255, 255, 109,  // Color
                (float)0, (float) 40, (float) 200,     // Position
                (float) 0, (float) -0.5, (float) -0.5,  // Direction
                (float) (mv.PI / 2), (float) 2);     // Angle, concentration
        }
}
