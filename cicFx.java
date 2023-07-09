package application;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.geometry.*;
import java.lang.Math.*;
import javafx.stage.*;

public class cicFx extends Application {
    Button btn1;
    Stage window;
    Scene scene1, scene2, scene3;
    double initialInvest;
    double monthlyInvest;
    int timeLength;
    double interestRate;
    int compoundFrequency;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("CompoundInterestCalculatorFX");

//Close Program Button
        Button btnClose = new Button("Close Program");
        btnClose.setOnAction(e -> closeProgram());

        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

//Start Button
        btn1 = new Button();
        btn1.setText("Click to Begin");
        btn1.setOnAction(e -> window.setScene(scene2));

//Layout 1 - children are laid out in vertical column
        Label label1 = new Label("Welcome to Compound Interest Calculator (FX)");
        // grid layout
        GridPane grid1 = new GridPane();
        // border padding
        grid1.setPadding(new Insets(10, 10, 10, 10));
        // vertical cell space gap
        grid1.setVgap(8);
        // horizontal cell space gap
        grid1.setHgap(10);

        GridPane.setConstraints(label1, 0, 3);
        GridPane.setConstraints(btn1, 0, 8);
        grid1.getChildren().addAll(label1, btn1);

        scene1 = new Scene(grid1, 600, 400);

//Back Buttons
        Button backBtn1 = new Button("Back");
        backBtn1.setOnAction(e -> window.setScene(scene1));
        Button backBtn2 = new Button("Back");
        backBtn2.setOnAction(e -> window.setScene(scene2));

//Layout 2
        GridPane grid2 = new GridPane();
        grid2.setPadding(new Insets(10, 10, 10, 10));
        grid2.setVgap(8);
        grid2.setHgap(10);
        GridPane.setConstraints(backBtn1, 0, 0);

//Initial Investment Input
        Label labelInitialInvest = new Label("Initial Investment:");
        TextField inputInitialInvest = new TextField();
        inputInitialInvest.setPromptText("initial investment");
        Label labelExplainInitialInvest = new Label("(Amount in $ being initially invested)");
        GridPane.setConstraints(labelExplainInitialInvest, 2, 1);
        GridPane.setConstraints(inputInitialInvest, 1, 1);
        GridPane.setConstraints(labelInitialInvest, 0, 1);
        grid2.getChildren().addAll(backBtn1, inputInitialInvest, labelInitialInvest, labelExplainInitialInvest);

//Monthly Investment Input
        Label labelMonthlyInvest = new Label("Monthly Investment:");
        TextField inputMonthlyInvest = new TextField();
        inputMonthlyInvest.setPromptText("monthly investment");
        Label labelExplainMonthlyInvest = new Label("(Amount in $ being invested monthly)");
        GridPane.setConstraints(labelExplainMonthlyInvest, 2, 2);
        GridPane.setConstraints(inputMonthlyInvest, 1, 2);
        GridPane.setConstraints(labelMonthlyInvest, 0, 2);
        grid2.getChildren().addAll(inputMonthlyInvest, labelMonthlyInvest, labelExplainMonthlyInvest);

//Length of Time Input
        Label labelLengthTime = new Label("Length of Time:");
        TextField inputLengthTime = new TextField();
        inputLengthTime.setPromptText("length of time");
        Label labelExplainLengthTime = new Label("(Amount of years that money is saved for)");
        GridPane.setConstraints(labelExplainLengthTime, 2, 3);
        GridPane.setConstraints(inputLengthTime, 1, 3);
        GridPane.setConstraints(labelLengthTime, 0, 3);
        grid2.getChildren().addAll(inputLengthTime, labelLengthTime, labelExplainLengthTime);

//Interest Rate Input
        Label labelInterestRate = new Label("Interest Rate:");
        TextField inputInterestRate = new TextField();
        inputInterestRate.setPromptText("interest rate : x.xx%");
        Label labelExplainInterestRate = new Label("(Annual Interest Rate -- exclude % symbol)");
        GridPane.setConstraints(labelExplainInterestRate, 2, 4);
        GridPane.setConstraints(inputInterestRate, 1, 4);
        GridPane.setConstraints(labelInterestRate, 0, 4);
        grid2.getChildren().addAll(inputInterestRate, labelInterestRate, labelExplainInterestRate);

//Compound Frequency Input
        Label labelCompoundFrequency = new Label("Compound Frequency:");
        TextField inputCompoundFrequency = new TextField();
        inputCompoundFrequency.setPromptText("compound frequency");
        Label labelExplainCompoundFrequency = new Label("(Times per year that interest is compounded)");
        GridPane.setConstraints(labelExplainCompoundFrequency, 2, 5);
        GridPane.setConstraints(inputCompoundFrequency, 1, 5);
        GridPane.setConstraints(labelCompoundFrequency, 0, 5);
        grid2.getChildren().addAll(inputCompoundFrequency, labelCompoundFrequency, labelExplainCompoundFrequency);

//Calculate Button
        Button calcBtn = new Button("Calculate");
        calcBtn.setOnAction(e -> {
            if (!((isInt(inputInitialInvest, inputInitialInvest.getText())
                    || ((isDouble(inputInitialInvest, inputInitialInvest.getText())))))) {
                popupError.display(
                        "The input for initial investment is invalid or missing. The input must be in numeric form.");
            } else if (!((isInt(inputMonthlyInvest, inputMonthlyInvest.getText())
                    || ((isDouble(inputMonthlyInvest, inputMonthlyInvest.getText())))))) {
                popupError.display(
                        "The input for monthly investment is invalid or missing. The input must be in numeric form.");
            } else if (!(isInt(inputLengthTime, inputLengthTime.getText()))) {
                popupError.display(
                        "The input for length of time is invalid or missing. The input must be in numeric form.");
            } else if (!((isInt(inputInterestRate, inputInterestRate.getText())
                    || ((isDouble(inputInterestRate, inputInterestRate.getText())))))) {
                popupError.display(
                        "The input for interest rate is invalid or missing. The input must be in numeric form- no % symbol.");
            } else if (!(isInt(inputCompoundFrequency, inputCompoundFrequency.getText()))) {
                popupError.display(
                        "The input for compound frequency is invalid or missing. The input must be in numeric form-- no decimals.");
            } else {
                initialInvest = Double.parseDouble(inputInitialInvest.getText());
                monthlyInvest = Double.parseDouble(inputMonthlyInvest.getText());
                timeLength = Integer.parseInt(inputLengthTime.getText());
                interestRate = Double.parseDouble(inputInterestRate.getText());
                compoundFrequency = Integer.parseInt(inputCompoundFrequency.getText());
//Layout 3 - Confirmation & Results Screen
                GridPane grid3 = new GridPane();
                grid3.setPadding(new Insets(10, 10, 10, 10));
                grid3.setVgap(8);
                grid3.setHgap(10);

                // Value Confirmation Labels
                GridPane.setConstraints(backBtn2, 0, 0);
                grid3.getChildren().addAll(backBtn2);
                Label confirmInitialInvest = new Label("Initial Investment: $" + initialInvest);
                GridPane.setConstraints(confirmInitialInvest, 0, 1);
                grid3.getChildren().addAll(confirmInitialInvest);
                Label confirmMonthlyInvest = new Label("Monthly Investment: $" + monthlyInvest);
                GridPane.setConstraints(confirmMonthlyInvest, 0, 2);
                grid3.getChildren().addAll(confirmMonthlyInvest);
                Label confirmTimeLength = new Label("Length of Time: " + timeLength + " years");
                GridPane.setConstraints(confirmTimeLength, 0, 3);
                grid3.getChildren().addAll(confirmTimeLength);
                Label confirmInterestRate = new Label("Interest Rate: " + interestRate + "%");
                GridPane.setConstraints(confirmInterestRate, 0, 4);
                grid3.getChildren().addAll(confirmInterestRate);
                Label confirmCompoundFrequency = new Label(
                        "Compound Frequency: " + compoundFrequency + " times a year");
                GridPane.setConstraints(confirmCompoundFrequency, 0, 5);
                grid3.getChildren().addAll(confirmCompoundFrequency);

                // Result Label
                double result = calculate(initialInvest, monthlyInvest, timeLength * 12, interestRate / 100,
                        compoundFrequency, true);
                Label resultLabel = new Label("Result: $" + result);
                GridPane.setConstraints(resultLabel, 0, 7);
                grid3.getChildren().addAll(resultLabel);
                scene3 = new Scene(grid3, 600, 400);
                window.setScene(scene3);
            }
        });
        scene2 = new Scene(grid2, 600, 400);
        GridPane.setConstraints(calcBtn, 1, 6);
        grid2.getChildren().addAll(calcBtn);

        window.setScene(scene1);
        window.show();
    }

    private void closeProgram() {
        boolean result = closeConfirmation.display();
        if (result == true) {
            window.close();
        }
    }

    private boolean isInt(TextField input, String message) {
        try {
            int a = Integer.parseInt(input.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isDouble(TextField input, String message) {
        try {
            double b = Double.parseDouble(input.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private double calculate(double initialInvest, double monthlyInvest, int months, double interestRate,
            int compoundFrequency, boolean start) {
        if (months == 0) {
            initialInvest *= (1 + interestRate);
            return initialInvest;
        } else {
            if ((months % (12 / compoundFrequency) == 0) && (!(start))) {
                initialInvest *= (1 + interestRate);
            }
            initialInvest += monthlyInvest;
            months--;
            return calculate(initialInvest, monthlyInvest, months, interestRate, compoundFrequency, false);
        }
    }
}