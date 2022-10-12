package spaceInvanders;

import spaceInvanders.services.Game;
import spaceInvanders.utility.Constants;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Application {
    public static void main(String[] args) {

        try {
            Game game = new Game(Constants.WIDTH, Constants.HEIGHT);
            game.run();
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException | LineUnavailableException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
