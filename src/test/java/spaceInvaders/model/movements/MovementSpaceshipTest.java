package spaceInvaders.model.movements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import spaceInvaders.entities.SpaceShip;
import spaceInvaders.model.Position;

class MovementSpaceshipTest {
    @Mock
    SpaceShip spaceShip;
    @InjectMocks
    MovementSpaceship movementSpaceship;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMoveSpaceShip() {
        movementSpaceship.moveSpaceShip(new Position(0, 0));
    }
}

