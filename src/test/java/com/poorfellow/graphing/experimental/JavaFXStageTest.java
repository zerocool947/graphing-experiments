package com.poorfellow.graphing.experimental;

import com.sun.javafx.tk.Toolkit;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxService;
import org.testfx.api.FxToolkit;
import org.testfx.service.finder.NodeFinder;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by David on 11/30/2014.
 */
public class JavaFXStageTest {

    private static Stage targetStage;
    private static FxRobot fxRobot;
    private static NodeFinder nodeFinder;

    @BeforeClass
    public static void setupFactories() throws Exception {
        fxRobot = new FxRobot();
        nodeFinder = FxService.serviceContext().getNodeFinder();
        FxToolkit.registerPrimaryStage();

        fxRobot.target(FxToolkit.registerTargetStage(() -> {
                    targetStage = new Stage();
                    targetStage.setTitle("Demo Stage");
                    return targetStage;
                }
        ));
        FxToolkit.setupStage((stageSetUp) -> {
            stageSetUp.show();
            stageSetUp.toBack();
            stageSetUp.toFront();
        });
    }

    //Causes FXViewsTest to hang on setting targetStage or scene root
    @AfterClass
    public static void teardownStage() throws Exception {
        Toolkit.getToolkit().defer(() -> {});
        Platform.runLater(targetStage::close);
    }



    @Test
    public void testStageExists() throws Exception {
        Stage stage = (Stage) FxService.serviceContext().getWindowFinder().target();
        assertNotNull(stage);
    }

    @Test
    public void testStageTitle() throws Exception {
        Stage stage = (Stage) FxService.serviceContext().getWindowFinder().target();
        assertEquals("Demo Stage", stage.getTitle());
    }

    protected Parent getRootNode() {
        targetStage = new Stage();
        return null;
    }
}
