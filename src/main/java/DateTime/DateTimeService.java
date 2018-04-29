package DateTime;

import GLaDOSService.GLaDOSService;
import Player.Player;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimeService {

    private Calendar calendar;

    public DateTimeService() {
        calendar = Calendar.getInstance();
    }

    public String getDateTime(DateTimeType dateTimeType){
        SimpleDateFormat simpleDateFormat = null;
        if(dateTimeType == DateTimeType.TIME)
        simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        else if(dateTimeType == DateTimeType.DATE)
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
         return simpleDateFormat.format(calendar.getTime());
    }
}
