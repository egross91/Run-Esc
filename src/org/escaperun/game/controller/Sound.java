package org.escaperun.game.controller;

import javax.sound.sampled.*;
import java.io.File;

public enum Sound {

    INTRO_MUSIC(System.getProperty("user.dir") + "/assets/sounds/scapemain.mid", true),
    PLAYING(System.getProperty("user.dir") + "/assets/sounds/harmony.mid", true);

    private Sound(String filename, boolean loop) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(filename));
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            this.clip = clip;
            this.loop = loop;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Clip clip;
    private boolean loop;

    public void play() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                clip.stop();
                clip.setFramePosition(0);
                if (loop) {
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                } else {
                    clip.start();
                }
            }
        }).start();
    }

    public void stop() {
        if (clip == null) return;
        clip.stop();
    }
}
