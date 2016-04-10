package me.zacharyjia.naruto.game.utils;

import me.zacharyjia.naruto.core.utils.ResourcesLoader;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by jia19 on 2016/4/9.
 */
public class SoundManager {

    private static SoundManager instance;
    private static Clip clip;

    public static void play(String fileName) {
        try {
            InputStream inputStream = ResourcesLoader.getInputStream(fileName);
            AudioStream audioStream = new AudioStream(inputStream);
            AudioPlayer.player.start(audioStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setBackground(String fileName) {
        try {
            clip = AudioSystem.getClip();
            URL url = SoundManager.class.getResource(fileName);
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            clip.open(ais);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public static void startBackgroundMusic() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
    }

    public static void stopBackgroundMusic() {
        clip.stop();
    }

}
