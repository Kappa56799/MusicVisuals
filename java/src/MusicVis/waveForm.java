package MusicVis;

public class waveForm {

    MyVisual mv;
    int totalPts = 10000;
    float steps = totalPts + 1;

    void setup() {
    mv.stroke(255);
    mv.frameRate(1);
    } 

    void draw() {
    float rand = 0;
    for  (int i = 1; i < steps; i++) {
        mv.point( (mv.width/steps) * i, (mv.height/2) + mv.random(-rand, rand) );
        rand += mv.random(-5, 5);
    }
}


}