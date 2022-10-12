package spaceInvanders.entities;

import spaceInvanders.model.Position;

public class Bullet extends Element{
    public Bullet(int x, int y) {
        super(x, y);
    }

    public Position bulletMovementUP() {
        return new Position(position.getX(), position.getY() - 1);
    }
    public Position bulletMovementDOWN() {
        return new Position(position.getX(), position.getY() + 1);
    }


}
