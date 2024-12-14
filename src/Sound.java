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


    //joue musique une fois
    public static void playOnce(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/" + soundName + ".wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // arrête le son
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    // vérifie si le son est en train de jouer
    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }
}


