package spaceInvaders.model.movements;

import org.junit.jupiter.api.Test;
import spaceInvaders.entities.Bullet;

import java.util.List;

class MovementBulletsTest {
    MovementBullets movementBullets = new MovementBullets();

    @Test
    void testMoveBullets() {
        movementBullets.moveBullets(List.of(new Bullet(0, 0)), List.of(new Bullet(0, 0)));
    }
}

