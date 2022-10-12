package spaceInvanders.model.movements;

import org.junit.jupiter.api.Test;
import spaceInvanders.entities.Bullet;

import java.util.List;

class MovementBulletsTest {
    MovementBullets movementBullets = new MovementBullets();

    @Test
    void testMoveBullets() {
        movementBullets.moveBullets(List.of(new Bullet(0, 0)), List.of(new Bullet(0, 0)));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme