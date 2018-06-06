import java.awt.*;

public class Background extends GameObject {

    public Background() {
        this.renderer = new BackgroundRenderer(1024, 600, Color.BLACK);
    }
}
