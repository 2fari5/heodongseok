import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

class Backgroundsound {

    public void main_sound() {
        File bgm;
        AudioInputStream stream;
        AudioFormat format;
        DataLine.Info info;

        bgm = new File("sound/sound.wav"); 

        Clip clip;

        try {
            stream = AudioSystem.getAudioInputStream(bgm);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();

        } catch (Exception e) {
            System.out.println("err : " + e);
        }

    }
}