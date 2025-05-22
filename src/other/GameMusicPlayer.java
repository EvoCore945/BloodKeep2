package other;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
public class GameMusicPlayer {
    private Clip clip;
    public void playloop(String filename) {
        stop();
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(filename));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println("Error while playing sound: " + e.getMessage());
        }
    }
    public void stop(){
        if(clip != null && clip.isRunning()){
            clip.stop();
            clip.close();
        }
    }


}
