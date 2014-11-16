package com.poorfellow.graphing.experimental.JUNG;

import edu.uci.ics.jung.graph.Graph;
import org.apache.commons.collections15.Factory;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Created by David on 11/15/2014.
 */
public class VertexFactory implements Factory<Integer> {

    private Integer vertecies;
    private Graph<Integer, String> graph;
    private static VertexFactory vertexFactory;
    private JFrame frame;

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public static VertexFactory getInstance(Graph<Integer, String> g) {
        if (vertexFactory == null) {
            vertexFactory = new VertexFactory(g);
        }

        return vertexFactory;
    }

    public VertexFactory(Graph<Integer, String> g) {
        vertecies = -1;
        graph = g;
    }

    @Override
    public Integer create() {
        Integer name = Integer.valueOf(JOptionPane.showInputDialog(
            frame,
            "Enter a number for this vertex:",
            "Create Vertex",
            JOptionPane.PLAIN_MESSAGE
        ));
        return name;
    }

    public void addVertex() {
        graph.addVertex(create());
    }
}
