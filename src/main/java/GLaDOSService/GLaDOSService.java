package GLaDOSService;

import GoogleRecognize.TranscriptVoice;
import Player.Player;
import SoundRecord.RecordService;

public class GLaDOSService {
    private Player player;
    private TranscriptVoice transcriptVoice;
    private RecordService recordService;
    private HandleCommands handleCommands;
    public GLaDOSService() {
        player = new Player();
        transcriptVoice = new TranscriptVoice();
        recordService = new RecordService();
        handleCommands = new HandleCommands();
    }

    public void startService() throws Exception {
        Player player = new Player();
        String s = null;
        while (true) {
            if(recordService.record(2000))
                if (transcriptVoice.getTranscription().equals("GLaDOS")) {
                    player.speak("Witam jestem GLaDOS, w czym mogę służyć?");
                    if (recordService.record(3000)) {
                        try {
                            s = transcriptVoice.getTranscription();
                            handleCommands.handleCommand(s);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        }
    }


}
