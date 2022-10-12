package spaceInvanders.model.movements;

import spaceInvanders.entities.AlienFleet;
import spaceInvanders.model.Position;
import spaceInvanders.utility.Constants;

import java.util.List;

public class MovementAlienFleet {


    private boolean moveRight = true;
    private boolean moveLeft = false;


    public void moveAlienFleet(List<AlienFleet> alienFleet) {

        int maxX = 0;
        int minX = 30;
        for (AlienFleet alien : alienFleet) {
            if (alien.getPosition().getX() > maxX) maxX = alien.getPosition().getX();
            if (alien.getPosition().getX() < minX) minX = alien.getPosition().getX();
        }

        if (moveRight) {
            if (maxX < Constants.WIDTH - 1) {
                for (AlienFleet alien : alienFleet) {
                    Position alienPosition = alien.moveRight();
                    alien.setPosition(alienPosition);

                }
            }
            if (maxX == Constants.WIDTH - 1) {
                moveRight = false;
                moveLeft = true;
            }
        }

        if (moveLeft) {
            if (minX > 0) {
                for (AlienFleet alien : alienFleet) {
                    Position alienPosition = alien.moveLeft();
                    alien.setPosition(alienPosition);

                }
            }
            if (minX == 1) {
                moveLeft = false;
            }
        }

        if (!moveLeft && !moveRight) {
            for (AlienFleet alien : alienFleet) {
                Position alienPosition = alien.moveDown();
                alien.setPosition(alienPosition);

            }
            moveRight = true;
        }

    }
}
