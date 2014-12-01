package com.poorfellow.graphing.experimental.JavaFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by David on 11/30/2014.
 */
public class ComboBoxWindow extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ComboBox<String> comboBox = ViewTestUtility.getInstance().createDemoComboBoxWithPopup(primaryStage);

        StackPane root = new StackPane();
        root.getChildren().add(comboBox);
        Scene scene = new Scene(root, 500, 500);

        primaryStage.setTitle("Dummy Test");
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println("Primary stage is showing? " + primaryStage.isShowing());


    }
}
