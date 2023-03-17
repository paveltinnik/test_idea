import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JsonReader jsonReader = new JsonReader();
        List<Ticket> ticketList = jsonReader.readTicketsFromJson();

        ticketList.stream()
                .map(e -> e.getArrival_date().getTime() + " " + e.getArrival_time().getTime()
                ).forEach(System.out::println);

    }
}
