package test.java.com.poorfellow.graphing.experimental.test;

import main.java.com.poorfellow.graphing.experimental.JavaFX.ViewTestUtility;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxRobot;
import org.testfx.api.FxService;
import org.testfx.api.FxToolkit;
import org.testfx.api.FxToolkitContext;
import org.testfx.service.finder.NodeFinder;
import org.testfx.util.WaitForAsyncUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.loadui.testfx.Assertions.assertNodeExists;
import static org.loadui.testfx.Assertions.verifyThat;

/**
 * Created by David on 11/30/2014.
 */
public class JavaFXViewsTest {

    private static ViewTestUtility viewTestUtility;
    private static FxRobot fxRobot;
    private static NodeFinder nodeFinder;

    @BeforeClass
    public static void setUpFactories() throws Exception {
        System.out.println("Attempting to Set up the view test");
        fxRobot = new FxRobot();
        nodeFinder = FxService.serviceContext().getNodeFinder();

        fxRobot.target(FxToolkit.registerPrimaryStage());
        System.out.println("Primary stage registered");
        System.out.println("My Stage Name is " + FxToolkit.toolkitContext().getTargetStage().getTitle());
        FxToolkit.setupStage((stage) -> {
            stage.show();
            stage.toBack();
            stage.toFront();
        });
        System.out.println("Primary stage setup");
        FxToolkit.setupSceneRoot(() -> {
                    return getRootNode();
                }
        );
        System.out.println("Scene root setup");
        System.out.println("Test established, waiting for FX events");
        WaitForAsyncUtils.waitForFxEvents();
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
