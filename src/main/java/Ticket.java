import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private String origin;
    private String origin_name;
    private String destination;
    private String destination_name;
    @JsonFormat(pattern = "dd.MM.yy")
    private Calendar departure_date;
    @JsonFormat(pattern = "HH:mm")
    private Calendar departure_time;
    @JsonFormat(pattern = "dd.MM.yy")
    private Calendar arrival_date;
    @JsonFormat(pattern = "HH:mm")
    private Calendar arrival_time;
    private String carrier;
    private int stops;
    private int price;
}