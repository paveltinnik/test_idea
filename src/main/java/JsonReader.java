import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class JsonReader {
    private final String FILENAME = "json/tickets.json";
    private final String JSON_NAME = "tickets";

    public List<Ticket> readTicketsFromJson() {
        try (InputStream inputStream = getFileFromResourceAsStream(FILENAME)) {
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<HashMap<String, List<Ticket>>> typeRef = new TypeReference<HashMap<String, List<Ticket>>>() {};
            HashMap<String, List<Ticket>> tickets = objectMapper.readValue(inputStream, typeRef);

            return tickets.get(JSON_NAME);
        } catch (IOException e) {
            System.out.println("Error while reading file: " + e);
        }
        return null;
    }

    private InputStream getFileFromResourceAsStream(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }
}
