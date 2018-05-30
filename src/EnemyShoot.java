import java.util.ArrayList;
import java.util.List;

public class EnemyShoot {
    public List<BulletEnemy> bulletEnemies;

    public EnemyShoot() {
        this.bulletEnemies = new ArrayList<>();
    }

    public void run(Enemy enemy) {
        // create bullet
        BulletEnemy bulletEnemy = new BulletEnemy();
        bulletEnemy.position.set(enemy.position);
        bulletEnemy.velocity.set(3, 0);
        this.bulletEnemies.add(bulletEnemy);
    }
}
