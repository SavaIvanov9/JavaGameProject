package Game;

import sun.audio.*;
import java.io.*;

public class Sound {

    public static void music(boolean a) {
        if (a == true) {
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
        }
    }
}
