package MusicVis;

public class rotatingcube {
    MyVisual mv;
    public rotatingcube(MyVisual mv) {
        this.mv = mv;
    }
    float a;                 // Angle of rotation
    float offset = (float)(mv.PI/24.0);  // Angle offset between boxes
    int num = 12;            // Number of boxes

    void render() {
        mv.pushMatrix();
        mv.lights();


        mv.translate(mv.width/2, mv.height/2); 

        for(int i = 0; i < num; i++) {
            float gray = mv.map(i, 0, num-1, 0, 255);
            mv.pushMatrix();
            mv.fill(gray);
            mv.rotateY(a + offset*i);
            mv.rotateX(a/2 + offset*i);
            mv.box(200);
            mv.popMatrix();
        }

        a += 0.01;
        mv.popMatrix();
    } 
}
