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

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by David on 11/30/2014.
 */
public class JavaFXApplicationTest extends GuiTest {

    private Stage stage;

    //THIS WON'T WORK
    //I need to find a reliable way to test the stage
    //JUnit Rules have croped up, look promising

    @After
    public void closeStage() {
        stage.close();
    }

    @Ignore
    @Test
    public void testStageTitle() {
        Stage stage = findStageByTitle("Demo Stage");
        assertNotNull(stage);
    }

    @Override
    protected Parent getRootNode() {
        stage = new Stage();
        return null;
    }
}
