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
        numParticles = 1;
        minDistance = 300;
    }

    public void render() {
        mv.smooth();
        int min = 100;

        Particle p = new Particle(mv.random(min, mv.width - min), mv.random(min, mv.height - min), 5);
        particles.add(p);
        p.update();

        drawParticles();
    }

void drawParticles() {
    mv.background(20);

    // Iterate over each particle in the list
    for (int i = 0; i < particles.size(); i++) {
        Particle p1 = particles.get(i); // Get the current particle

        // Iterate over subsequent particles in the list
        for (int j = i + 1; j < particles.size(); j++) {
            Particle p2 = particles.get(j); // Get the next particle

            // Calculate the distance between the two particles
            float distance = mv.dist(p1.x, p1.y, p2.x, p2.y);

            // Check if the distance is less than the minimum distance
            if (distance < minDistance) {
                // Calculate stroke color based on distance
                mv.stroke(mv.color(255, 0, 0), 255 - ((distance / minDistance) * 255));
                mv.strokeWeight(1);
                mv.line(p1.x, p1.y, p2.x, p2.y); // Draw a line between the two particles
            }
        }
    }

    // Draw each particle

    // Assuming particles is an array or a collection
    for (int i = 0; i < 1; i++) {
        Particle p = particles.get(i);
        p.draw();
        p.update();
    }

}

    class Particle {
        float px, py;
        float x, y;
        float rx, ry;
        float rT;
        float size;

        Particle(float x, float y, float size) {
            this.px = x;
            this.py = y;
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
            x = (int) (px + rx * mv.cos(t));
            y = (int) (py + ry * mv.sin(t));
        }

        void draw() {
            mv.noStroke();
            mv.fill(200);
            mv.ellipse(x, y, size, size);
        }
    }
}

