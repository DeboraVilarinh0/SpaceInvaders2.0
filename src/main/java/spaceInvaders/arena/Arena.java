package spaceInvaders.arena;


import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import spaceInvaders.entities.AlienFleet;
import spaceInvaders.entities.Bullet;
import spaceInvaders.entities.PowerUps;
import spaceInvaders.entities.SpaceShip;
import spaceInvaders.model.createElements.CreateElements;
import spaceInvaders.model.movements.MovementSpaceship;
import spaceInvaders.utility.Constants;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Arena {

    static int WIDTH = Constants.WIDTH;
    static int HEIGHT = Constants.HEIGHT;

    private final SpaceShip spaceShip;
    private List<Bullet> bullets;
    private List<Bullet> enemyBullets;
    private List<PowerUps> powerUps;
    private List<AlienFleet> alienFleet;

    CreateElements createElements = new CreateElements();
    long runTimer = 0;




    public Arena() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        this.spaceShip = new SpaceShip(WIDTH / 2, HEIGHT - 1);
        this.alienFleet = new ArrayList<>();
        this.bullets = new ArrayList<>();
        this.enemyBullets = new ArrayList<>();
        this.powerUps = new ArrayList<>();


    }

    public void setAlienFleet(List<AlienFleet> alienFleet) {
        this.alienFleet = alienFleet;
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }

    public void setEnemyBullets(List<Bullet> enemyBullets) {
        this.enemyBullets = enemyBullets;
    }

    public List<AlienFleet> getAlienFleet() {
        return alienFleet;
    }

    public List<Bullet> getEnemyBullets() {
        return enemyBullets;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public SpaceShip getSpaceShip() {
        return spaceShip;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.newTextGraphics(new TerminalPosition(0, 0), new TerminalSize(WIDTH, HEIGHT));

        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(WIDTH, HEIGHT), ' ');

        spaceShip.drawElements(graphics, "#FFAE42", "&");
        if (bullets.size() != 0) {
            for (Bullet bullet : bullets) {
                bullet.drawElements(graphics, "#FFFFFF", "_");
            }
        }
        if (enemyBullets.size() != 0) {
            for (Bullet enemyBullet : enemyBullets) {
                enemyBullet.drawElements(graphics, "#FFFFFF", "'");
            }
        }

        if (!powerUps.isEmpty()) {
            for (PowerUps powerUp : powerUps) {
                if (powerUp.getPowerUpType() == 0) powerUp.drawElements(graphics, "#bff8ff", "o");
                if (powerUp.getPowerUpType() == 1) powerUp.drawElements(graphics, "#f5dc00", "o");
                if (powerUp.getPowerUpType() == 2) powerUp.drawElements(graphics, "#FDCAFF", "o");
            }
        }
        for (AlienFleet alien : alienFleet) {

            if (alien.getHitPoints() == 1) {
                do {
                    alien.drawElements(graphics, "#ff0000", "/");
                } while (alien.getHitPoints() == 0);

            } else if (alien.getHitPoints() == 0) {
                alien.drawElements(graphics, "#e3c205", "/");
            }
        }
    }

    public void processKey(KeyStroke key) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        MovementSpaceship movementSpaceship = new MovementSpaceship(spaceShip);
        if (key.getKeyType() == KeyType.ArrowLeft) {
            movementSpaceship.moveSpaceShip(spaceShip.moveLeft());
        }

        if (key.getKeyType() == KeyType.ArrowRight) {
            movementSpaceship.moveSpaceShip(spaceShip.moveRight());
        }

        if (key.getKeyType() == KeyType.ArrowUp) {

            if (bullets.size() == 0) {
                this.bullets = createElements.createBullets(spaceShip.getPosition(), spaceShip.getFireMultipleBullets());

            } else {
                if (bullets.get(bullets.size() - 1).getPosition().getY() < HEIGHT - (spaceShip.getShootFaster())) {
                    this.bullets = createElements.createBullets(spaceShip.getPosition(), spaceShip.getFireMultipleBullets());
                }
            }
        }

    }

    public void shootBullet(long shotNumb) {
        if (alienFleet.size() != 0) {
            for (int i = 0; i < shotNumb; i++) {

                Random rand = new Random();
                int rand_int1 = rand.nextInt(alienFleet.size());
                this.enemyBullets = createElements.createEnemyBullets(alienFleet.get(rand_int1).getPosition());
            }
        }
    }

    public boolean getIsInvincible() {
        return spaceShip.getIsInvincible();
    }

    public void setIsInvincible(boolean isInvincible) {
        spaceShip.setIsInvincible(isInvincible);
    }

    public void setShootFaster(int shootFaster) {
        spaceShip.setShootFaster(shootFaster);
    }

    public int getShootFaster() {
        return spaceShip.getShootFaster();
    }

    public void setFireMultipleBullets(boolean fireMultipleBullets) {
        spaceShip.setFireMultipleBullets(fireMultipleBullets);
    }

    public boolean getFireMultipleBullets() {
        return spaceShip.getFireMultipleBullets();
    }

    public List<PowerUps> getPowerUps() {
        return powerUps;
    }

    public void setPowerUps(List<PowerUps> powerUps) {
        this.powerUps = powerUps;
    }

    public long getRunTimer() {
        return runTimer;
    }

    public void setRunTimer(long runTimer) {
        this.runTimer = runTimer;
    }

    public boolean aliensIsEmpty() {
        return alienFleet.isEmpty();
    }

}
