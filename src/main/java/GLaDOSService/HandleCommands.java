package GLaDOSService;

import DateTime.DateTimeService;
import DateTime.DateTimeType;
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
                String czas = dateTimeService.getDateTime(DateTimeType.TIME);
                Player.getInstance().speak(czas);
                    GLaDOSService.repeatPhrase(czas);
            break;
            case "podaj datę":
                String data = dateTimeService.getDateTime(DateTimeType.DATE);
                Player.getInstance().speak(data);
                GLaDOSService.repeatPhrase(data);
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
