package spaceInvaders.entities;
import spaceInvaders.utility.Constants;
import java.util.ArrayList;
import java.util.List;

public class SpaceShip extends Element {
    private boolean isInvincible = false;
    private int shootFaster = 4;
    private boolean fireMultipleBullets = false;
    private List<SpaceShipHP> spaceShipHP;




    public void setFireMultipleBullets(boolean fireMultipleBullets) {
        this.fireMultipleBullets = fireMultipleBullets;
    }

    public boolean getFireMultipleBullets() {
        return fireMultipleBullets;
    }

    public SpaceShip(int x,int heigh) {
        super(x, heigh);
        spaceShipHP = new ArrayList<>();
        for (int hpIndex = 1; hpIndex <= Constants.SPACESHIP_HP; hpIndex++) {
            spaceShipHP.add(new SpaceShipHP(Constants.WIDTH - hpIndex, 1));
        }
    }
    public void setIsInvincible(boolean isInvincible) {
        this.isInvincible = isInvincible;
    }

    public boolean getIsInvincible() {
        return isInvincible;
    }

    public void setShootFaster(int shootFaster) {
        this.shootFaster = shootFaster;
    }

    public int getShootFaster() {
        return shootFaster;
    }

    public List<SpaceShipHP> getSpaceShipHP() {
        return spaceShipHP;
    }

}

