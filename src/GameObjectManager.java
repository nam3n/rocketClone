import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameObjectManager {

    public static GameObjectManager instance = new GameObjectManager();

    private List<GameObject> list;

    private GameObjectManager() {
        this.list = new ArrayList<>();
    }

    public void add(GameObject gameObject) {
        this.list.add(gameObject);
    }

    public void runAll() {
        this.list.forEach(gameObject -> gameObject.run());
    }

    public void renderAll(Graphics graphics) {
        this.list.forEach(gameObject -> gameObject.render(graphics));
    }
}
