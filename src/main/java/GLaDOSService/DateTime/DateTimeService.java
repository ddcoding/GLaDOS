package GLaDOSService.DateTime;

import GLaDOSService.GLaDOSService;
import Player.Player;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimeService {

    private Player player;

    private Calendar calendar;

    public DateTimeService() {
        player = new Player();
        calendar = Calendar.getInstance();
    }

    public void checkTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String czas = simpleDateFormat.format(calendar.getTime());
        player.speak(czas);
        GLaDOSService.repeatPhrase(czas);
    }

    public void checkDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String data = simpleDateFormat.format(calendar.getTime());
        player.speak(data);
        GLaDOSService.repeatPhrase(data);
    }
}
