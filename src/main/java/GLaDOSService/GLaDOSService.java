package GLaDOSService;

import GoogleRecognize.TranscriptVoice;
import Player.Player;
import SoundRecord.RecordService;

import java.lang.reflect.Array;
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
                if (getTranscriptFromRecord(2000).equals("GLaDOS")) {
                    player.speak("Witam jestem GLaDOS, w czym mogę służyć?");
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


}
