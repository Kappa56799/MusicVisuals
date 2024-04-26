package c22491452;

import MusicVis.MyVisual;
import java.util.ArrayList;

public class nodes {

    MyVisual mv;
    ArrayList<Particle> particles;
    int numParticles;
    int minDistance;

    public nodes(MyVisual mv) {
        this.mv = mv;
        particles = new ArrayList<>();
        numParticles = 10; // Number of particles
        minDistance = 50; // Minimum distance for connection
        initializeParticles();
    }

    public void initializeParticles() {
        for (int i = 0; i < numParticles; i++) {
            Particle p = new Particle(
                    mv.random(mv.width),
                    mv.random(mv.height),
                    mv.random(10, 20));
            particles.add(p);
        }
    }

    public void render() {
        mv.background(20);
        mv.smooth();

        // Draw connections between particles
        for (int i = 0; i < particles.size(); i++) {
            Particle p1 = particles.get(i);
            for (int j = i + 1; j < particles.size(); j++) {
                Particle p2 = particles.get(j);
                float distance = mv.dist(p1.x, p1.y, p2.x, p2.y);
                if (distance < minDistance) {
                    mv.stroke(255);
                    mv.strokeWeight(1);
                    mv.line(p1.x, p1.y, p2.x, p2.y);
                }
            }
        }

        // Draw particles
        for (Particle p : particles) {
            p.update();
            p.draw();
        }
    }

    class Particle {
        float x, y;
        float rx, ry;
        float rT;
        float size;

        Particle(float x, float y, float size) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.rx = mv.random(20, 100);
            this.ry = this.rx;
            if (mv.random(100) < 50) {
                rx *= -1;
            }
            this.rT = mv.random(500, 5000);
        }

        void update() {
            float t = mv.millis() / rT;
            x = (int) (x + rx * mv.cos(t));
            y = (int) (y + ry * mv.sin(t));
        }

        void draw() {
            mv.noStroke();
            mv.fill(200);
            mv.ellipse(x, y, size, size);
        }
    }

}
