import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Player {
    public Vector2D position;
    public Vector2D velocity;
    private Random random;
    private List<Vector2D> verties;
    private Polygon polygon;

    public Player() {
        this.position = new Vector2D();
        this.velocity = new Vector2D();
        this.random = new Random();
        this.verties = Arrays.asList(
                new Vector2D(),
                new Vector2D(0, 16),
                new Vector2D(20, 8)
        );
        this.polygon = new Polygon();
    }

    public void run() {
        this.position.addUp(this.velocity);
        this.backToScreen();
    }

    private void backToScreen() {
        if (this.position.x > 1024) {
            this.position.set(0, this.random.nextInt(600));
        }
        if (this.position.x < 0) {
            this.position.set(1024, this.random.nextInt(600));
        }
        if (this.position.y > 600) {
            this.position.set(this.random.nextInt(1024), 0);
        }
        if (this.position.y < 0) {
            this.position.set(this.random.nextInt(1024), 600);
        }
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.RED);

        this.updatePolygon();
        graphics.fillPolygon(this.polygon);
    }

    public void updatePolygon() {
        this.polygon.reset();

        Vector2D center = this.verties
                .stream()
                .reduce(new Vector2D(), (v1, v2) -> v1.add(v2))
                .multiply(1.0f / this.verties.size());

        Vector2D translate = this.position.subtract(center);

        this.verties.stream()
                .map(vector2D -> vector2D.add(translate))
                .forEach(vertex -> polygon.addPoint((int)vertex.x, (int)vertex.y));
    }
}
