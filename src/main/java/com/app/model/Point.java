package com.app.model;

import com.app.App;
import javafx.scene.image.Image;

/**
 * Class Point
 * A point represents a fragment of the snake
 */
public class Point {
    protected Image image;
    private double positionX;
    private double positionY;

    public Point(double x, double y){
        image = new Image(String.valueOf(App.class.getResource("snake_body_point.png")),20,20,false,false);
        positionX = x;
        positionY = y;
    }

    public Image getImage() {
        return image;
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
}
