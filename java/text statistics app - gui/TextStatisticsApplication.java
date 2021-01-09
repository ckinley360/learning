
package textstatistics;
 
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.Arrays;
 
public class TextStatisticsApplication extends Application {
 
    @Override
    public void start(Stage window) {
        // Create the layout objects.
        BorderPane layout = new BorderPane();
        HBox labels = new HBox();
        labels.setSpacing(10);
        
        // Create the UI components.
        Label letterCountLabel = new Label("Letters: 0");
        Label wordCountLabel = new Label("Words: 0");
        Label longestWordLabel = new Label("The longest word is: ");
        TextArea textArea = new TextArea("");
        
        // Add the label UI components to the HBox object.
        labels.getChildren().addAll(letterCountLabel, wordCountLabel, longestWordLabel);
        
        // Listen for changes to the text of the text area component. If changes occur, then update the text statistics.
        textArea.textProperty().addListener((change, oldValue, newValue) -> {
            int characters = newValue.length();
            String[] parts = newValue.split(" ");
            int words = parts.length;
            
            String longest = Arrays.stream(parts)
                    .sorted((s1, s2) -> s2.length() - s1.length())
                    .findFirst()
                    .get();
          
            letterCountLabel.setText("Letters: " + characters);
            wordCountLabel.setText("Words: " + words);
            longestWordLabel.setText("The longest word is: " + longest);
        });
        
        // Add the text area component and HBox object to the BorderPane object.
        layout.setCenter(textArea);
        layout.setBottom(labels);
        
        // Create the Scene object.
        Scene view = new Scene(layout);
        
        // Add the Scene object to the Stage object.
        window.setScene(view);
        window.show();
    }
    
    public static void main(String[] args) {
        launch(TextStatisticsApplication.class);
    }
}
