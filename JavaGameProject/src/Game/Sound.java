package Game;

import sun.audio.*;
import java.io.*;

public class Sound {

    public static void music() {
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        ContinuousAudioDataStream loop = null;
        try {
            BGM = new AudioStream(new FileInputStream("resources\\Sound\\song2.wav"));
            AudioPlayer.player.start(BGM);
        } catch (IOException e) {
            try {
                BGM = new AudioStream(new FileInputStream("JavaGameProject\\resources\\Sound\\song2.wav"));
                AudioPlayer.player.start(BGM);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

        MGP.start(loop);
    }
}