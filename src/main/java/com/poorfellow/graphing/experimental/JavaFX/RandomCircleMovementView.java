package com.poorfellow.graphing.experimental.JavaFX;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

/**
 * Created by David on 12/9/2014.
 */
public class RandomCircleMovementView {

    public static final String CIRCLE_ID = "RandomMovementCircle";
    public static final String CIRCLE_LABEL = "Click Me";

    private Group root;
    private Scene scene;
    private Circle movingCircle;
    private AnchorPane pane;
    private double radius = 15.0;

    public RandomCircleMovementView() {

    }

    public Parent setupLayout() {
        root = new Group();
        pane = new AnchorPane(root);
        movingCircle = new Circle(radius, Color.CADETBLUE);
        movingCircle.setId(CIRCLE_ID);

        setMovingCircleOnClickEvent(movingCircle);

        root.getChildren().add(movingCircle);

        Platform.runLater(() -> {
            positionCircleRandomly();
        });
        return pane;
    }


    public void setRadiusAndRedraw(double radius) {
        setRadius(radius);
        positionCircleRandomly();
    }

    private void setMovingCircleOnClickEvent(Circle circle) {
        circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                circle.setVisible(false);
                positionCircleRandomly();
                circle.setVisible(true);
            }
        });
    }

    private void positionCircleRandomly() {
        Random r = new Random();

        double xPosition = r.nextDouble() * borderXBound() + widthBorderMin();
        double yPosition = r.nextDouble() * borderYBound() + heightBorderMin();

        verifyCircleIsContainedInWindow(xPosition, yPosition);

        Platform.runLater(() -> {
            movingCircle.setCenterX(xPosition);
            movingCircle.setCenterY(yPosition);
        });
    }

    private void verifyCircleIsContainedInWindow(double xPosition, double yPosition) {
        if (xPosition + radius >= pane.getWidth() ||
            xPosition - radius <= 0 ||
            yPosition + radius >= pane.getHeight() ||
            yPosition - radius <= 0) {
            throw new RuntimeException("Cricle falls outside of window");
        }
    }

    private double borderYBound() {
        return heightBorderMax() - heightBorderMin();
    }

    private double heightBorderMax() {
                //max is already excluded in Random
        return pane.getHeight() - movingCircle.getRadius();
    }

    private double heightBorderMin() {
        //min is inclusive in Random, want to exclude it
        return movingCircle.getRadius() + 1;
    }

    private double borderXBound() {
        return widthBorderMax() - widthBorderMin();
    }

    private double widthBorderMax() {
        //max is already excluded in Random
        return pane.getWidth() - movingCircle.getRadius();
    }

    private double widthBorderMin() {
        //Want the border to be exclusive, have to shift to account for Random inclusive lower
        return movingCircle.getRadius() + 1;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
