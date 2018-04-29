package GLaDOSService.DateTime;

import GLaDOSService.GLaDOSService;
import Player.Player;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimeService {

    private Calendar calendar;

    public DateTimeService() {
        calendar = Calendar.getInstance();
    }

    public void checkDateTime(DateTimeType dateTimeType, boolean repeat){
        SimpleDateFormat simpleDateFormat = null;
        if(dateTimeType == DateTimeType.TIME)
        simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        else if(dateTimeType == DateTimeType.DATE)
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String czas = simpleDateFormat.format(calendar.getTime());
        Player.getInstance().speak(czas);
        if(repeat)
        GLaDOSService.repeatPhrase(czas);
    }
}
