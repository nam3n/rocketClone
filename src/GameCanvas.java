import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameCanvas extends JPanel {
    BufferedImage backBuffered;
    Graphics graphics;

    Background background;
    List<Star> stars;
    List<Enemy> enemies;
    public Player player;
    private Random random = new Random();
    private int countStar = 0;
    private int countEnemy = 0;

    public GameCanvas() {
        this.setSize(1024, 600);
        this.setupBackBuffered();
        this.setupCharacter();

        this.setVisible(true);
    }

    private void setupBackBuffered() {
        this.backBuffered = new BufferedImage(1024, 600, BufferedImage.TYPE_4BYTE_ABGR);
        this.graphics = this.backBuffered.getGraphics();
    }

    private void setupCharacter() {
        this.background = new Background(0,0, 1024, 600, Color.BLACK);
        this.enemies = new ArrayList<>();
        this.player = new Player();
        this.player.position.set(500, 300);
        this.setupStar();
    }

    private void setupStar() {
        this.stars = new ArrayList<>();

    }

    @Override
    protected void paintComponent(Graphics g) {
        // lat backbuffered
        g.drawImage(this.backBuffered, 0, 0, null);
    }

    public void renderAll() {
        this.background.render(this.graphics);

        this.stars.forEach(star -> star.render(graphics));

        this.enemies.forEach(enemy -> enemy.render(graphics));

        this.player.render(this.graphics);

        this.repaint();
    }

    public void runAll() {
        this.createStar();
        this.stars.forEach(star -> star.run());

        this.createEnemy();
        this.enemies.forEach(enemy -> enemy.run());

        this.player.run();
    }

    private void createStar() {
        if (this.countStar == 30) {
            Star star = new Star(
                    this.loadImage("resources/images/star.png"),
                    1024,
                    this.random.nextInt(600),
                    5,
                    5,
                    -(this.random.nextInt(3) + 1),
                    0
            );
            this.stars.add(star);
            this.countStar = 0;
        } else {
            this.countStar += 1;
        }

    }

    private void createEnemy() {
        if (this.countEnemy == 200) {
            int dau = this.random.nextInt(2);
            if (dau == 0) dau = -1;
            else dau = 1;
            int velocityX = dau * (this.random.nextInt(4) + 1);

            dau = this.random.nextInt(2);
            if (dau == 0) dau = -1;
            else dau = 1;
            int velocityY = dau * (this.random.nextInt(4) + 1);

            Enemy enemy = new Enemy(
                    this.loadImage("resources/images/circle.png"),
                    this.random.nextInt(1024),
                    this.random.nextInt(600),
                    20,
                    20,
                    velocityX,
                    velocityY
            );
            this.enemies.add(enemy);
            this.countEnemy = 0;
        } else {
            this.countEnemy += 1;
        }
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
