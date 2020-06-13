package graphicalInterface;
import javafx.application.Application;
import javafx.stage.Stage;

import  graphicalInterface.controller.Controller;
import graphicalInterface.model.Model;
import graphicalInterface.appForm.View;

public class MainWindow extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }



    @Override
    public void start(Stage mainStage) {

        Model 	   model 	  = new Model( 5);
     Controller controller = new Controller (model);
        View view 	  = new View(controller);

        mainStage = view.getStage();
        mainStage.show();
    }
}
