package MusicVis;

import java.util.ArrayList; // Import ArrayList class
import processing.core.PVector; // Import PVector class


public class birds {
    MyVisual mv;
    Flock flock;
    int maxBoids = 500; // Maximum number of boids

    public birds(MyVisual mv) {
        this.mv = mv;
        flock = new Flock();
    }

    public void render() {
        mv.pushMatrix();
        mv.hint(mv.DISABLE_DEPTH_TEST);
        int width = mv.width; // Define width
        int height = mv.height; // Define height

        // Add boids only if total count is less than 150
        if (flock.getCount() < maxBoids) {
            // Calculate how many boids to add
            int numToAdd = maxBoids - flock.getCount();
            // Add boids
            for (int i = 0; i < numToAdd; i++) {
                flock.addBoid(new Boid(width/2, height/2, mv));
            }
        }
        flock.run();
        mv.hint(mv.ENABLE_DEPTH_TEST);
        mv.popMatrix();
    }
}


class Boid {
    MyVisual mv;
    PVector position;
    PVector velocity;
    PVector acceleration;
    float r;
    float maxforce;    // Maximum steering force
    float maxspeed;    // Maximum speed
    long lastColorChangeTime = 0; // Variable to store the last time color was changed
    int interval = 1500; // Interval in milliseconds for color change
    int currentColor; // Variable to store the current color
    int nextColor; // Variable to store the next color

    Boid(float x, float y, MyVisual mv) {
        this.mv = mv;
        acceleration = new PVector(0, 0);
        float angle = mv.random(mv.TWO_PI);
        velocity = new PVector(mv.cos(angle), mv.sin(angle));
        position = new PVector(x, y);
        r = (float)8.0;
        maxspeed = 8;
        maxforce = (float)0.03;
    }

    
void run(ArrayList<Boid> boids) {
    flock(boids);
    update();
    borders();
    render();
  }

  void applyForce(PVector force) {
    // We could add mass here if we want A = F / M
    acceleration.add(force);
  }

  // We accumulate a new acceleration each time based on three rules
  void flock(ArrayList<Boid> boids) {
    PVector sep = separate(boids);   // Separation
    PVector ali = align(boids);      // Alignment
    PVector coh = cohesion(boids);   // Cohesion
    // Arbitrarily weight these forces
    sep.mult(1.5f);
    ali.mult(1.0f);
    coh.mult(1.0f);
    // Add the force vectors to acceleration
    applyForce(sep);
    applyForce(ali);
    applyForce(coh);
  }

  // Method to update position
  void update() {
    // Update velocity
    velocity.add(acceleration);
    // Limit speed
    velocity.limit(maxspeed);
    position.add(velocity);
    // Reset accelertion to 0 each cycle
    acceleration.mult(0);
  }

  // A method that calculates and applies a steering force towards a target
  // STEER = DESIRED MINUS VELOCITY
  PVector seek(PVector target) {
    PVector desired = PVector.sub(target, position);  // A vector pointing from the position to the target
    // Scale to maximum speed
    desired.normalize();
    desired.mult(maxspeed);

    // Above two lines of code below could be condensed with new PVector setMag() method
    // Not using this method until Processing.js catches up
    // desired.setMag(maxspeed);

    // Steering = Desired minus Velocity
    PVector steer = PVector.sub(desired, velocity);
    steer.limit(maxforce);  // Limit to maximum steering force
    return steer;
  }

  void render() {
    // Draw a triangle rotated in the direction of velocity
    float theta = velocity.heading2D() + mv.radians(90f);
    // heading2D() above is now heading() but leaving old syntax until Processing.js catches up
    
    float amt = (float)(mv.millis() - lastColorChangeTime) / interval;
    if (mv.millis() - lastColorChangeTime >= interval) {
        // Change color
        currentColor = mv.color(mv.random(70, 500), 100, 100);
        nextColor = mv.color(mv.random(70,500), mv.random(70,500), mv.random(70,500));
        // Update last color change time
        lastColorChangeTime = mv.millis();
    }
    mv.fill(200);
    mv.stroke(mv.lerpColor(currentColor, nextColor, amt));
    mv.pushMatrix();
    mv.translate(position.x, position.y);
    mv.rotate(theta);
    mv.beginShape(mv.TRIANGLES);
    mv.vertex(0, -r*2);
    mv.vertex(-r, r*2);
    mv.vertex(r, r*2);
    mv.endShape();
    mv.popMatrix();
  }

  // Wraparound
  void borders() {
    if (position.x < -r) position.x = mv.width+r;
    if (position.y < -r) position.y = mv.height+r;
    if (position.x > mv.width+r) position.x = -r;
    if (position.y > mv.height+r) position.y = -r;
  }

  // Separation
  // Method checks for nearby boids and steers away
  PVector separate (ArrayList<Boid> boids) {
    float desiredseparation = 80.0f;
    PVector steer = new PVector(0, 0, 0);
    int count = 0;
    // For every boid in the system, check if it's too close
    for (Boid other : boids) {
      float d = PVector.dist(position, other.position);
      // If the distance is greater than 0 and less than an arbitrary amount (0 when you are yourself)
      if ((d > 0) && (d < desiredseparation)) {
        // Calculate vector pointing away from neighbor
        PVector diff = PVector.sub(position, other.position);
        diff.normalize();
        diff.div(d);        // Weight by distance
        steer.add(diff);
        count++;            // Keep track of how many
      }
    }
    // Average -- divide by how many
    if (count > 0) {
      steer.div((float)count);
    }

    // As long as the vector is greater than 0
    if (steer.mag() > 0) {
      // First two lines of code below could be condensed with new PVector setMag() method
      // Not using this method until Processing.js catches up
      // steer.setMag(maxspeed);

      // Implement Reynolds: Steering = Desired - Velocity
      steer.normalize();
      steer.mult(maxspeed);
      steer.sub(velocity);
      steer.limit(maxforce);
    }
    return steer;
  }

  // Alignment
  // For every nearby boid in the system, calculate the average velocity
  PVector align (ArrayList<Boid> boids) {
    float neighbordist = 50;
    PVector sum = new PVector(0, 0);
    int count = 0;
    for (Boid other : boids) {
      float d = PVector.dist(position, other.position);
      if ((d > 0) && (d < neighbordist)) {
        sum.add(other.velocity);
        count++;
      }
    }
    if (count > 0) {
      sum.div((float)count);
      // First two lines of code below could be condensed with new PVector setMag() method
      // Not using this method until Processing.js catches up
      // sum.setMag(maxspeed);

      // Implement Reynolds: Steering = Desired - Velocity
      sum.normalize();
      sum.mult(maxspeed);
      PVector steer = PVector.sub(sum, velocity);
      steer.limit(maxforce);
      return steer;
    } 
    else {
      return new PVector(0, 0);
    }
  }

  // Cohesion
  // For the average position (i.e. center) of all nearby boids, calculate steering vector towards that position
  PVector cohesion (ArrayList<Boid> boids) {
    float neighbordist = 50;
    PVector sum = new PVector(0, 0);   // Start with empty vector to accumulate all positions
    int count = 0;
    for (Boid other : boids) {
      float d = PVector.dist(position, other.position);
      if ((d > 0) && (d < neighbordist)) {
        sum.add(other.position); // Add position
        count++;
      }
    }
    if (count > 0) {
      sum.div(count);
      return seek(sum);  // Steer towards the position
    } 
    else {
      return new PVector(0, 0);
    }
  }
}



class Flock {
    ArrayList<Boid> boids; // An ArrayList for all the boids

    Flock() {
        boids = new ArrayList<Boid>(); // Initialize the ArrayList
    }

    void run() {
        for (Boid b : boids) {
            b.run(boids);  // Passing the entire list of boids to each boid individually
        }
    }

    void addBoid(Boid b) {
        boids.add(b);
    }

    int getCount() {
        return boids.size(); // Return the count of boids
    }
}
