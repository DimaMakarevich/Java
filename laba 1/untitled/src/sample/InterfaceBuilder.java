package sample;


import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class InterfaceBuilder {
	
	private Stage stage;
	
	private Scene scene;
	
	private Pane mainPane;
	
	private ComponentBuilder builder;

	private AnimationTimer animationTimer;

	public InterfaceBuilder(Stage stage) {
		this.stage = stage;
		mainPane = new HBox();
		scene = new Scene(mainPane);
		builder = new ComponentBuilder();
	}
	
	public void build() {
		buildFirstPane();
		configureStage();
	}

	private void buildFirstPane() {
		TextField field = builder.getTextField();
		Button add = builder.getButton("Add");
		ComboBox<String> items = builder.getComboBox();
		addToMainPane(field, add, items);
		configurePane(field, add, items);
		setAction(field, add, items);
	}

//	public void setAction( Node ...nodes) {
//		scene.setOnKeyPressed(key -> {
//			if(key.getCode()==KeyCode.R) {
//				animationTimer = new AnimationTimer() {
//					@Override
//					public void handle(long l) {
//						swapNodes(nodes);
//						try {
//							Thread.sleep(500);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//					}
//				}; animationTimer.start();
//			}
//			if(key.getCode()==KeyCode.S) {
//				animationTimer.stop();
//			}
//		});
//	}

	public void setAction( Node ...nodes) {
		scene.setOnKeyPressed(key -> {
			if(key.getCode()==KeyCode.R) {
				animationTimer = new AnimationTimer() {
					@Override
					public void handle(long l) {
						swapNodes(nodes);
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}; animationTimer.start();
			}
			if(key.getCode()==KeyCode.S) {
				animationTimer.stop();
			}
		});
	}





	private void swapNodes(Node ...nodes) {
		Node tempNodeNext;
		Node tempNodePrevious = nodes[0];
		mainPane.getChildren().removeAll(nodes);
		for (int i = 0; i < nodes.length; i++) {
			if (i != nodes.length - 1) {
				tempNodeNext = nodes[i + 1];
				nodes[i + 1] = tempNodePrevious;
				tempNodePrevious = tempNodeNext;
			} else {
				nodes[0] = tempNodePrevious;
			}
		}
		addToMainPane(nodes);
	}

	public void configurePane(TextField field, Button add, ComboBox<String> items) {
		add.setOnAction((e) -> {
			doAction(items, field.getText());
			field.clear();
		});
	}

	private void doAction(ComboBox<String> items, String value) {
		if(!items.getItems().contains(value)) {
			items.getItems().add(value);
		} else {
			showMessage("Value " + value + " has already been added");
		}
	}

	private void addToMainPane(Node ...nodes) {
//		Pane localPane = builder.getPane();
//		localPane.getChildren().addAll(nodes);
//		mainPane.getChildren().addAll(localPane);
		mainPane.getChildren().addAll(nodes);
	}

	private void configureStage() {
		stage.setScene(scene);
		stage.setTitle("LabWork 1");
	}
	
	public void showMessage(String message) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setContentText(message);
		alert.showAndWait();
	}
}