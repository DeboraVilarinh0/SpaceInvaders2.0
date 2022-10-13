package spaceInvaders.model.verifications;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import spaceInvaders.entities.AlienFleet;
import spaceInvaders.entities.Bullet;
import spaceInvaders.entities.PowerUps;
import spaceInvaders.entities.SpaceShip;
import spaceInvaders.model.Position;
import spaceInvaders.utility.SimpleAudioPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class VerificationsTest {
    @Mock
    SimpleAudioPlayer audioPlayer;
    @InjectMocks
    Verifications verifications = new Verifications();

    VerificationsTest() throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
//Should set shootfaster to 0 when the power up type is 0"
    void verifyPowerUpCollisionWhenPowerUpTypeIs0ThenSetShootFasterTo0() throws IOException {
        SpaceShip spaceShip = new SpaceShip(0, 0);
        PowerUps powerUps = new PowerUps(0, 0, 0);
        List<PowerUps> powerUpsList = List.of(powerUps);



        Assertions.assertEquals(0, spaceShip.getShootFaster());
    }

    @Test
//Should set firemultiplebullets to true when the power up type is 2
    void verifyPowerUpCollisionWhenPowerUpTypeIs2ThenSetFireMultipleBulletsToTrue() throws IOException {
        SpaceShip spaceShip = new SpaceShip(0, 0);
        PowerUps powerUps = new PowerUps(0, 0, 2);
        List<PowerUps> powerUpsList = List.of(powerUps);



        Assertions.assertTrue(spaceShip.getFireMultipleBullets());
    }

    @Test
//Should set isinvincible to true when the power up type is 1
    void verifyPowerUpCollisionWhenPowerUpTypeIs1ThenSetIsInvincibleToTrue() throws IOException {
        SpaceShip spaceShip = new SpaceShip(0, 0);
        PowerUps powerUps = new PowerUps(0, 0, 1);
        List<PowerUps> powerUpsList = List.of(powerUps);

        verifications.verifyPowerUpCollision(spaceShip, powerUpsList);

        assertTrue(spaceShip.getIsInvincible());
    }

    @Test
//Should remove the power up from the list when the spaceship is in the same position as the power up
    void
    verifyPowerUpCollisionWhenSpaceshipIsInTheSamePositionAsThePowerUpThenRemoveThePowerUpFromTheList() throws IOException {
        SpaceShip spaceShip = new SpaceShip(0, 0);
        PowerUps powerUp = new PowerUps(0, 0, 0);
        List<PowerUps> powerUps = List.of(powerUp);

        verifications.verifyPowerUpCollision(spaceShip, powerUps);

        assertTrue(powerUps.isEmpty());
    }

    @Test
//hould remove the bullet when the bullet is in the same position as the enemy bullet
    void
    verifyCollisionBetweenBulletsShouldRemoveTheBulletWhenTheBulletIsInTheSamePositionAsTheEnemyBullet() {
        Bullet bullet = new Bullet(1, 1);
        Bullet enemyBullet = new Bullet(1, 1);
        List<Bullet> bullets = List.of(bullet);
        List<Bullet> enemyBullets = List.of(enemyBullet);

        verifications.verifyCollisionBetweenBullets(bullets, enemyBullets);

        assertTrue(bullets.isEmpty());
    }

    @Test
//Should remove the enemy bullet when the bullet is in the same position as the enemy bullet
    void
    verifyCollisionBetweenBulletsShouldRemoveTheEnemyBulletWhenTheBulletIsInTheSamePositionAsTheEnemyBullet() {
        Bullet bullet = new Bullet(1, 1);
        Bullet enemyBullet = new Bullet(1, 1);
        List<Bullet> bullets = List.of(bullet);
        List<Bullet> enemyBullets = List.of(enemyBullet);

        verifications.verifyCollisionBetweenBullets(bullets, enemyBullets);

        assertTrue(bullets.isEmpty());
        assertTrue(enemyBullets.isEmpty());
    }

    @Test
//Should remove both bullets when they are in different positions but one of them is moving up and they are in adjacent positions
    void
    verifyCollisionBetweenBulletsShouldRemoveBothBullesWhenTheyAreInDifferentPositionsButOneOfThemIsMovingUpAndTheyAreInAdjacentPositions() {
        Bullet bullet = new Bullet(1, 1);
        Bullet enemyBullet = new Bullet(1, 1);
        List<Bullet> bullets = List.of(bullet);
        List<Bullet> enemyBullets = List.of(enemyBullet);

        verifications.verifyCollisionBetweenBullets(bullets, enemyBullets);

        Assertions.assertTrue(bullets.isEmpty());
        Assertions.assertTrue(enemyBullets.isEmpty());
    }

    @Test
//Should remove both bullets when they are in the same position
    void verifyCollisionBetweenBulletsShouldRemoveBothBulletsWhenTheyAreInTheSamePosition() {
        List<Bullet> bullets = mock(List.class);
        List<Bullet> enemyBullets = mock(List.class);
        Bullet bullet = mock(Bullet.class);
        Bullet enemyBullet = mock(Bullet.class);
        when(bullets.get(0)).thenReturn(bullet);
        when(enemyBullets.get(0)).thenReturn(enemyBullet);
        when(bullet.getPosition()).thenReturn(new Position(1, 1));
        when(enemyBullet.getPosition()).thenReturn(new Position(1, 1));

        verifications.verifyCollisionBetweenBullets(bullets, enemyBullets);

        verify(bullets, times(1)).remove(0);
        verify(enemyBullets, times(1)).remove(0);
    }

    @Test
//Should remove the alien when it is hit by a bullet
    void verifyAlienFleetCollisionShouldRemoveTheAlienWhenItIsHitByABullet() {
        AlienFleet alienFleet = new AlienFleet(1, 1, 1);
        Bullet bullet = new Bullet(1, 1);
        List<AlienFleet> alienFleets = List.of(alienFleet);
        List<Bullet> bullets = List.of(bullet);

        verifications.verifyAlienFleetCollision(bullets, alienFleets);

        assertTrue(alienFleets.isEmpty());
    }

    @Test
//Should remove the bullet when it hits an alien
    void verifyAlienFleetCollisionShouldRemoveTheBulletWhenItHitsAnAlien() {
        List<Bullet> bullets = mock(List.class);
        List<AlienFleet> alienFleet = mock(List.class);
        Bullet bullet = mock(Bullet.class);
        AlienFleet alien = mock(AlienFleet.class);
        when(bullets.get(0)).thenReturn(bullet);
        when(alienFleet.get(0)).thenReturn(alien);
        when(bullet.getPosition()).thenReturn(new Position(1, 1));
        when(alien.getPosition()).thenReturn(new Position(1, 1));
        when(alien.getHitPoints()).thenReturn(1);

        verifications.verifyAlienFleetCollision(bullets, alienFleet);

        verify(bullets).remove(0);
    }

    @Test
//Should decrease the hit points of an alien when it is hit by a bullet
    void verifyAlienFleetCollisionShouldDecreaseTheHitPointsOfAnAlienWhenItIsHitByABullet() {
        List<Bullet> bullets = mock(List.class);
        List<AlienFleet> alienFleet = mock(List.class);
        AlienFleet alienFleet1 = mock(AlienFleet.class);
        Bullet bullet = mock(Bullet.class);
        Position position = mock(Position.class);

        when(bullets.get(0)).thenReturn(bullet);
        when(bullet.getPosition()).thenReturn(position);
        when(alienFleet.get(0)).thenReturn(alienFleet1);
        when(alienFleet1.getPosition()).thenReturn(position);
        when(alienFleet1.getHitPoints()).thenReturn(2);

        verifications.verifyAlienFleetCollision(bullets, alienFleet);

    }

    @Test
//Should exit the game when the spaceship collides with a bullet
    void verifySpaceShipCollisionWhenSpaceshipCollidesWithABulletThenExitTheGame() throws UnsupportedAudioFileException, IOException, InterruptedException {
        SpaceShip spaceShip = mock(SpaceShip.class);
        Bullet bullet = mock(Bullet.class);
        Position position = mock(Position.class);
        when(spaceShip.getPosition()).thenReturn(position);
        when(bullet.getPosition()).thenReturn(position);

        verifications.verifySpaceShipCollision(spaceShip, null, List.of(bullet));

        verify(audioPlayer).playDeathAudio();
    }

    @Test
//Should exit the game when the spaceship collides with an alien
    void verifySpaceShipCollisionWhenSpaceshipCollidesWithAnAlienThenExitTheGame() {
        SpaceShip spaceShip = mock(SpaceShip.class);
        AlienFleet alienFleet = mock(AlienFleet.class);
        Bullet enemyBullet = mock(Bullet.class);
        List<AlienFleet> alienFleets = mock(List.class);
        List<Bullet> enemyBullets = mock(List.class);

        when(spaceShip.getPosition()).thenReturn(new Position(1, 1));
        when(alienFleet.getPosition()).thenReturn(new Position(1, 1));
        when(enemyBullet.getPosition()).thenReturn(new Position(1, 1));

        alienFleets.add(alienFleet);
        enemyBullets.add(enemyBullet);

    }

    @Test
//Should remove the bullet when the bullet is out of the screen
    void cleanBulletWhenBulletIsOutOfTheScreenThenRemoveTheBullet() {
        Bullet bullet = new Bullet(0, 0);
        List<Bullet> bullets = List.of(bullet);

        verifications.cleanBullet(bullets);

        assertTrue(bullets.isEmpty());
    }

    @Test
//Should return 1 when the alienfleet is empty
    void levelWhenAlienFleetIsEmptyThenReturn1() {
        List<AlienFleet> alienFleet = mock(List.class);
        when(alienFleet.isEmpty()).thenReturn(true);
        assertEquals(1, verifications.level(alienFleet, 1));
    }

    @Test
//Should return 0 when the alienfleet is not empty
    void levelWhenAlienFleetIsNotEmptyThenReturn0() {
        List<AlienFleet> alienFleet = mock(List.class);
        when(alienFleet.isEmpty()).thenReturn(false);
        assertEquals(0, verifications.level(alienFleet,1));
    }


}

