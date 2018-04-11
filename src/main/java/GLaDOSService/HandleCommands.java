package GLaDOSService;

import GLaDOSService.Translate.TranslateVoice;
import GLaDOSService.Weather.WeatherService;
import Player.Player;

public class HandleCommands {

    private Player player;

    private TranslateVoice translateVoice;

    private WeatherService weatherService;

    public HandleCommands() {
        player = new Player();
        translateVoice = new TranslateVoice();
        weatherService = new WeatherService();
    }

    void handleCommand(String command){
        switch (command.toLowerCase()){
            case "kocham cię": player.speak("Tak? Miło mi");
            break;
            case "kim zostanę w przyszłości": player.speak("Mam dla Ciebie przykrą wiadomość. Zostaniesz nikim kochanie.");
            break;
            case"tłumacz": player.speak("Jaką frazę mam dla Ciebie przetłumaczyć?");
            translateVoice.translate();
                break;
            case "pogoda": player.speak("Z jakiego miasta chcesz usłyszeć pogodę?");
                weatherService.sayWeather();
                break;
            case "kim jest kuba": player.speak("Kuba jest dla mnie śmieciem. Jestem najlepsza. Żaden człowiek mi nie podskoczy. Kuba to zwykły programista java , a ja jestem wielki GLADOS hehe.");
            break;
            default: player.speak("Nie znam tej komendy.");
            break;
        }
    }

}
