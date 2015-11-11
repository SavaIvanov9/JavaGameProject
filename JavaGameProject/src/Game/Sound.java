package Game;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Sound {

    public static void music(boolean a) throws FileNotFoundException {
        if (a) {
            AudioPlayer MGP = AudioPlayer.player;
            AudioStream BGM;
            ContinuousAudioDataStream loop = null;
            try {
                BGM = new AudioStream(new FileInputStream("resources\\Sound\\theme2.wav"));
                AudioPlayer.player.start(BGM);
            } catch (IOException e) {
                try {
                    BGM = new AudioStream(new FileInputStream("JavaGameProject\\resources\\Sound\\theme2.wav"));
                    AudioPlayer.player.start(BGM);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }

            MGP.start(loop);
        } else {
            try {
                AudioStream BGM = new AudioStream(new FileInputStream("resources\\Sound\\theme2.wav"));
                AudioPlayer.player.stop(BGM);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
