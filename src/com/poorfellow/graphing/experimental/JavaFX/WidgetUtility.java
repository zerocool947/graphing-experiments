package com.poorfellow.graphing.experimental.JavaFX;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Control;

/**
 * Created by David on 11/30/2014.
 */
public class WidgetUtility {

    private static WidgetUtility widgetUtility;

    public static WidgetUtility getInstance() {
        if (widgetUtility == null) {
            widgetUtility = new WidgetUtility();
        }

        return widgetUtility;
    }

    public WidgetUtility() {

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
}
