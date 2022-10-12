package spaceInvanders.entities;

public class SpaceShip extends Element {
    private boolean isInvincible = false;
    private int shootFaster = 4;
    private boolean fireMultipleBullets = false;


    public void setFireMultipleBullets(boolean fireMultipleBullets) {
        this.fireMultipleBullets = fireMultipleBullets;
    }

    public boolean getFireMultipleBullets() {
        return fireMultipleBullets;
    }

    public SpaceShip(int x, int Height) {
        super(x, Height);
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


}

