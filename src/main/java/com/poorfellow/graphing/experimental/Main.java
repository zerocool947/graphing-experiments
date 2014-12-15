package com.poorfellow.graphing.experimental;


import com.poorfellow.graphing.experimental.JavaFX.workflow.BasicWorkflowViewManager;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.*;

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
        BasicWorkflowViewManager workflowView = new BasicWorkflowViewManager();
        Scene scene = new Scene(workflowView.setupRoot(), 800, 800);
        workflowView.addNodeWithTitle("FirstNode");
        workflowView.addNodeWithTitle("SecondNode");

        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();

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

    }

    private void fxmlComboBoxCreation(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("testLayout.fxml"));

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Lets see if I can change this now");
        primaryStage.setScene(scene);
        primaryStage.show();

    }



}
