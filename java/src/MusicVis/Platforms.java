package MusicVis;


import java.util.ArrayList;

public class Platforms {
    MyVisual mv;
    ArrayList<Platform> platforms;
    float gapWidth = 200; // Width of the gap between platforms
    float speed = 20; // Speed of the platform movement
    float minWidth = 600; // Minimum width of the platform
    float maxWidth = 1600; // Maximum width of the platform
    boolean hasSpawnedNewThisFrame = false; // Flag to track whether a new platform has been spawned in this frame

    public Platforms(MyVisual mv) {
        this.mv = mv;
        platforms = new ArrayList<>();
        platforms.add(new Platform(mv, mv.width / 2, mv.height / 2 + 280, mv.random(minWidth, maxWidth)));
    }

    public void render() {
        mv.stroke(0); // Set stroke color to white
        mv.hint(mv.DISABLE_DEPTH_TEST);

        for (int i = platforms.size() - 1; i >= 0; i--) {
            Platform platform = platforms.get(i);
            platform.move(speed);

            if (platform.isVisible()) {
                platform.display();
            } else {
                platforms.remove(i);
            }

            // Check if the last platform is ready to spawn a new one and no new platform has been spawned in this frame
            if (i == platforms.size() - 1 && !hasSpawnedNewThisFrame && platform.shouldSpawnNew()) {
                float newX = platform.getX() + platform.getWidth() + gapWidth;
                platforms.add(new Platform(mv, newX, platform.getHeight(), mv.random(minWidth, maxWidth)));
                hasSpawnedNewThisFrame = true; // Set the flag to true after spawning a new platform
            }
        }

        // Reset the flag for the next frame
        hasSpawnedNewThisFrame = false;

        mv.hint(mv.ENABLE_DEPTH_TEST);
    }
}

class Platform {
    MyVisual mv;
    float x; // X position of the platform
    float y; // Y position of the platform
    float width; // Width of the platform

    public Platform(MyVisual mv, float x, float y, float width) {
        this.mv = mv;
        this.x = x;
        this.y = y;
        this.width = width;
    }

    public void move(float speed) {
        x -= speed;
    }

    public void display() {
        mv.fill(100); // Set platform color to gray
        mv.rectMode(mv.CENTER); // Set rectangle mode to center
        mv.stroke(0, 0, 100);
        mv.rect(x, y, width, 20); // Draw platform
    }

    public boolean isVisible() {
        return x + width / 2 > 0 && x - width / 2 < mv.width;
    }

    public boolean shouldSpawnNew() {
        return x + width / 2 <= mv.width;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return y;
    }
    
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}

