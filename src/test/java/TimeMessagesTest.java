import java.lang.IllegalArgumentException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Created by kateryna on 19.07.16.
 */
public class TimeMessagesTest {
    private TimeMessages timeMessages = new TimeMessages();

    @Test
    public void testGetCorrectMessageRU() throws Exception {
        int timeInMinutes = 60;
        ResourceBundle currentLocale = ResourceBundle.getBundle("message", new Locale("ru", "RU"));
        Assert.assertEquals("Доброй ночи, Мир!", timeMessages.getAppropriateMessage(timeInMinutes, currentLocale));
    }

    @Test
    public void testGetCorrectMessageEN() throws Exception {
        int timeInMinutes = 762;
        ResourceBundle currentLocale = ResourceBundle.getBundle("message", Locale.ENGLISH);
        Assert.assertEquals("Good day, World!", timeMessages.getAppropriateMessage(timeInMinutes, currentLocale));
    }

    @Test
    public void testGetCorrectMessageDefault() throws Exception {
        int timeInMinutes = 1305;
        ResourceBundle currentLocale = ResourceBundle.getBundle("message", Locale.getDefault());
        Assert.assertEquals("Good evening, World!", timeMessages.getAppropriateMessage(timeInMinutes, currentLocale));
    }

    @Test
    public void testGetCorrectMessageOtherLanguage() throws Exception {
        int timeInMinutes = 454;
        ResourceBundle currentLocale = ResourceBundle.getBundle("message", Locale.GERMAN);
        Assert.assertEquals("Good morning, World!", timeMessages.getAppropriateMessage(timeInMinutes, currentLocale));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCorrectMessageGreaterThanTimeInterval() throws Exception {
        int timeInMinutes = 1500;
        ResourceBundle currentLocale = ResourceBundle.getBundle("message", Locale.getDefault());
        timeMessages.getAppropriateMessage(timeInMinutes, currentLocale);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCorrectMessageSmallerThanTimeInterval() throws Exception {
        int timeInMinutes = -300;
        ResourceBundle currentLocale = ResourceBundle.getBundle("message", Locale.ENGLISH);
        timeMessages.getAppropriateMessage(timeInMinutes, currentLocale);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testGetCorrectMessageNullLocale() throws Exception {
        int timeInMinutes = 0;
        ResourceBundle currentLocale = null;
        timeMessages.getAppropriateMessage(timeInMinutes, currentLocale);
    }
}