package view;

import controller.ExpressionTreeController;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;


public class CalculatorForm {
    private ExpressionTreePanel expressionTreePanel;
    private ExpressionRowPanel expressionRowPanel;
    private OperButtonPanel operButtonPanel;

    private GridPane gridPane;


    public CalculatorForm(ExpressionTreeController expressionTreeController) {
        expressionRowPanel = new ExpressionRowPanel();
        expressionTreePanel = new ExpressionTreePanel(expressionRowPanel, expressionTreeController);
        operButtonPanel = new OperButtonPanel(expressionRowPanel, expressionTreePanel, expressionTreeController);

        gridPane = new GridPane();
        configureGridPane();
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    /*
     *      Configs
     */

    private void configureGridPane() {
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(expressionTreePanel.getGridPane(), 0, 0, 1, 2);
        gridPane.add(expressionRowPanel.getExpRowScrollPane(), 1, 0);
        gridPane.add(operButtonPanel.getGridPane(), 1, 1);
    }
}
