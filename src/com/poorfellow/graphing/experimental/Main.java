package com.poorfellow.graphing.experimental;


import com.poorfellow.graphing.experimental.JUNG.JUNG;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {

       //follow this pattern for future experiments
       // GraphStream.execute();

        //JUNG Test
        //JUNG.execute();

        //JUNG JAVAFX Test
        launch(args);

    }

    public Main() {

    }

    public void show() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        dynamicComboBoxCreation(primaryStage);
        //fxmlComboBoxCreation(primaryStage);

    }

    private void dynamicComboBoxCreation(Stage primaryStage) {

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

    private void fxmlComboBoxCreation(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("testLayout.fxml"));

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Lets see if I can change this now");
        primaryStage.setScene(scene);
        primaryStage.show();

    }



}
