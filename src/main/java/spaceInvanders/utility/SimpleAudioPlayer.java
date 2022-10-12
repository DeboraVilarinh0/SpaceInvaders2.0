package spaceInvanders.utility;
// Java program to play an Audio
// file using Clip Object

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SimpleAudioPlayer {

    // to store current position
    Long currentFrame;
    Clip clip, clip2, clip3, clip4, clip5;

    // current status of clip
    String status;

    AudioInputStream audioInputStream;
    AudioInputStream audioInputStream2;
    AudioInputStream audioInputStream3;
    AudioInputStream audioInputStream4;
    AudioInputStream audioInputStream5;
    static String filePathSpaceShipBullet = "src/main/resources/audio/mixkit-short-laser-gun-shot-1670.wav";
    static String filePathGameBackgroundMusic = "src/main/resources/audio/John-Williams-Battle-of-the-Hero (online-audio-converter.com).wav";
    static String filePathDeath = "src/main/resources/audio/mixkit-epic-impact-afar-explosion-2782.wav";
    static String filePathDeath2 = "src/main/resources/audio/LEGO Yoda Death Sound (online-audio-converter.com).wav";
    static String filePathLastLevel = "src/main/resources/audio/Y2Mateis-Run-Meme-sound-effect-1 (online-audio-converter.com).wav";

    // constructor to initialize streams and clip
    public SimpleAudioPlayer() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        // create AudioInputStream object
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePathSpaceShipBullet).getAbsoluteFile());
        audioInputStream2 = AudioSystem.getAudioInputStream(new File(filePathGameBackgroundMusic).getAbsoluteFile());
        audioInputStream3 = AudioSystem.getAudioInputStream(new File(filePathDeath).getAbsoluteFile());
        audioInputStream4 = AudioSystem.getAudioInputStream(new File(filePathDeath2).getAbsoluteFile());
        audioInputStream5 = AudioSystem.getAudioInputStream(new File(filePathLastLevel).getAbsoluteFile());

        // create clip reference
        clip = AudioSystem.getClip();
        clip2 = AudioSystem.getClip();
        clip3 = AudioSystem.getClip();
        clip4 = AudioSystem.getClip();
        clip5 = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);
        clip2.open(audioInputStream2);
        clip3.open(audioInputStream3);
        clip4.open(audioInputStream4);
        clip5.open(audioInputStream5);

        //clip2.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void playBulletAudio() {
        //start the clip
        clip.start();
    }

    // Method to stop the audio
    public void stopBackgroundAudio() throws UnsupportedAudioFileException, IOException {
        currentFrame = 0L;
        clip2.stop();
        clip2.close();
        status = "stop background audio";
    }

    // Method to restart the audio
    public void restartBulletsAudio() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        clip.stop();
        clip.setMicrosecondPosition(0);
        this.playBulletAudio();
    }

    public void playBackgroundAudio() {
        //start the clip
        clip2.start();
        clip2.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void playDeathAudio() {
        //start the clip
        clip3.start();
        clip4.start();
    }

    public void playLastLevelAudio() {
        //start the clip
        clip5.start();
        clip2.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stopLastLevelAudio() throws UnsupportedAudioFileException, IOException {
        currentFrame = 0L;
        clip5.stop();
        clip5.close();
    }
}
