
package application;
 
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.nio.file.Paths;
 
public class PartiesApplication extends Application {
 
    @Override
    public void start(Stage stage) {
        // Create the x & y axes.
        NumberAxis xAxis = new NumberAxis(1968, 2008, 5);
        NumberAxis yAxis = new NumberAxis(0,30,5);
        
        // Create the line chart.
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Relative support of the parties");
        
        // Read the data from the file.
        Map<String, Map<Integer, Double>> values = readData("partiesdata.tsv");
        
        // Go through the parties and add them to the chart.
        values.keySet().stream().forEach(party -> {
            // Create a different data set for each party.
            XYChart.Series data = new XYChart.Series();
            data.setName(party);
            
            // Add the party's support numbers to the data set.
            values.get(party).entrySet().stream().forEach(pair -> {
                data.getData().add(new XYChart.Data(pair.getKey(), pair.getValue()));
            });
            
            // Add the data set to the chart.
            lineChart.getData().add(data);
        });
        
        // Display the line chart.
        Scene view = new Scene(lineChart, 640, 480);
        stage.setScene(view);
        stage.show();
    }
    
    public static Map<String, Map<Integer, Double>> readData(String filePath) {
        // Create the object that will be returned.
        Map<String, Map<Integer, Double>> values = new HashMap<String, Map<Integer, Double>>();
        
        // Create a Scanner object to read the file.
        try (Scanner scanner = new Scanner(Paths.get(filePath))) {
            
            // Capture the header row and keep it separate. We will use it to grab the years for the other rows.
            String firstRow = scanner.nextLine();
            String[] firstRowPieces = firstRow.split("\t");
            
            // Read the file until all lines have been read.
            while (scanner.hasNextLine()) {
                
                // Read the row.
                String row = scanner.nextLine();
                String[] pieces = row.split("\t");
                
                // Add the party to the hashmap.
                Map<Integer, Double> pairs = new HashMap<Integer, Double>(); // Create the hashmap that will store the year-support pairs.
                values.put(pieces[0], pairs); // Add the party and pairs hashmap to the parent hashmap.
                
                // Add the year-support pairs to the pairs hashmap for the current party.
                for (int i = 1; i < pieces.length; i++) {
                    
                    // If the support is a dash (representing absence of data for that year), then replace it with a 0.
                    double support = 0.0;
                    if (!pieces[i].equals("-")) {
                        support = Double.valueOf(pieces[i]);
                    }
                    
                    pairs.put(Integer.valueOf(firstRowPieces[i]), support);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        return values;
    }
    
    public static void main(String[] args) {
// Test the readData() method.
//        Map<String, Map<Integer, Double>> values = readData("partiesdata.tsv");
//        System.out.println(values);

        launch(PartiesApplication.class);
    }
}
