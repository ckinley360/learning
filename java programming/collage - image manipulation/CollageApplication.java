
package collage;
 
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
 
public class CollageApplication extends Application {
 
    @Override
    public void start(Stage stage) {
 
        // Create the Image object for the source image.
        Image sourceImage = new Image("file:monalisa.png");
        int sourceWidth = (int) sourceImage.getWidth();
        int sourceHeight = (int) sourceImage.getHeight();
        
        // Create and add the modified image which is composed of 4 shrunk versions of the source image.
        WritableImage modifiedImage = new WritableImage(sourceWidth, sourceHeight);
        modifyImage(sourceImage, modifiedImage, 0.5, 0.5, 0, 0);
        modifyImage(sourceImage, modifiedImage, 0.5, 0.5, sourceWidth / 2, 0);
        modifyImage(sourceImage, modifiedImage, 0.5, 0.5, 0, sourceHeight / 2);
        modifyImage(sourceImage, modifiedImage, 0.5, 0.5, sourceWidth / 2, sourceHeight / 2);
 
        // Create the image view for the modified image.
        ImageView image = new ImageView(modifiedImage);
 
        // Create the pane layout and add the modified image to it.
        Pane pane = new Pane();
        pane.getChildren().add(image);
        
        stage.setScene(new Scene(pane));
        stage.show();
    }
    
    // xFactor is the factor by which we want to either shrink or stretch the x values of the source image.
    // yFactor is the factor by which we want to either shrink or stretch the y values of the source image.
    // xShift is the number of units that we'd like to shift the x values of the source image by.
    // yShift is the number of units that we'd like to shift the y values of the source image by.
    public static void modifyImage(Image sourceImage, WritableImage targetImage, double xFactor, double yFactor, int xShift, int yShift) {
        // Create the image reader for source image.
        PixelReader imageReader = sourceImage.getPixelReader();
 
        // Dimensions of the source image.
        int width = (int) sourceImage.getWidth();
        int height = (int) sourceImage.getHeight();
        
        // Create the image writer for the target image.
        PixelWriter imageWriter = targetImage.getPixelWriter();
        
        int yCoordinate = 0;
        while (yCoordinate < height) {
            int xCoordinate = 0;
            while (xCoordinate < width) {
 
                Color color = imageReader.getColor(xCoordinate, yCoordinate);
                double red = 1.0 - color.getRed();
                double green = 1.0 - color.getGreen();
                double blue = 1.0 - color.getBlue();
                double opacity = color.getOpacity();
 
                Color newColor = new Color(red, green, blue, opacity);
                
                // Only map the even x & y values of the source file to the target file, since there's no such thing as fractional pixels. They are discrete, not continuous.
                if (xCoordinate % 2 == 0 && yCoordinate % 2 == 0) {
                    imageWriter.setColor((int) (xCoordinate * xFactor) + xShift, (int) (yCoordinate * yFactor) + yShift, newColor);
                }
             
                xCoordinate++;
            }
 
            yCoordinate++;
        }
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}
