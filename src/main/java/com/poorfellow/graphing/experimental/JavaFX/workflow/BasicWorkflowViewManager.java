package com.poorfellow.graphing.experimental.JavaFX.workflow;

import eu.mihosoft.vrl.workflow.*;
import eu.mihosoft.vrl.workflow.fx.FXSkinFactory;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import jfxtras.labs.scene.layout.ScalableContentPane;

import java.util.List;

/**
 * Created by David on 12/14/2014.
 */
public class BasicWorkflowViewManager {

    private static VFlow flow;
    private final int gridGap;
    private final int nodesPerRow;

    public BasicWorkflowViewManager() {
        flow = FlowFactory.newFlow();
        flow.setVisible(true);
        gridGap = 30;
        nodesPerRow = 2;
    }

    public Parent setupFlows() {
        ScalableContentPane canvas = new ScalableContentPane();
        canvas.setStyle("-fx-background-color: #F1F0FF");
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(canvas);
        borderPane.setBottom(createHBox());

        FXSkinFactory fxSkinFactory = new FXSkinFactory(canvas.getContentPane());
        flow.setSkinFactories(fxSkinFactory);

        return borderPane;
    }

    private Node createHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(20));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #443266");

        Button createNode = new Button("Create Node");
        createNode.setPrefSize(100, 20);

        hbox.getChildren().addAll(createNode);

        return hbox;
    }

    public void addNodeWithId(String id) {
        VNode n = createNode();
        n.setId(id);
        positionNode(n);
    }

    private VNode createNode() {
        VNode n = flow.newNode();

        return n;
    }

    public VNode getNodeById(String id) {
        return flow.getNodeLookup().getById(id);
    }

    public VNode addNodeWithTitle(String title) {
        VNode n = createNode();
        n.setTitle(title);
        positionNode(n);
        return n;
    }

    public VNode getNodeByTitle(String title) {
        List<VNode> nodes  = flow.getNodes();
        for (VNode node : nodes) {
            if (title.equals(node.getTitle())) {
                return node;
            }
        }

        return null;
    }

    private void positionNode(VNode node) {
        node.setWidth(300);
        node.setHeight(200);

        node.setX(calculateNodeXPosition(node));
        node.setY(calculateNodeYPosition(node));

    }

    private double calculateNodeXPosition(VNode node) {
        return gridGap + ((getNodesInFlow().size() - 1)  % nodesPerRow) *
                (node.getWidth() + gridGap);
    }

    private double calculateNodeYPosition(VNode node) {
        return gridGap + ((getNodesInFlow().size() - 1) / nodesPerRow) *
                (node.getHeight() + gridGap);
    }

    public void removeAllNodes() {
        for (VNode node : flow.getNodes()) {
            Platform.runLater(() -> {
                System.out.println("Removing " + node.getTitle());
                flow.remove(node);
            });
        }
    }

    public List<VNode> getNodesInFlow() {
        return flow.getNodes();
    }

    public void addNodeWithTitleAndId(String titleId) {
        addNodeWithTitle(titleId).setId(titleId);

    }
}
