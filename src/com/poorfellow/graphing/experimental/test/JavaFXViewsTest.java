package com.poorfellow.graphing.experimental.test;

import com.poorfellow.graphing.experimental.JavaFX.WidgetUtility;
import com.poorfellow.graphing.experimental.Main;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertTrue;
import static org.loadui.testfx.Assertions.assertNodeExists;
import static org.loadui.testfx.Assertions.verifyThat;

/**
 * Created by David on 11/30/2014.
 */
public class JavaFXViewsTest extends GuiTest {

    Popup helloPopup;
    WidgetUtility widgetUtility = WidgetUtility.getInstance();

    @Test
    public void testButtonClick() {
        Button btn = find("#demoButton");
        click(btn);
        String postClickText = btn.getText();
        verifyThat("Been Clicked", is(postClickText));
        //assertNodeExists("demoButton");
    }

    @Test
    public void testComboBoxSelect() {
        ComboBox comboBox = find("#demoComboBox");
        click(comboBox).click("Hello");

        assertTrue(helloPopup.isShowing());

    }

    @Override
    protected Parent getRootNode() {
        helloPopup = new Popup();
        Button demoButton = widgetUtility.createButtonWithTextAndId("Hello World", "demoButton");
        widgetUtility.setButtonOnClickTextChange(demoButton, "Been Clicked");
        AnchorPane root = new AnchorPane();

        ObservableList<String> comboBoxOptions = FXCollections.observableArrayList("Hello", "Goodbye");
        ComboBox<String> comboBox = new ComboBox<String>(comboBoxOptions);
        comboBox.setId("demoComboBox");
        comboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (comboBox.getSelectionModel().getSelectedItem().equals("Hello")) {
                    Text popupText = new Text();
                    popupText.setText("World");
                    helloPopup.getContent().add(popupText);
                    helloPopup.show(root, 250, 250);
                }
            }
        });

        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(10);
        grid.add(demoButton, 1, 1);
        grid.add(comboBox, 1, 2);


        root.getChildren().addAll(grid);

        return root;
    }
}
