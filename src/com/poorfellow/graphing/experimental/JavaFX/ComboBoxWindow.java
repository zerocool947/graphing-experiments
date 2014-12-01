package com.poorfellow.graphing.experimental.JavaFX;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Created by David on 11/30/2014.
 */
public class ComboBoxWindow extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ObservableList<String> comboBoxOptions = FXCollections.observableArrayList("Hello", "Goodbye");
        ComboBox<String> comboBox = new ComboBox<String>(comboBoxOptions);
        comboBox.setId("demoComboBox");
        comboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (comboBox.getSelectionModel().getSelectedItem().equals("Hello")) {
                    Popup popup = new Popup();
                    Text popupText = new Text();
                    popupText.setText("World");
                    popup.getContent().add(popupText);
                    popup.show(primaryStage);
                }
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(comboBox);
        Scene scene = new Scene(root, 500, 500);

        primaryStage.setTitle("Dummy Test");
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println("Primary stage is showing? " + primaryStage.isShowing());


    }
}
