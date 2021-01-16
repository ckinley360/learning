
package ticTacToe;
 
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import java.util.ArrayList;
 
public class TicTacToeApplication extends Application {
 
    @Override
    public void start(Stage window) throws Exception {
        // Create the main layout.
        BorderPane mainLayout = new BorderPane();
        
        // Create the label that displays information about the game.
        Label label = new Label("Turn: X");
        
        // Set the font of the label.
        label.setFont(Font.font("Monospaced Bold", 40));
        
        // Create the grid pane layout that will contain the 9 buttons.
        GridPane gridPaneLayout = new GridPane();
        
        // Style the grid pane layout.
        gridPaneLayout.setVgap(10);
        gridPaneLayout.setHgap(10);
        gridPaneLayout.setPadding(new Insets(10, 10, 10, 10));
        
        // Create the 9 buttons.
        Button buttonOne = new Button();
        Button buttonTwo = new Button();
        Button buttonThree = new Button();
        Button buttonFour = new Button();
        Button buttonFive = new Button();
        Button buttonSix = new Button();
        Button buttonSeven = new Button();
        Button buttonEight = new Button();
        Button buttonNine = new Button();
        
        // Style the 9 buttons.
        buttonOne.setFont(Font.font("Monospaced", 40));
        buttonTwo.setFont(Font.font("Monospaced", 40));
        buttonThree.setFont(Font.font("Monospaced", 40));
        buttonFour.setFont(Font.font("Monospaced", 40));
        buttonFive.setFont(Font.font("Monospaced", 40));
        buttonSix.setFont(Font.font("Monospaced", 40));
        buttonSeven.setFont(Font.font("Monospaced", 40));
        buttonEight.setFont(Font.font("Monospaced", 40));
        buttonNine.setFont(Font.font("Monospaced", 40));
        
        int buttonHeight = 100;
        int buttonWidth = 100;
        buttonOne.setPrefSize(buttonHeight, buttonWidth);
        buttonTwo.setPrefSize(buttonHeight, buttonWidth);
        buttonThree.setPrefSize(buttonHeight, buttonWidth);
        buttonFour.setPrefSize(buttonHeight, buttonWidth);
        buttonFive.setPrefSize(buttonHeight, buttonWidth);
        buttonSix.setPrefSize(buttonHeight, buttonWidth);
        buttonSeven.setPrefSize(buttonHeight, buttonWidth);
        buttonEight.setPrefSize(buttonHeight, buttonWidth);
        buttonNine.setPrefSize(buttonHeight, buttonWidth);
        
        // Add the 9 buttons to the grid pane layout.
        gridPaneLayout.add(buttonOne, 0, 0);
        gridPaneLayout.add(buttonTwo, 0, 1);
        gridPaneLayout.add(buttonThree, 0, 2);
        gridPaneLayout.add(buttonFour, 1, 0);
        gridPaneLayout.add(buttonFive, 1, 1);
        gridPaneLayout.add(buttonSix, 1, 2);
        gridPaneLayout.add(buttonSeven, 2, 0);
        gridPaneLayout.add(buttonEight, 2, 1);
        gridPaneLayout.add(buttonNine, 2, 2);
        
        // Add the label and grid pane to the main layout.
        mainLayout.setTop(label);
        mainLayout.setCenter(gridPaneLayout);
        
        // Create the scene and set it to use the main layout.
        Scene scene = new Scene(mainLayout);
        
        // Add event handlers to the buttons.
        StringBuilder currentPlayer = new StringBuilder("X"); // X always gets the first turn.
        
        buttonOne.setOnAction((event) -> {
            if (!gameIsOver(buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine)) {
                processButtonClick(buttonOne, currentPlayer, label);
            }
            
            if (gameIsOver(buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine)) {
                label.setText("The end!");
            }
        });
        
        buttonTwo.setOnAction((event) -> {
            if (!gameIsOver(buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine)) {
                processButtonClick(buttonTwo, currentPlayer, label);
            }
            
            if (gameIsOver(buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine)) {
                label.setText("The end!");
            }
        });
        
        buttonThree.setOnAction((event) -> {
            if (!gameIsOver(buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine)) {
                processButtonClick(buttonThree, currentPlayer, label);
            }
            
            if (gameIsOver(buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine)) {
                label.setText("The end!");
            }
        });
        
        buttonFour.setOnAction((event) -> {
            if (!gameIsOver(buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine)) {
                processButtonClick(buttonFour, currentPlayer, label);
            }
            
            if (gameIsOver(buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine)) {
                label.setText("The end!");
            }
        });
        
        buttonFive.setOnAction((event) -> {
            if (!gameIsOver(buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine)) {
                processButtonClick(buttonFive, currentPlayer, label);
            }
            
            if (gameIsOver(buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine)) {
                label.setText("The end!");
            }
        });
        
        buttonSix.setOnAction((event) -> {
            if (!gameIsOver(buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine)) {
                processButtonClick(buttonSix, currentPlayer, label);
            }
            
            if (gameIsOver(buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine)) {
                label.setText("The end!");
            }
        });
        
        buttonSeven.setOnAction((event) -> {
            if (!gameIsOver(buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine)) {
                processButtonClick(buttonSeven, currentPlayer, label);
            }
            
            if (gameIsOver(buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine)) {
                label.setText("The end!");
            }
        });
        
        buttonEight.setOnAction((event) -> {
            if (!gameIsOver(buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine)) {
                processButtonClick(buttonEight, currentPlayer, label);
            }
            
            if (gameIsOver(buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine)) {
                label.setText("The end!");
            }
        });
        
        buttonNine.setOnAction((event) -> {
            if (!gameIsOver(buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine)) {
                processButtonClick(buttonNine, currentPlayer, label);
            }
            
            if (gameIsOver(buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine)) {
                label.setText("The end!");
            }
        });
        
        // Set and show the scene.
        window.setScene(scene);
        window.show();
    }
    
    public static void processButtonClick(Button button, StringBuilder currentPlayer, Label label) {
        // If the square hasn't been claimed by a player yet, then set that square to the current player, switch the current player to the other player, and update the label..
        if (button.getText().equals("")) {
            button.setText(currentPlayer.toString());
            
            if (currentPlayer.toString().equals("X")) {
                currentPlayer.delete(0, currentPlayer.length());
                currentPlayer.append("O");
                label.setText("Turn: O");
            } else {
                currentPlayer.delete(0, currentPlayer.length());
                currentPlayer.append("X");
                label.setText("Turn: X");
            }
        }
    }
    
    public static boolean gameIsOver(Button buttonOne, Button buttonTwo, Button buttonThree, Button buttonFour, Button buttonFive, Button buttonSix, Button buttonSeven, Button buttonEight, Button buttonNine) {
        // Add the text of all buttons to a two-dimensional array.
        String[][] buttons = new String[3][3];
        
        buttons[0][0] = buttonOne.getText();
        buttons[0][1] = buttonTwo.getText();
        buttons[0][2] = buttonThree.getText();
        buttons[1][0] = buttonFour.getText();
        buttons[1][1] = buttonFive.getText();
        buttons[1][2] = buttonSix.getText();
        buttons[2][0] = buttonSeven.getText();
        buttons[2][1] = buttonEight.getText();
        buttons[2][2] = buttonNine.getText();
 
        
        // If the board is entirely filled, then the game is over. If we find any blank value in the board, then we know the board is not filled.
        boolean boardIsFilled = true;
        
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                if (buttons[i][j].equals("")) {
                    boardIsFilled = false;
                    break;
                }
            }
        }
        
        if (boardIsFilled) {
            return true;
        }
        
        // If there are either three X's or three O's in a row, column, or diagonal, then the game is over.
        // Check for three X's or three O's in a row.
        if (allEqual(buttons[0][0], buttons[0][1], buttons[0][2]) || allEqual(buttons[1][0], buttons[1][1], buttons[1][2]) || allEqual(buttons[2][0], buttons[2][1], buttons[2][2])) {
            return true;
        }
        
        // Check for three X's or three O's in a column.
        if (allEqual(buttons[0][0], buttons[1][0], buttons[2][0]) || allEqual(buttons[0][1], buttons[1][1], buttons[2][1]) || allEqual(buttons[0][2], buttons[1][2], buttons[2][2])) {
            return true;
        }
        
        // Check for three X's or three O's in a diagonal.
        if (allEqual(buttons[0][0], buttons[1][1], buttons[2][2]) || allEqual(buttons[0][2], buttons[1][1], buttons[2][0])) {
            return true;
        }
        
        return false;
    }
    
    public static boolean allEqual(String value1, String value2, String value3) {
        // If all three values are equal, and are not all blank, then we consider the values to be equal.
        return value1.equals(value2) && value2.equals(value3) && (!(value1.equals("") && value2.equals("") && value3.equals("")));
    }
    
    public static void main(String[] args) {
        launch(TicTacToeApplication.class);
    }
}
