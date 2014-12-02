package com.poorfellow.graphing.experimental.test;

import com.poorfellow.graphing.experimental.JavaFX.ViewTestUtility;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.junit.After;
import org.junit.Test;
import org.loadui.testfx.GuiTest;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.loadui.testfx.Assertions.assertNodeExists;
import static org.loadui.testfx.Assertions.verifyThat;

/**
 * Created by David on 11/30/2014.
 */
public class JavaFXViewsTest extends GuiTest {

    ViewTestUtility viewTestUtility;

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
        AnchorPane root = new AnchorPane();
        viewTestUtility = new ViewTestUtility(root);
        viewTestUtility.createDemoButtonWithTextAndId("Hello World", "demoButton");
        viewTestUtility.setDemoButtonChangeOnClickText("Been Clicked");
        viewTestUtility.createDemoComboBoxWithPopup();

        viewTestUtility.createAndAssembleDemoGridPane();
        viewTestUtility.addDemoGridPaneToRootPane();

        return root;
    }
}
