package com.poorfellow.graphing.experimental.test;

import com.poorfellow.graphing.experimental.JavaFX.ViewTestUtility;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Popup;
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

    ViewTestUtility viewTestUtility = ViewTestUtility.getInstance();

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

        assertTrue(viewTestUtility.isHelloPopupShowing());

    }

    @Override
    protected Parent getRootNode() {
        Button demoButton = viewTestUtility.createButtonWithTextAndId("Hello World", "demoButton");
        viewTestUtility.setButtonOnClickTextChange(demoButton, "Been Clicked");
        AnchorPane root = new AnchorPane();

        ComboBox<String> comboBox = viewTestUtility.createDemoComboBoxWithPopup(root);

        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(10);
        grid.add(demoButton, 1, 1);
        grid.add(comboBox, 1, 2);


        root.getChildren().addAll(grid);

        return root;
    }
}
