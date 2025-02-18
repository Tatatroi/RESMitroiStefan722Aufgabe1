import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String filename = "src/ninja_events.json";
        List<NinjaSchlachten> ninjas = new ArrayList<>();
        ninjas = readJSONFile(filename);
//        System.out.println(ninjas);

        System.out.println("Enter Krafpunkte zu fiter: ");
        Scanner scanner = new Scanner(System.in);

//        double x = Double.parseDouble(scanner.nextLine());

//        erstefilter(ninjas, x);

        zweiteFilter(ninjas);

    }

    public static List<NinjaSchlachten> readJSONFile(String fileName) {
        List<NinjaSchlachten> ninjas = new ArrayList<>();
        try {
            // Read JSON file as a string
            String jsonContent = new String(Files.readAllBytes(Paths.get(fileName)));

            // Extract the array of JSON objects
            jsonContent = jsonContent.trim();
            if (!jsonContent.startsWith("[") || !jsonContent.endsWith("]")) {
                throw new RuntimeException("Invalid JSON format!");
            }

            // Remove brackets and split JSON objects
            String logsArray = jsonContent.substring(1, jsonContent.length() - 1).trim();
            String[] logEntries = logsArray.split("},\\s*\\{"); // Splitting individual objects

            for (String log : logEntries) {
                log = log.replace("{", "").replace("}", "").replace("\"", "").trim();
                String[] fields = log.split(",");

                Map<String, String> data = new HashMap<>();
                for (String field : fields) {
                    String[] keyValue = field.split(":");
                    if (keyValue.length == 2) {
                        data.put(keyValue[0].trim(), keyValue[1].trim());
                    }
                }

                // Create and add the Case object
                NinjaSchlachten ninjaSchlachten = new NinjaSchlachten(
                        Integer.parseInt(data.get("Id")),
                        data.get("Charaktername"),
                        data.get("Stufe"),
                        data.get("Beschreibung"),
                        data.get("Datum"),
                        Double.parseDouble(data.get("Kraftpunkte"))
                );

                ninjas.add(ninjaSchlachten);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return ninjas;
    }

    /**
     * filter der ninjas das hat der score grosser als 5000
     * @param ninjas
     * @param score
     */
    public static void erstefilter(List<NinjaSchlachten> ninjas, double score){
        ninjas.stream().filter(n -> n.getKraftPunkte() > 5000).map(n -> n.getName()).distinct()
                .forEach(n -> System.out.println(n));
    }

    /**
     * return in absteigend Ordnung die ninjas die hat die Stufe Jonin
     * @param ninjas
     */
    public static void zweiteFilter(List<NinjaSchlachten> ninjas){
        ninjas.stream().filter(n -> n.getStufe().equals("Jonin")).sorted(Comparator.comparing(NinjaSchlachten::getDatum).reversed()).
                map(n-> n.getDatum() + ": " + n.getName() + " - " + n.getDescription()).forEach(System.out::println);
    }

}