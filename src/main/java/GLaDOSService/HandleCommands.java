package GLaDOSService;

import GLaDOSService.DateTime.DateTimeService;
import GLaDOSService.DateTime.DateTimeType;
import GLaDOSService.Translate.TranslateVoice;
import GLaDOSService.Weather.WeatherService;
import GLaDOSService.Welcome.WelcomeService;
import Player.Player;

public class HandleCommands {

    private TranslateVoice translateVoice;

    private WeatherService weatherService;

    private DateTimeService dateTimeService;

    private WelcomeService welcomeService;

    public HandleCommands() {
        translateVoice = new TranslateVoice();
        weatherService = new WeatherService();
        dateTimeService = new DateTimeService();
        welcomeService = new WelcomeService();
    }

    void handleCommand(String command){
        switch (command.toLowerCase()){
            case "kocham cię": Player.getInstance().speak("Tak? Miło mi");
            break;
            case "kim zostanę w przyszłości": Player.getInstance().speak("Mam dla Ciebie przykrą wiadomość. Zostaniesz nikim kochanie.");
            break;
            case"tłumacz":
            translateVoice.translate();
                break;
            case "podaj pogodę":
                weatherService.sayWeather();
                break;
            case "kim jest kuba": Player.getInstance().speak("Kuba jest dla mnie śmieciem. Jestem najlepsza. Żaden człowiek mi nie podskoczy. Kuba to zwykły programista java , a ja jestem wielki GLADOS hehe.");
            break;
            case "podaj godzinę":
                dateTimeService.checkDateTime(DateTimeType.TIME,true);
            break;
            case "podaj datę":
                dateTimeService.checkDateTime(DateTimeType.DATE,true);
                break;
            case "dzień dobry":
                welcomeService.speakWelcomeMessage();
                break;
            case "dobranoc":
                welcomeService.speakGoodbyeMessage();
                break;
            default: Player.getInstance().speak("Nie znam tej komendy.");
            break;
        }
    }

}
