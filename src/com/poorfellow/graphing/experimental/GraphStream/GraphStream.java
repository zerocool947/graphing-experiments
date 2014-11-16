package com.poorfellow.graphing.experimental.GraphStream;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

/**
 * Created by David on 10/30/2014.
 */
public class GraphStream {

    public static void execute() {
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        Graph exp1 = new SingleGraph("Exp1");



        exp1.addNode("A");
        exp1.addNode("B");
        exp1.addNode("C");

        //exp1.addEdge("AB", "A", "B");
        exp1.addEdge("AC", "A", "C");

        exp1.getNode("A").addAttribute("ui.style", "shape: box; fill-color: red; size: 30px, 30px; ");
        exp1.getNode("A").addAttribute("layout.frozen");
        exp1.getNode("B").addAttribute("layout.frozen");
        exp1.getNode("C").addAttribute("layout.frozen");
        exp1.getNode("A").addAttribute("xy", 0, 0);
        exp1.getNode("B").addAttribute("xy", 2, 0);
        exp1.display();
    }
}
