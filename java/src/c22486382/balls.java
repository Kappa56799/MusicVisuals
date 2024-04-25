package c22486382;
import MusicVis.MyVisual;

public class balls {
    MyVisual mv;
    int numBalls = 20;
    float spring = 0.05f;
    float gravity = 0.03f;
    float friction = -0.9f;
    Ball[] balls = new Ball[numBalls];

    public balls(MyVisual mv) {
        this.mv = mv;
        setup(); // Call setup function to initialize balls
    }

    void setup() {
        for (int i = 0; i < numBalls; i++) {
            balls[i] = new Ball(mv.random(mv.width), mv.random(mv.height), mv.random(30, 70), i, balls);
        }
        mv.noStroke();
        mv.fill(255, 204);
    }

    public void render() {
        for (Ball ball : balls) {
            if (ball != null) {
                ball.collide();
                ball.move();
                ball.display();
            }
        }
    }

    class Ball {
        float x, y;
        float diameter;
        float vx = 0;
        float vy = 0;
        int id;
        Ball[] others;
        
        Ball(float xin, float yin, float din, int idin, Ball[] oin) {
            x = xin;
            y = yin;
            diameter = din;
            id = idin;
            others = oin;
        } 
        
        void collide() {
            for (int i = id + 1; i < numBalls; i++) {
                if (others[i] != null) { // Check if other ball is not null
                    float dx = others[i].x - x;
                    float dy = others[i].y - y;
                    float distance = mv.sqrt(dx*dx + dy*dy);
                    float minDist = others[i].diameter/2 + diameter/2;
                    if (distance < minDist) { 
                        float angle = mv.atan2(dy, dx);
                        float targetX = x + mv.cos(angle) * minDist;
                        float targetY = y + mv.sin(angle) * minDist;
                        float ax = (targetX - others[i].x) * spring;
                        float ay = (targetY - others[i].y) * spring;
                        vx -= ax;
                        vy -= ay;
                        others[i].vx += ax;
                        others[i].vy += ay;
                    }
                }
            }
        }
            void move() {
                vy += gravity;
                x += vx;
                y += vy;
                if (x + diameter/2 > mv.width) {
                    x = mv.width - diameter/2;
                    vx *= friction; 
                }
                else if (x - diameter/2 < 0) {
                    x = diameter/2;
                    vx *= friction;
                }
                if (y + diameter/2 > mv.height/2) { // Only move if y is in the top half of the screen
                    y = mv.height/2 - diameter/2;
                    vy *= friction; 
                } 
                else if (y - diameter/2 < 0) {
                    y = diameter/2;
                    vy *= friction;
                }
            }
            void display() {
            mv.ellipse(x, y, diameter, diameter);
        }
    }
}
