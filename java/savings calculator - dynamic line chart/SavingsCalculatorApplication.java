
package application;
 
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.HashMap;
import java.lang.Math;
 
public class SavingsCalculatorApplication extends Application {
 
    @Override
    public void start(Stage stage) {
        // Create the main layout.
        BorderPane mainBorderPane = new BorderPane();
        
        // Create the slider layout.
        VBox vBox = new VBox();
        BorderPane savingsBorderPane = new BorderPane();
        BorderPane interestBorderPane = new BorderPane();
        
        // Create the UI components for the slider layout.
        Label savingsLabel = new Label("Monthly savings");
        Label interestLabel = new Label("Yearly interest rate");
        Slider savingsSlider = new Slider(25, 250, 50);
        Slider interestSlider = new Slider(0, 10, 1);
        Label savingsNumberLabel = new Label(String.valueOf(savingsSlider.getValue()));
        Label interestNumberLabel = new Label(String.valueOf(interestSlider.getValue()));
        
        // Style the UI components for the slider layout.
        savingsSlider.setShowTickMarks(true);
        savingsSlider.setShowTickLabels(true);
        interestSlider.setShowTickMarks(true);
        interestSlider.setShowTickLabels(true);
        
        // Add the UI components to the slider layout.
        savingsBorderPane.setLeft(savingsLabel);
        savingsBorderPane.setCenter(savingsSlider);
        savingsBorderPane.setRight(savingsNumberLabel);
        interestBorderPane.setLeft(interestLabel);
        interestBorderPane.setCenter(interestSlider);
        interestBorderPane.setRight(interestNumberLabel);
        
        // Add the sub-sub-layouts to the sub-layout.
        vBox.getChildren().addAll(savingsBorderPane, interestBorderPane);
 
        // Create the x & y axes.
        NumberAxis xAxis = new NumberAxis(0, 30, 1);
        NumberAxis yAxis = new NumberAxis();
        
        // Create the line chart.
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Savings Calculator");
        
        // Add the line chart and sub-layout to the main layout.
        mainBorderPane.setCenter(lineChart);
        mainBorderPane.setTop(vBox);
        
        // Prepare and plot the initial data.
        Map<String, Map<Integer, Double>> data = prepareDataForPlotting(savingsSlider.getValue(), interestSlider.getValue() / 100.0);
        plotData(lineChart, data);
        
        // Add listeners to the sliders.
        DecimalFormat formatter = new DecimalFormat("#0.00");
        
        savingsSlider.valueProperty().addListener((event) -> {
            // Update the savings slider label.
            savingsNumberLabel.setText(String.valueOf(formatter.format(savingsSlider.getValue())));
            
            // Clear out the old data from the line chart.
            lineChart.getData().clear();
            
            // Prepare and plot the data.
            Map<String, Map<Integer, Double>> updatedData = prepareDataForPlotting(savingsSlider.getValue(), interestSlider.getValue() / 100.0);
            plotData(lineChart, updatedData);
        });
        
        interestSlider.valueProperty().addListener((event) -> {
            // Update the interest slider label.
            interestNumberLabel.setText(String.valueOf(formatter.format(interestSlider.getValue())));
            
            // Clear out the old data from the line chart.
            lineChart.getData().clear();
            
            // Prepare and plot the data.
            Map<String, Map<Integer, Double>> updatedData = prepareDataForPlotting(savingsSlider.getValue(), interestSlider.getValue() / 100.0);
            plotData(lineChart, updatedData);
        });
        
        // Display all the things.
        Scene view = new Scene(mainBorderPane, 640, 480);
        stage.setScene(view);
        stage.show();
    }
    
    public static Map<Integer, Double> calculateDataPoints(double monthlySavings, double apy) {
        // Create the object that will be returned.
        Map<Integer, Double> dataPoints = new HashMap<>();
        
        double yearlySavings = monthlySavings * 12;
        
        for (int i = 0; i <= 30; i++) {
            
            // If the apy is 0, then we can do simple multiplication since there is no interest involved. Otherwise, we use a formula to calculate compound interest with regular contributions.
            if (apy == 0) {
                dataPoints.put(i, yearlySavings * i);
            } else {
                dataPoints.put(i, calculateCompoundInterestForPrincipal(yearlySavings, apy, 1, i) + calculateFutureValueOfASeries(monthlySavings, apy, 1, i));
            } 
        }
        
        return dataPoints;
    }
    
    // Formula for both calculation methods: https://www.thecalculatorsite.com/articles/finance/compound-interest-formula.php
    public static double calculateCompoundInterestForPrincipal(double principal, double apy, int compoundingFrequency, int years) {
        return principal * Math.pow(1 + (apy / compoundingFrequency), compoundingFrequency * years); 
    }
    
    public static double calculateFutureValueOfASeries(double monthlyPayment, double apy, int compoundingFrequency, int years) {
        double annualPayment = monthlyPayment * 12;
        
        return annualPayment * ((Math.pow(1 + apy / compoundingFrequency, compoundingFrequency * years) - 1) / (apy / compoundingFrequency)) * (1 + apy / compoundingFrequency);
    }
    
    public static Map<String, Map<Integer, Double>> prepareDataForPlotting(double monthlySavings, double apy) {
        // Create the object that will be returned.    
        Map<String, Map<Integer, Double>> data = new HashMap<String, Map<Integer, Double>>();
        
        // Prepare the savings-only data.
        data.put("Savings Only", calculateDataPoints(monthlySavings, 0));
        
        // Prepare the savings and interest data.
        data.put("Savings and Interest", calculateDataPoints(monthlySavings, apy));
        
        return data;
    }
    
    public static void plotData(LineChart<Number, Number> lineChart, Map<String, Map<Integer, Double>> data) {
        // Go through the series and add them to the chart.
        data.keySet().stream().forEach(series -> {
            // Create a different data set for each series.
            XYChart.Series seriesData = new XYChart.Series();
            seriesData.setName(series);
            
            // Add the series' data points to the data set.
            data.get(series).entrySet().stream().forEach(point -> {
                seriesData.getData().add(new XYChart.Data(point.getKey(), point.getValue()));
            });
            
            // Add the data set to the chart.
            lineChart.getData().add(seriesData);
        });
    }
    
    public static void main(String[] args) {
        launch(SavingsCalculatorApplication.class);
    }
}
