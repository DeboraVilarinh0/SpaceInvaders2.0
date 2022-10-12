package spaceInvanders.model.movements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import spaceInvanders.entities.SpaceShip;
import spaceInvanders.model.Position;

import static org.mockito.Mockito.*;

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

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme