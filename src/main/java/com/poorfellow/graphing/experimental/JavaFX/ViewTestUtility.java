package main.java.com.poorfellow.graphing.experimental.JavaFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Popup;

/**
 * Created by David on 11/30/2014.
 */
public class ViewTestUtility {

    private static ViewTestUtility viewTestUtility;
    private Popup helloPopup;
    private Button demoButton;
    private ComboBox<String> demoComboBox;
    private Pane parentPane;
    private GridPane demoGridPane;

    public ViewTestUtility(Pane pane) {
        parentPane = pane;
    }

    public Button createDemoButtonWithTextAndId(String text, String id) {
        demoButton = new Button(text);
        demoButton.setId(id);

        return demoButton;
    }

    public void setDemoButtonChangeOnClickText(String text) {
        demoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                demoButton.setText(text);
            }
        });
    }

    public ComboBox<String> createDemoComboBoxWithPopup() {
        ObservableList<String> comboBoxOptions = FXCollections.observableArrayList("Hello", "Goodbye");
        demoComboBox = new ComboBox<String>(comboBoxOptions);
        demoComboBox.setId("demoComboBox");
        createHandlerForDemoComboBox();

        return demoComboBox;
    }

    public void createHandlerForDemoComboBox() {
        demoComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (demoComboBox.getSelectionModel().getSelectedItem().equals("Hello")) {
                    createDemoPopupWithText("World");
                    showDemoPopup();
                }
            }
        });
    }

    private void showDemoPopup() {
        helloPopup.show(parentPane, 250, 250);
    }

    public Popup createDemoPopupWithText(String text) {
        helloPopup = new Popup();
        Text popupText = new Text();
        popupText.setText("World");
        helloPopup.getContent().add(popupText);

        return helloPopup;
    }

    public boolean isHelloPopupShowing() {
        return helloPopup.isShowing();
    }

    public GridPane createAndAssembleDemoGridPane() {
        demoGridPane = new GridPane();
        demoGridPane.setVgap(4);
        demoGridPane.setHgap(10);
        assembleGridPane();

        return demoGridPane;
    }

    public void assembleGridPane() {
        demoGridPane.add(demoButton, 1, 1);
        demoGridPane.add(demoComboBox, 1, 2);
    }

    public void addDemoGridPaneToRootPane() {
        parentPane.getChildren().addAll(demoGridPane);
    }

    public Pane getRoot() {
        return parentPane;
    }

}
