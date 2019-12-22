package backend.controllers;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

public class SoundController {
    public static Clip clip = null;
    public static Clip battleClip = null;
    public static boolean mainPlaying = true;

    public static void playMainSound(){
        new Thread(new Runnable() {
            public void run() {
                try{
                    File f = new File("src/assets/sound/mainSound.wav");
                    InputStream in = new BufferedInputStream(new FileInputStream(f));
                    clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(in);
                    clip.open(inputStream);
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                    mainPlaying = true;
                } catch (Exception e){
                    System.out.println("Media Exception: " + e);
                }
            }
        }).start();
    }
    public static void toggleMainSound(){
        if( clip != null && clip.isRunning()) {
            clip.stop();
            mainPlaying = false;
        }
        else if( clip != null){
            clip.loop( Clip.LOOP_CONTINUOUSLY);
            mainPlaying = true;
        }
    }
    public static void playBattleSound(){
        if( clip != null && mainPlaying) {
            clip.stop();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    clip.loop( Clip.LOOP_CONTINUOUSLY);
                }
            }, 3000);
        }

        new Thread(new Runnable() {
            public void run() {
                try{
                    File f = new File("src/assets/sound/battleSound.wav");
                    InputStream in = new BufferedInputStream(new FileInputStream(f));
                    battleClip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(in);
                    battleClip.open(inputStream);
                    battleClip.start();
                } catch (Exception e){
                    System.out.println("Media Exception: " + e);
                }
            }
        }).start();
    }
}
