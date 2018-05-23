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


    BufferedImage enemyImage;
    BufferedImage playerImage;
    BufferedImage backBuffered;
    Graphics graphics;

    List<Star> stars;
    private Random random = new Random();
    private int countStar = 0;

    public int positionXEnemy = 500;
    public int positionYEnemy = 0;

    public int positionXPlayer = 400;
    public int positionYPlayer = 200;

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

        this.enemyImage = this.loadImage("resources/images/circle.png");

        this.playerImage = this.loadImage("resources/images/circle.png");

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
        this.renderBackground();

        this.stars.forEach(star -> star.render(graphics));

        this.graphics.drawImage(this.enemyImage, this.positionXEnemy, this.positionYEnemy, 10, 10, null);
        this.graphics.drawImage(this.playerImage, this.positionXPlayer, this.positionYPlayer, null);

        this.repaint();
    }

    private void renderBackground() {
        this.graphics.setColor(Color.BLACK);
        this.graphics.fillRect(0, 0, 1024, 600);
    }

    public void runAll() {
        this.createStar();
        this.stars.forEach(star -> star.run());
        this.positionYEnemy += 2;
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



    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
