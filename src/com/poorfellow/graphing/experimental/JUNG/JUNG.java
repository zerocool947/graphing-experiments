package com.poorfellow.graphing.experimental.JUNG;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.graph.util.TestGraphs;
import edu.uci.ics.jung.visualization.DefaultVisualizationModel;
import edu.uci.ics.jung.visualization.VisualizationModel;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.EditingPopupGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.PickingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.PluggableGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import  java.awt.geom.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;
import javafx.stage.Stage;
import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.Transformer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import javafx.scene.paint.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;

/**
 * Created by David on 11/15/2014.
 */
public class JUNG {

    private static Graph<Integer, String> createBasicGraph() {
        Graph<Integer, String> g = new SparseMultigraph<Integer, String>();

        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);

        g.addEdge("Edge12", 1, 2);
        g.addEdge("Edge14", 1, 4);
        g.addEdge("Edge34", 3, 4);

        return g;
    }

    public static void execute() {
        
        //Instantiating
        JFrame frame = new JFrame("Simple_Graph_View");

        //basic graph definition
        Graph<Integer, String> g = new SparseMultigraph<Integer, String>();

        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);

        g.addEdge("Edge12", 1, 2);
        g.addEdge("Edge14", 1, 4);
        g.addEdge("Edge34", 3, 4);

        Layout<Integer, String> layout = new CircleLayout<Integer, String>(g);
        layout.setSize(new Dimension(300,300));
        VisualizationViewer<Integer, String> vv = new VisualizationViewer<Integer, String>(layout);
        vv.setPreferredSize(new Dimension(350, 350));

        //Altering tho look of the graph
        Transformer<Integer, Paint> vertexPaint = new Transformer<Integer, Paint>() {
            @Override
            public Paint transform(Integer i) {
                if (i == 1) {
                    return java.awt.Color.DARK_GRAY;
                } else if (i ==2 || i == 3 || i ==4) {
                    return java.awt.Color.CYAN;
                }

                return java.awt.Color.RED;
            }
        };
        float dash[] = {10.0f};
        float dash2[] = {7.0f};
        float dash3[] = {15.0f};
        final Stroke edgeStroke3 = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER, 15.0f, dash3, 5.0f);
        final Stroke edgeStroke2 = new BasicStroke(3.0f, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND, 7.0f, dash2, 3.0f);
        final Stroke edgeStroke1 = new BasicStroke(1.0f, BasicStroke.CAP_SQUARE,
                BasicStroke.JOIN_BEVEL, 10.0f, dash, 0.0f);
        Transformer<String, Stroke> edgeDashTransform = new Transformer<String, Stroke>() {
            @Override
            public Stroke transform(String s) {
                if (s.equals("Edge12")) {
                    return edgeStroke1;
                } else if (s.equals("Edge14") || s.equals("Edge34")) {
                    return edgeStroke3;
                }

                return edgeStroke2;
            }
        };

        Transformer<Integer, Shape> vertexShapeTrans = new Transformer<Integer, Shape>() {
            @Override
            public Shape transform(Integer integer) {
                return new Rectangle(50, 50);
            }
        };

        //Interactive test
        PluggableGraphMouse gm = new PluggableGraphMouse();
        PickingGraphMousePlugin<Integer, String> pickingMouse = new PickingGraphMousePlugin<Integer, String>();

        Factory<Integer> vertexFactory = new VertexFactory(g);
        Factory<String> edgeFactory = new EdgeFactory();
        EditingPopupGraphMousePlugin<Integer, String> popupMouse = new EditingPopupGraphMousePlugin<Integer, String>(
                vertexFactory, edgeFactory);


        //Assigning alterations
        gm.add(pickingMouse);
        gm.add(popupMouse);
        vv.setGraphMouse(gm);
        vv.getRenderContext().setVertexShapeTransformer(vertexShapeTrans);
        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
        vv.getRenderContext().setEdgeStrokeTransformer(edgeDashTransform);
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Integer>());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<String>());
        vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);

        JPanel controls = new JPanel();
        JButton addVertexButten = new JButton("Add Vertex");

        JPanel addGrid = new JPanel(new GridLayout(1, 0));
        addGrid.setBorder(BorderFactory.createTitledBorder("Creation"));
        addGrid.add(addVertexButten);
        controls.add(addGrid);
        frame.getContentPane().add(controls, BorderLayout.SOUTH);

        //Displaying graph
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);

    }

    public static void executeFX(Stage primaryStage) throws Exception {
        Group root = new Group();

        Group viz1 = new Group();
        Graph graph1 = createBasicGraph();
        Layout circleLayout = new CircleLayout(graph1);

        VisualizationModel<Integer, String> vm1 = new DefaultVisualizationModel<Integer, String>(
                circleLayout, new Dimension(400, 400));

        renderGraph(graph1, circleLayout, viz1);

        root.getChildren().add(viz1);

        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("You can't tell me what to do!");
            }
        });

        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 400);

        primaryStage.setTitle("JUNG Integration Test!");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private static void renderGraph(Graph<Integer, String> graph, Layout layout, Group viz) {
        // draw the vertices in the graph
        for (Integer v : graph.getVertices()) {
            // Get the position of the vertex
            Point2D p = (Point2D) layout.transform(v);

            // draw the vertex as a circle
            Circle circle = new Circle(p.getX(), p.getY(), 15, Color.DARKORCHID);


            // add it to the group, so it is shown on screen
            viz.getChildren().add(circle);
        }

        // draw the edges
        for (String n : graph.getEdges()) {
            // get the end points of the edge
            Pair endpoints = graph.getEndpoints(n);

            // Get the end points as Point2D objects so we can use them in the
            // builder
            Point2D pStart = (Point2D)layout.transform(endpoints.getFirst());
            Point2D pEnd = (Point2D)layout.transform(endpoints.getSecond());

            // Draw the line
            Line line = new Line(pStart.getX(), pStart.getY(), pEnd.getX(), pEnd.getY());
            line.setFill(Color.AZURE);
            // add the edges to the screen
            viz.getChildren().add(line);
        }
    }


}
