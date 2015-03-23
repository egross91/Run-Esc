package org.escaperun.game.controller;

import javax.sound.sampled.*;
import java.io.File;

public enum Sound {

    INTRO_MUSIC(System.getProperty("user.dir") + "/assets/sounds/scapemain.mid", true),
    PLAYING1(System.getProperty("user.dir") + "/assets/sounds/harmony.mid", true),
    PLAYING2(System.getProperty("user.dir") + "/assets/sounds/newbiemelody.mid", true),
    BOSS_FIGHT(System.getProperty("user.dir") + "/assets/sounds/boss.mid", true),
    CASTSPELL(System.getProperty("user.dir") + "/assets/sounds/castspell.wav", false),
    CREEPDEAD(System.getProperty("user.dir") + "/assets/sounds/creepdead.wav", false),
    LEVELUP(System.getProperty("user.dir") + "/assets/sounds/levelup.wav", false),
    MELEE(System.getProperty("user.dir") + "/assets/sounds/melee.wav", false),
    PROJECTILE(System.getProperty("user.dir") + "/assets/sounds/projectile.wav", false),
    TELEPORT(System.getProperty("user.dir") + "/assets/sounds/teleport.wav", false);

    private Sound(String filename, boolean areaSound) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(filename));
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            this.clip = clip;
            this.loop = areaSound;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Clip clip;
    private boolean loop;

    public void play() {
        if (clip.isRunning() && loop) return;
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (loop) {
                    for (Sound s : Sound.values()) {
                        if (s.loop) s.stop();
                    }
                }
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
