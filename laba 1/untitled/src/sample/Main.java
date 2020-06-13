package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
        Application.launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		new InterfaceBuilder(primaryStage).build();
		//StringBuilder s = new StringBuilder("sdfs").append(2).append("ewr").append(5).toString()
		primaryStage.show();
	}
}