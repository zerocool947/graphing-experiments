package com.poorfellow.graphing.experimental.JUNG;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.EditingPopupGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.PickingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.PluggableGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.Transformer;

import javax.swing.JFrame;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;

/**
 * Created by David on 11/15/2014.
 */
public class JUNG {

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
                    return Color.DARK_GRAY;
                } else if (i ==2 || i == 3 || i ==4) {
                    return Color.CYAN;
                }

                return Color.RED;
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


        //Displaying graph
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);

    }
}
