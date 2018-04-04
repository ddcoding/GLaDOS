package GLaDOSService;

import Player.Player;
import SoundRecord.RecordService;

public class GLaDOSService {
    private Player player;
    private RecordService recordService;
    private HandleCommands handleCommands;
    public GLaDOSService() {
        player = new Player();
        recordService = new RecordService();
        handleCommands = new HandleCommands();
    }

    public void startService() throws Exception {
        Player player = new Player();
        String s = null;
        while (true) {
                if (recordService.record(2000).equals("GLaDOS")) {
                    player.speak("Witam jestem GLaDOS, w czym mogę służyć?");
                        try {
                            s = recordService.record(3000);
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
            if(recordService.record(3000).toLowerCase().equals("tak")) {
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
            if(recordService.record(3000).toLowerCase().equals("tak")) {
                player.speak(phrase, language);
                repeatPhrase(phrase, language);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
