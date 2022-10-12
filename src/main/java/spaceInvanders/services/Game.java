package spaceInvanders.services;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import spaceInvanders.model.createElements.CreateElements;
import spaceInvanders.model.movements.MovementAlienFleet;
import spaceInvanders.model.movements.MovementBullets;
import spaceInvanders.model.verifications.Verifications;
import spaceInvanders.utility.DrawUtil;
import spaceInvanders.utility.SimpleAudioPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Game {

    private Arena arena;
    TerminalScreen screen;
    boolean keepRunning = true;
    long shotTimer = 1000;
    long moveTimer = 100;
    long shotNumb = 1;
    long powerUpTimer = 5000;
    boolean playedLevelTwo = false;
    CreateElements createElements = new CreateElements();
    MovementBullets movementBullets = new MovementBullets();
    MovementAlienFleet moveAlienFleet = new MovementAlienFleet();
    Verifications verifications = new Verifications();
    SimpleAudioPlayer audioPlayer = new SimpleAudioPlayer();
    long quickFireCount = 0;
    long invincibleCount = 0;
    long multipleFireCount = 0;


    public Game(int Width, int Height) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        AWTTerminalFontConfiguration cfg = new SwingTerminalFontConfiguration(true, AWTTerminalFontConfiguration.BoldMode.EVERYTHING, changeFont());
        TerminalSize terminalSize = new TerminalSize(Width, Height);
        DefaultTerminalFactory TerminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        TerminalFactory.setTerminalEmulatorFontConfiguration(cfg);
        Terminal terminal = TerminalFactory.createTerminal();


        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen();           // screens must be started
        screen.doResizeIfNecessary();   // resize screen if necessary
        drawMenu(screen);


    }

    public void drawMenu(TerminalScreen screen) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {

        screen.clear();
        DrawUtil.menuText(screen);
        screen.refresh();


        while (keepRunning) {

            KeyStroke keyPressed = screen.pollInput();

            if (keyPressed != null) {
                switch (keyPressed.getKeyType()) {
                    case F1 -> {
                        keepRunning = false;
                    }
                    case Enter -> {
                        arena = new Arena(createElements);
                        run();
                    }
                    case Character -> {

                        switch (keyPressed.getCharacter()) {
                            case 'I', 'i' -> {

                                drawInstruction(screen);

                            }
                        }
                    }

                    default -> {
                    }
                }

                screen.refresh();
            }


        }
        screen.close();

    }

    private void drawInstruction(TerminalScreen screen) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {


        screen.clear();
        DrawUtil.instructionsText(screen);
        screen.refresh();

        while (keepRunning) {
            KeyStroke key = screen.pollInput();

            if (key != null) {
                if (key.getKeyType() == KeyType.Backspace) {
                   drawMenu(screen);
                   break;
                }
            }
        }


    }


    public void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }


    public void run() throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {


        int FPS = 30;
        int frameTime = 1100 / FPS;
        long lastMonsterMovement = 0;
        long lastMonsterMovement2 = 0;
        long powerUpActivated = 0;
        long powerUp1Activated = 0;
        long powerUp2Activated = 0;
        long powerUp3Activated = 0;


        while (keepRunning) {

            long startTime = System.currentTimeMillis();

            audioPlayer.playBackgroundAudio();
            draw();
            KeyStroke key = screen.pollInput();
            if (key != null) {

                if (key.getKeyType() == KeyType.Backspace) {
                    drawMenu(screen);

                }

                arena.processKey(key);
                if (key.getKeyType() == KeyType.EOF) {
                    break;
                }
            }

            if (startTime - lastMonsterMovement > moveTimer) {
                moveAlienFleet.moveAlienFleet(arena.getAlienFleet());
                if (!arena.getIsInvincible()) {
                    verifications.verifySpaceShipCollision(arena.getSpaceShip(), arena.getAlienFleet(), arena.getEnemyBullets());
                }
                movementBullets.moveBullets(arena.getBullets(), arena.getEnemyBullets());

                verifications.verifyAlienFleetCollision(arena.getBullets(), arena.getAlienFleet());
                verifications.verifyCollisionBetweenBullets(arena.getBullets(), arena.getEnemyBullets());
                verifications.verifyPowerUpCollision(arena.getSpaceShip(), arena.getPowerUps());
                verifications.cleanBullet(arena.getBullets());

                draw();

                lastMonsterMovement = startTime;
            }

            if (startTime - lastMonsterMovement2 > shotTimer) {
                arena.shootBullet(shotNumb);
                lastMonsterMovement2 = startTime;
            }

            if (startTime - powerUpActivated > powerUpTimer) {
                arena.setPowerUps(createElements.createPowerUps());
                powerUpActivated = startTime;

            }
            if (arena.getShootFaster() == 0) {
                quickFireCount++;
                System.out.println(100 - quickFireCount);
                if (quickFireCount == 100) {
                    quickFireCount = 0;
                    arena.setShootFaster(6);
                    System.out.println("FAST SHOOTING OFF");
                }
            }

            if (arena.getIsInvincible()) {
                invincibleCount++;
                System.out.println(100 - invincibleCount);
                if (invincibleCount == 100) {
                    invincibleCount = 0;
                    arena.setIsInvincible(false);
                    System.out.println("Invincible OFF");
                }
            }

            if (arena.getFireMultipleBullets()) {
                multipleFireCount++;
                System.out.println(100 - multipleFireCount);
                if (multipleFireCount == 100) {
                    multipleFireCount = 0;
                    arena.setFireMultipleBullets(false);
                    System.out.println("Multiple Fire off");
                }
            }


            if (startTime - powerUp1Activated > powerUpTimer) {
                arena.setShootFaster(3);
                powerUp1Activated = startTime;
                System.out.println("FAST SHOOTING OFF");
            }

            if (startTime - powerUp2Activated > powerUpTimer) {
                arena.setIsInvincible(false);
                powerUp2Activated = startTime;
                System.out.println("INVENCIBILITY OFF");
            }

            if (startTime - powerUp3Activated > powerUpTimer) {
                arena.setFireMultipleBullets(false);
                powerUp3Activated = startTime;
                System.out.println("MULTIPLE SHOTS OFF");
            }

            if (arena.aliensIsEmpty() && arena.getRunTimer() < 80) {
                audioPlayer.stopBackgroundAudio();
                audioPlayer.playLastLevelAudio();
                arena.setRunTimer(arena.getRunTimer() + 1);
                System.out.println(arena.getRunTimer());
            }


            switch (verifications.level(arena.getAlienFleet())) {
                case 2 -> {
                    audioPlayer.stopBackgroundAudio();
                    audioPlayer.playLastLevelAudio();
                    shotTimer = 300;
                    shotNumb = 3;
                    moveTimer = 60;
                    createElements.createAlienFleet(25, 5);
                    System.out.println("ENTREI NO 2");
                    playedLevelTwo = true;
                }
                case 3 -> {
                    System.out.println("entrei no 3");
                    shotTimer = 10;
                    shotNumb = 5;
                    moveTimer = 30;
                    createElements.createAlienFleet(30, 6);
                }
                case 4 -> {
                    System.out.println("GG");
                    System.exit(0);

                    createElements.createAlienFleet(30, 6);
                }
            }

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;
            if (sleepTime > 0) try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ignored) {
            }
        }
    }

    public Font changeFont() {
        File fontFile = new File("src/main/resources/fonts/Square-Regular.ttf");
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            font = font.deriveFont(font.getSize() * 30F);

        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        return font;
    }
}



