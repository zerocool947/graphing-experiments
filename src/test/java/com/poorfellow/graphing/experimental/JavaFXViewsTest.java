package com.poorfellow.graphing.experimental;

import com.poorfellow.graphing.experimental.JavaFX.ViewTestUtility;
import com.sun.javafx.tk.Toolkit;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.junit.*;
import org.testfx.api.FxRobot;
import org.testfx.api.FxService;
import org.testfx.api.FxToolkit;
import org.testfx.service.finder.NodeFinder;
import org.testfx.util.WaitForAsyncUtils;

import static org.hamcrest.Matchers.*;

import static org.junit.Assert.*;
import static org.loadui.testfx.Assertions.*;

/**
 * Created by David on 11/30/2014.
 */
public class JavaFXViewsTest {

    private static ViewTestUtility viewTestUtility;
    private static FxRobot fxRobot;
    private static NodeFinder nodeFinder;
    private static Stage primaryStage;

    @BeforeClass
    public static void setupTest() throws Exception {
        System.out.println("Attempting to Set up the view test");
        fxRobot = new FxRobot();
        nodeFinder = FxService.serviceContext().getNodeFinder();

        primaryStage = FxToolkit.registerPrimaryStage();
        fxRobot.target(primaryStage);
        System.out.println("Primary primaryStage registered");
        System.out.println("My Stage Name is " + FxToolkit.toolkitContext().getTargetStage().getTitle());
        FxToolkit.setupStage((stage) -> {
            stage.show();
            stage.toBack();
            stage.toFront();
        });
        System.out.println("Primary primaryStage setup");
        FxToolkit.setupSceneRoot(() -> {
                    return getRootNode();
                }
        );
        System.out.println("Scene root setup");
        System.out.println("Test established, waiting for FX events");
        WaitForAsyncUtils.waitForFxEvents();
    }

    @AfterClass
    public static void teardownStage() throws Exception {
        Toolkit.getToolkit().defer(() -> {});
        Platform.runLater(primaryStage::close);
    }

    @Test
    public void testButtonClick() {
        Button btn = (Button) nodeFinder.node("#demoButton");
        fxRobot.clickOn(btn);
        String postClickText = btn.getText();
        verifyThat("Been Clicked", is(postClickText));
        assertNodeExists("#demoButton");
    }

    @Test
    public void testComboBoxSelect() {
        ComboBox comboBox = (ComboBox) nodeFinder.node("#demoComboBox");
        fxRobot.clickOn(comboBox).clickOn("Hello");

        assertTrue(viewTestUtility.isHelloPopupShowing());
    }

    public static Parent getRootNode() {
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
