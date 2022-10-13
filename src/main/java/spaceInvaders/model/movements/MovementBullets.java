package spaceInvaders.model.movements;

import spaceInvaders.entities.Bullet;

import java.util.List;

public class MovementBullets {

    public void moveBullets(List<Bullet> bullets, List<Bullet> enemyBullets) {
        for (Bullet bullet : bullets) {

            bullet.setPosition(bullet.bulletMovementUP());
        }

        for (Bullet enemyBullet : enemyBullets) {

            enemyBullet.setPosition(enemyBullet.bulletMovementDOWN());

        }
    }
}
