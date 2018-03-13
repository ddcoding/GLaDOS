package GLaDOSService;

import GLaDOSService.Translate.TranslateVoice;
import Player.Player;

public class HandleCommands {

    private Player player;

    private TranslateVoice translateVoice;

    public HandleCommands() {
        player = new Player();
        translateVoice = new TranslateVoice();
    }

    void handleCommand(String command){
        switch (command.toLowerCase()){
            case "kocham cię": player.speak("Tak? Miło mi");
            break;
            case"tłumacz": player.speak("Jaką frazę mam dla Ciebie przetłumaczyć?");
            translateVoice.translate();
                break;
            default: player.speak("Nie znam tej komendy.");
            break;
        }
    }

}
