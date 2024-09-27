package no.uib.inf101.sem2.MusicSFX;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.*;

public class PlatformerMusic extends JPanel {
    private Clip clip;

    public PlatformerMusic(String filename) {
        try {
            File file = new File(filename);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // loop the audio file
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * used to play the music file.
     */
    public void play() {
        if (clip != null) {
            clip.start();
        }
        else {
            System.out.println("X");
        }
    }

    /**
     * used to stop playing the music file
     */
    public void stop() {
        if (clip != null) {
            clip.stop();
            clip.setFramePosition(0);
        }
    }
}
