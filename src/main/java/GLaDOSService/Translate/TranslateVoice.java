package GLaDOSService.Translate;

import GLaDOSService.GLaDOSService;
import Player.Player;
import SoundRecord.RecordService;
import Translate.GoogleTranslate;
import jdk.nashorn.internal.objects.Global;

import static GLaDOSService.GLaDOSService.repeatPhrase;

public class TranslateVoice {


    public TranslateVoice() {
    }

    public void translate() {
        try {
            Player.getInstance().speak("Jaką frazę mam dla Ciebie przetłumaczyć?");
            String fraza = GLaDOSService.getTranscriptFromRecord(3000);
            Player.getInstance().speak("Na jaki język mam tłumaczyć?");
            String language = GLaDOSService.getTranscriptFromRecord(3000).toLowerCase();
            Player.getInstance().speak("Przetłumaczony tekst " + language + "to");
            translateOnLangugage(fraza,language);
        } catch (Exception e) {
            Player.getInstance().speak("Nie znaleziono danego języka");
            e.printStackTrace();
        }


    }

    private void translateOnLangugage(String fraza, String language) throws Exception, LanguageNotFoundException {
        String lang = "";
        switch (language) {
            case "na angielski":
                lang = "en-us";
                fraza = GoogleTranslate.translate("auto", lang, fraza);
                Player.getInstance().speak(fraza, lang);
                break;
            case "na niemiecki":
                lang = "de";
                fraza = GoogleTranslate.translate("auto",lang,fraza);
                Player.getInstance().speak(fraza, lang);
                break;
            default:
                throw new LanguageNotFoundException();
        }
        repeatPhrase(fraza,lang);
    }

}
