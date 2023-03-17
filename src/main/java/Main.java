import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            JsonReader jsonReader = new JsonReader();
            List<Ticket> ticketList = jsonReader.readTicketsFromJson();
    
            Solver solver = new Solver();
            String averageTime = solver.getAverageFlightTime(ticketList);
            System.out.println("Среднее время полета: " + averageTime);
    
            String percentile = solver.getPercentile(90, ticketList);
            System.out.println("90 перцентиль: " + percentile);
            
        } catch (Exception e) {
            System.out.println("Error while running program: " + e);
        }
    }
}