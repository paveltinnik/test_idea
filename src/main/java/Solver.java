import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class Solver {
    public String getAverageFlightTime(List<Ticket> ticketList) {
	    long averageTimeInSeconds = ticketList.stream()
			    .filter(ticket -> ticket.getOrigin_name().equals("Владивосток") && ticket.getDestination_name().equals("Тель-Авив")
					    || ticket.getOrigin_name().equals("Тель-Авив") && ticket.getDestination_name().equals("Владивосток"))
			    .map(ticket -> {
				    long startMillis = getDateAndTimeInMillis(ticket.getDeparture_date(), ticket.getDeparture_time());
				    long endMillis = getDateAndTimeInMillis(ticket.getArrival_date(), ticket.getArrival_time());
				
				    return (long) (endMillis - startMillis) / 1000;
			    })
			    .collect(Collectors.averagingLong(l -> l))
			    .longValue();
		
	    return convertSecondsToTime(averageTimeInSeconds);
    }
	
	public String getPercentile(int percentile, List<Ticket> ticketList) {
		List<Long> times = ticketList.stream()
				.filter(ticket -> ticket.getOrigin_name().equals("Владивосток") && ticket.getDestination_name().equals("Тель-Авив")
						|| ticket.getOrigin_name().equals("Тель-Авив") && ticket.getDestination_name().equals("Владивосток"))
				.map(ticket -> {
					long startMillis = getDateAndTimeInMillis(ticket.getDeparture_date(), ticket.getDeparture_time());
					long endMillis = getDateAndTimeInMillis(ticket.getArrival_date(), ticket.getArrival_time());

					return (endMillis - startMillis) / 1000;
				})
				.sorted()
				.collect(Collectors.toList());
		
		int percentileIndex = (int) Math.ceil((double) percentile / 100 * times.size()) - 1;
		
		return convertSecondsToTime(times.get(percentileIndex));
	}
	
	private static long getDateAndTimeInMillis(Calendar date, Calendar time) {
		date.set(Calendar.HOUR_OF_DAY, time.get(Calendar.HOUR_OF_DAY));
		date.set(Calendar.MINUTE, time.get(Calendar.MINUTE));
		
		return date.getTimeInMillis();
	}
	
	private String convertSecondsToTime(long inputSeconds) {
		int hours = (int) inputSeconds / (60 * 60);
		int minutes = (int) inputSeconds / 60 % 60;
		int seconds = (int) inputSeconds % 60;
		
		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}
}