import java.awt.*;

public class Bullet {
    public Vector2D position;
    public Vector2D velocity;
    private Renderer renderer;

    public Bullet() {
        this.position = new Vector2D();
        this.velocity = new Vector2D();
        this.renderer = new ImageRenderer("resources/images/circle.png", 6, 6);
    }

    public void run() {
        this.position.addUp(this.velocity);
    }


    public void render(Graphics graphics) {
        this.renderer.render(graphics, this.position);
    }
}
