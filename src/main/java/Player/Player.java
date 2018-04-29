package Player;

import Synthesizer.SynthesiserV2;
import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;

public class Player {
    private SynthesiserV2 synthesiserV2;

    private static Player ourInstance = new Player();

    public static Player getInstance() {
        return ourInstance;
    }


    public Player() {
        synthesiserV2 = new SynthesiserV2("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");
        synthesiserV2.setSpeed(0.7);
        synthesiserV2.setLanguage("pl");
    }

    public boolean speak(String text,String language){
        synthesiserV2.setLanguage(language);
        boolean result = speak(text);
        synthesiserV2.setLanguage("pl");
        return result;
    }

    public boolean speak(String text){

        javazoom.jl.player.Player player = null;
        try {
            player = new javazoom.jl.player.Player(synthesiserV2.getMP3Data(text));
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        try {
            if (player != null) {
                return player.play(2147483647);
            }
            return false;
        } catch (JavaLayerException e) {
            e.printStackTrace();
            return false;
        }
    }
}
