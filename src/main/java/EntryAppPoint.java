/**
 * Created by kateryna on 19.07.16.
 */
public class EntryAppPoint {

    public static void main(String[] args) {
        TimeMessages timeMessages = new TimeMessages();
        try {
            System.out.println(timeMessages.getMessage());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
