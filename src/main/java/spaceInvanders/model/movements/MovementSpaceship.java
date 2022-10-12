package spaceInvanders.model.movements;

import spaceInvanders.entities.SpaceShip;
import spaceInvanders.model.Position;
import spaceInvanders.utility.Constants;

public class MovementSpaceship {

    private final SpaceShip spaceShip;

    public MovementSpaceship(SpaceShip spaceShip) {
        this.spaceShip = spaceShip;
    }

    private boolean canSpaceshipMove(Position position) {
        return position.getX() <= Constants.WIDTH - 2 && position.getX() >= 1;
    }

    public void moveSpaceShip(Position position) {
        if (canSpaceshipMove(position)) spaceShip.setPosition(position);
    }

}
