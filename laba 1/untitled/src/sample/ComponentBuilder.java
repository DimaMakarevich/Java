package sample;


import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ComponentBuilder {

//	public VBox getPane() {
//		VBox defaultGridPane = new VBox();
//		//defaultGridPane.getChildren().removeAll();
//		//defaultGridPane.getChildren().clear();
//		defaultGridPane.setMinSize(200.0, 150.0);
//		return defaultGridPane;
//	}
	
	public Button getButton(String nameOfButton) {
		Button defaultButton = new Button(nameOfButton);
		defaultButton.setMinSize(100.0, 45.0);
		defaultButton.setMaxSize(100.0, 45.0);
		defaultButton.setContentDisplay(ContentDisplay.CENTER);
		defaultButton.setTextFill(Color.GREEN);
		defaultButton.setFont(Font.font(19));
		return defaultButton;
	}

	public TextField getTextField() {
		TextField defaultTextField = new TextField();
		defaultTextField.setMinSize(100.0, 30.0);
		return defaultTextField;
	}
	
	public ComboBox<String> getComboBox() {
		ComboBox<String> defaultComboBox = new ComboBox<>();
		defaultComboBox.setMinSize(150.0, 30.0);
		return defaultComboBox;
	}
}