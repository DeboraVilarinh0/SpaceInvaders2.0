package spaceInvanders.model.movements;

import org.junit.jupiter.api.Test;
import spaceInvanders.entities.AlienFleet;

import java.util.List;

class MovementAlienFleetTest {
    MovementAlienFleet movementAlienFleet = new MovementAlienFleet();

    @Test
    void testMoveAlienFleet() {
        movementAlienFleet.moveAlienFleet(List.of(new AlienFleet(0, 0, 0)));
    }
}

