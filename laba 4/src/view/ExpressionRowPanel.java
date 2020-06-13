package view;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;


public class ExpressionRowPanel {
    private TextField expressionRowTextField;

    private ScrollPane expRowScrollPane;


    public ExpressionRowPanel() {
        expressionRowTextField = new TextField();
        configureExpressionRowTextField();
        expRowScrollPane = new ScrollPane();
        configureExpRowScrollPane();
    }

    public ScrollPane getExpRowScrollPane() {
        return expRowScrollPane;
    }

    public TextField getExpressionRowTextField() {
        return expressionRowTextField;
    }

    /*
     *      Configs
     */

    private void configureExpressionRowTextField() {
        expressionRowTextField.setEditable(false);
        expressionRowTextField.setFocusTraversable(false);
        expressionRowTextField.setFont(new Font("Segoe UI", 18));
        expressionRowTextField.setPrefHeight(50);
        expressionRowTextField.setPrefWidth(1000);
        expressionRowTextField.setStyle("-fx-text-fill: #ffffff;" +"-fx-background-color: #131313");

        expressionRowTextField.textProperty().addListener(e -> {
            expressionRowTextField.positionCaret(expressionRowTextField.getText().length());
        });
    }

    private void configureExpRowScrollPane() {
        expRowScrollPane.setContent(expressionRowTextField);
        expRowScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        expRowScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        expRowScrollPane.setPrefWidth(100);


        expRowScrollPane.getStylesheets().add("ScrollPaneStyle.css");
    //    expRowScrollPane.lookup(".scroll-bar").lookup(".track").setStyle("-fx-background-color: #131313");


    }



}

