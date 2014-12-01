package com.poorfellow.graphing.experimental.JavaFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Window;

/**
 * Created by David on 11/30/2014.
 */
public class ViewTestUtility {

    private static ViewTestUtility viewTestUtility;
    private Popup helloPopup;

    public static ViewTestUtility getInstance() {
        if (viewTestUtility == null) {
            viewTestUtility = new ViewTestUtility();
        }

        return viewTestUtility;
    }

    public ViewTestUtility() {
    }

    public Button createButtonWithTextAndId(String text, String id) {
        Button result = new Button(text);
        result.setId(id);

        return result;
    }

    public void setButtonOnClickTextChange(Button button, String text) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                button.setText(text);
            }
        });
    }

    public ComboBox<String> createDemoComboBoxWithPopup(Node node) {
        ObservableList<String> comboBoxOptions = FXCollections.observableArrayList("Hello", "Goodbye");
        ComboBox<String> comboBox = new ComboBox<String>(comboBoxOptions);
        comboBox.setId("demoComboBox");
        createHandlerForDemoComboBox(comboBox, node);

        return comboBox;
    }

    public ComboBox<String> createDemoComboBoxWithPopup(Window window) {
        ObservableList<String> comboBoxOptions = FXCollections.observableArrayList("Hello", "Goodbye");
        ComboBox<String> comboBox = new ComboBox<String>(comboBoxOptions);
        comboBox.setId("demoComboBox");
        createHandlerForDemoComboBox(comboBox, window);

        return comboBox;
    }

    public void createHandlerForDemoComboBox(ComboBox<String> comboBox, Node node) {
        comboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (comboBox.getSelectionModel().getSelectedItem().equals("Hello")) {
                    helloPopup = createPopupWithText("World");
                    helloPopup.show(node, 250, 250);
                }
            }
        });
    }

    public void createHandlerForDemoComboBox(ComboBox<String> comboBox, Window window) {
        comboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (comboBox.getSelectionModel().getSelectedItem().equals("Hello")) {
                    helloPopup = createPopupWithText("World");
                    helloPopup.show(window, 250, 250);
                }
            }
        });
    }

    public Popup createPopupWithText(String text) {
        Popup popup = new Popup();
        Text popupText = new Text();
        popupText.setText("World");
        popup.getContent().add(popupText);

        return popup;
    }

    public boolean isHelloPopupShowing() {
        return helloPopup.isShowing();
    }

}
