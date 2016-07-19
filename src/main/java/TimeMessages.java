import java.lang.IllegalArgumentException;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Created by kateryna on 19.07.16.
 */
public class TimeMessages {
    private static final String RESOURCE_BUNDLE_NAME = "message";
    private static final int MINUTES_IN_HOUR_QUANTITY = 60;
    private static final int END_HOUR_OF_MORNING = 9;
    private static final int END_HOUR_OF_DAY = 19;
    private static final int END_HOUR_OF_EVENING = 23;
    private static final int END_HOUR_OF_NIGHT = 24;
    private static final int END_HOUR_OF_NIGHT_NEXT_DAY = 6;
    private static final String KEY_WORLD = "m.world";
    private static Map<Integer, String> timeIntervals = new TreeMap<Integer, String>();

    static {
        timeIntervals.put(END_HOUR_OF_MORNING * MINUTES_IN_HOUR_QUANTITY, "m.morning");
        timeIntervals.put(END_HOUR_OF_DAY * MINUTES_IN_HOUR_QUANTITY, "m.day");
        timeIntervals.put(END_HOUR_OF_EVENING * MINUTES_IN_HOUR_QUANTITY, "m.evening");
        timeIntervals.put(END_HOUR_OF_NIGHT * MINUTES_IN_HOUR_QUANTITY, "m.night");
        timeIntervals.put(END_HOUR_OF_NIGHT_NEXT_DAY * MINUTES_IN_HOUR_QUANTITY, "m.night");
    }

    public TimeMessages() {}

    public String getAppropriateMessage(int timeInMinutes, ResourceBundle currentLocale) throws IllegalArgumentException {
        int appropriateTimeInterval = -1;
        for (Map.Entry<Integer, String> currentEntry : timeIntervals.entrySet()) {
            if (timeInMinutes <= currentEntry.getKey() && appropriateTimeInterval == -1) {
                appropriateTimeInterval = currentEntry.getKey();
            }
        }
        if (timeInMinutes < 0 || appropriateTimeInterval == -1 || currentLocale == null) {
            throw new IllegalArgumentException("Time in minutes parameter is out of range");
        }
        return currentLocale.getString(timeIntervals.get(appropriateTimeInterval)) + ", "
                + currentLocale.getString(KEY_WORLD);
    }

    public String getMessage() throws IllegalArgumentException {
        int timeInMinutes = getLocalizedTimeInMinutes();
        ResourceBundle currentLocale = getCurrentLocale();
        return getAppropriateMessage(timeInMinutes, currentLocale);
    }

    private ResourceBundle getCurrentLocale() {
        Locale locale = Locale.getDefault();
        return ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, locale);
    }

    private int getLocalizedTimeInMinutes() {
        ZonedDateTime currentTime = ZonedDateTime.now();
        return currentTime.getHour() * MINUTES_IN_HOUR_QUANTITY + currentTime.getMinute();
    }
}
