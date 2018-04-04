package GLaDOSService.Translate;

import Player.Player;
import SoundRecord.RecordService;
import Translate.GoogleTranslate;

import static GLaDOSService.GLaDOSService.repeatPhrase;

public class TranslateVoice {
    private RecordService recordService;

    private Player player;

    public TranslateVoice() {
        recordService = new RecordService();
        player = new Player();
    }

    public void translate() {
        try {
            String fraza = recordService.record(3000);
            player.speak("Na jaki język mam tłumaczyć?");
            String language = recordService.record(3000).toLowerCase();
            player.speak("Przetłumaczony tekst " + language + "to");
            translateOnLangugage(fraza,language);
        } catch (Exception e) {
            player.speak("Nie znaleziono danego języka");
            e.printStackTrace();
        }


    }

    private void translateOnLangugage(String fraza, String language) throws Exception, LanguageNotFoundException {
        String lang = "";
        switch (language) {
            case "na angielski":
                lang = "en-us";
                fraza = GoogleTranslate.translate("auto", lang, fraza);
                player.speak(fraza, lang);
                break;
            case "na niemiecki":
                lang = "de";
                fraza = GoogleTranslate.translate("auto",lang,fraza);
                player.speak(fraza, lang);
                break;
            default:
                throw new LanguageNotFoundException();
        }
        repeatPhrase(fraza,lang);
    }

}
