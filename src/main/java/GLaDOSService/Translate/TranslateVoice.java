package GLaDOSService.Translate;

import GoogleRecognize.TranscriptVoice;
import Player.Player;
import SoundRecord.RecordService;
import Translate.GoogleTranslate;

public class TranslateVoice {
    private TranscriptVoice transcriptVoice;

    private RecordService recordService;

    private Player player;

    public TranslateVoice() {
        transcriptVoice = new TranscriptVoice();
        recordService = new RecordService();
        player = new Player();
    }

    public void translate() {
        recordService.record(3000);
        try {
            String fraza = transcriptVoice.getTranscription();
            player.speak("Na jaki język mam tłumaczyć?");
            recordService.record(3000);
            String language = transcriptVoice.getTranscription().toLowerCase();
            player.speak("Przetłumaczony tekst " + language + "to");
            translateOnLangugage(fraza,language);
        } catch (Exception e) {
            player.speak("Nie znaleziono danego języka");
            e.printStackTrace();
        }


    }

    private void translateOnLangugage(String fraza, String language) throws Exception, LanguageNotFoundException {
        switch (language) {
            case "na angielski":
                fraza = GoogleTranslate.translate("auto", "en-us", fraza);
                player.speak(fraza, "en-us");
                break;
            default:
                throw new LanguageNotFoundException();
        }
    }

}
