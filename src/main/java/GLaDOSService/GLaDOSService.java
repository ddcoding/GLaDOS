package GLaDOSService;

import Constans.ModelConstants;
import GoogleRecognize.TranscriptVoice;
import Player.Player;
import SoundRecord.RecordService;
import ai.kitt.snowboy.SnowboyDetect;

import javax.sound.sampled.*;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GLaDOSService {
    private Player player;
    private static RecordService recordService = new RecordService();
    private static TranscriptVoice transcriptVoice = new TranscriptVoice();
    private HandleCommands handleCommands;
    public GLaDOSService() {
        player = new Player();
        handleCommands = new HandleCommands();
    }

    public void startService() throws Exception {
        Player player = new Player();
        String s = null;
        while (true) {
                if (isCalled(ModelConstants.AVA)) {
                    player.speak("Witam jestem Ejwa, w czym mogę służyć?");
                        try {
                            s = getTranscriptFromRecord(3000);
                            handleCommands.handleCommand(s);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }
        }
    }

    public static void repeatPhrase(String phrase){
        Player player = new Player();
        RecordService recordService = new RecordService();
        player.speak("Czy powtórzyć ?");
        try {
            if(getTranscriptFromRecord(3000).toLowerCase().equals("tak")) {
                player.speak(phrase);
                repeatPhrase(phrase);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void repeatPhrase(String phrase, String language){
        Player player = new Player();
        RecordService recordService = new RecordService();
        player.speak("Czy powtórzyć ?");
        try {
            if(getTranscriptFromRecord(3000).toLowerCase().equals("tak")) {
                player.speak(phrase, language);
                repeatPhrase(phrase, language);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static List<String> splitPhrase(String phrase){
        return  Arrays.stream(phrase.split(" "))
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }

    public static String getTranscriptFromRecord(int time){
        try {
            recordService.record(time);
            return transcriptVoice.getTranscription();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Wystąpił błąd.";
    }

    private boolean isCalled(String modelName){
        SnowboyDetect detector = new SnowboyDetect("common.res",
                modelName);

        AudioFormat format = new AudioFormat(16000, 16, 1, true, false);
        DataLine.Info targetInfo = new DataLine.Info(TargetDataLine.class, format);

        TargetDataLine targetLine =
                null;
        try {
            targetLine = (TargetDataLine) AudioSystem.getLine(targetInfo);
        targetLine.open(format);
        targetLine.start();

        // Reads 0.1 second of audio in each call.
        byte[] targetData = new byte[3200];
        short[] snowboyData = new short[1600];
        int numBytesRead;

        while (true) {
            // Reads the audio data in the blocking mode. If you are on a very slow
            // machine such that the hotword detector could not process the audio
            // data in real time, this will cause problem...
            numBytesRead = targetLine.read(targetData, 0, targetData.length);

            if (numBytesRead == -1) {
                System.out.print("Fails to read audio data.");
                break;
            }

            // Converts bytes into int16 that Snowboy will read.
            ByteBuffer.wrap(targetData).order(
                    ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(snowboyData);

            // Detection.
            int result = detector.RunDetection(snowboyData, snowboyData.length);
            if (result > 0) {
                return true;
            }
        }
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        return false;
    }

}
