package spaceInvanders.entities;

public class Alien extends AlienFleet {

    public Alien(int x, int y, int hitPoints) {
        super(x, y, hitPoints);

    }

    public int getHitPoints() {
        return hitPoints;
    }
}
