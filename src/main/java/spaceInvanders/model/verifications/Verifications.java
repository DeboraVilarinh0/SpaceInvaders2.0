package spaceInvanders.model.verifications;

import spaceInvanders.entities.AlienFleet;
import spaceInvanders.entities.Bullet;
import spaceInvanders.entities.PowerUps;
import spaceInvanders.entities.SpaceShip;
import spaceInvanders.utility.SimpleAudioPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.List;

public class Verifications {
    public int level = 1;
    int powerUpType;
    boolean fireMultipleBullets;
    SimpleAudioPlayer audioPlayer = new SimpleAudioPlayer();

    public Verifications() throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
    }

    public void verifySpaceShipCollision(SpaceShip spaceShip, List<AlienFleet> alienFleet, List<Bullet> enemyBullets) throws UnsupportedAudioFileException, IOException, InterruptedException {

        for (AlienFleet monsters : alienFleet) {
            if (spaceShip.getPosition().equals(monsters.getPosition())) {
                audioPlayer.stopBackgroundAudio();
                audioPlayer.stopLastLevelAudio();
                audioPlayer.playDeathAudio();
                Thread.sleep(3000);
                System.exit(0);
            }
        }


        for (Bullet enemyBullet : enemyBullets) {
            if (spaceShip.getPosition().equals(enemyBullet.getPosition())) {

                audioPlayer.stopBackgroundAudio();
                audioPlayer.stopLastLevelAudio();
                audioPlayer.playDeathAudio();
                System.out.println("You died!!!");
                Thread.sleep(3000);
                System.exit(0);
            }
        }
    }


    public int level(List<AlienFleet> alienFleet) {
        if (alienFleet.isEmpty()) {
            level += 1;
            System.out.print("o nivel é   ");
            System.out.println(level);
            return level;
        }
        return 0;
    }


    public void verifyAlienFleetCollision(List<Bullet> bullets, List<AlienFleet> alienFleet) {
        for (int indexBullets = 0; indexBullets < bullets.size(); indexBullets++) {

            for (int indexMonsters = 0; indexMonsters < alienFleet.size(); indexMonsters++) {
                if (bullets.get(indexBullets).getPosition().equals(alienFleet.get(indexMonsters).getPosition())) {

                    if (alienFleet.get(indexMonsters).getHitPoints() >= 1) {

                        alienFleet.get(indexMonsters).setHitPoints(alienFleet.get(indexMonsters).getHitPoints() - 1);
                        bullets.remove(indexBullets);

                    } else {
                        bullets.remove(indexBullets);
                        alienFleet.remove(indexMonsters);
                    }
                    break;
                }
            }
        }
    }


    public void cleanBullet(List<Bullet> bullets) {
        for (int indexBullets = 0; indexBullets < bullets.size(); indexBullets++) {
            if (bullets.get(indexBullets).getPosition().getY() <= 0) {
                bullets.remove(indexBullets);
            }
        }
    }

    public void verifyCollisionBetweenBullets(List<Bullet> bullets, List<Bullet> enemyBullets) {
        for (int indexBullets = 0; indexBullets < bullets.size(); indexBullets++) {

            for (int indexBulletsAliens = 0; indexBulletsAliens < enemyBullets.size(); indexBulletsAliens++) {

                if (bullets.get(indexBullets).getPosition().equals(enemyBullets.get(indexBulletsAliens).getPosition())
                        || bullets.get(indexBullets).bulletMovementUP().equals(enemyBullets.get(indexBulletsAliens).getPosition())) {
                    bullets.remove(indexBullets);
                    enemyBullets.remove(indexBulletsAliens);
                    break;
                }
            }
        }
    }

    public void verifyPowerUpCollision(SpaceShip spaceShip, List<PowerUps> powerUps) {
        for (int i = 0; i < powerUps.size(); i++) {

            if (powerUps.get(i).getPosition().equals(spaceShip.getPosition())) {
                powerUpType = powerUps.get(i).getPowerUpType();
                powerUps.remove(i);


                switch (powerUpType) {
                    case 0:
                        spaceShip.setShootFaster(0);
                        System.out.println("shoot faster");
                        break;

                    case 1:
                        spaceShip.setIsInvincible(true);
                        System.out.println("invincible");
                        break;

                    case 2:
                        spaceShip.setFireMultipleBullets(true);
                        break;
                }
            }
        }
    }

}
