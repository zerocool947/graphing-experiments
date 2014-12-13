package com.poorfellow.graphing.experimental;

import com.poorfellow.graphing.experimental.JavaFX.RandomCircleMovementView;
import javafx.scene.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxService;
import org.testfx.api.FxToolkit;
import org.testfx.service.finder.NodeFinder;
import org.testfx.util.WaitForAsyncUtils;


import static java.lang.Thread.sleep;
import static org.loadui.testfx.controls.Commons.hasText;
import static org.junit.Assert.*;

/**
 * Created by David on 12/9/2014.
 */
public class RandomCircleMovementTest {

    private static FxRobot fxRobot;
    private static NodeFinder nodeFinder;
    private static RandomCircleMovementView circleView;
    private static Stage primaryStage;

    private static final int SCENE_WIDTH = 500;
    private static final int SCENE_HEIGHT = 500;
    private static final double RADIUS = 15.0;
    private static final double LARGE_RADIUS = 255; //dependent on scene_height and width

    @BeforeClass
    public static void setupScene() throws Exception {
        circleView = new RandomCircleMovementView();
        circleView.setRadius(RADIUS);

        fxRobot = new FxRobot();
        nodeFinder = FxService.serviceContext().getNodeFinder();
        primaryStage = FxToolkit.registerPrimaryStage();
        fxRobot.target(primaryStage);
        System.out.println("Primary stage registered");
        System.out.println("My Stage Name is " + FxToolkit.toolkitContext().getTargetStage().getTitle());
        FxToolkit.setupStage((stage) -> {
            stage.initStyle(StageStyle.DECORATED);
            stage.show();
            stage.toBack();
            stage.toFront();
        });
        FxToolkit.setupScene(() -> {
            Scene scene = new Scene(circleView.setupLayout(), SCENE_WIDTH, SCENE_HEIGHT);
            return scene;
        });
        System.out.println("Primary stage setup");
    }

    @Test
    public void testCircleExists() throws Exception {
        Circle circle = findCircle();
        Assert.assertNotNull(circle);
    }

    @Test
    public void testCircleFullyInScene() throws Exception {
        Circle circle = findCircle();
        int i = 0;

        while (i < 20) {
            double centerX = circle.getCenterX();
            double centerY = circle.getCenterY();
            double radius = circle.getRadius();

            assertTrue((centerX - radius) >= 0);
            assertTrue((centerX + radius) <= primaryStage.getWidth());
            assertTrue((centerY - radius) >= 0);
            assertTrue((centerY + radius) <= primaryStage.getHeight());

            fxRobot.clickOn(circle);
            WaitForAsyncUtils.waitForFxEvents();
            i++;
        }
    }

    @Test
    public void testCircleClickAndReposition() {
        Circle circle = findCircle();
        double startingX = circle.getCenterX();
        double startingY = circle.getCenterY();

        fxRobot.clickOn(circle);
        WaitForAsyncUtils.waitForFxEvents();

        double endingX = circle.getCenterX();
        double endingY = circle.getCenterY();

        assertTrue(startingX != endingX);
        assertTrue(startingY != endingY);
    }

    @Test (expected = Exception.class)
    public void testCircleTooLarge() {
        circleView.setRadiusAndRedraw(LARGE_RADIUS);
    }

    private Circle findCircle() {
        Circle circle = (Circle)nodeFinder.node("#" + circleView.CIRCLE_ID);
        return circle;
    }
}
