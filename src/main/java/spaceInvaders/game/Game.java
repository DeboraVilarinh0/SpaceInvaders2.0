package spaceInvaders.game;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import spaceInvaders.arena.Arena;
import spaceInvaders.entities.Bullet;
import spaceInvaders.model.Position;
import spaceInvaders.model.createElements.CreateElements;
import spaceInvaders.model.movements.MovementAlienFleet;
import spaceInvaders.model.movements.MovementBullets;
import spaceInvaders.model.verifications.Verifications;
import spaceInvaders.utility.Constants;
import spaceInvaders.utility.DrawUtil;
import spaceInvaders.utility.SimpleAudioPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        arena = new Arena(createElements);

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
                    case F4 -> {
                        keepRunning = false;
                    }
                    case Enter -> {

                        audioPlayer.playBackgroundAudio();

                        createElements.setAlienFleet(new ArrayList<>());
                        arena.setBullets(new ArrayList<>());
                        arena.setEnemyBullets(new ArrayList<>());
                        arena.setPowerUps(new ArrayList<>());

                        arena.setAlienFleet(createElements.createAlienFleet(20, 5));

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

    private void drawPause(TerminalScreen screen) throws IOException {
        screen.clear();
        DrawUtil.drawPause(screen);
        screen.refresh();
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

            KeyStroke key = screen.pollInput();
            if (key != null) {

                if (key.getKeyType() == KeyType.Backspace) {
                    audioPlayer.stopBackgroundAudio();
                    arena.setBullets(new ArrayList<>());
                    arena.setEnemyBullets(new ArrayList<>());
                    arena.setPowerUps(new ArrayList<>());
                    screen.clear();
                    drawMenu(screen);
                    screen.refresh();


                    break;

                }


                if (key.getKeyType() == KeyType.F2) {
                    audioPlayer.stopBackgroundAudio();
                    drawPause(screen);
                    if (key.getKeyType() == KeyType.F2) {

                    }

                    break;
                }


                this.arena.processKey(key);
                if (key.getKeyType() == KeyType.EOF) {
                    break;
                }
            }
            draw();

            if (startTime - lastMonsterMovement > moveTimer) {
                moveAlienFleet.moveAlienFleet(this.arena.getAlienFleet());
                if (!this.arena.getIsInvincible()) {
                    verifications.verifySpaceShipCollision(this.arena.getSpaceShip(), this.arena.getAlienFleet(), this.arena.getEnemyBullets());
                }
                movementBullets.moveBullets(this.arena.getBullets(), this.arena.getEnemyBullets());

                verifications.verifyAlienFleetCollision(this.arena.getBullets(), this.arena.getAlienFleet());
                verifications.verifyCollisionBetweenBullets(this.arena.getBullets(), this.arena.getEnemyBullets());
                verifications.verifyPowerUpCollision(this.arena.getSpaceShip(), this.arena.getPowerUps());
                verifications.cleanBullet(this.arena.getBullets());


                lastMonsterMovement = startTime;
            }

            if (startTime - lastMonsterMovement2 > shotTimer) {
                this.arena.shootBullet(shotNumb);
                lastMonsterMovement2 = startTime;
            }

            if (startTime - powerUpActivated > powerUpTimer) {
                this.arena.setPowerUps(createElements.createPowerUps());
                powerUpActivated = startTime;

            }
            if (this.arena.getShootFaster() == 0) {
                quickFireCount++;
                for (int i = 0; i <= 1; i++) {
                    screen.newTextGraphics().putString(Constants.WIDTH / 2 - 10, 1, "power up: SHOOT FASTER");
                    screen.refresh();
                    i++;
                }

                if (quickFireCount == 100) {
                    quickFireCount = 0;
                    this.arena.setShootFaster(6);

                }

            }

            if (this.arena.getIsInvincible()) {
                invincibleCount++;
                for (int i = 0; i <= 1; i++) {
                    screen.newTextGraphics().putString(Constants.WIDTH / 2 - 10, 1, "power up: INVINCIBLE");
                    screen.refresh();
                    i++;
                }

                if (invincibleCount == 100) {
                    invincibleCount = 0;
                    this.arena.setIsInvincible(false);

                }
            }

            if (this.arena.getFireMultipleBullets()) {
                multipleFireCount++;
                for (int i = 0; i <= 1; i++) {
                    screen.newTextGraphics().putString(Constants.WIDTH / 2 - 10, 1, "power up: MULTIPLE BULLETS");
                    screen.refresh();
                    i++;

                }

                if (multipleFireCount == 100) {
                    multipleFireCount = 0;
                    this.arena.setFireMultipleBullets(false);

                }
            }

            if (this.arena.aliensIsEmpty() && this.arena.getRunTimer() < 80) {
                audioPlayer.stopBackgroundAudio();
                audioPlayer.playLastLevelAudio();
                this.arena.setRunTimer(this.arena.getRunTimer() + 1);
                System.out.println(this.arena.getRunTimer());
            }


            switch (verifications.level(this.arena.getAlienFleet(), arena.getRunTimer())) {
                case 2 -> {
                    for (int i = 0; i <= 1; i++) {
                        screen.newTextGraphics().putString(Constants.WIDTH / 2 - 10, Constants.HEIGHT / 2, "level: 2");
                        screen.refresh();
                        i++;
                    }
                    this.arena.setRunTimer(0);
                    shotTimer = 300;
                    shotNumb = 3;
                    moveTimer = 60;
                    createElements.createAlienFleet(25, 5);
                    System.out.println("ENTREI NO 2");
                    playedLevelTwo = true;
                }
                case 3 -> {
                    for (int i = 0; i <= 1; i++) {
                        screen.newTextGraphics().putString(Constants.WIDTH / 2 - 10, Constants.HEIGHT / 2, "level: 3");
                        screen.refresh();
                        i++;

                    }
                    this.arena.setRunTimer(0);
                    shotTimer = 10;
                    shotNumb = 5;
                    moveTimer = 30;
                    createElements.createAlienFleet(30, 6);
                }
                case 4 -> {
                    for (int i = 0; i <= 1; i++) {
                        screen.newTextGraphics().putString(Constants.WIDTH / 2 - 10, Constants.HEIGHT / 2, "YOU WIN!");
                        screen.refresh();
                        i++;

                    }
                    drawMenu(screen);

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



