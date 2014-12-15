package com.poorfellow.graphing.experimental.JavaFX.workflow;

import eu.mihosoft.vrl.workflow.*;
import eu.mihosoft.vrl.workflow.fx.FXSkinFactory;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import jfxtras.scene.layout.HBox;

import java.util.List;

/**
 * Created by David on 12/14/2014.
 */
public class BasicWorkflowViewManager {

    private static VFlow flow;
    private final int gridGap;
    private final int nodesPerRow;
    private final BorderPane borderPane;

    public BasicWorkflowViewManager() {
        flow = FlowFactory.newFlow();
        flow.setVisible(true);
        gridGap = 30;
        nodesPerRow = 2;
        borderPane = new BorderPane();
    }

    public Parent setupRoot() {
        FXSkinFactory fxSkinFactory = new FXSkinFactory(setupParentWithCanvas());
        flow.setSkinFactories(fxSkinFactory);

        return borderPane;
    }

    private AnchorPane setupParentWithCanvas() {
        AnchorPane canvas = new AnchorPane();
        canvas.setStyle("-fx-background-color: #F1F0FF");
        borderPane.setCenter(canvas);
        borderPane.setBottom(createHBox());

        return canvas;
    }

    private Node createHBox() {
        HBox hbox = new HBox(5.0);
        hbox.add(new Button("Create Node"));
        hbox.setPadding(new Insets(20));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #443266");

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
