package com.poorfellow.graphing.experimental.test;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxRobot;
import org.testfx.api.FxService;
import org.testfx.api.FxToolkit;
import org.testfx.service.finder.NodeFinder;
import org.testfx.util.WaitForAsyncUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by David on 11/30/2014.
 */
public class JavaFXStageTest {

    private Stage stage;
    private static FxRobot fxRobot;
    private static NodeFinder nodeFinder;

    @BeforeClass
    public static void setupFactories() throws Exception {
        fxRobot = new FxRobot();
        nodeFinder = FxService.serviceContext().getNodeFinder();
        FxToolkit.registerPrimaryStage();
        fxRobot.target(FxToolkit.registerTargetStage(() -> {
                    Stage stage = new Stage();
                    stage.setTitle("Demo Stage");
                    return stage;
                }
        ));
        FxToolkit.setupStage((stage) -> {
            stage.show();
            stage.toBack();
            stage.toFront();
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    //Causes FXViewsTest to hang on setting stage or scene root
    @AfterClass
    public static void teardown() throws Exception {
        WaitForAsyncUtils.asyncFx(() -> {
                    Stage stage = (Stage) FxService.serviceContext().getWindowFinder().target();
                    stage.close();
            }
        );
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
        stage = new Stage();
        return null;
    }
}
