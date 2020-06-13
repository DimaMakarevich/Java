package sample;

import controller.ExpressionTreeController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import model.ExpressionTree;
import view.CalculatorForm;

import java.awt.*;

public class Main extends Application {

       //

    @Override
    public void start(Stage primaryStage) throws Exception {

        ExpressionTree expressionTree = new ExpressionTree();
        ExpressionTreeController expressionTreeController = new ExpressionTreeController(expressionTree);
        Scene scene = new Scene(new CalculatorForm(expressionTreeController).getGridPane());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Calculator");
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
