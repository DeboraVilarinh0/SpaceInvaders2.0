package spaceInvaders.model.movements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spaceInvaders.entities.AlienFleet;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovementAlienFleetTest {
    MovementAlienFleet movementAlienFleet = new MovementAlienFleet();
    int y;


    @BeforeEach
    public void setPosition(){
         y = 1;

    }

    @Test//Should move the alien fleet to the RIGHT when the minx is greater than 0
    void moveAlienFleetWhenMinXIIsGreaterThanZeroThenMoveToTheRight() {
        List<AlienFleet> alienFleet = List.of(new AlienFleet(4, y, 1), new AlienFleet(5, y, 1));
        movementAlienFleet.moveAlienFleet(alienFleet);
        assertEquals(new AlienFleet(5, y, 1).getPosition().getX(), alienFleet.get(0).getPosition().getX());
        assertEquals(new AlienFleet(6, y, 1).getPosition().getX(), alienFleet.get(1).getPosition().getX());
    }

    @Test//Should move the alien fleet to the LEFT when the minx is greater than 0
    void moveAlienFleetWhenMinXIsGreaterThanZeroThenMoveToTheLeft() {

        List<AlienFleet> alienFleet = List.of(new AlienFleet(63, y, 1), new AlienFleet(62, y, 1));
        movementAlienFleet.moveAlienFleet(alienFleet);
        assertEquals(new AlienFleet(62, y, 1).getPosition().getX(), alienFleet.get(0).getPosition().getX());
        assertEquals(new AlienFleet(61, y, 1).getPosition().getX(), alienFleet.get(1).getPosition().getX());
    }

    @Test//Should move the alien fleet DOWN when the minx is greater than 0
    void moveAlienFleetWhenMinXIsGreaterThanZeroThenMoveDown() {
        List<AlienFleet> alienFleet = List.of(new AlienFleet(63, y, 1), new AlienFleet(1, y, 1));
        movementAlienFleet.moveAlienFleet(alienFleet);
        assertEquals(new AlienFleet(0, y+1, 1).getPosition().getY(), alienFleet.get(0).getPosition().getY());
        assertEquals(new AlienFleet(0, y+1, 1).getPosition().getY(), alienFleet.get(1).getPosition().getY());
    }



}

