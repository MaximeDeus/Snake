package com.app.model;

import javafx.scene.paint.Color;

/**
 * Class Point
 * A point represents a fragment of the snake
 */
public class Point {
    private static Color color;
    private double positionX;
    private double positionY;

    public Point(double x, double y){
        positionX = x;
        positionY = y;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public boolean equals(Point p){
        return this.positionX == p.positionX && this.positionY == p.positionY;
    }

    public static void setColor (Color c){
        color = c;
    }

    public static Color getColor() {
        return color;
    }
}
