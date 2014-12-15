package com.poorfellow.graphing.experimental.workflow;

import com.poorfellow.graphing.experimental.JavaFX.workflow.BasicWorkflowViewManager;
import com.sun.javafx.tk.Toolkit;
import eu.mihosoft.vrl.workflow.VNode;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jfxtras.labs.scene.layout.ScalableContentPane;
import org.junit.*;
import org.testfx.api.*;
import org.testfx.service.finder.NodeFinder;
import org.testfx.service.finder.WindowFinder;
import org.testfx.util.WaitForAsyncUtils;

import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

/**
 * Created by David on 12/14/2014.
 */
public class DisplayWorkflowTest {

    private static FxRobot fxRobot;
    private static NodeFinder nodeFinder;
    private static BasicWorkflowViewManager basicWorkflowView;
    private static Stage primaryStage;
    private static WindowFinder windowFinder;

    @BeforeClass
    public static void setupTest() throws Exception {
        fxRobot = new FxRobot();
        nodeFinder = FxService.serviceContext().getNodeFinder();
        windowFinder = FxService.serviceContext().getWindowFinder();
        basicWorkflowView = new BasicWorkflowViewManager();

        primaryStage = FxToolkit.registerPrimaryStage();
        fxRobot.target(primaryStage);

        FxToolkit.setupStage((stage) -> {
            stage.initStyle(StageStyle.DECORATED);
            stage.show();
            stage.toBack();
            stage.toFront();
        });

        FxToolkit.setupScene(() -> {
            return new Scene(basicWorkflowView.setupFlows(), 800, 800);
        });
    }

    @AfterClass
    public static void teardownStage() throws Exception {
        Toolkit.getToolkit().defer(() -> {});
        Platform.runLater(primaryStage::close);
    }

    @After
    public void cleanupNodes() {
        basicWorkflowView.removeAllNodes();
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    public void testAddNodeByIdToFlow() throws Exception {
        Platform.runLater(() -> {
            basicWorkflowView.addNodeWithId("TestNodeId");
        });
        WaitForAsyncUtils.waitForFxEvents();
        VNode node = basicWorkflowView.getNodeById("TestNodeId");
        assertNotNull(node);
        assertTrue(node.isSelectable());
    }

    @Test
    public void testAddNodeByNameToFlow() throws Exception {
        Platform.runLater(() -> {
            basicWorkflowView.addNodeWithTitle("TestNodeName");
        });
        WaitForAsyncUtils.waitForFxEvents();
        VNode node = basicWorkflowView.getNodeByTitle("TestNodeName");
        assertNotNull(node);
    }

    @Test
    public void testOnlyOneNodeInFlow() throws Exception {
        Platform.runLater(() -> {
            basicWorkflowView.addNodeWithTitle("OnlyNode");
        });
        WaitForAsyncUtils.waitForFxEvents();
        List<VNode> allNodes = basicWorkflowView.getNodesInFlow();
        assertEquals(1, allNodes.size());
    }

    @Test
    public void testNodesNotDirectlyOverlapping() throws Exception {
        Platform.runLater(() -> {
            basicWorkflowView.addNodeWithTitleAndId("FirstNode");
            basicWorkflowView.addNodeWithTitleAndId("SecondNode");
        });
        WaitForAsyncUtils.waitForFxEvents();
        VNode node1 = basicWorkflowView.getNodeById("FirstNode");
        VNode node2 = basicWorkflowView.getNodeById("SecondNode");

        System.out.println("My nodes xy's are");
        System.out.println(node1.getX());
        System.out.println(node1.getY());
        System.out.println(node2.getX());
        System.out.println(node2.getY());

        assertTrue((node1.getX() != node2.getX()) || node1.getY() != node2.getY());
    }

    @Test
    public void testBorderLayoutCenter() throws Exception {
        BorderPane borderPane = getBorderPaneFromWindow();
        assertNotNull(borderPane.getCenter());
    }

    @Test
    public void testBorderLayerBottom() {
        BorderPane borderPane = getBorderPaneFromWindow();
        assertNotNull(borderPane.getBottom());
    }

    private BorderPane getBorderPaneFromWindow() {
        return (BorderPane)windowFinder.target().getScene().getRoot();
    }
}
