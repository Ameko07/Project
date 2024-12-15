import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class Sound {

    private Clip clip;

    public Sound(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);

        } catch
        (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

    }

    //joue musique en boucle
    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
    }


    // arrête le son
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    // joue un son une fois
    public void playOnce() {
        if (clip != null) {
            clip.stop(); // Arrête le son si déjà en lecture
            clip.setFramePosition(0); // Repositionne au début du fichier
            clip.start();
        }
    }


    //teste si un son est en train d'être joué
    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }

}



