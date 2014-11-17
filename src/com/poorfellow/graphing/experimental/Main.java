package com.poorfellow.graphing.experimental;


import com.poorfellow.graphing.experimental.JUNG.JUNG;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {

       //follow this pattern for future experiments
       // GraphStream.execute();

        //JUNG Test
        //JUNG.execute();

        //JUNG JAVAFX Test
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        JUNG.executeFX(primaryStage);
    }
}
