package spaceInvaders.arena;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import spaceInvaders.entities.AlienFleet;
import spaceInvaders.entities.Bullet;
import spaceInvaders.entities.PowerUps;
import spaceInvaders.entities.SpaceShip;
import spaceInvaders.model.Position;
import spaceInvaders.model.createElements.CreateElements;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ArenaTest {
    @Mock
    SpaceShip spaceShip;
    @Mock
    List<Bullet> bullets;
    @Mock
    List<Bullet> enemyBullets;
    @Mock
    List<PowerUps> powerUps;
    @Mock
    List<AlienFleet> alienFleet;
    @Mock
    CreateElements createElements;
    @InjectMocks
    Arena arena = new Arena(createElements);

    ArenaTest() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
//Should set the powerups when the powerups is not empty
    void setPowerUpsWhenPowerUpsIsNotEmpty() {
        when(powerUps.isEmpty()).thenReturn(false);
        arena.setPowerUps(powerUps);
        assertEquals(powerUps, arena.getPowerUps());
    }

    @Test
//Should set the runtimer to the given value
    void setRunTimerShouldSetTheRunTimerToTheGivenValue() {
        arena.setRunTimer(10);
        assertEquals(10, arena.getRunTimer());
    }

    @Test
//Should return the runtimer
    void getRunTimerShouldReturnTheRunTimer() {
        arena.setRunTimer(1);
        assertEquals(1, arena.getRunTimer());
    }

    @Test
//Should not create a bullet when the alien fleet is empty
    void shootBulletWhenAlienFleetIsEmptyThenDoNotCreateBullet() {
        when(alienFleet.size()).thenReturn(0);
        arena.shootBullet(1);
        verify(createElements, never()).createEnemyBullets(any());
    }

    /*@Test
//Should create a bullet when the alien fleet is not empty
    void shootBulletWhenAlienFleetIsNotEmptyThenCreateBullet() {
        when(alienFleet.size()).thenReturn(1);
        arena.shootBullet(1);
        verify(createElements, times(1)).createEnemyBullets(new Position(0,0));
    }

     */


    /*@Test
    void testDraw() {
        when(spaceShip.getSpaceShipHP()).thenReturn(List.of(new SpaceShipHP(0, 0)));

        TextGraphics graphics = mock(TextGraphics.class);

        arena.draw(graphics);
    }*/

    @Test
    void testProcessKey() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        when(spaceShip.getFireMultipleBullets()).thenReturn(true);
        when(spaceShip.getShootFaster()).thenReturn(0);
        when(spaceShip.getPosition()).thenReturn(new Position(0, 0));
        when(spaceShip.moveRight()).thenReturn(new Position(0, 0));
        when(spaceShip.moveLeft()).thenReturn(new Position(0, 0));
        when(createElements.createBullets(any(), anyBoolean())).thenReturn(List.of(new Bullet(0, 0)));

        arena.processKey(new KeyStroke(KeyType.Enter));
    }

    @Test
    void testShootBullet() {
        when(spaceShip.getPosition()).thenReturn(new Position(0, 0));
        when(createElements.createEnemyBullets(any())).thenReturn(List.of(new Bullet(0, 0)));

        arena.shootBullet(0L);
    }

    @Test
    void testGetIsInvincible() {
        when(spaceShip.getIsInvincible()).thenReturn(true);

        boolean result = arena.getIsInvincible();
        Assertions.assertEquals(true, result);
    }

    @Test
    void testSetIsInvincible() {
        arena.setIsInvincible(true);
    }

    @Test
    void testSetShootFaster() {
        arena.setShootFaster(0);
    }

    @Test
    void testGetShootFaster() {
        when(spaceShip.getShootFaster()).thenReturn(0);

        int result = arena.getShootFaster();
        Assertions.assertEquals(0, result);
    }

    @Test
    void testSetFireMultipleBullets() {
        arena.setFireMultipleBullets(true);
    }

    @Test
    void testGetFireMultipleBullets() {
        when(spaceShip.getFireMultipleBullets()).thenReturn(true);

        boolean result = arena.getFireMultipleBullets();
        Assertions.assertEquals(true, result);
    }

    @Test
    void testAliensIsEmpty() {
        boolean result = arena.aliensIsEmpty();
        Assertions.assertEquals(false, result);
    }
}

