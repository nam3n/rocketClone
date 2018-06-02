import java.util.ArrayList;
import java.util.List;

public class EnemyShoot {
    public List<Bullet> bulletEnemies;

    public EnemyShoot() {
        this.bulletEnemies = new ArrayList<>();
    }

    public void run(Enemy enemy) {
        // create bullet
        Bullet bulletEnemy = new Bullet();
        bulletEnemy.position.set(enemy.position);
        bulletEnemy.velocity.set(3, 0);
        this.bulletEnemies.add(bulletEnemy);
    }
}
