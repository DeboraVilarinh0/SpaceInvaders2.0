package spaceInvaders.entities;

import spaceInvaders.model.Position;

public class AlienFleet extends Element {

    protected int hitPoints;

    public AlienFleet(int x, int y, int hitPoints) {
        super(x, y);
        this.hitPoints = hitPoints;

    }

    public Position moveDown() {
        return new Position(position.getX(), position.getY() + 1);
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;

    }

}







