package test.java.com.poorfellow.graphing.experimental.test;

import main.java.com.poorfellow.graphing.experimental.JavaFX.RandomCircleMovementView;
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

    @BeforeClass
    public static void setupScene() throws Exception {
        circleView = new RandomCircleMovementView();

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

    @Before
    public void positionScene() {
        circleView.positionCircleRandomly();
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

        while (i < 100) {
            double centerX = circle.getCenterX();
            double centerY = circle.getCenterY();
            double radius = circle.getRadius();

            assertTrue((centerX - radius) >= 0);
            assertTrue((centerX + radius) <= primaryStage.getWidth());
            assertTrue((centerY - radius) >= 0);
            assertTrue((centerY + radius) <= primaryStage.getHeight());

            circleView.positionCircleRandomly();
            WaitForAsyncUtils.waitForFxEvents();
            i++;
        }
    }

    private Circle findCircle() {
        Circle circle = (Circle)nodeFinder.node("#" + circleView.CIRCLE_ID);
        return circle;
    }
}
