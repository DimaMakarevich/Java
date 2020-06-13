package view;

import controller.ExpressionTreeController;
import controller.RPNExpressionConverter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import model.OperatorFactory;


public class OperButtonPanel {
    public static final String CLEAR = "C";
    public static final String BACKSPACE = "←";
    public static final String EQUAL = "=";
    public static final String REVERSE = "1/x";
    public static final String OPEN = "(";
    public static final String CLOSE = ")";

    public static final String ONE = "1";
    public static final String TWO = "2";
    public static final String THREE = "3";
    public static final String FOUR = "4";
    public static final String FIVE = "5";
    public static final String SIX = "6";
    public static final String SEVEN = "7";
    public static final String EIGHT = "8";
    public static final String NINE = "9";
    public static final String ZERO = "0";

    private static final String BUTTON_STYLE =
            "-fx-pref-width: 42; " +
                    "-fx-pref-height: 42; " +
                    "-fx-font-size: 15;" +
                    "-fx-background-radius: 50;" +
                    "-fx-text-fill:  #ffffff;" +
                    "-fx-background-color: #ff9e13 ";


    private static final String BUTTON_NUMBERS_STYLE =
            "-fx-pref-width: 42; " +
                    "-fx-pref-height: 42; " +
                    "-fx-font-size: 15;" +
                    "-fx-background-radius: 50;" +
                    "-fx-text-fill:  #ffffff;" +
                    "-fx-background-color: #424142 ";


    private TextField expRowTextField;
    private ExpressionTreePanel expressionTreePanel;

    private GridPane gridPane;

    private ExpressionTreeController expressionTreeController;


    public OperButtonPanel(ExpressionRowPanel expressionRowPanel, ExpressionTreePanel expressionTreePanel,
                           ExpressionTreeController expressionTreeController) {
        expRowTextField = expressionRowPanel.getExpressionRowTextField();
        this.expressionTreePanel = expressionTreePanel;
        gridPane = new GridPane();
        configureGridPane();

        this.expressionTreeController = expressionTreeController;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    /*
     *      Configs
     */

    private void configureGridPane() {
        gridPane.setStyle("-fx-background-color: #131313");
        Button reverse = new Button(REVERSE);
        Button sqrt = new Button(OperatorFactory.SQRT);
        Button plus = new Button(OperatorFactory.PLUS);
        Button minus = new Button(OperatorFactory.MINUS);
        Button divide = new Button(OperatorFactory.DIVIDE);
        Button multiplication = new Button(OperatorFactory.MULTIPLICATION);
        Button mod = new Button(OperatorFactory.MOD);
        Button pow = new Button(OperatorFactory.POWER);

        pow.setOnAction(powerEventHandler);
        reverse.setOnAction(reverseEventHandler);
        sqrt.setOnAction(sqrtEventHandler);
        plus.setOnAction(plusEventHandler);
        minus.setOnAction(minusEventHandler);
        divide.setOnAction(divideEventHandler);
        multiplication.setOnAction(multiplicationEventHandler);
        mod.setOnAction(modEventHandler);


        Button openBracket = new Button(OPEN);
        Button closeBracket = new Button(CLOSE);
        Button clear = new Button(CLEAR);
        Button backspace = new Button(BACKSPACE);
        Button dot = new Button(RPNExpressionConverter.DOT);
        Button equal = new Button(EQUAL);

        openBracket.setOnAction(openBracketEventHandler);
        closeBracket.setOnAction(closeBracketEventHandler);
        clear.setOnAction(clearEventHandler);
        backspace.setOnAction(backspaceEventHandler);
        dot.setOnAction(dotEventHandler);
        equal.setOnAction(equalEventHandler);

        Button one = new Button(ONE);
        Button two = new Button(TWO);
        Button three = new Button(THREE);
        Button four = new Button(FOUR);
        Button five = new Button(FIVE);
        Button six = new Button(SIX);
        Button seven = new Button(SEVEN);
        Button eight = new Button(EIGHT);
        Button nine = new Button(NINE);
        Button zero = new Button(ZERO);

        one.setOnAction(oneEventHandler);
        two.setOnAction(twoBracketEventHandler);
        three.setOnAction(threeEventHandler);
        four.setOnAction(fourEventHandler);
        five.setOnAction(fiveEventHandler);
        six.setOnAction(sixEventHandler);
        seven.setOnAction(sevenEventHandler);
        eight.setOnAction(eightEventHandler);
        nine.setOnAction(nineEventHandler);
        zero.setOnAction(zeroEventHandler);

        pow.setStyle(BUTTON_STYLE);
        reverse.setStyle(BUTTON_STYLE);
        sqrt.setStyle(BUTTON_STYLE);
        plus.setStyle(BUTTON_STYLE);
        minus.setStyle(BUTTON_STYLE);
        divide.setStyle(BUTTON_STYLE);
        multiplication.setStyle(BUTTON_STYLE);
        mod.setStyle(BUTTON_STYLE);

        openBracket.setStyle(BUTTON_STYLE);
        closeBracket.setStyle(BUTTON_STYLE);
        clear.setStyle(BUTTON_STYLE);
        backspace.setStyle(BUTTON_STYLE);
        dot.setStyle(BUTTON_STYLE);

        equal.setStyle(BUTTON_STYLE);

        equal.setStyle("-fx-pref-width: 210;" +
                "-fx-pref-height: 42;" +
                "-fx-text-fill:  #ffffff;" +
                "-fx-background-color: #ff9e13 ");

        one.setStyle(BUTTON_NUMBERS_STYLE);
        two.setStyle(BUTTON_NUMBERS_STYLE);
        three.setStyle(BUTTON_NUMBERS_STYLE);
        four.setStyle(BUTTON_NUMBERS_STYLE);
        five.setStyle(BUTTON_NUMBERS_STYLE);
        six.setStyle(BUTTON_NUMBERS_STYLE);
        seven.setStyle(BUTTON_NUMBERS_STYLE);
        eight.setStyle(BUTTON_NUMBERS_STYLE);
        nine.setStyle(BUTTON_NUMBERS_STYLE);
        zero.setStyle(BUTTON_NUMBERS_STYLE);

        CheckBox logOperationActivator = new CheckBox("log()");
        logOperationActivator.setStyle("-fx-text-fill: #fffbff");
        logOperationActivator.setSelected(true);
        logOperationActivator.setOnAction(e -> {
            pow.setDisable(!logOperationActivator.isSelected());
        });



        gridPane.add(logOperationActivator, 0, 0, 2, 1);
        gridPane.add(backspace, 0, 1);
        gridPane.add(clear, 1, 1);
        gridPane.add(openBracket, 2, 1);
        gridPane.add(closeBracket, 3, 1);
        gridPane.add(mod, 2, 5);
        gridPane.add(sqrt, 4, 1);
        gridPane.add(one, 0, 2);
        gridPane.add(two, 1, 2);
        gridPane.add(three, 2, 2);
        gridPane.add(plus, 3, 2);
        gridPane.add(reverse, 4, 2);
        gridPane.add(four, 0, 3);
        gridPane.add(five, 1, 3);
        gridPane.add(six, 2, 3);
        gridPane.add(minus, 3, 3);
        gridPane.add(pow,4,3);
      //  gridPane.add(factorial, 4, 3);
        gridPane.add(seven, 0, 4);
        gridPane.add(eight, 1, 4);
        gridPane.add(nine, 2, 4);
        gridPane.add(divide, 3, 4);
       // gridPane.add(lg, 4, 4);
        gridPane.add(dot, 0, 5);
        gridPane.add(zero, 1, 5);
        gridPane.add(multiplication, 3, 5);
     //   gridPane.add(ln, 4, 5);
        gridPane.add(equal, 0, 6, 5, 1);

        GridPane.setMargin(logOperationActivator, new Insets(15));
    }

    /*
     *      Event handlers
     */




    private EventHandler<ActionEvent> oneEventHandler = e -> {
        expRowTextField.setText(expRowTextField.getText() + ONE);
    };

    private EventHandler<ActionEvent> twoBracketEventHandler = e -> {
        expRowTextField.setText(expRowTextField.getText() + TWO);
    };

    private EventHandler<ActionEvent> threeEventHandler = e -> {
        expRowTextField.setText(expRowTextField.getText() + THREE);
    };

    private EventHandler<ActionEvent> fourEventHandler = e -> {
        expRowTextField.setText(expRowTextField.getText() + FOUR);
    };

    private EventHandler<ActionEvent> fiveEventHandler = e -> {
        expRowTextField.setText(expRowTextField.getText() + FIVE);
    };

    private EventHandler<ActionEvent> sixEventHandler = e -> {
        expRowTextField.setText(expRowTextField.getText() + SIX);
    };

    private EventHandler<ActionEvent> sevenEventHandler = e -> {
        expRowTextField.setText(expRowTextField.getText() + SEVEN);
    };

    private EventHandler<ActionEvent> eightEventHandler = e -> {
        expRowTextField.setText(expRowTextField.getText() + EIGHT);
    };

    private EventHandler<ActionEvent> nineEventHandler = e -> {
        expRowTextField.setText(expRowTextField.getText() + NINE);
    };

    private EventHandler<ActionEvent> zeroEventHandler = e -> {
        expRowTextField.setText(expRowTextField.getText() + ZERO);
    };

    private EventHandler<ActionEvent> openBracketEventHandler = e -> {
        expRowTextField.setText(expRowTextField.getText() + OPEN);
    };

    private EventHandler<ActionEvent> closeBracketEventHandler = e -> {
        expRowTextField.setText(expRowTextField.getText() + CLOSE);
    };

    private EventHandler<ActionEvent> clearEventHandler = e -> {
        expRowTextField.setText("");
    };

    private EventHandler<ActionEvent> backspaceEventHandler = e -> {
        if (!expRowTextField.getText().equals("")) {
            expRowTextField.setText(expRowTextField.getText().substring(0, expRowTextField.getText().length() - 1));
        }
    };

    private EventHandler<ActionEvent> dotEventHandler = e -> {
        expRowTextField.setText(expRowTextField.getText() + RPNExpressionConverter.DOT);
    };

    private EventHandler<ActionEvent> equalEventHandler = e -> {
        try {
            expressionTreeController.createTree(expRowTextField.getText());
        } catch (Exception ex) {
            expRowTextField.setText(ex.getMessage());
            return;
        }

        expressionTreePanel.construct();
    };

    private EventHandler<ActionEvent> reverseEventHandler = e -> {
        expRowTextField.setText(expRowTextField.getText() + REVERSE.substring(0, 2));
    };

    private EventHandler<ActionEvent> sqrtEventHandler = e -> {
        expRowTextField.setText(expRowTextField.getText() + OperatorFactory.SQRT);
    };

    private EventHandler<ActionEvent> plusEventHandler = e -> {
        expRowTextField.setText(expRowTextField.getText() + OperatorFactory.PLUS);
    };

    private EventHandler<ActionEvent> minusEventHandler = e -> {
        expRowTextField.setText(expRowTextField.getText() + OperatorFactory.MINUS);
    };

    private EventHandler<ActionEvent> divideEventHandler = e -> {
        expRowTextField.setText(expRowTextField.getText() + OperatorFactory.DIVIDE);
    };

    private EventHandler<ActionEvent> multiplicationEventHandler = e -> {
        expRowTextField.setText(expRowTextField.getText() + OperatorFactory.MULTIPLICATION);
    };

    private EventHandler<ActionEvent> modEventHandler = e -> {
        expRowTextField.setText(expRowTextField.getText() + OperatorFactory.MOD);
    };

    private EventHandler<ActionEvent> powerEventHandler = e -> {
        expRowTextField.setText(expRowTextField.getText() + OperatorFactory.POWER);
    };

}
