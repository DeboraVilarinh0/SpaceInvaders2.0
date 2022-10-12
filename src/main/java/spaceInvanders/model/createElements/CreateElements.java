package spaceInvanders.model.createElements;


import spaceInvanders.entities.Alien;
import spaceInvanders.entities.AlienFleet;
import spaceInvanders.entities.Bullet;
import spaceInvanders.entities.PowerUps;
import spaceInvanders.model.Position;
import spaceInvanders.utility.Constants;
import spaceInvanders.utility.SimpleAudioPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateElements {

     private List<AlienFleet> alienFleet = new ArrayList<>();
    private final List<Bullet> bullets = new ArrayList<>();
    private final List<Bullet> enemyBullets = new ArrayList<>();
    private final List<PowerUps> powerUps = new ArrayList<>();

    SimpleAudioPlayer audioPlayer = new SimpleAudioPlayer();

    public CreateElements() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
    }

    public List<AlienFleet> createAlienFleet(int Width, int Height) {

        boolean alienLVL2 = true;
        for (int row = 0; row < Height; row++) {
            for (int column = 0; column < Width; column += 3) {
                if (alienLVL2) {
                    alienFleet.add(new Alien(column + (50 - Width) / 2, row, 1));
                    alienLVL2 = false;
                } else {
                    alienFleet.add(new Alien(column + (50 - Width) / 2, row, 0));
                    alienLVL2 = true;
                }
            }
        }
        return alienFleet;
    }

    public List<Bullet> createBullets(Position position, boolean fireMultipleBullets) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (!fireMultipleBullets) {
            bullets.add(new Bullet(position.getX(), position.getY() - 1));
            audioPlayer.restartBulletsAudio();
        } else if (fireMultipleBullets) {
            bullets.add(new Bullet(position.getX(), position.getY() - 1));
            bullets.add(new Bullet(position.getX() - 1, position.getY() - 1));
            bullets.add(new Bullet(position.getX() + 1, position.getY() - 1));
            audioPlayer.restartBulletsAudio();
        }
        System.out.println(bullets.size());
        return bullets;
    }

    public List<PowerUps> createPowerUps() {    //power up na casa da esquerda não é possível apanhar
        Random rand = new Random();
        Random rand1 = new Random();
        int randPos = rand.nextInt(Constants.WIDTH - 2);
        randPos += 1;
        int randPowerUp = rand1.nextInt(3);
        System.out.println(randPowerUp);
        powerUps.add(new PowerUps(randPos, Constants.HEIGHT - 1, randPowerUp));
        return powerUps;
    }

    public List<Bullet> createEnemyBullets(Position position) {
        enemyBullets.add(new Bullet(position.getX(), position.getY()));
        return enemyBullets;
    }
}
